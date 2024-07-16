package com.local.market.my_local_market.exceptions;

public class MarketNotFoundException extends RuntimeException {
    public MarketNotFoundException(String format) {
        super(format);
    }
}
