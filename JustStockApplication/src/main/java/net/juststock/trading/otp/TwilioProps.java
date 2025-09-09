package net.juststock.trading.otp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioProps {
    private String accountSid;
    private String authToken;
    private String verifyServiceSid;

    public String getAccountSid() { return accountSid; }
    public void setAccountSid(String accountSid) { this.accountSid = accountSid; }
    public String getAuthToken() { return authToken; }
    public void setAuthToken(String authToken) { this.authToken = authToken; }
    public String getVerifyServiceSid() { return verifyServiceSid; }
    public void setVerifyServiceSid(String verifyServiceSid) { this.verifyServiceSid = verifyServiceSid; }
}
