package net.juststock.trading.controller;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.dto.SignalSelectionDTO;
import net.juststock.trading.service.interfaces.SignalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    private final SignalService signalService;

    public InstrumentController(SignalService signalService) {
        this.signalService = signalService;
    }

    // List available instruments for selection
    @GetMapping
    public ResponseEntity<List<InstrumentType>> listInstruments() {
        return ResponseEntity.ok(Arrays.asList(InstrumentType.values()));
    }

    // After user selects an instrument, return available signal types
    @PostMapping("/signals/types")
    public ResponseEntity<List<String>> signalTypes(@RequestBody SignalSelectionDTO selection) {
        return ResponseEntity.ok(signalService.availableSignalTypes(selection.getInstrumentType()));
    }
}
