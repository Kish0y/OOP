package store;

public class CartItem {
    private final Product product;
    private int qty;

    public CartItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() { return product; }
    public int getQty() { return qty; }
    public void addQty(int add) { qty += add; }

    public double sum() {
        return product.getPrice() * qty;
    }

    public String toString() {
        return product.getName() + " x" + qty + " = " + sum();
    }
}