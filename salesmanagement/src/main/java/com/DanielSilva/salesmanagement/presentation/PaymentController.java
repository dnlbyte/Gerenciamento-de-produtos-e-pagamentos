package com.DanielSilva.salesmanagement.presentation;

import com.DanielSilva.salesmanagement.domain.model.Payment;
import com.DanielSilva.salesmanagement.application.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAll(@RequestParam(value = "method", required = false) String method) {
        if (method != null && !method.isEmpty()) {
            return paymentService.findByMethod(method);
        }
        return paymentService.findAll();
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return paymentService.save(payment);
    }

    @PutMapping
    public ResponseEntity<Payment> update(@RequestParam("method") String method, @RequestBody Payment payment) {
        List<Payment> payments = paymentService.findByMethod(method);
        if (payments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Payment existing = payments.get(0);
        payment.setIdPayment(existing.getIdPayment());
        return ResponseEntity.ok(paymentService.save(payment));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("method") String method) {
        List<Payment> payments = paymentService.findByMethod(method);
        if (payments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        paymentService.deleteByMethod(method);
        return ResponseEntity.noContent().build();
    }
} 