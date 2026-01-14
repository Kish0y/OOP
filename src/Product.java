public class Product {

    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        setProductId(productId);
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
    }

    public Product() {
        this(0, "Unknown Product", 0.0, 0);
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public void setProductId(int productId) {
        if (productId >= 0) this.productId = productId;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) this.stockQuantity = stockQuantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public boolean takeFromStock(int qty) {
        if (qty <= 0 || qty > stockQuantity) return false;
        stockQuantity -= qty;
        return true;
    }

    public void restock(int amount) {
        if (amount > 0) stockQuantity += amount;
    }

    public double finalPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stockQuantity +
                ", finalPrice=" + finalPrice() +
                '}';
    }
}