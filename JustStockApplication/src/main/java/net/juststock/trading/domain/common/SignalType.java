package net.juststock.trading.domain.common;


public enum SignalType {

    /**
     * Call option signal — advice to buy a call option.
     */
    CALL,

    /**
     * Put option signal — advice to buy a put option.
     */
    PUT,

    /**
     * Futures contract signal — advice related to futures trading.
     */
    FUTURE


}
