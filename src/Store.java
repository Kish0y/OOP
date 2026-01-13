package store;

import java.util.ArrayList;

public class Store {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) { products.add(p); }

    public void showProducts() {
        System.out.println("=== PRODUCTS ===");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}