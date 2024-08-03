package com.local.market.my_local_market.model;

public class Product {
    private Integer productID;
    private Integer providerID;
    private Integer standID;
    private Float price;
    private Integer amount;
    private String name;
    private Type tip;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Integer getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public Integer getStandID() {
        return standID;
    }

    public void setStandID(int standID) {
        this.standID = standID;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getTip() {
        return tip;
    }

    public void setTip(Type tip) {
        this.tip = tip;
    }

    public String getTipString(){
        return Type.typeToString(this.tip);
    }
    public void setTipString(String tip){
        setTip(Type.stringToType(tip));
    }
}
