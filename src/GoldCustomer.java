package store;

public class GoldCustomer extends Customer {
    public GoldCustomer(int customerId, String name, double totalPurchases) {
        super(customerId, name, "Gold", totalPurchases);
    }

    @Override
    public double getDiscountRate() {
        return 0.10; // 10% скидка
    }

    @Override
    public String toString() {
        return super.toString() + " | discount=10%";
    }
}