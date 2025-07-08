package com.DanielSilva.salesmanagement.application.service;

import com.DanielSilva.salesmanagement.domain.model.ProductChangeRequest;
import com.DanielSilva.salesmanagement.domain.repository.ProductChangeRequestRepository;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import com.DanielSilva.salesmanagement.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductChangeRequestService {
    @Autowired
    private ProductChangeRequestRepository requestRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ProductChangeRequest> findAll() {
        return requestRepository.findAll();
    }

    public List<ProductChangeRequest> findByStatus(String status) {
        return requestRepository.findByStatusContainingIgnoreCase(status);
    }

    public List<ProductChangeRequest> findByEmployeeUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) return List.of();
        return requestRepository.findByEmployee(user);
    }

    public ProductChangeRequest save(ProductChangeRequest request) {
        return requestRepository.save(request);
    }

    public ProductChangeRequest approveRequest(Integer idRequest, UserModel admin) {
        ProductChangeRequest req = requestRepository.findById(idRequest).orElse(null);
        if (req != null) {
            req.setStatus("APROVADO");
            req.setAdmin(admin);
            return requestRepository.save(req);
        }
        return null;
    }

    public ProductChangeRequest rejectRequest(Integer idRequest, UserModel admin) {
        ProductChangeRequest req = requestRepository.findById(idRequest).orElse(null);
        if (req != null) {
            req.setStatus("REJEITADO");
            req.setAdmin(admin);
            return requestRepository.save(req);
        }
        return null;
    }

    public void deleteByStatus(String status) {
        List<ProductChangeRequest> requests = requestRepository.findByStatusContainingIgnoreCase(status);
        requests.forEach(r -> requestRepository.delete(r));
    }
} 