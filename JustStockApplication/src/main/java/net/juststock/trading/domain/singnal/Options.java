package net.juststock.trading.domain.singnal;

import jakarta.persistence.*;
import net.juststock.trading.domain.OptionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "option_chain")
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String underlying;
    private Double spotPrice;
    private LocalDate expiryDate;
    private Integer lotSize;
    private Integer maxPain;
    private Double putCallRatio;

    @OneToMany(mappedBy = "optionChain", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OptionStrike> options = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnderlying() {
		return underlying;
	}

	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public Double getSpotPrice() {
		return spotPrice;
	}

	public void setSpotPrice(Double spotPrice) {
		this.spotPrice = spotPrice;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	public Integer getMaxPain() {
		return maxPain;
	}

	public void setMaxPain(Integer maxPain) {
		this.maxPain = maxPain;
	}

	public Double getPutCallRatio() {
		return putCallRatio;
	}

	public void setPutCallRatio(Double putCallRatio) {
		this.putCallRatio = putCallRatio;
	}

	public List<OptionStrike> getOptions() {
		return options;
	}

	public void setOptions(List<OptionStrike> options) {
		this.options = options;
	}
    
    
    
}