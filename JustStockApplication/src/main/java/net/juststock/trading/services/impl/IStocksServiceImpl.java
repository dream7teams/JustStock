package net.juststock.trading.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.juststock.trading.domain.Stock;
import net.juststock.trading.repository.IStocksServiceRepo;
import net.juststock.trading.services.IStocksService;

@Service
public class IStocksServiceImpl implements IStocksService {

	private final IStocksServiceRepo stocksServiceRepo;
	
	
	
	public IStocksServiceImpl(IStocksServiceRepo stocksServiceRepo) {
		super();
		this.stocksServiceRepo = stocksServiceRepo;
	}



	@Override
	public Optional<Stock> addUserStock(Stock stock) {
		
		
		return Optional.empty();
	}

}
