package net.juststock.trading.dto;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;

public class AdminSignalMessageDTO {
    private Long id;
    private SignalType signalType;
    private InstrumentType instrumentType;
    private String message;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public SignalType getSignalType() { return signalType; }
    public void setSignalType(SignalType signalType) { this.signalType = signalType; }
    public InstrumentType getInstrumentType() { return instrumentType; }
    public void setInstrumentType(InstrumentType instrumentType) { this.instrumentType = instrumentType; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
