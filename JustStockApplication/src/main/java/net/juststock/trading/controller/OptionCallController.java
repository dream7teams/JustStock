package net.juststock.trading.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.juststock.trading.domain.singnal.Options;
import net.juststock.trading.services.MarketDataService;

@RestController
@RequestMapping("api/options/")
public class OptionCallController {

	
	private final MarketDataService marketDataService;
	
	
	
	public OptionCallController(MarketDataService marketDataService) {
		super();
		this.marketDataService = marketDataService;
	}



	@PostMapping("/addoptions")
	public ResponseEntity<Options> createOptionChain(@RequestBody Options optionChain) {
	    Options saved = marketDataService.saveOptionChain(optionChain);
	    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
}
