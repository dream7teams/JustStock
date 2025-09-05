package net.juststock.trading.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "signal_purchases",
       uniqueConstraints = @UniqueConstraint(name="uk_user_signal", columnNames={"user_id","signal_id"}))
public class SignalPurchase {

  public enum Selection { CALL, PUT, FUTURE }
  public enum Status { PENDING, PAID, FAILED, REFUNDED }

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="user_id", nullable=false)
  private Long userId;

  @Column(name="user_contact", nullable=false)
  private String userContact;

  @Column(name="signal_id", nullable=false)
  private Long signalId;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false, length=10)
  private Selection selection;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false, length=10)
  private Status status = Status.PENDING;

  @Column(nullable=false)
  private Long amountCents;

  @Column(nullable=false, length=3)
  private String currency = "INR";

  @Column(nullable=false, length=20)
  private String provider = "RAZORPAY";

  @Column(unique=true, length=80)
  private String providerOrderId;

  @Column(columnDefinition = "json")
  private String instrumentSnapshotJson;

  @Column(nullable=false)
  private Instant createdAtUtc = Instant.now();

  private Instant paidAtUtc;

  // getters/setters
  public Long getId() { return id; }
  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  public String getUserContact() { return userContact; }
  public void setUserContact(String userContact) { this.userContact = userContact; }
  public Long getSignalId() { return signalId; }
  public void setSignalId(Long signalId) { this.signalId = signalId; }
  public Selection getSelection() { return selection; }
  public void setSelection(Selection selection) { this.selection = selection; }
  public Status getStatus() { return status; }
  public void setStatus(Status status) { this.status = status; }
  public Long getAmountCents() { return amountCents; }
  public void setAmountCents(Long amountCents) { this.amountCents = amountCents; }
  public String getCurrency() { return currency; }
  public void setCurrency(String currency) { this.currency = currency; }
  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }
  public String getProviderOrderId() { return providerOrderId; }
  public void setProviderOrderId(String providerOrderId) { this.providerOrderId = providerOrderId; }
  public String getInstrumentSnapshotJson() { return instrumentSnapshotJson; }
  public void setInstrumentSnapshotJson(String instrumentSnapshotJson) { this.instrumentSnapshotJson = instrumentSnapshotJson; }
  public Instant getCreatedAtUtc() { return createdAtUtc; }
  public void setCreatedAtUtc(Instant createdAtUtc) { this.createdAtUtc = createdAtUtc; }
  public Instant getPaidAtUtc() { return paidAtUtc; }
  public void setPaidAtUtc(Instant paidAtUtc) { this.paidAtUtc = paidAtUtc; }
}
