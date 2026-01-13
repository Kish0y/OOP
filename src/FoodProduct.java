package store;

public class FoodProduct extends Product {
    private String expiryDate;

    public FoodProduct(int id, String name, double price, int stock, String expiryDate) {
        super(id, name, price, stock);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() { return expiryDate; }

    @Override
    public double finalPrice() {
        return price * 0.95;
    }
}