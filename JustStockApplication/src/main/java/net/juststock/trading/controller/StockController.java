package net.juststock.trading.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.juststock.trading.domain.Stock;
import net.juststock.trading.services.IStocksService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	private final IStocksService stockService;

	public StockController(IStocksService stockService) {
		super();
		this.stockService = stockService;
	}

	@PostMapping("userstock")
	public ResponseEntity<?> addUserStocks(@RequestBody Stock stock) {

		Optional<Stock> addedStock = stockService.addUserStock(stock);

		return addedStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(406).build());

	}
	
	@GetMapping("userallstock")
	public ResponseEntity<List<Stock>> viewUserStocks()
	{
		return null;
		
	}
}
