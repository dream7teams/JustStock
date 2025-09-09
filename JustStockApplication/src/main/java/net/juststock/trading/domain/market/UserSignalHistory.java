package net.juststock.trading.domain.market;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import net.juststock.trading.domain.user.UserProfile;

@Entity
@Table(name = "user_signal_history",
       indexes = @Index(name = "idx_user_instrument_time", columnList = "user_id,instrumentType,createdAt"))
public class UserSignalHistory {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Relationship instead of primitive userId
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // cascade added
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    private InstrumentType instrumentType;

    @Enumerated(EnumType.STRING)
    private SignalType signalType;

    @Column(length = 1000)
    private String adviceMessage;

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

    public String getAdviceMessage() { return adviceMessage; }
    public void setAdviceMessage(String adviceMessage) { this.adviceMessage = adviceMessage; }

    public ZonedDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(ZonedDateTime createdAt) { this.createdAt = createdAt; }
}
