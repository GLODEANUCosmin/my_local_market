package com.local.market.my_local_market.model;


public class User {

    protected Integer id;

    protected Float wallet;
    protected String name;

    protected String password;


    public Integer getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    public Float getWallet() {
        return this.wallet;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id= "+ this.id +
                " name= " + this.name +
                " wallet= " + this.wallet +
                " password= " + this.password +
                "}";
    }
}
