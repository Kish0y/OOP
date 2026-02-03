package grocerystore.model;

public class SimpleProduct extends Product {

    private final String category;

    public SimpleProduct(int id, String name, double price, int stock, String category) {
        super(id, name, price, stock);
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }
}