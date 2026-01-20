public class Customer implements Discountable {

    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;

    public Customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        setCustomerId(customerId);
        setName(name);
        setMembershipLevel(membershipLevel);
        setTotalPurchases(totalPurchases);
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getMembershipLevel() { return membershipLevel; }
    public double getTotalPurchases() { return totalPurchases; }

    public void setCustomerId(int customerId) {
        if (customerId <= 0) throw new IllegalArgumentException("Customer ID must be positive");
        this.customerId = customerId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = name.trim();
    }

    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel == null || membershipLevel.trim().isEmpty())
            throw new IllegalArgumentException("Membership level cannot be empty");
        this.membershipLevel = membershipLevel.trim();
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases < 0) throw new IllegalArgumentException("Total purchases cannot be negative");
        this.totalPurchases = totalPurchases;
    }

    public void addPurchase(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Purchase amount must be > 0");
        totalPurchases += amount;
    }

                    // POLYMORPHISM
    @Override
    public double discountRate() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", name='" + name + '\'' +
                ", level='" + membershipLevel + '\'' +
                ", totalPurchases=" + totalPurchases +
                ", discount=" + (discountRate() * 100) + "%" +
                '}';
    }
}