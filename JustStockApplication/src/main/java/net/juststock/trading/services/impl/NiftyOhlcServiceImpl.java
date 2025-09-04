package net.juststock.trading.services.impl;

import net.juststock.trading.domain.NiftyOhlc;
import net.juststock.trading.repository.NiftyOhlcRepo;
import net.juststock.trading.services.INiftyOhlcService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NiftyOhlcServiceImpl implements INiftyOhlcService {

    private final NiftyOhlcRepo repo;

    public NiftyOhlcServiceImpl(NiftyOhlcRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NiftyOhlc> findByTradeDate(LocalDate date) {
        return repo.findByTradeDate(date);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NiftyOhlc> findByTradeDateBetween(LocalDate from, LocalDate to, Pageable pageable) {
        return repo.findByTradeDateBetween(from, to, pageable);
    }

    @Override
    @Transactional
    public NiftyOhlc save(NiftyOhlc entity) {
        return repo.save(entity);
    }

    @Override
    @Transactional
    public List<NiftyOhlc> saveAll(Iterable<NiftyOhlc> entities) {
        return repo.saveAll(entities);
    }
}
