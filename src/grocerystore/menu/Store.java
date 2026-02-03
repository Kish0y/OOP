package grocerystore.menu;

import grocerystore.database.ProductDAO;
import grocerystore.exception.InvalidInputException;
import grocerystore.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store implements Menu {

                    //только через DAO
    private final ProductDAO productDAO = new ProductDAO();

    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Sale> sales = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public Store() {
        customers.add(new Customer(111, "Aidar", "+77011234567", 20000));
        customers.add(new GoldCustomer(211, "Asel", "+77012345678", 30000, 120));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n==============================");
        System.out.println("     GROCERY STORE MENU");
        System.out.println("==============================");
        System.out.println("1. Add Food Product (DB)");
        System.out.println("2. View Products (DB)");
        System.out.println("3. Update Product (DB)");
        System.out.println("4. Delete Product (DB)");
        System.out.println("5. Search Product by Name (DB)");
        System.out.println("6. Search Product by Price Range (DB)");
        System.out.println("7. Add Customer");
        System.out.println("8. View Customers (Polymorphism)");
        System.out.println("9. Make Sale (uses DB product)");
        System.out.println("10. View Sales");
        System.out.println("0. Exit");
        System.out.println("==============================");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            try {
                int choice = readInt("Enter choice: ");

                switch (choice) {
                    case 1 -> addFoodProductDB();
                    case 2 -> viewProductsDB();
                    case 3 -> updateProductDB();
                    case 4 -> deleteProductDB();
                    case 5 -> searchByNameDB();
                    case 6 -> searchByPriceRangeDB();
                    case 7 -> addCustomer();
                    case 8 -> viewCustomers();
                    case 9 -> makeSale();
                    case 10 -> viewSales();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }

            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Number format error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("Bye! Store closed.");
    }

                                       //DB Product actions

    private void addFoodProductDB() throws InvalidInputException {
        System.out.println("\n--- ADD FOOD PRODUCT (DB) ---");
        int id = readInt("Product ID: ");
        String name = readNonEmpty("Name: ");
        double price = readDouble("Price: ");
        int stock = readInt("Stock quantity: ");
        String exp = readNonEmpty("Expiration date (YYYY-MM-DD): ");

        Product p = new FoodProduct(id, name, price, stock, exp);

        boolean ok = productDAO.insertProduct(p);
        System.out.println(ok ? "Inserted ✅" : "Insert failed ❌");
    }

    private void viewProductsDB() {
        System.out.println("\n--- PRODUCTS (DB) ---");
        List<Product> list = productDAO.getAllProducts();
        if (list.isEmpty()) {
            System.out.println("No products in DB.");
            return;
        }
        list.forEach(System.out::println);
    }

    private void updateProductDB() throws InvalidInputException {
        System.out.println("\n--- UPDATE PRODUCT (DB) ---");
        int id = readInt("Product ID to update: ");

        Product current = productDAO.getProductById(id);
        if (current == null) {
            System.out.println("Product not found: " + id);
            return;
        }

                                     // contentReference[oaicite:3]{index=3}
        System.out.println("Current: " + current);

        String newName = readNonEmpty("New name: ");
        double newPrice = readDouble("New price: ");
        int newStock = readInt("New stock quantity: ");
        double newDiscount = readDouble("New discount percent (0..100): ");

        String newExp = null;
        if (current instanceof FoodProduct) {
            newExp = readNonEmpty("New expiration date (YYYY-MM-DD): ");
        }

        Product updated = new FoodProduct(id, newName, newPrice, newStock, newExp);
        if (newDiscount > 0) updated.applyDiscount(newDiscount);

        boolean ok = productDAO.updateProduct(updated);
        System.out.println(ok ? "Updated ✅" : "Update failed ❌");
    }

    private void deleteProductDB() throws InvalidInputException {
        System.out.println("\n--- DELETE PRODUCT (DB) ---");
        int id = readInt("Product ID to delete: ");

                                        // delete:contentReference[oaicite:4]{index=4}
        String confirm = readNonEmpty("Are you sure? type YES: ");
        if (!confirm.equalsIgnoreCase("YES")) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean ok = productDAO.deleteProduct(id);
        System.out.println(ok ? "Deleted ✅" : "Delete failed (maybe id not found) ❌");
    }

    private void searchByNameDB() throws InvalidInputException {
        System.out.println("\n--- SEARCH BY NAME (DB) ---");
        String keyword = readNonEmpty("Keyword: ");

        List<Product> list = productDAO.searchByName(keyword);
        if (list.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        list.forEach(System.out::println);
    }

    private void searchByPriceRangeDB() throws InvalidInputException {
        System.out.println("\n--- SEARCH BY PRICE RANGE (DB) ---");
        double min = readDouble("Min price: ");
        double max = readDouble("Max price: ");

        List<Product> list = productDAO.searchByPriceRange(min, max);
        if (list.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        list.forEach(System.out::println);
    }

                                        // Customers / Sales

    private void addCustomer() throws InvalidInputException {
        System.out.println("\n--- ADD CUSTOMER ---");
        int id = readInt("Customer ID: ");
        String name = readNonEmpty("Name: ");
        String phone = readNonEmpty("Phone: ");
        double balance = readDouble("Balance: ");
        String type = readNonEmpty("Type (regular/gold): ").toLowerCase();

        if (type.equals("gold")) {
            int points = readInt("Loyalty points: ");
            customers.add(new GoldCustomer(id, name, phone, balance, points));
        } else if (type.equals("regular")) {
            customers.add(new Customer(id, name, phone, balance));
        } else {
            throw new IllegalArgumentException("Type must be regular or gold");
        }

        System.out.println("Customer added!");
    }

    private void viewCustomers() {
        System.out.println("\n--- CUSTOMERS (POLYMORPHISM) ---");
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }

        for (Customer c : customers) {
            System.out.println(c);

            if (c instanceof GoldCustomer gc && gc.getLoyaltyPoints() >= 100) {
                System.out.println("   VIP Gold 👑");
            }
        }
    }

    private void makeSale() throws InvalidInputException {
        System.out.println("\n--- MAKE SALE ---");
        int customerId = readInt("Customer ID: ");
        int productId = readInt("Product ID: ");
        int qty = readInt("Quantity: ");

        Customer customer = findCustomer(customerId);

                                        // продукт берём из DB
        Product product = productDAO.getProductById(productId);
        if (product == null) throw new IllegalArgumentException("Product not found: " + productId);

        if (product.getStockQuantity() <= 0) throw new IllegalArgumentException("Product out of stock");
        if (qty > product.getStockQuantity()) throw new IllegalArgumentException("Not enough stock");

                               // уменьшаем stock + апдейтим в DB
        if (product instanceof FoodProduct fp) {
            FoodProduct updated = new FoodProduct(
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStockQuantity() - qty,
                    fp.getExpirationDate()
            );
            if (product.getDiscountPercent() > 0) updated.applyDiscount(product.getDiscountPercent());
            productDAO.updateProduct(updated);
            product = updated;
        }

        double unitFinal = product.getFinalPrice();
        double extraDisc = customer.getExtraDiscountPercent();
        double total = unitFinal * qty * (1 - extraDisc / 100.0);

        customer.pay(total);

        int saleId = sales.size() + 1;
        sales.add(new Sale(saleId, customerId, productId, qty, unitFinal, extraDisc, total, "Completed"));

        if (customer instanceof GoldCustomer gc) gc.addPoints((int) total / 1000);

        System.out.println("Sale completed! Total: " + String.format("%.2f", total) + " KZT");
    }

    private void viewSales() {
        System.out.println("\n--- SALES ---");
        if (sales.isEmpty()) {
            System.out.println("No sales.");
            return;
        }
        for (Sale s : sales) System.out.println(s);
    }

    private Customer findCustomer(int id) {
        for (Customer c : customers) if (c.getCustomerId() == id) return c;
        throw new IllegalArgumentException("Customer not found: " + id);
    }

                                       //input helpers

    private int readInt(String prompt) throws InvalidInputException {
        String s = readLine(prompt);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Expected integer, got: " + s);
        }
    }

    private double readDouble(String prompt) throws InvalidInputException {
        String s = readLine(prompt);
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Expected number, got: " + s);
        }
    }

    private String readNonEmpty(String prompt) throws InvalidInputException {
        String s = readLine(prompt);
        if (s.trim().isEmpty()) throw new InvalidInputException("Input cannot be empty");
        return s.trim();
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}