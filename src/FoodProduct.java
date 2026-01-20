public class FoodProduct extends Product {

    private String expiryDate;

    public FoodProduct(int productId, String name, double price, int stockQuantity, String expiryDate) {
        super(productId, name, price, stockQuantity);                    // super
        setExpiryDate(expiryDate);
    }

    public String getExpiryDate() { return expiryDate; }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.trim().isEmpty())
            throw new IllegalArgumentException("Expiry date cannot be empty");
        this.expiryDate = expiryDate.trim();
    }

    @Override
    public double finalPrice() {            // override
        return getPrice() * 0.95;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "id=" + getProductId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", stock=" + getStockQuantity() +
                ", expiryDate='" + expiryDate + '\'' +
                ", finalPrice=" + finalPrice() +
                '}';
    }
}