package net.juststock.trading.services.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.juststock.trading.services.PaymentProvider;

@Component
public class RazorpayProvider implements PaymentProvider {

    @Value("${razorpay.key:dummy_key}")
    private String key;

    @Value("${razorpay.secret:dummy_secret}")
    private String secret;

    @Override
    public OrderResp createOrder(long amountCents, String currency, String receipt) {
        // TODO: replace with real Razorpay Orders API call and return actual order id
        String fakeOrderId = "order_" + UUID.randomUUID();
        return new OrderResp(fakeOrderId);
    }

    @Override
    public boolean verifySignature(String payload, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            String actual = Base64.getEncoder().encodeToString(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
            return constantTimeEquals(actual, signature);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ParsedEvent parseWebhookPayload(String payload) {
        // TODO: parse actual Razorpay webhook JSON
        // Return fake values so the flow works end-to-end in dev.
        return new ParsedEvent("order_dummy", "pay_dummy", "payment.captured");
    }

    private boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        int r = 0;
        for (int i = 0; i < a.length(); i++) r |= a.charAt(i) ^ b.charAt(i);
        return r == 0;
    }
}
