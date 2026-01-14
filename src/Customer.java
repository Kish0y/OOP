public class Customer {

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

    public Customer() {
        this(0, "Unknown Customer", "Regular", 0.0);
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getMembershipLevel() { return membershipLevel; }
    public double getTotalPurchases() { return totalPurchases; }

    public void setCustomerId(int customerId) {
        if (customerId >= 0) this.customerId = customerId;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name.trim();
    }

    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel == null || membershipLevel.trim().isEmpty()) {
            this.membershipLevel = "Regular";
        } else {
            this.membershipLevel = membershipLevel.trim();
        }
    }

    public void setTotalPurchases(double totalPurchases) {
        if (totalPurchases >= 0) this.totalPurchases = totalPurchases;
    }

    public void addPurchase(double amount) {
        if (amount > 0) totalPurchases += amount;
    }

    public boolean isVIP() {
        return totalPurchases > 10000;
    }

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