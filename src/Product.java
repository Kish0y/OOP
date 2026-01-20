public abstract class Product {

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

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public void setProductId(int productId) {
        if (productId <= 0) throw new IllegalArgumentException("Product ID must be positive");
        this.productId = productId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty");
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stockQuantity = stockQuantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void restock(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Restock amount must be > 0");
        stockQuantity += amount;
    }

    public void takeFromStock(int qty) throws OutOfStockException {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (qty > stockQuantity) {
            throw new OutOfStockException("Not enough stock for product: " + name);
        }
        stockQuantity -= qty;
    }

    // POLYMORPHISM
    public abstract double finalPrice();

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