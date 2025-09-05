package net.juststock.trading.web.dto;

public record PurchaseView(
        Long purchaseId,
        Long userId,
        String userContact,
        Long signalId,
        String selection, // CALL/PUT/FUTURE
        String status,    // PENDING/PAID/FAILED/REFUNDED
        String message
) {}
