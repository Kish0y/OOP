import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Store Store = new Store();

        Store.addProduct(new FoodProduct(1, "Milk", 450.0, 20, "2026-01-10"));
        Store.addProduct(new FoodProduct(2, "Bread", 250.0, 30, "2025-12-31"));
        Store.addProduct(new Product(3, "Soap", 300.0, 10));

        int choice;

        do {
            System.out.println("=== GROCERY STORE SYSTEM ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Add Customer");
            System.out.println("4. View All Customers");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = readInt(sc);

            switch (choice) {
                case 1:
                    addProductMenu(sc, Store);
                    break;
                case 2:
                    Store.showProducts();
                    break;
                case 3:
                    addCustomerMenu(sc, Store);
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            System.out.println();

        } while (choice != 0);

        sc.close();
    }


    private static void addProductMenu(Scanner sc, Store store) {
        System.out.println("Add Product:");
        System.out.println("1) Normal Product");
        System.out.println("2) Food Product");
        System.out.print("Choose type: ");

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
            store.addProduct(new Product(id, name, price, stock));
        }

        System.out.println(" Product added.");
    }

    private static void addCustomerMenu(Scanner sc, Store store) {
        System.out.println("Add Customer:");
        System.out.println("1) Regular Customer");
        System.out.println("2) Gold Customer");
        System.out.print("Choose type: ");

        int type = readInt(sc);

        System.out.print("Customer ID: ");
        int id = readInt(sc);

        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Total purchases: ");
        double total = readDouble(sc);



        System.out.println(" Customer added.");
    }


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
