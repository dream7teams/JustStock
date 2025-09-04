package net.juststock.trading.services;

import java.util.Optional;

import net.juststock.trading.domain.Stock;

public interface IStocksService {

	public Optional<Stock> addUserStock(Stock stock);

}
