package net.juststock.trading.domain.singnal;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "futures", uniqueConstraints = @UniqueConstraint(columnNames = { "underlying_symbol", "exchange",
		"expiry_date", "trading_symbol" }))
public class Futures {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "underlying_symbol", nullable = false, length = 60)
	private String underlyingSymbol;
	@Column(nullable = false, length = 24)
	private String exchange; // NFO
	@Column(name = "expiry_date", nullable = false)
	private java.sql.Date expiryDate;
	@Column(name = "lot_size", nullable = false)
	private Integer lotSize;
	@Column(name = "contract_multiplier", precision = 10, scale = 4)
	private BigDecimal contractMultiplier;
	@Column(name = "tick_size", precision = 12, scale = 4)
	private BigDecimal tickSize;
	@Column(name = "trading_symbol", length = 80)
	private String tradingSymbol;

	public Futures() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnderlyingSymbol() {
		return underlyingSymbol;
	}

	public void setUnderlyingSymbol(String underlyingSymbol) {
		this.underlyingSymbol = underlyingSymbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public java.sql.Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(java.sql.Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	public BigDecimal getContractMultiplier() {
		return contractMultiplier;
	}

	public void setContractMultiplier(BigDecimal contractMultiplier) {
		this.contractMultiplier = contractMultiplier;
	}

	public BigDecimal getTickSize() {
		return tickSize;
	}

	public void setTickSize(BigDecimal tickSize) {
		this.tickSize = tickSize;
	}

	public String getTradingSymbol() {
		return tradingSymbol;
	}

	public void setTradingSymbol(String tradingSymbol) {
		this.tradingSymbol = tradingSymbol;
	}

	public Futures(String underlyingSymbol, String exchange, Date expiryDate, Integer lotSize,
			BigDecimal contractMultiplier, BigDecimal tickSize, String tradingSymbol) {
		super();
		this.underlyingSymbol = underlyingSymbol;
		this.exchange = exchange;
		this.expiryDate = expiryDate;
		this.lotSize = lotSize;
		this.contractMultiplier = contractMultiplier;
		this.tickSize = tickSize;
		this.tradingSymbol = tradingSymbol;
	}

	

}