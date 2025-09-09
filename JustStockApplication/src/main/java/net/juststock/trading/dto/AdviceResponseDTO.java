package net.juststock.trading.dto;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;

public class AdviceResponseDTO {
    private Long userId;
    private InstrumentType instrumentType;
    private SignalType signalType;
    private String advice;

    public AdviceResponseDTO() {}

    public AdviceResponseDTO(Long userId, InstrumentType instrumentType, SignalType signalType, String advice) {
        this.userId = userId; this.instrumentType = instrumentType; this.signalType = signalType; this.advice = advice;
    }

    // getters/setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public InstrumentType getInstrumentType() { return instrumentType; }
    public void setInstrumentType(InstrumentType instrumentType) { this.instrumentType = instrumentType; }
    public SignalType getSignalType() { return signalType; }
    public void setSignalType(SignalType signalType) { this.signalType = signalType; }
    public String getAdvice() { return advice; }
    public void setAdvice(String advice) { this.advice = advice; }
}
