package net.juststock.trading.service.interfaces;

public interface OtpService {
    String sendOtp(String mobileNumber);
    boolean verifyOtp(String mobileNumber, String otp);
}
