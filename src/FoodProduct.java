package store;

public class FoodProduct extends Product {
    private String expiryDate;

    public FoodProduct(int id, String name, double price, int stock, String expiryDate) {
        super(id, name, price, stock);
        this.expiryDate = expiryDate;
    }

    public FoodProduct() {
        super();
        this.expiryDate = "Not set";
    }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public String toString() {
        return super.toString() + " | expiry=" + expiryDate;
    }
}