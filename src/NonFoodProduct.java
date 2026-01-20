public class NonFoodProduct extends Product {

    public NonFoodProduct(int productId, String name, double price, int stockQuantity) {
        super(productId, name, price, stockQuantity);
    }

    @Override
    public double finalPrice() {
        return getPrice();
    }
}