package net.juststock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.market.UserSignalHistory;


public interface UserSignalHistoryRepository extends JpaRepository<UserSignalHistory, Long> { }
