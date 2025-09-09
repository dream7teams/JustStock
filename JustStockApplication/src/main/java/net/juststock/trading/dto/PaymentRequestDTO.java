package net.juststock.trading.dto;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;

public class PaymentRequestDTO {
    private Long userId;
    private InstrumentType instrumentType;
    private SignalType signalType;
    private Integer amount; // in INR

    // getters/setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public InstrumentType getInstrumentType() { return instrumentType; }
    public void setInstrumentType(InstrumentType instrumentType) { this.instrumentType = instrumentType; }
    public SignalType getSignalType() { return signalType; }
    public void setSignalType(SignalType signalType) { this.signalType = signalType; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
}
