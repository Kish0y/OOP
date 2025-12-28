package store;

public class Sale {
    private int saleId;
    private String customerName;
    private double totalAmount;
    private String date;

    public Sale(int saleId, String customerName, double totalAmount, String date) {
        this.saleId = saleId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Sale() {
        this(0, "Unknown", 0.0, "Not set");
    }

    public String getDate() { return date; }

    public void checkout(Customer customer, Cart cart) {
        if (customer == null || cart == null || cart.isEmpty()) return;

        customerName = customer.getName();

        double sum = cart.total();
        double discount = sum * customer.getDiscountRate();
        totalAmount = sum - discount;

        customer.addPurchase(totalAmount);
        cart.clear();
    }

    public boolean isLargeSale() {
        return totalAmount > 5000;
    }

    @Override
    public String toString() {
        return "Sale#" + saleId + " | customer=" + customerName + " | total=" + totalAmount + " | date=" + date;
    }
}