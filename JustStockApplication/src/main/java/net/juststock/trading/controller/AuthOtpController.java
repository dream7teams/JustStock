// src/main/java/net/juststock/trading/controller/AuthOtpController.java
package net.juststock.trading.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import net.juststock.trading.service.interfaces.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthOtpController {

    private final OtpService otpService;

    public AuthOtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    // Accepts any of: phone / phoneNumber / mobileNumber
    public static class SendReq {
        @JsonAlias({"phone","phoneNumber","mobileNumber"})
        @NotBlank
        @Pattern(regexp="^\\+?\\d{10,15}$", message="Phone must be digits, optionally starting with +")
        public String phone;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String,Object>> send(@RequestBody @Valid SendReq req) {
        String otp = otpService.sendOtp(req.phone);
        // ⚠ Do NOT return the OTP in production responses!
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "OTP sent",
            "to", req.phone
            // "debugOtp", otp   // remove in prod
        ));
    }
}
