package net.juststock.trading.controller;


import net.juststock.trading.domain.NiftyOhlc;
import net.juststock.trading.services.INiftyOhlcService;
import net.juststock.trading.web.dto.NiftyDailyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/nifty")
public class NiftyController {

	private final INiftyOhlcService niftyIhlcService;

	public NiftyController(INiftyOhlcService niftyIhlcService) {
		this.niftyIhlcService = niftyIhlcService;
	}

	// GET /api/nifty/daily?date=2025-09-04
	@GetMapping("/daily")
	public ResponseEntity<NiftyDailyDto> getByDate(@RequestParam("date") String date) {
		var d = LocalDate.parse(date);
		return niftyIhlcService.findByTradeDate(d).map(this::toDto).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// GET /api/nifty/range?from=2025-01-01&to=2025-09-04&page=0&size=50
	@GetMapping("/range")
	public Page<NiftyDailyDto> listByRange(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size) {
		var sort = Sort.by(Sort.Direction.ASC, "tradeDate");
		var p = PageRequest.of(page, size, sort);
		return niftyIhlcService.findByTradeDateBetween(LocalDate.parse(from), LocalDate.parse(to), p).map(this::toDto);
	}

	// POST /api/nifty/daily (create or upsert one day)
	@PostMapping("/daily")
	public ResponseEntity<NiftyDailyDto> upsertOne(@RequestBody NiftyDailyDto dto) {
		var saved = niftyIhlcService.findByTradeDate(dto.tradeDate()).map(e -> apply(dto, e))
				.orElseGet(() -> apply(dto, new NiftyOhlc()));
		return ResponseEntity.ok(toDto(niftyIhlcService.save(saved)));
	}

	// POST /api/nifty/bulk (create/update many)
	@PostMapping("/bulk")
	public ResponseEntity<?> upsertBulk(@RequestBody Iterable<NiftyDailyDto> list) {
		var batch = new java.util.ArrayList<NiftyOhlc>();
		for (var dto : list) {
			var e = niftyIhlcService.findByTradeDate(dto.tradeDate()).map(x -> apply(dto, x))
					.orElseGet(() -> apply(dto, new NiftyOhlc()));
			batch.add(e);
		}
		niftyIhlcService.saveAll(batch);
		return ResponseEntity.accepted().build();
	}

	private NiftyDailyDto toDto(NiftyOhlc e) {
		return new NiftyDailyDto(e.getTradeDate(), e.getOpen(), e.getHigh(), e.getLow(), e.getClose(),
				e.getSharesTraded(), e.getTurnover());
	}

	private NiftyOhlc apply(NiftyDailyDto dto, NiftyOhlc e) {
		e.setTradeDate(dto.tradeDate());
		e.setOpen(dto.open());
		e.setHigh(dto.high());
		e.setLow(dto.low());
		e.setClose(dto.close());
		e.setSharesTraded(dto.sharesTraded());
		e.setTurnover(dto.turnover());
		return e;
	}
}


