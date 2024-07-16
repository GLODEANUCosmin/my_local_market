package com.local.market.my_local_market.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String format) {
        super(format);
    }
}
