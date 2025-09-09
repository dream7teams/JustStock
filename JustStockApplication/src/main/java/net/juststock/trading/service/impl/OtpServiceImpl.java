package net.juststock.trading.service.impl;

import net.juststock.trading.service.interfaces.OtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Value("${twilio.phone.number}")
    private String fromNumber;

    @Override
    public String sendOtp(String toNumber) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        String messageBody = "Your OTP is: " + otp;

        Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromNumber),
                messageBody
        ).create();

        return otp;
    }

	@Override
	public boolean verifyOtp(String mobileNumber, String otp) {
		return false;
	}
}