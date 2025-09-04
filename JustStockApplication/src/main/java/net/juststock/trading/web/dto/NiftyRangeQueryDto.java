package net.juststock.trading.web.dto;

import java.time.LocalDate;

public record NiftyRangeQueryDto(LocalDate from, LocalDate to) {
}