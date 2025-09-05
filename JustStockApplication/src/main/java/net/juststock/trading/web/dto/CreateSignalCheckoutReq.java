package net.juststock.trading.web.dto;

import net.juststock.trading.domain.SignalPurchase;

public record CreateSignalCheckoutReq(
        Long signalId,
        SignalPurchase.Selection selection // CALL, PUT, FUTURE
) {}
