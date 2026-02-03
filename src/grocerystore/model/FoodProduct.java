package grocerystore.model;

public class FoodProduct extends Product {

    private String expirationDate; // "YYYY-MM-DD"

    public FoodProduct(int productId, String name, double price, int stockQuantity, String expirationDate) {
        super(productId, name, price, stockQuantity);
        setExpirationDate(expirationDate);
    }

    @Override
    public String getCategory() {
        return "Food";
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.trim().isEmpty())
            throw new IllegalArgumentException("Expiration date cannot be empty");
                         // минимальная проверка формата
        if (!expirationDate.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new IllegalArgumentException("Expiration date must be YYYY-MM-DD");
        this.expirationDate = expirationDate.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", exp=" + expirationDate;
    }
}