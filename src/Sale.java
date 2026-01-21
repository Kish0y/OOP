public class Sale {
    private int saleId;
    private int customerId;
    private int productId;
    private int quantity;

    private double unitFinalPrice;
    private double extraCustomerDiscountPercent;
    private double totalCost;

    private String status;                                   // Pending  Completed  Cancelled

    public Sale(int saleId, int customerId, int productId, int quantity,
                double unitFinalPrice, double extraCustomerDiscountPercent, double totalCost, String status) {
        setSaleId(saleId);
        setCustomerId(customerId);
        setProductId(productId);
        setQuantity(quantity);
        setUnitFinalPrice(unitFinalPrice);
        setExtraCustomerDiscountPercent(extraCustomerDiscountPercent);
        setTotalCost(totalCost);
        setStatus(status);
    }

    public int getSaleId() { return saleId; }
    public int getCustomerId() { return customerId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getUnitFinalPrice() { return unitFinalPrice; }
    public double getExtraCustomerDiscountPercent() { return extraCustomerDiscountPercent; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }

    public void setSaleId(int saleId) {
        if (saleId <= 0) throw new IllegalArgumentException("Sale ID must be positive");
        this.saleId = saleId;
    }

    public void setCustomerId(int customerId) {
        if (customerId <= 0) throw new IllegalArgumentException("Customer ID must be positive");
        this.customerId = customerId;
    }

    public void setProductId(int productId) {
        if (productId <= 0) throw new IllegalArgumentException("Product ID must be positive");
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.quantity = quantity;
    }

    public void setUnitFinalPrice(double unitFinalPrice) {
        if (unitFinalPrice < 0) throw new IllegalArgumentException("Unit price cannot be negative");
        this.unitFinalPrice = unitFinalPrice;
    }

    public void setExtraCustomerDiscountPercent(double p) {
        if (p < 0 || p > 100) throw new IllegalArgumentException("Customer discount must be in [0..100]");
        this.extraCustomerDiscountPercent = p;
    }

    public void setTotalCost(double totalCost) {
        if (totalCost < 0) throw new IllegalArgumentException("Total cost cannot be negative");
        this.totalCost = totalCost;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty())
            throw new IllegalArgumentException("Status cannot be empty");
        String s = status.trim();
        if (!s.equalsIgnoreCase("Pending") &&
                !s.equalsIgnoreCase("Completed") &&
                !s.equalsIgnoreCase("Cancelled")) {
            throw new IllegalArgumentException("Status must be Pending/Completed/Cancelled");
        }
        this.status = Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return "Sale{id=" + saleId +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", qty=" + quantity +
                ", unitFinal=" + String.format("%.2f", unitFinalPrice) +
                ", customerExtraDiscount=" + extraCustomerDiscountPercent + "%" +
                ", total=" + String.format("%.2f", totalCost) +
                ", status='" + status + "'}";
    }
}