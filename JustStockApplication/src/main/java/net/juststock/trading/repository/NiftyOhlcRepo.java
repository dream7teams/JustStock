package net.juststock.trading.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.juststock.trading.domain.NiftyOhlc;

@Repository
public interface NiftyOhlcRepo extends JpaRepository<NiftyOhlc, Long>{

	Optional<NiftyOhlc> findByTradeDate(LocalDate date);

	Page<NiftyOhlc> findByTradeDateBetween(LocalDate from, LocalDate to, Pageable pageable);

}
