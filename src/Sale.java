package store;

public class Sale {
    private int saleId;
    private String customerName;
    private double totalAmount;

    public Sale(int saleId) {
        this.saleId = saleId;
        this.customerName = "Unknown";
        this.totalAmount = 0.0;
    }

    public double getTotalAmount() { return totalAmount; }

    public void addItem(Product product, int qty) {
        if (product == null || qty <= 0) return;
        totalAmount += product.finalPrice() * qty; // полиморфизм: finalPrice()
    }

    public void checkout(Customer customer) {
        customerName = customer.getName();
        double discount = totalAmount * customer.discountRate(); // полиморфизм: discountRate()
        totalAmount -= discount;
        customer.addPurchase(totalAmount);
    }
    public void checkout(Cart cart, Customer customer) {
        if (cart == null || cart.isEmpty() || customer == null) return;

        double amount = cart.total();
        double discount = amount * customer.discountRate(); // полиморфизм
        amount -= discount;

        totalAmount += amount;
        customerName = customer.getName();
        customer.addPurchase(amount);

        cart.clear();
    }

    @Override
    public String toString() {
        return "Sale#" + saleId + " | customer=" + customerName + " | total=" + totalAmount;
    }
}