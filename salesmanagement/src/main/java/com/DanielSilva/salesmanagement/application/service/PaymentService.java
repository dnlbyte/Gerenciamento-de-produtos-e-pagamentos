package com.DanielSilva.salesmanagement.application.service;

import com.DanielSilva.salesmanagement.domain.model.Payment;
import com.DanielSilva.salesmanagement.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findByMethod(String method) {
        return paymentRepository.findByMethodContainingIgnoreCase(method);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deleteByMethod(String method) {
        List<Payment> payments = paymentRepository.findByMethodContainingIgnoreCase(method);
        payments.forEach(p -> paymentRepository.delete(p));
    }
} 