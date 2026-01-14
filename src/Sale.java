public class Sale {

    private int saleId;
    private Customer customer;
    private double totalAmount;
    private String date;

    public Sale(int saleId, Customer customer, String date) {
        this.saleId = saleId;
        this.customer = customer;
        this.date = date;
        this.totalAmount = 0.0;
    }

    public int getSaleId() { return saleId; }
    public Customer getCustomer() { return customer; }
    public double getTotalAmount() { return totalAmount; }
    public String getDate() { return date; }

    public void addItem(Product product, int qty) {
        if (product == null || qty <= 0) return;

        if (!product.takeFromStock(qty)) return;

        totalAmount += product.finalPrice() * qty;
    }

    public double totalAfterDiscount() {
        return totalAmount * (1 - customer.discountRate());
    }

    public boolean isLargeSale() {
        return totalAfterDiscount() > 5000;
    }

    public void printReceiptHeader() {
        System.out.println("=== RECEIPT ===");
        System.out.println("Sale ID: " + saleId);
        System.out.println("Customer: " + customer.getName() + " (" + customer.getMembershipLevel() + ")");
        System.out.println("Discount: " + (customer.discountRate() * 100) + "%");

        if (customer instanceof GoldCustomer) {
            GoldCustomer gc = (GoldCustomer) customer;
            System.out.println("Gold customer detected ✅ (rate=" + (gc.discountRate() * 100) + "%)");
        }
    }

    public void printProductExtra(Product p) {
        if (p instanceof FoodProduct) {
            FoodProduct fp = (FoodProduct) p;
            System.out.println("   (Food expiry: " + fp.getExpiryDate() + ")");
        }
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", customer=" + customer.getName() +
                ", subtotal=" + totalAmount +
                ", totalAfterDiscount=" + totalAfterDiscount() +
                ", date='" + date + '\'' +
                '}';
    }
}