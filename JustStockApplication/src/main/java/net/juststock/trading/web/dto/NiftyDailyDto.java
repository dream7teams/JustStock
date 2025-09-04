package net.juststock.trading.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NiftyDailyDto(
        LocalDate tradeDate,
        BigDecimal open,
        BigDecimal high,
        BigDecimal low,
        BigDecimal close,
        Long sharesTraded,
        BigDecimal turnover
) {}

