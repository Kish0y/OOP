package store;

import java.util.ArrayList;

public class Store {
    private final ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void showProducts() {
        if (products.isEmpty()) {
            System.out.println("No products.");
            return;
        }
        for (Product p : products) System.out.println(p);
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
