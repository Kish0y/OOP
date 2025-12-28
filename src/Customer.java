package store;

public class Customer {
    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;

    public Customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        this.customerId = customerId;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.totalPurchases = totalPurchases;
    }

    public Customer() {
        this(0, "Unknown Customer", "Regular", 0.0);
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getMembershipLevel() { return membershipLevel; }
    public double getTotalPurchases() { return totalPurchases; }


    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setName(String name) { this.name = name; }
    public void setMembershipLevel(String membershipLevel) { this.membershipLevel = membershipLevel; }
    public void setTotalPurchases(double totalPurchases) { this.totalPurchases = totalPurchases; }

    public void addPurchase(double amount) {
        if (amount > 0) totalPurchases += amount;
    }

    public boolean isVIP() {
        return totalPurchases > 10000;
    }

    public double getDiscountRate() {
        return 0.0;
    }

    @Override
    public String toString() {
        return customerId + ") " + name + " | level=" + membershipLevel + " | total=" + totalPurchases;
    }
}
