package grocerystore.menu;

import grocerystore.exception.InvalidInputException;
import grocerystore.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Store implements Menu {

    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Sale> sales = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public Store() {
        customers.add(new Customer(1, "Aidar", "+77011234567", 20000));
        customers.add(new GoldCustomer(2, "Asel", "+77012345678", 30000, 120));

        products.add(new FoodProduct(10, "Milk", 600, 30, "2026-02-01"));
        products.add(new FoodProduct(11, "Bread", 250, 50, "2026-01-28"));
        products.add(new FoodProduct(12, "Apples", 900, 15, "2026-02-10"));
        products.get(2).applyDiscount(10);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n==============================");
        System.out.println("     GROCERY STORE MENU");
        System.out.println("==============================");
        System.out.println("1. Add Food Product");
        System.out.println("2. View Products");
        System.out.println("3. Restock Product");
        System.out.println("4. Apply Discount to Product");
        System.out.println("5. Add Customer");
        System.out.println("6. View Customers (Polymorphism)");
        System.out.println("7. Make Sale");
        System.out.println("8. View Sales");
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
                    case 1 -> addFoodProduct();
                    case 2 -> viewProducts();
                    case 3 -> restock();
                    case 4 -> discount();
                    case 5 -> addCustomer();
                    case 6 -> viewCustomers();
                    case 7 -> makeSale();
                    case 8 -> viewSales();
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

    private void addFoodProduct() throws InvalidInputException {
        System.out.println("---ADD FOOD PRODUCT----");
        int id = readInt("Product id: ");
        String name = readNonEmpty("Name: ");
        double price= readDouble("Price: ");
        int stock = readInt("Stoak: ");
        String exp = readNonEmpty("Experation(YYYY-MM-DD) ");
        products.add(new FoodProduct(id, name, price, stock, exp));
        System.out.println("Added!");

    }

    private void viewProducts(){
        System.out.println("/n---Products---");
        if(products.isEmpty()){
            System.out.println("No products");
            return;
        }
        for(Product p:products) System.out.println(p);
    }

    private void restock() throws InvalidInputException {
        System.out.println("\n--- RESTOCK ---");
        int id = readInt("Product ID: ");
        Product p = findProduct(id);
        int amount = readInt("Amount: ");
        p.restock(amount);
        System.out.println("Updated: " + p);
    }

    private void discount() throws InvalidInputException {
        System.out.println("\n--- DISCOUNT ---");
        int id = readInt("Product ID: ");
        Product p = findProduct(id);
        double percent = readDouble("Discount percent: ");
        p.applyDiscount(percent);
        System.out.println("Updated: " + p);
    }

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
        Product product = findProduct(productId);

        if (!product.isInStock()) throw new IllegalArgumentException("Product out of stock");
        product.takeFromStock(qty);

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

    private Product findProduct(int id) {
        for (Product p : products) if (p.getProductId() == id) return p;
        throw new IllegalArgumentException("Product not found: " + id);
    }

    private Customer findCustomer(int id) {
        for (Customer c : customers) if (c.getCustomerId() == id) return c;
        throw new IllegalArgumentException("Customer not found: " + id);
    }

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