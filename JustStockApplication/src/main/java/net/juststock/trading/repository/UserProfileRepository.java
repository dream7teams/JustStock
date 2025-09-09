package net.juststock.trading.repository;

import net.juststock.trading.domain.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByContactNumber(String contactNumber);
}
