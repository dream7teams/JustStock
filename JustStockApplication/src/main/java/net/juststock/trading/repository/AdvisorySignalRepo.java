package net.juststock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.AdvisorySignal;

public interface AdvisorySignalRepo extends JpaRepository<AdvisorySignal, Long> {}
