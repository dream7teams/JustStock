package net.juststock.trading.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "payment_events")
public class PaymentEvent {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private String provider; // RAZORPAY
  @Column(nullable=false) private String event;    // payment.captured
  private String orderId;
  private String paymentId;
  private String signature;

  @Lob
  @Column(columnDefinition = "json")
  private String payload;

  @Column(nullable=false)
  private Instant createdAtUtc = Instant.now();

  // getters/setters
  public Long getId() { return id; }
  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }
  public String getEvent() { return event; }
  public void setEvent(String event) { this.event = event; }
  public String getOrderId() { return orderId; }
  public void setOrderId(String orderId) { this.orderId = orderId; }
  public String getPaymentId() { return paymentId; }
  public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
  public String getSignature() { return signature; }
  public void setSignature(String signature) { this.signature = signature; }
  public String getPayload() { return payload; }
  public void setPayload(String payload) { this.payload = payload; }
  public Instant getCreatedAtUtc() { return createdAtUtc; }
  public void setCreatedAtUtc(Instant createdAtUtc) { this.createdAtUtc = createdAtUtc; }
}
