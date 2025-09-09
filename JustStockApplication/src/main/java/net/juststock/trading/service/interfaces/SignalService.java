package net.juststock.trading.service.interfaces;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import net.juststock.trading.dto.AdviceResponseDTO;

import java.util.List;

public interface SignalService {
    List<String> availableSignalTypes(InstrumentType instrumentType);
    AdviceResponseDTO getAdviceAfterPayment(Long userId, InstrumentType instrumentType, SignalType signalType);
}
