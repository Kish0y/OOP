public class Main {
    public static void main(String[] args) {

        System.out.println("=== Grocery Store Management System (Week 4 Full Demo) ===\n");

        Product product1 = new FoodProduct(1, "Milk", 450.0, 20, "2026-01-10");
        Product product2 = new FoodProduct(2, "Bread", 250.0, 30, "2025-12-31");


        Product product3 = new Product();
        product3.setProductId(3);
        product3.setName("Soap");
        product3.setPrice(300.0);
        product3.setStockQuantity(10);


        Customer customer1 = new Customer(101, "Alice", "Regular", 5000);
        Customer customer2 = new GoldCustomer(102, "Bob", 15000);

        System.out.println("--- PRODUCTS (polymorphism in finalPrice) ---");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        System.out.println("\n--- CUSTOMERS (polymorphism in discountRate) ---");
        System.out.println(customer1);
        System.out.println(customer2);


        System.out.println("\n--- POLYMORPHISM DEMO ---");
        System.out.println("Product1 finalPrice (FoodProduct override): " + product1.finalPrice());
        System.out.println("Product3 finalPrice (Product default):     " + product3.finalPrice());

        System.out.println("Customer1 discountRate (Customer default): " + (customer1.discountRate() * 100) + "%");
        System.out.println("Customer2 discountRate (Gold override):    " + (customer2.discountRate() * 100) + "%");


        Store store = new Store();
        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);

        System.out.println("\n--- STORE PRODUCTS ---");
        store.showProducts();

        Sale sale1 = new Sale(1001, customer1.getName(), 0.0, "2025-09-10");

        sale1.addItem(product1, 2);
        sale1.addItem(product2, 3);

        System.out.println("\n--- SALE ---");
        System.out.println(sale1);
        System.out.println("Large sale: " + sale1.isLargeSale());


        System.out.println("\n--- DOWNCASTING DEMO (Product -> FoodProduct) ---");
        if (product1 instanceof FoodProduct) {
            FoodProduct fp = (FoodProduct) product1;
            System.out.println(fp.getName() + " expiry: " + fp.getExpiryDate());
        } else {
            System.out.println("product1 is NOT FoodProduct");
        }

        System.out.println("\n--- DOWNCASTING DEMO (Customer -> GoldCustomer) ---");
        if (customer2 instanceof GoldCustomer) {
            GoldCustomer gc = (GoldCustomer) customer2;
            System.out.println("Customer2 is GoldCustomer, discount = " + (gc.discountRate() * 100) + "%");
        } else {
            System.out.println("customer2 is NOT GoldCustomer");
        }

        System.out.println("\n--- OVERRIDE PROOF ---");
        System.out.println("FoodProduct overrides finalPrice(), so Milk finalPrice = " + product1.finalPrice());
        System.out.println("GoldCustomer overrides discountRate(), so Bob discount = " + (customer2.discountRate() * 100) + "%");

        System.out.println("\n=== Program Complete ===");
    }
}