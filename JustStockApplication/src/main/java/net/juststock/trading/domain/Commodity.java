package net.juststock.trading.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "commodities", uniqueConstraints = @UniqueConstraint(columnNames = { "symbol", "exchange" }))
public class Commodity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String symbol; // e.g., GOLD
	@Column(nullable = false, length = 24)
	private String exchange; // MCX
	@Column(name = "commodity_group", length = 40)
	private String commodityGroup; // METALS, ENERGY
	@Column(length = 20)
	private String unit; // grams, kg, barrel
	@Column(name = "tick_size", precision = 12, scale = 4)
	private BigDecimal tickSize;

	public Commodity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCommodityGroup() {
		return commodityGroup;
	}

	public void setCommodityGroup(String commodityGroup) {
		this.commodityGroup = commodityGroup;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getTickSize() {
		return tickSize;
	}

	public void setTickSize(BigDecimal tickSize) {
		this.tickSize = tickSize;
	}

	public Commodity(String symbol, String exchange, String commodityGroup, String unit, BigDecimal tickSize) {
		super();
		this.symbol = symbol;
		this.exchange = exchange;
		this.commodityGroup = commodityGroup;
		this.unit = unit;
		this.tickSize = tickSize;
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", symbol=" + symbol + ", exchange=" + exchange + ", commodityGroup="
				+ commodityGroup + ", unit=" + unit + ", tickSize=" + tickSize + "]";
	}

}
