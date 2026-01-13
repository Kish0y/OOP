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
        this.saleId = 0;
        this.customerName = "Unknown";
        this.totalAmount = 0.0;
        this.date = "Not set";
    }

    public int getSaleId() { return saleId; }
    public String getCustomerName() { return customerName; }
    public double getTotalAmount() { return totalAmount; }
    public String getDate() { return date; }

    public void setSaleId(int saleId) { if (saleId > 0) this.saleId = saleId; }
    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.isEmpty()) this.customerName = customerName;
    }
    public void setTotalAmount(double totalAmount) { if (totalAmount >= 0) this.totalAmount = totalAmount; }
    public void setDate(String date) { this.date = date; }

    public void addItem(Product product, int qty) {
        if (product == null || qty <= 0) return;

        if (product.takeFromStock(qty)) {
            totalAmount += product.finalPrice() * qty; // polymorphism
        }
    }

    public boolean isLargeSale() {
        return totalAmount > 5000;
    }

    @Override
    public String toString() {
        return "Sale{saleId=" + saleId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", date='" + date + '\'' +
                '}';
    }
}}