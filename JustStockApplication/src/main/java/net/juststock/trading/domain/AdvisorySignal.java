package net.juststock.trading.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "advisory_signals")
public class AdvisorySignal {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 40)
  private String instrumentType; // NIFTY/BANKNIFTY/STOCK/OPTION/FUTURE/COMMODITY

  @Column(nullable = false)
  private Long instrumentId; // FK to Stock.id (or other)

  @Column(nullable = false, length = 120)
  private String title; // Display for UI

  @Column(length = 255)
  private String message; // Admin message

  @Column(nullable = false)
  private Instant createdAtUtc = Instant.now();

  // getters/setters
  public Long getId() { return id; }
  public String getInstrumentType() { return instrumentType; }
  public void setInstrumentType(String instrumentType) { this.instrumentType = instrumentType; }
  public Long getInstrumentId() { return instrumentId; }
  public void setInstrumentId(Long instrumentId) { this.instrumentId = instrumentId; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
  public Instant getCreatedAtUtc() { return createdAtUtc; }
  public void setCreatedAtUtc(Instant createdAtUtc) { this.createdAtUtc = createdAtUtc; }
}
