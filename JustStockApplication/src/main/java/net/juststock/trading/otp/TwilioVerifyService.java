package net.juststock.trading.otp;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.stereotype.Service;

@Service
public class TwilioVerifyService {
    private final TwilioProps props;

    public TwilioVerifyService(TwilioProps props) {
        this.props = props;
        if (props.getAccountSid() != null && props.getAuthToken() != null) {
            Twilio.init(props.getAccountSid(), props.getAuthToken());
        }
    }

    public Verification start(String phoneE164) {
        try {
            return Verification.creator(props.getVerifyServiceSid(), phoneE164, "sms").create();
        } catch (ApiException ex) {
            throw new RuntimeException("Failed to start verification: " + ex.getMessage(), ex);
        }
    }

    public VerificationCheck check(String phoneE164, String code) {
        try {
            return VerificationCheck.creator(props.getVerifyServiceSid())
                    .setTo(phoneE164)
                    .setCode(code)
                    .create();
        } catch (ApiException ex) {
            throw new RuntimeException("Failed to verify code: " + ex.getMessage(), ex);
        }
    }
}
