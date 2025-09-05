package net.juststock.trading.controller;

import net.juststock.trading.domain.singnal.StockQuote;
import net.juststock.trading.services.IStocksService;
import net.juststock.trading.web.dto.StockIngestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StocksIngestController {

  private final IStocksService service;

  public StocksIngestController(IStocksService service) { this.service = service; }

  // POST /api/stocks/ingest
  @PostMapping("/ingest")
  public ResponseEntity<StockQuote> ingest(@RequestBody StockIngestDto dto) {
    return service.addStock(dto)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.badRequest().build());
  }
}
