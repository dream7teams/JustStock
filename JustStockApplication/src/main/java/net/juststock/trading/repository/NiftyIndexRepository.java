package net.juststock.trading.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.singnal.NiftyIndex;

public interface NiftyIndexRepository extends JpaRepository<NiftyIndex, Long> {
	Optional<NiftyIndex> findTopByOrderByTimestampDesc();
}