package net.juststock.trading.controller;

import net.juststock.trading.domain.user.UserProfile;
import net.juststock.trading.dto.SendCodeRequestDTO;
import net.juststock.trading.security.JwtUtil;
import net.juststock.trading.service.interfaces.OtpService;
import net.juststock.trading.service.interfaces.UserProfileService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
public class AuthController {

    private final OtpService otpService;
    private final UserProfileService userService;
    private final JwtUtil jwtUtil;

    public AuthController(OtpService otpService, UserProfileService userService, JwtUtil jwtUtil) {
        this.otpService = otpService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Send OTP: POST /api/auth/send-otp/8317237646
    @PostMapping("/send-otp/{mobileNumber}")
    public ResponseEntity<?> sendOtpPath(@PathVariable String mobileNumber) {
        otpService.sendOtp(mobileNumber);
        return ResponseEntity.ok("OTP sent to " + mobileNumber);
    }

    // JSON API: POST /api/auth/send-code
    // Body: { "mobileNumber": "8317237646" }
    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody SendCodeRequestDTO request) {
        String mobileNumber = request.getMobileNumber();
        otpService.sendOtp(mobileNumber);
        return ResponseEntity.ok(Map.of(
                "message", "OTP sent",
                "mobileNumber", mobileNumber
        ));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String mobileNumber, @RequestParam String otp) {
        boolean verified = otpService.verifyOtp(mobileNumber, otp);
        if (!verified) return ResponseEntity.status(401).body("Invalid or expired OTP");

        UserProfile user = userService.getUserByContactNumber(mobileNumber)
            .orElseGet(() -> userService.createUser(new UserProfile(null, "Unknown", mobileNumber)));

        String token = jwtUtil.generateToken(user.getId(), mobileNumber);
        return ResponseEntity.ok(Map.of("jwt", token, "userId", user.getId()));
    }
}
