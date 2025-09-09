package net.juststock.trading.domain.admin;

import jakarta.persistence.*;
import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;

@Entity
@Table(name = "admin_signal_message",
       uniqueConstraints = @UniqueConstraint(columnNames = {"signalType","instrumentType"}))
public class AdminSignalMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SignalType signalType; // CALL, PUT, FUTURE

    @Enumerated(EnumType.STRING)
    private InstrumentType instrumentType; // NIFTY, BANKNIFTY, STOCK, COMMODITY, SENSEX

    @Column(nullable = false, length = 1000)
    private String message; // Advice text

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
