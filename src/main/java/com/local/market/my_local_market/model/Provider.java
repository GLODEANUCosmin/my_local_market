package com.local.market.my_local_market.model;

import org.springframework.lang.NonNull;

public class Provider extends User{

    private String codCUI;
    private Integer rating;

    public String getCodCUI() {
        return codCUI;
    }

    public void setCodCUI(String codCUI) {
        this.codCUI = codCUI;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


}
