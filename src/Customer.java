public class Customer {
    protected int customerId;
    protected String name;
    protected String phone;
    protected double balance;

    public Customer(int customerId, String name, String phone, double balance) {
        setCustomerId(customerId);
        setName(name);
        setPhone(phone);
        setBalance(balance);
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public double getBalance() { return balance; }

    public void setCustomerId(int customerId) {
        if (customerId <= 0) throw new IllegalArgumentException("Customer ID must be positive");
        this.customerId = customerId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty())
            throw new IllegalArgumentException("Phone cannot be empty");
        this.phone = phone.trim();
    }

    public void setBalance(double balance) {
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative");
        this.balance = balance;
    }

    public void pay(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (amount > balance) throw new IllegalArgumentException("Not enough money");
        balance -= amount;
    }

    public double getExtraDiscountPercent() {
        return 0;      // обычнй
    }

    public String getType() {
        return "Regular";
    }

    @Override
    public String toString() {
        return "Customer{id=" + customerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + String.format("%.2f", balance) +
                ", type=" + getType() + "}";
    }
}