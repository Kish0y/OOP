package grocerystore.model;

public class GoldCustomer extends Customer {

    private int loyaltyPoints;

    public GoldCustomer(int customerId, String name, String phone, double balance, int loyaltyPoints) {
        super(customerId, name, phone, balance);
        setLoyaltyPoints(loyaltyPoints);
    }

    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints < 0) throw new IllegalArgumentException("Loyalty points cannot be negative");
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addPoints(int points) {
        if (points <= 0) throw new IllegalArgumentException("Points must be > 0");
        loyaltyPoints += points;
    }

    @Override
    public double getExtraDiscountPercent() {
        return 5.0;
    }

    @Override
    public String getType() {
        return "Gold";
    }

    @Override
    public String toString() {
        return super.toString() + ", points=" + loyaltyPoints;
    }
}