public class FoodProduct extends Product {

    private String expiryDate;

    public FoodProduct(int productId, String name, double price, int stockQuantity, String expiryDate) {
        super(productId, name, price, stockQuantity);
        setExpiryDate(expiryDate);
    }

    public FoodProduct() {
        super();
        this.expiryDate = "Not set";
    }

    public String getExpiryDate() { return expiryDate; }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate != null && !expiryDate.isEmpty()) this.expiryDate = expiryDate;
    }

    @Override
    public double finalPrice() {
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