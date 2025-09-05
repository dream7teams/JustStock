package net.juststock.trading.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.juststock.trading.domain.singnal.NiftyIndex;
import net.juststock.trading.domain.singnal.OptionStrike;
import net.juststock.trading.domain.singnal.Options;
import net.juststock.trading.repository.NiftyIndexRepository;
import net.juststock.trading.repository.OptionChainRepository;
import net.juststock.trading.services.MarketDataService;

@Service
public class MarketDataServiceImpl implements MarketDataService {

    private final NiftyIndexRepository niftyIndexRepository;
    private final OptionChainRepository optionChainRepository;

    public MarketDataServiceImpl(NiftyIndexRepository niftyIndexRepository,
                                 OptionChainRepository optionChainRepository) {
        this.niftyIndexRepository = niftyIndexRepository;
        this.optionChainRepository = optionChainRepository;
    }

    @Override
    public NiftyIndex getLatestNiftyIndex() {
        return niftyIndexRepository.findTopByOrderByTimestampDesc()
                .orElseThrow(() -> new EntityNotFoundException("Nifty index data not found"));
    }

    @Override
    public List<Options> getOptionChain(String underlying, LocalDate expiryDate) {
        return optionChainRepository.findByUnderlyingAndExpiryDate(underlying, expiryDate);
    }

    
    
    @Override
    public Options saveOptionChain(Options optionChain) {
        for (OptionStrike strike : optionChain.getOptions()) {
            strike.setOptionChain(optionChain); // maintain bidirectional link
        }
        return optionChainRepository.save(optionChain);
    }
}