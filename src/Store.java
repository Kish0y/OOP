import java.util.ArrayList;

public class Store {

    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        if (p != null) products.add(p);
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) return p;
        }
        return null;
    }

    public void showProducts() {
        System.out.println("=== STORE PRODUCTS ===");
        for (Product p : products) System.out.println(p);
    }
}