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
        this.customerId = 0;
        this.name = "Unknown Customer";
        this.membershipLevel = "Regular";
        this.totalPurchases = 0.0;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getMembershipLevel() { return membershipLevel; }
    public double getTotalPurchases() { return totalPurchases; }

    public void setCustomerId(int customerId) {
        if (customerId > 0) this.customerId = customerId;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) this.name = name;
    }

    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel != null && !membershipLevel.isEmpty())
            this.membershipLevel = membershipLevel;
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
        return "Customer{customerId=" + customerId +
                ", name='" + name + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", totalPurchases=" + totalPurchases +
                ", discount=" + (discountRate() * 100) + "%" +
                '}';
    }
}