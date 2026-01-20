import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Store store = new Store();

        // стартовые данные
        store.addProduct(new FoodProduct(1, "Milk", 450.0, 20, "2026-01-10"));
        store.addProduct(new FoodProduct(2, "Bread", 250.0, 30, "2025-12-31"));
        store.addProduct(new NonFoodProduct(3, "Soap", 300.0, 10));

        store.addCustomer(new Customer(101, "Alice", "Regular", 5000));
        store.addCustomer(new GoldCustomer(102, "Bob", 15000));

        int choice;

        do {
            System.out.println("\n=== GROCERY STORE SYSTEM ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Add Customer");
            System.out.println("4. View All Customers");
            System.out.println("5. Make Sale");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = readInt(sc);

            try {
                switch (choice) {
                    case 1 -> addProductMenu(sc, store);
                    case 2 -> store.showProducts();
                    case 3 -> addCustomerMenu(sc, store);
                    case 4 -> store.showCustomers();
                    case 5 -> makeSaleMenu(sc, store);
                    case 0 -> System.out.println("Bye!");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Input error: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }

                                                 // Menus

    private static void addProductMenu(Scanner sc, Store store) {
        System.out.println("\nAdd Product:");
        System.out.println("1) Non-food product");
        System.out.println("2) Food product");
        System.out.print("Type: ");
        int type = readInt(sc);

        System.out.print("ID: ");
        int id = readInt(sc);

        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Price: ");
        double price = readDouble(sc);

        System.out.print("Stock: ");
        int stock = readInt(sc);

        if (type == 2) {
            System.out.print("Expiry date (YYYY-MM-DD): ");
            String expiry = sc.nextLine();
            store.addProduct(new FoodProduct(id, name, price, stock, expiry));
        } else {
            store.addProduct(new NonFoodProduct(id, name, price, stock));
        }

        System.out.println("✅ Product added.");
    }

    private static void addCustomerMenu(Scanner sc, Store store) {
        System.out.println("\nAdd Customer:");
        System.out.println("1) Regular customer");
        System.out.println("2) Gold customer");
        System.out.print("Type: ");
        int type = readInt(sc);

        System.out.print("Customer ID: ");
        int id = readInt(sc);

        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Total purchases: ");
        double total = readDouble(sc);

        if (type == 2) {
            store.addCustomer(new GoldCustomer(id, name, total));
        } else {
            store.addCustomer(new Customer(id, name, "Regular", total));
        }

        System.out.println("✅ Customer added.");
    }

    private static void makeSaleMenu(Scanner sc, Store store) {
        System.out.println("\nMake Sale:");

        System.out.print("Sale ID: ");
        int saleId = readInt(sc);

        System.out.print("Customer ID: ");
        int custId = readInt(sc);

        Customer customer = store.findCustomerById(custId);
        if (customer == null) {
            System.out.println("❌ Customer not found.");
            return;
        }

        System.out.print("Date (YYYY-MM-DD): ");
        sc.nextLine();
        String date = sc.nextLine();

        Sale sale = new Sale(saleId, customer, date);

        while (true) {
            store.showProducts();
            System.out.print("Enter product ID (0 to finish): ");
            int pid = readInt(sc);
            if (pid == 0) break;

            Product p = store.findProductById(pid);
            if (p == null) {
                System.out.println("❌ Product not found.");
                continue;
            }

            System.out.print("Quantity: ");
            int qty = readInt(sc);

            try {
                sale.addItem(p, qty);
                System.out.println("✅ Added.");
            } catch (OutOfStockException e) {
                System.out.println("❌ Stock error: " + e.getMessage());
            }
        }

        customer.addPurchase(sale.totalAfterDiscount());

        sale.printReceipt();
    }

                              // Safe input

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Enter a number (double): ");
            sc.next();
        }
        return sc.nextDouble();
    }
}