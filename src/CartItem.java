package store;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        if (quantity >= 0) this.quantity = quantity;
    }

    public void addQuantity(int qty) {
        if (qty > 0) this.quantity += qty;
    }

    public double getLineTotal() {
        return product.finalPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " | lineTotal=" + getLineTotal();
    }
}
