package net.juststock.trading.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
    name = "options",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_options_series",
        columnNames = {"underlying_symbol", "exchange", "expiry_date", "strike", "option_type", "trading_symbol"}
    )
)
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "underlying_symbol", nullable = false, length = 60)
    private String underlyingSymbol;

    @Column(name = "exchange", nullable = false, length = 24)
    private String exchange; // e.g., NFO

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "strike", nullable = false, precision = 14, scale = 4)
    private BigDecimal strike;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type", nullable = false, length = 4)
    private OptionType optionType;

    @Column(name = "lot_size", nullable = false)
    private Integer lotSize;

    @Column(name = "contract_multiplier", precision = 10, scale = 4)
    private BigDecimal contractMultiplier;

    @Column(name = "tick_size", precision = 12, scale = 4)
    private BigDecimal tickSize;

    @Column(name = "trading_symbol", length = 80)
    private String tradingSymbol;

    public Options() {}

    public Options(String underlyingSymbol, String exchange, LocalDate expiryDate,
                   BigDecimal strike, OptionType optionType, Integer lotSize,
                   BigDecimal contractMultiplier, BigDecimal tickSize, String tradingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
        this.exchange = exchange;
        this.expiryDate = expiryDate;
        this.strike = strike;
        this.optionType = optionType;
        this.lotSize = lotSize;
        this.contractMultiplier = contractMultiplier;
        this.tickSize = tickSize;
        this.tradingSymbol = tradingSymbol;
    }

    public Long getId() { return id; }
    public String getUnderlyingSymbol() { return underlyingSymbol; }
    public void setUnderlyingSymbol(String underlyingSymbol) { this.underlyingSymbol = underlyingSymbol; }

    public String getExchange() { return exchange; }
    public void setExchange(String exchange) { this.exchange = exchange; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public BigDecimal getStrike() { return strike; }
    public void setStrike(BigDecimal strike) { this.strike = strike; }

    public OptionType getOptionType() { return optionType; }
    public void setOptionType(OptionType optionType) { this.optionType = optionType; }

    public Integer getLotSize() { return lotSize; }
    public void setLotSize(Integer lotSize) { this.lotSize = lotSize; }

    public BigDecimal getContractMultiplier() { return contractMultiplier; }
    public void setContractMultiplier(BigDecimal contractMultiplier) { this.contractMultiplier = contractMultiplier; }

    public BigDecimal getTickSize() { return tickSize; }
    public void setTickSize(BigDecimal tickSize) { this.tickSize = tickSize; }

    public String getTradingSymbol() { return tradingSymbol; }
    public void setTradingSymbol(String tradingSymbol) { this.tradingSymbol = tradingSymbol; }

    @Override
    public String toString() {
        return "Options{" +
                "id=" + id +
                ", underlyingSymbol='" + underlyingSymbol + '\'' +
                ", exchange='" + exchange + '\'' +
                ", expiryDate=" + expiryDate +
                ", strike=" + strike +
                ", optionType=" + optionType +
                ", lotSize=" + lotSize +
                ", contractMultiplier=" + contractMultiplier +
                ", tickSize=" + tickSize +
                ", tradingSymbol='" + tradingSymbol + '\'' +
                '}';
    }
}
