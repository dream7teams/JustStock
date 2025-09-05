package net.juststock.trading.domain.singnal;

import jakarta.persistence.Embeddable;

@Embeddable
public class OptionDetail {

	private Double lastPrice;
	private Double changePercent;
	private Integer openInterest;
	private Integer changeInOI;
	private Double impliedVolatility;

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(Double changePercent) {
		this.changePercent = changePercent;
	}

	public Integer getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
	}

	public Integer getChangeInOI() {
		return changeInOI;
	}

	public void setChangeInOI(Integer changeInOI) {
		this.changeInOI = changeInOI;
	}

	public Double getImpliedVolatility() {
		return impliedVolatility;
	}

	public void setImpliedVolatility(Double impliedVolatility) {
		this.impliedVolatility = impliedVolatility;
	}

}
