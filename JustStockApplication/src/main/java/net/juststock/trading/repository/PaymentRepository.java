package net.juststock.trading.repository;

import net.juststock.trading.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> { }
