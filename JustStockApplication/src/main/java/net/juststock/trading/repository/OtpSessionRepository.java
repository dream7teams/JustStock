package net.juststock.trading.repository;

import net.juststock.trading.domain.auth.OtpSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpSessionRepository extends JpaRepository<OtpSession, Long> {
    Optional<OtpSession> findTopByMobileNumberOrderByExpiresAtDesc(String mobileNumber);
}