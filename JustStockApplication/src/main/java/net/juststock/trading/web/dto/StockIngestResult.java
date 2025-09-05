package net.juststock.trading.web.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record StockIngestResult(
        Long stockId,
        String symbol,
        String exchange,
        Long quoteId,
        BigDecimal current,
        Long volume,
        Instant timestamp
) {}
