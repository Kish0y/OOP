package store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Store store = new Store();
        Cart cart = new Cart();

        store.addProduct(new Product(1, "Milk", 450, 10));
        store.addProduct(new Product(2, "Bread", 200, 15));
        store.addProduct(new Product(3, "Eggs", 800, 12));

        while (true) {
            System.out.println("\n1) Show products");
            System.out.println("2) Buy product");
            System.out.println("3) Show cart");
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

                System.out.print("Enter quantity: ");
                int qty = input.nextInt();

                Product p = store.findById(id);
                if (p == null) {
                    System.out.println("Not found ❌");
                } else if (p.take(qty)) {
                    cart.add(p, qty);
                    System.out.println("Added ✅");
                } else {
                    System.out.println("Not enough stock ❌");
                }
            } else if (choice == 3) {
                cart.show();
            } else {
                System.out.println("Wrong option");
            }
        }

        System.out.println("Bye!");
    }
}