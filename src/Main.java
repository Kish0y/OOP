public class Main {
    public static void main(String[] args) {

        Store store = new Store();

        store.addProduct(new FoodProduct(1, "Milk", 450.0, 20, "2026-01-10"));
        store.addProduct(new FoodProduct(2, "Bread", 250.0, 30, "2025-12-31"));
        store.addProduct(new Product(3, "Soap", 300.0, 10));

        Customer customer = new GoldCustomer(102, "Bob", 15000);

        store.showProducts();

        Sale sale = new Sale(1001, customer, "2026-01-14");

        Product milk = store.findById(1);
        Product soap = store.findById(3);

        sale.addItem(milk, 2);
        sale.addItem(soap, 1);

        System.out.println();
        sale.printReceiptHeader();
        System.out.println("Subtotal: " + sale.getTotalAmount());
        System.out.println("Pay: " + sale.totalAfterDiscount());
        System.out.println("Large sale: " + sale.isLargeSale());

        System.out.println("\nExtra info:");
        sale.printProductExtra(milk);
        sale.printProductExtra(soap);

        System.out.println("\n" + sale);
    }
}