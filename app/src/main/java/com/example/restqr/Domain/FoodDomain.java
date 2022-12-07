package com.example.restqr.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int quantity;

    public FoodDomain(String title, String pic, String description, Double price) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = price;
    }

    public FoodDomain(String title, String pic, String description, Double price, int quantity) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getQuantity() {
        return  quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
