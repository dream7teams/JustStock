package net.juststock.trading.web.dto;

public record StockDto(
        Long id,
        String symbol,
        String exchange,
        String isin,
        String sector,
        String companyName,   
        String currency       
        
        
) {

	@Override
	public String toString() {
		return "StockDto [id=" + id + ", symbol=" + symbol + ", exchange=" + exchange + ", isin=" + isin + ", sector="
				+ sector + "]";
	}}
