package com.local.market.my_local_market.model;

import org.springframework.lang.NonNull;

public class Provider extends User{


    private String codCui;
    private Float rating;

    public String getCodCui() {
        return codCui;
    }

    public void setCodCui(String codCui) {
        this.codCui = codCui;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
