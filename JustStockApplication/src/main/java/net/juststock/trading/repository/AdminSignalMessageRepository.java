package net.juststock.trading.repository;

import net.juststock.trading.domain.admin.AdminSignalMessage;
import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminSignalMessageRepository extends JpaRepository<AdminSignalMessage, Long> {
    List<AdminSignalMessage> findByInstrumentType(InstrumentType instrumentType);
    Optional<AdminSignalMessage> findBySignalTypeAndInstrumentType(SignalType signalType, InstrumentType instrumentType);
}
