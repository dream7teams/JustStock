package net.juststock.trading.service.interfaces;

import net.juststock.trading.dto.PaymentRequestDTO;
import net.juststock.trading.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO request);
}
