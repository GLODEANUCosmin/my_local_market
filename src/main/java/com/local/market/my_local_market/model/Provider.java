package com.local.market.my_local_market.model;

import org.springframework.lang.NonNull;

public class Provider extends User{


    private String codCui;

    public String getCodCui() {
        return codCui;
    }

    public void setCodCui(String codCui) {
        this.codCui = codCui;
    }
}
