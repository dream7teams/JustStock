package net.juststock.trading.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.juststock.trading.domain.singnal.Options;


public interface OptionChainRepository extends JpaRepository<Options, Long> {
    List<Options> findByUnderlyingAndExpiryDate(String underlying, LocalDate expiryDate);
}
