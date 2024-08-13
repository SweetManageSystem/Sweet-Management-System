package org.example.Reciepes;

public class Product {
    private int id;
    private String name;
    private double price;
    private double originalPrice;
    private int sellCounter;

    public Product(int id, String name, double price, int sellCounter) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originalPrice = price;
        this.sellCounter = sellCounter;
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

    public void applyDiscount(double discountPercentage) {
        this.price = this.originalPrice - (this.originalPrice * discountPercentage / 100);
    }
}