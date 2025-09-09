package net.juststock.trading.controller;

import net.juststock.trading.dto.PaymentRequestDTO;
import net.juststock.trading.dto.PaymentResponseDTO;
import net.juststock.trading.service.interfaces.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) { this.paymentService = paymentService; }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> pay(@RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO resp = paymentService.processPayment(request);
        return ResponseEntity.status(201).body(resp);
    }
}
