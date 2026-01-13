public class Main {
    public static void main(String[] args) {

        System.out.println("=== Grocery Store Management System ===\n");

        Product product1 = new FoodProduct(1, "Milk", 450.0, 20, "2026-01-10");
        Product product2 = new FoodProduct(2, "Bread", 250.0, 30, "2025-12-31");
        Product product3 = new Product();

        Customer customer1 = new Customer(101, "Alice", "Regular", 5000);
        Customer customer2 = new GoldCustomer(102, "Bob", 15000);
        Customer customer3 = new Customer();

        Store store = new Store();
        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);

        Sale sale1 = new Sale(1001, customer1.getName(), 0.0, "2025-09-10");

        System.out.println("--- PRODUCTS ---");
        store.showProducts();

        System.out.println("\n--- CUSTOMERS ---");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println(customer3);

        System.out.println("\n--- TESTING SETTERS ---");
        product3.setName("Apple");
        product3.setPrice(300.0);
        product3.setStockQuantity(50);
        System.out.println("Updated product3: " + product3);

        customer3.setName("Charles");
        customer3.setTotalPurchases(12000);
        System.out.println("Updated customer3: " + customer3);

        System.out.println("\n--- TESTING METHODS ---");
        System.out.println("Product1 in stock: " + product1.isInStock());
        product1.restock(10);
        System.out.println("Product1 after restock: " + product1);

        customer1.addPurchase(6000);
        System.out.println("Customer1 is VIP: " + customer1.isVIP());


        sale1.addItem(product1, 2);
        sale1.addItem(product2, 3);

        System.out.println("\n--- SALE ---");
        System.out.println(sale1);
        System.out.println("Large sale: " + sale1.isLargeSale());


        System.out.println("\n--- CHECK EXPIRY (instanceof + downcasting) ---");
        if (product1 instanceof FoodProduct) {
            FoodProduct fp = (FoodProduct) product1;
            System.out.println(fp.getName() + " expiry: " + fp.getExpiryDate());
        }

        System.out.println("\n=== Program Complete ===");
    }
}