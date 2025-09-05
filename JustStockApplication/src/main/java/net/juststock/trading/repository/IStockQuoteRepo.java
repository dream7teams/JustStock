package net.juststock.trading.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.singnal.StockQuote;

public interface IStockQuoteRepo extends JpaRepository<StockQuote, Long> {}
