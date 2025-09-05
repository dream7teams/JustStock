package net.juststock.trading.domain.singnal;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "stock_quotes", indexes = { @Index(name = "idx_quote_symbol_time", columnList = "symbol, ts_utc"),
		@Index(name = "idx_quote_stock_id_time", columnList = "stock_id, ts_utc") })
public class StockQuote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "stock_id")
	private Long stockId;

	@Column(nullable = false, length = 20)
	private String symbol;

	@Column(length = 24)
	private String exchange;

	@Column(length = 12)
	private String currency;

	@Column(name = "ts_utc", nullable = false)
	private Instant timestampUtc;

	@Column(precision = 16, scale = 4)
	private BigDecimal priceCurrent;
	@Column(precision = 16, scale = 4)
	private BigDecimal priceOpen;
	@Column(precision = 16, scale = 4)
	private BigDecimal priceHigh;
	@Column(precision = 16, scale = 4)
	private BigDecimal priceLow;
	@Column(precision = 16, scale = 4)
	private BigDecimal pricePreviousClose;

	private Long volumeCurrent;
	private Long volumeAverage;
	private Long marketCap;

	@Column(precision = 12, scale = 4)
	private BigDecimal peRatio;
	@Column(precision = 12, scale = 4)
	private BigDecimal eps;

	// getters/setters
	public Long getId() {
		return id;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Instant getTimestampUtc() {
		return timestampUtc;
	}

	public void setTimestampUtc(Instant timestampUtc) {
		this.timestampUtc = timestampUtc;
	}

	public BigDecimal getPriceCurrent() {
		return priceCurrent;
	}

	public void setPriceCurrent(BigDecimal priceCurrent) {
		this.priceCurrent = priceCurrent;
	}

	public BigDecimal getPriceOpen() {
		return priceOpen;
	}

	public void setPriceOpen(BigDecimal priceOpen) {
		this.priceOpen = priceOpen;
	}

	public BigDecimal getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(BigDecimal priceHigh) {
		this.priceHigh = priceHigh;
	}

	public BigDecimal getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(BigDecimal priceLow) {
		this.priceLow = priceLow;
	}

	public BigDecimal getPricePreviousClose() {
		return pricePreviousClose;
	}

	public void setPricePreviousClose(BigDecimal pricePreviousClose) {
		this.pricePreviousClose = pricePreviousClose;
	}

	public Long getVolumeCurrent() {
		return volumeCurrent;
	}

	public void setVolumeCurrent(Long volumeCurrent) {
		this.volumeCurrent = volumeCurrent;
	}

	public Long getVolumeAverage() {
		return volumeAverage;
	}

	public void setVolumeAverage(Long volumeAverage) {
		this.volumeAverage = volumeAverage;
	}

	public Long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}

	public BigDecimal getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(BigDecimal peRatio) {
		this.peRatio = peRatio;
	}

	public BigDecimal getEps() {
		return eps;
	}

	public void setEps(BigDecimal eps) {
		this.eps = eps;
	}
}
