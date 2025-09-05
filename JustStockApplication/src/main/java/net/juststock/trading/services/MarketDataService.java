package net.juststock.trading.services;

import java.time.LocalDate;
import java.util.List;

import net.juststock.trading.domain.singnal.NiftyIndex;
import net.juststock.trading.domain.singnal.Options;

public interface MarketDataService {
    NiftyIndex getLatestNiftyIndex();
    List<Options> getOptionChain(String underlying, LocalDate expiryDate);
    Options saveOptionChain(Options optionChain);
}