package net.juststock.trading.domain.payment;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import net.juststock.trading.domain.user.UserProfile;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Relationship to UserProfile instead of primitive userId
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    private InstrumentType instrumentType;

    @Enumerated(EnumType.STRING)
    private SignalType signalType;

    private Integer amount; // in INR
    private String currency; // e.g. "INR"

    @Column(length = 40)
    private String status; // SUCCESS, FAILED, PENDING

    @Column(length = 64)
    private String transactionId;

    private ZonedDateTime createdAt;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserProfile getUserProfile() { return userProfile; }
    public void setUserProfile(UserProfile userProfile) { this.userProfile = userProfile; }

    public InstrumentType getInstrumentType() { return instrumentType; }
    public void setInstrumentType(InstrumentType instrumentType) { this.instrumentType = instrumentType; }

    public SignalType getSignalType() { return signalType; }
    public void setSignalType(SignalType signalType) { this.signalType = signalType; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public ZonedDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(ZonedDateTime createdAt) { this.createdAt = createdAt; }
}
