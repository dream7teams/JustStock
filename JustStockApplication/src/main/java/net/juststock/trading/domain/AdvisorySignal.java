package net.juststock.trading.domain;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "advisory_signals")
public class AdvisorySignal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "future_id")
    private Futures future;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Options option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    public enum Action { PUT, CALL }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4)
    private Action action;

    @Column(name = "entry_price", nullable = false, precision = 14, scale = 4)
    private BigDecimal entryPrice;

    @Column(name = "stop_loss", precision = 14, scale = 4)
    private BigDecimal stopLoss;

    @Column(name = "target_price", precision = 14, scale = 4)
    private BigDecimal targetPrice;

    @Column(length = 255)
    private String note;

    @Column(name = "created_at_utc", nullable = false, updatable = false)
    private Instant createdAtUtc = Instant.now();

    @Column(name = "closed_at_utc")
    private Instant closedAtUtc;

    // --- Constructors ---
    public AdvisorySignal() {}

    // convenience constructor
    public AdvisorySignal(Long createdBy, Action action, BigDecimal entryPrice) {
        this.createdBy = createdBy;
        this.action = action;
        this.entryPrice = entryPrice;
    }

    // --- Getters & setters ---
    public Long getId() { return id; }
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }

    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }

    public Futures getFuture() { return future; }
    public void setFuture(Futures future) { this.future = future; }

    public Options getOption() { return option; }
    public void setOption(Options option) { this.option = option; }

    public Commodity getCommodity() { return commodity; }
    public void setCommodity(Commodity commodity) { this.commodity = commodity; }

    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }

    public BigDecimal getEntryPrice() { return entryPrice; }
    public void setEntryPrice(BigDecimal entryPrice) { this.entryPrice = entryPrice; }

    public BigDecimal getStopLoss() { return stopLoss; }
    public void setStopLoss(BigDecimal stopLoss) { this.stopLoss = stopLoss; }

    public BigDecimal getTargetPrice() { return targetPrice; }
    public void setTargetPrice(BigDecimal targetPrice) { this.targetPrice = targetPrice; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Instant getCreatedAtUtc() { return createdAtUtc; }
    public void setCreatedAtUtc(Instant createdAtUtc) { this.createdAtUtc = createdAtUtc; }

    public Instant getClosedAtUtc() { return closedAtUtc; }
    public void setClosedAtUtc(Instant closedAtUtc) { this.closedAtUtc = closedAtUtc; }
}
