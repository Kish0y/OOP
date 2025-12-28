package store;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void add(Product p, int qty) {
        if (p == null || qty <= 0) return;

        for (CartItem it : items) {
            if (it.getProduct().getId() == p.getId()) {
                it.addQty(qty);
                return;
            }
        }
        items.add(new CartItem(p, qty));
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public double total() {
        double sum = 0;
        for (CartItem it : items) sum += it.lineTotal();
        return sum;
    }

    public void clear() { items.clear(); }

    public void show() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (CartItem it : items) System.out.println(it);
        System.out.println("TOTAL = " + total());
    }
}