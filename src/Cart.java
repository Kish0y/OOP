package store;

import java.util.ArrayList;

public class Cart {
    private final ArrayList<CartItem> items = new ArrayList<>();

    public void add(Product p, int qty) {
        if (qty <= 0) return;

        for (CartItem it : items) {
            if (it.getProduct().getId() == p.getId()) {
                it.addQty(qty);
                return;
            }
        }
        items.add(new CartItem(p, qty));
    }

    public void show() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        double total = 0;
        for (CartItem it : items) {
            System.out.println(it);
            total += it.sum();
        }
        System.out.println("TOTAL = " + total);
    }
}