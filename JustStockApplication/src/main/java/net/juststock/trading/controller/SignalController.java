package net.juststock.trading.controller;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import net.juststock.trading.dto.AdviceResponseDTO;
import net.juststock.trading.service.interfaces.SignalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signals")
public class SignalController {

    private final SignalService signalService;
    public SignalController(SignalService signalService) { this.signalService = signalService; }

    // Returns paywall prompt (static) for UI before payment
    @GetMapping("/prompt")
    public ResponseEntity<?> paymentPrompt(@RequestParam InstrumentType instrumentType,
                                           @RequestParam SignalType signalType) {
        return ResponseEntity.ok(new Object() {
            public final boolean paymentRequired = true;
            public final int price = 499;
            public final String currency = "INR";
            public final String message = "Please complete payment to view the advice.";
        });
    }

    // After payment, return the configured admin advice and save user history
    @GetMapping("/advice")
    public ResponseEntity<AdviceResponseDTO> advice(@RequestParam Long userId,
                                                    @RequestParam InstrumentType instrumentType,
                                                    @RequestParam SignalType signalType) {
        return ResponseEntity.ok(signalService.getAdviceAfterPayment(userId, instrumentType, signalType));
    }
}
