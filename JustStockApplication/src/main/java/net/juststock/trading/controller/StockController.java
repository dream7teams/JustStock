package net.juststock.trading.controller;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.juststock.trading.services.IStocksService;
import net.juststock.trading.web.dto.StockDto;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

  private final IStocksService service;

  public StockController(IStocksService service) {
    this.service = service;
  }

  // GET /api/stocks?symbol=TCS&exchange=NSE&page=0&size=20
  @GetMapping
  public Page<StockDto> list(
      @RequestParam(defaultValue = "") String symbol,
      @RequestParam(defaultValue = "") String exchange,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size
  ) {
    var sort = Sort.by(Sort.Order.asc("symbol"), Sort.Order.asc("exchange"));
    var pageable = PageRequest.of(page, size, sort);
    return service.list(symbol, exchange, pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StockDto> get(@PathVariable Long id) {
    return service.get(id)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<StockDto> create(@RequestBody StockDto dto) {
	  
	  System.out.println(dto.toString());
	  
    return ResponseEntity.ok(service.create(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<StockDto> update(@PathVariable Long id, @RequestBody StockDto dto) {
    return service.update(id, dto)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return service.delete(id) ? ResponseEntity.noContent().build()
                              : ResponseEntity.notFound().build();
  }
}
