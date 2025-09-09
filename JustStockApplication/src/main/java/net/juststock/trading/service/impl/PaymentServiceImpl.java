package net.juststock.trading.service.impl;

import net.juststock.trading.domain.payment.Payment;
import net.juststock.trading.dto.PaymentRequestDTO;
import net.juststock.trading.dto.PaymentResponseDTO;
import net.juststock.trading.repository.PaymentRepository;
import net.juststock.trading.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        // Simulate gateway success
        String txnId = UUID.randomUUID().toString().replace("-", "").substring(0,16);

        Payment p = new Payment();
        p.setId(request.getUserId());
        p.setInstrumentType(request.getInstrumentType());
        p.setSignalType(request.getSignalType());
        p.setAmount(request.getAmount());
        p.setCurrency("INR");
        p.setStatus("SUCCESS");
        p.setTransactionId(txnId);
        p.setCreatedAt(ZonedDateTime.now());
        paymentRepository.save(p);

        return new PaymentResponseDTO("SUCCESS", txnId, "Payment successful. Fetching your advice...");
    }
}
