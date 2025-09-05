package net.juststock.trading.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.juststock.trading.domain.singnal.Stock;        // if your package is 'signal', change this import
import net.juststock.trading.domain.singnal.StockQuote;   // if your package is 'signal', change this import
import net.juststock.trading.repository.IStockRepo;       // Stock repo
import net.juststock.trading.repository.IStockQuoteRepo;  // StockQuote repo
import net.juststock.trading.services.IStocksService;
import net.juststock.trading.web.dto.StockDto;
import net.juststock.trading.web.dto.StockIngestDto;

@Service
public class StocksServiceImpl implements IStocksService {

    private final IStockRepo repo;
    private final IStockQuoteRepo quoteRepo;

    public StocksServiceImpl(IStockRepo repo, IStockQuoteRepo quoteRepo) {
        this.repo = repo;
        this.quoteRepo = quoteRepo;
    }

    // ---------- CRUD-over-Stock (if your interface includes these) ----------
    @Override
    @Transactional(readOnly = true)
    public Page<StockDto> list(String symbol, String exchange, Pageable pageable) {
        return repo.findBySymbolContainingIgnoreCaseAndExchangeContainingIgnoreCase(symbol, exchange, pageable)
                   .map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockDto> get(Long id) {
        return repo.findById(id).map(this::toDto);
    }

    @Override
    @Transactional
    public StockDto create(StockDto dto) {
        var saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    @Transactional
    public Optional<StockDto> update(Long id, StockDto dto) {
        return repo.findById(id).map(s -> {
            s.setSymbol(dto.symbol());
            s.setExchange(dto.exchange());
            s.setIsin(dto.isin());
            s.setSector(dto.sector());
            s.setCompanyName(dto.companyName());
            s.setCurrency(dto.currency());
            return toDto(repo.save(s));
        });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    // ---------- Ingest: upsert Stock + save StockQuote ----------
    @Override
    @Transactional
    public Optional<StockQuote> addStock(StockIngestDto dto) {
        // 1) Upsert master Stock by (symbol, exchange)
        var stock = repo
            .findFirstBySymbolIgnoreCaseAndExchangeIgnoreCase(dto.symbol(), dto.exchange())
            .orElseGet(() -> {
                var s = new Stock();
                s.setSymbol(dto.symbol());
                s.setExchange(dto.exchange());
                return s;
            });

        // Keep master fields fresh (idempotent upsert)
        stock.setCompanyName(dto.companyName());
        stock.setCurrency(dto.currency());
        stock = repo.save(stock); // ensure we have an ID

        // 2) Snapshot quote
        var q = new StockQuote();
        q.setStockId(stock.getId());
        q.setSymbol(dto.symbol());
        q.setExchange(dto.exchange());
        q.setCurrency(dto.currency());
        q.setTimestampUtc(dto.timestamp());

        if (dto.price() != null) {
            q.setPriceCurrent(dto.price().current());
            q.setPriceOpen(dto.price().open());
            q.setPriceHigh(dto.price().high());
            q.setPriceLow(dto.price().low());
            q.setPricePreviousClose(dto.price().previousClose());
        }
        if (dto.volume() != null) {
            q.setVolumeCurrent(dto.volume().current());
            q.setVolumeAverage(dto.volume().average());
        }
        q.setMarketCap(dto.marketCap());
        q.setPeRatio(dto.peRatio());
        q.setEps(dto.eps());

        // 3) Save snapshot via StockQuote repo
        return Optional.of(quoteRepo.save(q));
    }

    // ---------- Mappers ----------
    private StockDto toDto(Stock s) {
        return new StockDto(
                s.getId(),
                s.getSymbol(),
                s.getExchange(),
                s.getIsin(),
                s.getSector(),
                s.getCompanyName(),
                s.getCurrency()
        );
    }

    private Stock toEntity(StockDto d) {
        var s = new Stock();
        s.setSymbol(d.symbol());
        s.setExchange(d.exchange());
        s.setIsin(d.isin());
        s.setSector(d.sector());
        s.setCompanyName(d.companyName());
        s.setCurrency(d.currency());
        return s;
    }
}
