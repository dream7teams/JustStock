package net.juststock.trading.domain.singnal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
@Entity
@Table(name = "option_strike")
public class OptionStrike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double strikePrice;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "lastPrice", column = @Column(name = "call_last_price")),
        @AttributeOverride(name = "changePercent", column = @Column(name = "call_change_percent")),
        @AttributeOverride(name = "openInterest", column = @Column(name = "call_open_interest")),
        @AttributeOverride(name = "changeInOI", column = @Column(name = "call_change_inoi")),
        @AttributeOverride(name = "impliedVolatility", column = @Column(name = "call_iv"))
    })
    private OptionDetail call;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "lastPrice", column = @Column(name = "put_last_price")),
        @AttributeOverride(name = "changePercent", column = @Column(name = "put_change_percent")),
        @AttributeOverride(name = "openInterest", column = @Column(name = "put_open_interest")),
        @AttributeOverride(name = "changeInOI", column = @Column(name = "put_change_inoi")),
        @AttributeOverride(name = "impliedVolatility", column = @Column(name = "put_iv"))
    })
    private OptionDetail put;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_chain_id")
    @JsonBackReference
    private Options optionChain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getStrikePrice() {
		return strikePrice;
	}

	public void setStrikePrice(Double strikePrice) {
		this.strikePrice = strikePrice;
	}

	public OptionDetail getCall() {
		return call;
	}

	public void setCall(OptionDetail call) {
		this.call = call;
	}

	public OptionDetail getPut() {
		return put;
	}

	public void setPut(OptionDetail put) {
		this.put = put;
	}

	public Options getOptionChain() {
		return optionChain;
	}

	public void setOptionChain(Options optionChain) {
		this.optionChain = optionChain;
	}

}