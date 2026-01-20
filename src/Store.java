import java.util.ArrayList;

public class Store {

    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();

    public void addProduct(Product p) {
        if (p == null) throw new IllegalArgumentException("Product cannot be null");
        products.add(p);
    }

    public void addCustomer(Customer c) {
        if (c == null) throw new IllegalArgumentException("Customer cannot be null");
        customers.add(c);
    }

    public void showProducts() {
        if (products.isEmpty()) {
            System.out.println("No products yet.");
            return;
        }
        System.out.println("=== PRODUCTS ===");
        for (Product p : products) System.out.println(p);
    }

    public void showCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers yet.");
            return;
        }
        System.out.println("=== CUSTOMERS ===");
        for (Customer c : customers) System.out.println(c);
    }

    public Product findProductById(int id) {
        for (Product p : products) if (p.getProductId() == id) return p;
        return null;
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) if (c.getCustomerId() == id) return c;
        return null;
    }
}