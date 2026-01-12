package store;

public class FoodProduct extends Product {
    private String expiryDate; // срок годности

    public FoodProduct(int id, String name, double price, int stock, String expiryDate) {
        super(id, name, price, stock);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public double finalPrice() {
        return getPrice() * 0.95;
    }

    @Override
    public String toString() {
        return super.toString() + " | expiry=" + expiryDate + " | finalPrice=" + finalPrice();
    }
}
