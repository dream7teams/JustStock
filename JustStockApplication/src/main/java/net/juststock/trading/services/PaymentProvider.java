package net.juststock.trading.services;

public interface PaymentProvider {

    record OrderResp(String orderId) {}
    record ParsedEvent(String orderId, String paymentId, String event) {}

    /**
     * Create a payment order with the gateway.
     */
    OrderResp createOrder(long amountCents, String currency, String receipt);

    /**
     * Verify the gateway signature for webhook payload.
     */
    boolean verifySignature(String payload, String signature);

    /**
     * Parse webhook JSON into a simplified structure.
     */
    ParsedEvent parseWebhookPayload(String payload);
}
