package net.juststock.trading.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import net.juststock.trading.dto.TwilioRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class TwilioController {

    @Value("${twilio.accountSid:}")
    private String accountSid;

    @Value("${twilio.authToken:}")
    private String authToken;

    @Value("${twilio.fromNumber:}")
    private String defaultFrom;

    @Value("${twilio.messagingServiceSid:}")
    private String messagingServiceSid;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody TwilioRequest twilioRequest) {
        if (twilioRequest == null || twilioRequest.getMessage() == null || twilioRequest.getToPhoneNumber() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }

        String fromNumber = (twilioRequest.getFromPhoneNumber() != null && !twilioRequest.getFromPhoneNumber().isBlank())
                ? twilioRequest.getFromPhoneNumber() : defaultFrom;
        boolean hasMsid = messagingServiceSid != null && !messagingServiceSid.isBlank();
        boolean msidLooksValid = hasMsid && messagingServiceSid.startsWith("MG");
        boolean useMessagingService = msidLooksValid;
        if (hasMsid && !msidLooksValid) {
            return ResponseEntity.badRequest().body("twilio.messagingServiceSid must start with 'MG'. Provided SID looks like '" + messagingServiceSid + "'.");
        }
        if (!useMessagingService) {
            if (fromNumber == null || fromNumber.isBlank()) {
                return ResponseEntity.badRequest().body("Missing from. Configure twilio.fromNumber or twilio.messagingServiceSid, or pass fromPhoneNumber");
            }
        }
        if (accountSid == null || accountSid.isBlank() || authToken == null || authToken.isBlank()) {
            return ResponseEntity.status(500).body("Twilio credentials not configured");
        }

        Twilio.init(accountSid, authToken);
        MessageCreator creator;
        if (useMessagingService) {
            creator = Message.creator(new PhoneNumber(twilioRequest.getToPhoneNumber()), (PhoneNumber) null, twilioRequest.getMessage())
                    .setMessagingServiceSid(messagingServiceSid);
        } else {
            creator = Message.creator(new PhoneNumber(twilioRequest.getToPhoneNumber()), new PhoneNumber(fromNumber), twilioRequest.getMessage());
        }
        creator.create();
        return ResponseEntity.ok("SMS sent Successfully!");
    }
}
