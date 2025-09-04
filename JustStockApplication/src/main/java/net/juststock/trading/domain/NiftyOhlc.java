package net.juststock.trading.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "nifty_ohlc", uniqueConstraints = @UniqueConstraint(name = "uk_nifty_date", columnNames = "trade_date"), indexes = @Index(name = "idx_nifty_date", columnList = "trade_date"))
public class NiftyOhlc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Unique trade date
	@Column(name = "trade_date", nullable = false)
	private LocalDate tradeDate;

	@Column(name = "open", nullable = false, precision = 14, scale = 2)
	private BigDecimal open;

	@Column(name = "high", nullable = false, precision = 14, scale = 2)
	private BigDecimal high;

	@Column(name = "low", nullable = false, precision = 14, scale = 2)
	private BigDecimal low;

	@Column(name = "close", nullable = false, precision = 14, scale = 2)
	private BigDecimal close;

	@Column(name = "shares_traded", nullable = false)
	private Long sharesTraded;

	@Column(name = "turnover", nullable = false, precision = 20, scale = 2)
	private BigDecimal turnover;

	public NiftyOhlc() {
	}

	public NiftyOhlc(LocalDate tradeDate, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close,
			Long sharesTraded, BigDecimal turnover) {
		this.tradeDate = tradeDate;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.sharesTraded = sharesTraded;
		this.turnover = turnover;
	}

	// --- Getters & Setters ---
	public Long getId() {
		return id;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public Long getSharesTraded() {
		return sharesTraded;
	}

	public void setSharesTraded(Long sharesTraded) {
		this.sharesTraded = sharesTraded;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

	@Override
	public String toString() {
		return "NiftyOhlc{" + "id=" + id + ", tradeDate=" + tradeDate + ", open=" + open + ", high=" + high + ", low="
				+ low + ", close=" + close + ", sharesTraded=" + sharesTraded + ", turnover=" + turnover + '}';
	}
}
