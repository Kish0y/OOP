public abstract class Product implements Discountable {
    protected int productId;
    protected String name;
    protected double price;
    protected int stockQuantity;

    protected double discountPercent;

    public Product(int productId, String name, double price, int stockQuantity) {
        setProductId(productId);
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        this.discountPercent = 0;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public double getDiscountPercent() { return discountPercent; }

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

    public void takeFromStock(int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (qty > stockQuantity) throw new IllegalArgumentException("Not enough stock");
        stockQuantity -= qty;
    }

    @Override
    public void applyDiscount(double percent) {
        if (percent <= 0 || percent > 100)
            throw new IllegalArgumentException("Discount must be in (0..100]");
        this.discountPercent = percent;
    }

    @Override
    public boolean hasDiscount() {
        return discountPercent > 0;
    }

    @Override
    public double getFinalPrice() {
        return price * (1 - discountPercent / 100.0);
    }

    public abstract String getCategory();

    @Override
    public String toString() {
        return "Product{id=" + productId +
                ", name='" + name + '\'' +
                ", category='" + getCategory() + '\'' +
                ", price=" + price +
                ", discount=" + discountPercent + "%" +
                ", final=" + String.format("%.2f", getFinalPrice()) +
                ", stock=" + stockQuantity + "}";
    }
}