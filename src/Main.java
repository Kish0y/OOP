package store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Store store = new Store();
        Cart cart = new Cart();

        // товары
        store.addProduct(new Product(1, "Milk", 450, 20));
        store.addProduct(new Product(2, "Bread", 250, 30));
        store.addProduct(new FoodProduct(3, "Eggs", 800, 12, "2026-01-10"));
        store.addProduct(new FoodProduct(4, "Apple", 300, 50, "2025-12-31"));

        // клиенты
        Customer regular = new Customer(101, "Alice", "Regular", 5000);
        Customer gold = new GoldCustomer(102, "Bob", 15000);

        // выбЫР клиента
        Customer currentCustomer = regular;
        System.out.println("Choose customer: 1) Alice (Regular)  2) Bob (Gold)");
        int c = input.nextInt();
        if (c == 2) currentCustomer = gold;

        Sale sale = new Sale(1001, currentCustomer.getName(), 0.0, "2025-09-10");

        while (true) {
            System.out.println("\n=== GROCERY STORE ===");
            System.out.println("1) Show products");
            System.out.println("2) Buy product");
            System.out.println("3) Show cart");
            System.out.println("4) Checkout (create sale)");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();

            if (choice == 0) {
                System.out.println("Bye!");
                break;
            }

            if (choice == 1) {
                store.showProducts();

            } else if (choice == 2) {
                store.showProducts();
                System.out.print("Enter product id: ");
                int id = input.nextInt();
                System.out.print("Enter quantity: ");
                int qty = input.nextInt();

                Product p = store.findById(id);
                if (p == null) {
                    System.out.println("Product not found ❌");
                } else if (p.take(qty)) {
                    cart.add(p, qty);
                    System.out.println("Added to cart ✅");
                } else {
                    System.out.println("Not enough stock ❌");
                }

            } else if (choice == 3) {
                cart.show();

            } else if (choice == 4) {
                if (cart.isEmpty()) {
                    System.out.println("Cart is empty ❌");
                } else {
                    sale.checkout(currentCustomer, cart);
                    System.out.println("Checkout done ✅");
                    System.out.println(sale);
                    System.out.println("Customer total purchases: " + currentCustomer.getTotalPurchases());
                }

            } else {
                System.out.println("Wrong option ❌");
            }
        }
    }
}
