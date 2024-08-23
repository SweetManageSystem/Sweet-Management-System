package org.example.reciepes;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private String name;
    private double price;
    private double originalPrice;
    private int sellCounter;
    private List<String> tags = new ArrayList<>();

    public Product(int id, String name, double price, int sellCounter) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originalPrice = price;
        this.sellCounter = sellCounter;
    }

    public void setTags(String[] tags){
        for(String tag : tags){
            this.tags.add(tag);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getSellCounter() {
        return sellCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setSellCounter(int sellCounter) {
        this.sellCounter = sellCounter;
    }

    public void applyDiscount(double discountPercentage) {
        this.price = this.originalPrice - (this.originalPrice * discountPercentage / 100);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}