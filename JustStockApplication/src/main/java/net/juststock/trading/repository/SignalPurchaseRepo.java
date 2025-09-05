package net.juststock.trading.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.SignalPurchase;

public interface SignalPurchaseRepo extends JpaRepository<SignalPurchase, Long> {
    Optional<SignalPurchase> findByProviderOrderId(String providerOrderId);
}
