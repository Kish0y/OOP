import java.util.ArrayList;

public class Sale {

    private final int saleId;
    private final Customer customer;             // UPCASTING
    private final String date;
    private double subTotal = 0.0;

    private final ArrayList<String> lines = new ArrayList<>();

    public Sale(int saleId, Customer customer, String date) {
        if (saleId <= 0) throw new IllegalArgumentException("Sale ID must be positive");
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        if (date == null || date.trim().isEmpty()) throw new IllegalArgumentException("Date cannot be empty");

        this.saleId = saleId;
        this.customer = customer;
        this.date = date.trim();
    }

    public double getSubTotal() { return subTotal; }

    public double totalAfterDiscount() {
                          // POLYMORPHISM
        return subTotal * (1 - customer.discountRate());
    }

    public void addItem(Product product, int qty) throws OutOfStockException {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        if (qty <= 0) throw new IllegalArgumentException("Qty must be > 0");


        product.takeFromStock(qty);

                       // POLYMORPHISM
        double linePrice = product.finalPrice() * qty;
        subTotal += linePrice;

                         // DOWNCASTING
        String extra = "";
        if (product instanceof FoodProduct) {
            FoodProduct fp = (FoodProduct) product; // downcasting
            extra = " (expiry " + fp.getExpiryDate() + ")";
        }

        lines.add(product.getName() + " x" + qty + " = " + linePrice + extra);
    }

    public void printReceipt() {
        System.out.println("\n=== RECEIPT ===");
        System.out.println("Sale ID: " + saleId);
        System.out.println("Date: " + date);
        System.out.println("Customer: " + customer.getName() + " (" + customer.getMembershipLevel() + ")");
        System.out.println("Discount: " + (customer.discountRate() * 100) + "%");
        System.out.println("--- Items ---");
        for (String s : lines) System.out.println(s);
        System.out.println("----------------");
        System.out.println("Subtotal: " + subTotal);
        System.out.println("Pay: " + totalAfterDiscount());
    }
}