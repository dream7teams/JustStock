package net.juststock.trading.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.juststock.trading.domain.singnal.BankNiftyOhlc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBankNiftyOhlcService {
    Optional<BankNiftyOhlc> findByTradeDate(LocalDate date);
    Page<BankNiftyOhlc> findByTradeDateBetween(LocalDate from, LocalDate to, Pageable pageable);
    BankNiftyOhlc save(BankNiftyOhlc entity);
    List<BankNiftyOhlc> saveAll(Iterable<BankNiftyOhlc> entities);
}
