public class GoldCustomer extends Customer {

    public GoldCustomer(int customerId, String name, double totalPurchases) {
        super(customerId, name, "Gold", totalPurchases);
    }

    public GoldCustomer() {
        super();
        setMembershipLevel("Gold");
    }

    @Override
    public double discountRate() {
        return 0.10; // 10%
    }
}