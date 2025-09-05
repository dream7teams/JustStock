package net.juststock.trading.web.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record StockIngestDto(
        String symbol,
        String companyName,   // optional (not stored in Stock entity yet)
        String exchange,
        String currency,
        Price price,
        Volume volume,
        Long marketCap,
        BigDecimal peRatio,
        BigDecimal eps,
        Instant timestamp
) {
    public record Price(
            BigDecimal current,
            BigDecimal open,
            BigDecimal high,
            BigDecimal low,
            BigDecimal previousClose
    ) {}

    public record Volume(
            Long current,
            Long average
    ) {}
}
