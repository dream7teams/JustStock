package net.juststock.trading.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BankNiftyDailyDto(
        LocalDate tradeDate,
        BigDecimal open,
        BigDecimal high,
        BigDecimal low,
        BigDecimal close,
        Long sharesTraded,
        BigDecimal turnover
) {}

 record BankNiftyRangeQueryDto(LocalDate from, LocalDate to) {}
