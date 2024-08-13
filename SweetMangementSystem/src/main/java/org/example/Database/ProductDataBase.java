package org.example.Database;

import org.example.Reciepes.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDataBase {
    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

    public static void editProduct(int id, String name, double price) {
        Product product = getProduct(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
        }
    }

    public static void removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return;
            }
        }
        System.err.println("Product with ID " + id + " not found.");
    }

    public static Product getBestSelling() {
        int most = 0;
        int best = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getSellCounter() > most) {
                most = products.get(i).getSellCounter();
                best = i;
            }
        }
        return products.get(best);
    }

    public static Product getProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static void initialProduct() {
        // Initialize products if needed
        Product p1 = new Product(1, "test1", 50.5, 10);
        Product p2 = new Product(2, "test2", 99.9, 6);
        Product p3 = new Product(3, "test3", 14.5, 25);
        Product p4 = new Product(4, "test4", 19.9, 23);
        Product p5 = new Product(5, "test5", 9.9, 18);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
    }

    public static void applyDiscountToAllProducts(double discountPercentage) {
        for (Product product : products) {
            product.applyDiscount(discountPercentage);
        }
    }

    public static void applyDiscountToBestSellingProduct(double discountPercentage) {
        Product bestSelling = getBestSelling();
        if (bestSelling != null) {
            bestSelling.applyDiscount(discountPercentage);
        }
    }

    public static void applyDiscountToPriceRange(double minPrice, double maxPrice, double discountPercentage) {
        for (Product product : products) {
            if (product.getOriginalPrice() >= minPrice && product.getOriginalPrice() <= maxPrice) {
                product.applyDiscount(discountPercentage);
            }
        }
    }
}