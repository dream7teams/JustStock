package net.juststock.trading.services;


import net.juststock.trading.domain.NiftyOhlc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface INiftyOhlcService {

    Optional<NiftyOhlc> findByTradeDate(LocalDate date);

    Page<NiftyOhlc> findByTradeDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    NiftyOhlc save(NiftyOhlc entity);

    List<NiftyOhlc> saveAll(Iterable<NiftyOhlc> entities);
}
