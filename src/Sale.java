package store;

import java.util.ArrayList;

public class Sale {
    private ArrayList<String> lines = new ArrayList<>();
    private double total = 0;

    public void addItem(Product p, int qty) {
        if (p == null || qty <= 0) return;

        double line = p.finalPrice() * qty;
        total += line;
        lines.add(p.getName() + " x" + qty + " = " + line);
    }

    public boolean isEmpty() { return lines.isEmpty(); }

    public void printReceipt(Customer customer) {
        System.out.println("\n=== RECEIPT ===");
        for (String s : lines) System.out.println(s);

        System.out.println("SUBTOTAL = " + total);

        double discount = total * customer.discountRate();
        double pay = total - discount;

        System.out.println("Customer: " + customer.getName());
        System.out.println("Discount: " + (customer.discountRate() * 100) + "%");
        System.out.println("PAY = " + pay);

        lines.clear();
        total = 0;
    }
}