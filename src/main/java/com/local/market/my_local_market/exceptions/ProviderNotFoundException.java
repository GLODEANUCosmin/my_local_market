package com.local.market.my_local_market.exceptions;

public class ProviderNotFoundException extends RuntimeException{

    public ProviderNotFoundException(String format) {
        super(format);
    }
}
