package net.juststock.trading.controller;


import net.juststock.trading.domain.singnal.NiftyIndex;
import net.juststock.trading.domain.singnal.Options;
import net.juststock.trading.services.MarketDataService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
public class NiftyController {

    private final MarketDataService marketDataService;

    public NiftyController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/index/nifty")
    public ResponseEntity<NiftyIndex> getNiftyIndex() {
        NiftyIndex index = marketDataService.getLatestNiftyIndex();
        return ResponseEntity.ok(index);
    }

    @GetMapping("/options/{underlying}")
    public ResponseEntity<List<Options>> getOptionChain(
            @PathVariable String underlying,
            @RequestParam("expiry") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDate) {
        List<Options> chain = marketDataService.getOptionChain(underlying, expiryDate);
        return ResponseEntity.ok(chain);
    }
}