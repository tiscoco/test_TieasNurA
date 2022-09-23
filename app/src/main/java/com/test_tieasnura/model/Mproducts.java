package com.test_tieasnura.model;

public class Mproducts {
    String product_name;
    String product_price;
    String product_desc;
    String product_rate;
    String product_image;

    public Mproducts(String product_name, String product_price, String product_desc, String product_rate, String product_image) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.product_rate = product_rate;
        this.product_image = product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public String getProduct_rate() {
        return product_rate;
    }

    public String getProduct_image() {
        return product_image;
    }
}
