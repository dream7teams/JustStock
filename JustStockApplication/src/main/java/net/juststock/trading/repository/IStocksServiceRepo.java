package net.juststock.trading.repository;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.juststock.trading.domain.singnal.StockQuote;


@Repository
public interface IStocksServiceRepo  extends JpaRepository<StockQuote,Long>{

	Optional<StockQuote> findBySymbolContainingIgnoreCaseAndExchangeContainingIgnoreCase(String symbol, String exchange,
			PageRequest of);

}
