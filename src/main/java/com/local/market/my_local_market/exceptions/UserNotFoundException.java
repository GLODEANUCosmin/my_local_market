package com.local.market.my_local_market.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String format) {
        super(format);
    }
}
