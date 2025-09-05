package net.juststock.trading.web.dto;

public record CreateSignalCheckoutRes(
        Long purchaseId,
        long amountCents,
        String currency,
        String provider,
        String orderId,
        String displayMessage
) {}
