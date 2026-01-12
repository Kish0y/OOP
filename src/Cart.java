package store;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void add(Product product, int qty) {
        if (product == null || qty <= 0) return;

        // если товар уже есть в корзине — увеличиваем количество
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.addQuantity(qty);
                return;
            }
        }

        items.add(new CartItem(product, qty));
    }

    public double total() {
        double sum = 0;
        for (CartItem item : items) {
            sum += item.getLineTotal();
        }
        return sum;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void show() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("=== CART ===");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.println("TOTAL = " + total());
    }

    public void clear() {
        items.clear();
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }
}