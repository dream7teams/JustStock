package net.juststock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.PaymentEvent;

public interface PaymentEventRepo extends JpaRepository<PaymentEvent, Long> {}
