package net.juststock.trading.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import net.juststock.trading.domain.singnal.StockQuote;
import net.juststock.trading.web.dto.StockDto;
import net.juststock.trading.web.dto.StockIngestDto;

public interface IStocksService {

	Page<StockDto> list(String symbol, String exchange, Pageable pageable);

	Optional<StockDto> get(Long id);

	StockDto create(StockDto dto);

	Optional<StockDto> update(Long id, StockDto dto);

	boolean delete(Long id);

	public Optional<StockQuote> addStock(StockIngestDto dto);
}
