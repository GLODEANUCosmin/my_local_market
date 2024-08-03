package com.local.market.my_local_market.model;

import java.util.List;

public class Stand {

    private Integer standID;
    private Integer providerID;
    private Integer marketID;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getStandID() {
        return standID;
    }

    public void setStandID(Integer standID) {
        this.standID = standID;
    }

    public Integer getProviderID() {
        return providerID;
    }

    public void setProviderID(Integer providerID) {
        this.providerID = providerID;
    }

    public Integer getMarketID() {
        return marketID;
    }

    public void setMarketID(Integer marketID) {
        this.marketID = marketID;
    }
}
