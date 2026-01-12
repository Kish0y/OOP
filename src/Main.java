package store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();

        Product p1 = new Product(1, "Bread", 250, 30);
        Product p2 = new FoodProduct(2, "Milk", 450, 20, "2026-01-10"); // upcast
        Product p3 = new FoodProduct(3, "Eggs", 800, 12, "2025-12-31");

        store.addProduct(p1);
        store.addProduct(p2);
        store.addProduct(p3);

        Customer regular = new Customer(101, "Alice", "Regular", 5000);
        Customer gold = new GoldCustomer(102, "Bob", 15000); // upcast в Customer работает

        Scanner input = new Scanner(System.in);

        System.out.println("Choose customer: 1) Alice  2) Bob(Gold)");
        int c = input.nextInt();
        Customer current = (c == 2) ? gold : regular;

        Sale sale = new Sale(1001);

        while (true) {
            System.out.println("\n=== STORE MENU ===");
            System.out.println("1) Show products");
            System.out.println("2) Buy");
            System.out.println("3) Checkout");
            System.out.println("4) Show extra info (Downcasting demo)");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();

            if (choice == 0) break;

            if (choice == 1) {
                store.showProducts();

            } else if (choice == 2) {
                store.showProducts();
                System.out.print("Enter product id: ");
                int id = input.nextInt();
                System.out.print("Enter qty: ");
                int qty = input.nextInt();

                Product prod = store.findById(id);
                if (prod == null) {
                    System.out.println("Not found ");
                } else if (prod.take(qty)) {
                    sale.addItem(prod, qty);
                    System.out.println("Added ");
                } else {
                    System.out.println("Not enough stock ");
                }

            } else if (choice == 3) {
                sale.checkout(current);
                System.out.println("CHECKOUT ");
                System.out.println(sale);
                System.out.println("Customer total purchases: " + current.getTotalPurchases());

            } else if (choice == 4) {
                // Downcasting demo (Week 5)
                System.out.print("Enter product id to check expiry: ");
                int id = input.nextInt();
                Product prod = store.findById(id);

                if (prod instanceof FoodProduct) {
                    FoodProduct food = (FoodProduct) prod; // downcast
                    System.out.println("Expiry date = " + food.getExpiryDate());
                } else {
                    System.out.println("This product has no expiry date.");
                }

            } else {
                System.out.println("Wrong option ");
            }
        }

        System.out.println("Bye!");
    }
}