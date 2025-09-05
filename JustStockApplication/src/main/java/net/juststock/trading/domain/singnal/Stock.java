package net.juststock.trading.domain.singnal;

import jakarta.persistence.*;


@Entity
@Table(name = "stocks",
       uniqueConstraints = @UniqueConstraint(name = "uk_symbol_exchange", columnNames = {"symbol", "exchange"}))
public class Stock {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 60)
  private String symbol;

  @Column(nullable = false, length = 24)
  private String exchange;

  @Column(length = 120)
  private String companyName;

  @Column(length = 12)
  private String currency;

  @Column(length = 20)
  private String isin;

  @Column(length = 60)
  private String sector;
  // getters/setters...

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

  public String getCompanyName() {
	return companyName;
  }

  public void setCompanyName(String companyName) {
	this.companyName = companyName;
  }

  public String getCurrency() {
	return currency;
  }

  public void setCurrency(String currency) {
	this.currency = currency;
  }

  public String getIsin() {
	return isin;
  }

  public void setIsin(String isin) {
	this.isin = isin;
  }

  public String getSector() {
	return sector;
  }

  public void setSector(String sector) {
	this.sector = sector;
  }
  
  
}
