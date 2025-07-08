package com.DanielSilva.salesmanagement.presentation;

import com.DanielSilva.salesmanagement.domain.model.ProductChangeRequest;
import com.DanielSilva.salesmanagement.application.service.ProductChangeRequestService;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import com.DanielSilva.salesmanagement.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-change-requests")
public class ProductChangeRequestController {
    @Autowired
    private ProductChangeRequestService requestService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<ProductChangeRequest> getAll(@RequestParam(value = "status", required = false) String status,
                                             @RequestParam(value = "employee", required = false) String employee) {
        if (status != null && !status.isEmpty()) {
            return requestService.findByStatus(status);
        }
        if (employee != null && !employee.isEmpty()) {
            return requestService.findByEmployeeUsername(employee);
        }
        return requestService.findAll();
    }

    @PostMapping
    public ProductChangeRequest create(@RequestBody ProductChangeRequest request) {
        return requestService.save(request);
    }

    @PostMapping("/approve")
    public ResponseEntity<ProductChangeRequest> approve(@RequestParam Integer idRequest, @RequestParam String adminUsername) {
        UserModel admin = userRepository.findByUsername(adminUsername);
        ProductChangeRequest req = requestService.approveRequest(idRequest, admin);
        if (req == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(req);
    }

    @PostMapping("/reject")
    public ResponseEntity<ProductChangeRequest> reject(@RequestParam Integer idRequest, @RequestParam String adminUsername) {
        UserModel admin = userRepository.findByUsername(adminUsername);
        ProductChangeRequest req = requestService.rejectRequest(idRequest, admin);
        if (req == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(req);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("status") String status) {
        requestService.deleteByStatus(status);
        return ResponseEntity.noContent().build();
    }
} 