package store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();
        Sale sale = new Sale();

        store.addProduct(new Product(1, "Soap", 300, 10));
        store.addProduct(new FoodProduct(2, "Milk", 450, 20, "2026-01-10"));
        store.addProduct(new FoodProduct(3, "Bread", 250, 30, "2025-12-31"));
        store.addProduct(new Product(4, "Shampoo", 1200, 8));


        Customer regular = new Customer(101, "Alice");
        Customer gold = new GoldCustomer(102, "Bob");

        Scanner input = new Scanner(System.in);

        System.out.println("Choose customer: 1) Alice  2) Bob(Gold)");
        int c = input.nextInt();
        Customer current = (c == 2) ? gold : regular;

        while (true) {
            System.out.println("\n=== WEEK 4 SUPERMARKET ===");
            System.out.println("1) Show products (polymorphism)");
            System.out.println("2) Buy (uses overridden finalPrice)");
            System.out.println("3) Checkout (uses overridden discountRate)");
            System.out.println("4) Check expiry (instanceof + downcasting)");
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

                Product p = store.findById(id);
                if (p == null) {
                    System.out.println("Not found ❌");
                } else if (p.take(qty)) {
                    sale.addItem(p, qty);
                    System.out.println("Added ✅ (final price used: " + p.finalPrice() + ")");
                } else {
                    System.out.println("Not enough stock ❌");
                }

            } else if (choice == 3) {
                if (sale.isEmpty()) System.out.println("Nothing to checkout.");
                else sale.printReceipt(current);

            } else if (choice == 4) {
                System.out.print("Enter product id: ");
                int id = input.nextInt();
                Product p = store.findById(id);

                if (p == null) {
                    System.out.println("Not found ❌");
                } else if (p instanceof FoodProduct) {
                    FoodProduct fp = (FoodProduct) p;
                    System.out.println("Expiry date: " + fp.getExpiryDate());
                } else {
                    System.out.println("This product has no expiry date.");
                }

            } else {
                System.out.println("Wrong option ❌");
            }
        }

        System.out.println("Bye!");
    }
}