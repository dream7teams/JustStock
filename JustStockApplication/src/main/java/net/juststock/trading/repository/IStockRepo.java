package net.juststock.trading.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.singnal.Stock;


public interface IStockRepo extends JpaRepository<Stock, Long> {

    Optional<Stock> findFirstBySymbolIgnoreCaseAndExchangeIgnoreCase(String symbol, String exchange);

    Page<Stock> findBySymbolContainingIgnoreCaseAndExchangeContainingIgnoreCase(
            String symbol, String exchange, Pageable pageable
    );
}
