package net.juststock.trading.domain.user;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.juststock.trading.domain.market.UserSignalHistory;
import net.juststock.trading.domain.payment.Payment;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_profile")

public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 15)
    private String contactNumber;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSignalHistory> signalHistories;

    // ✅ Add this constructor
    public UserProfile(Long id, String fullName, String contactNumber) {
        this.id = id;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
    }

    // Optional: constructor for just contactNumber
    public UserProfile(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Default constructor (required by JPA)
    public UserProfile() {}

}