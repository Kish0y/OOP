public interface Discountable {
    void applyDiscount(double percent);
    boolean hasDiscount();
    double getFinalPrice();
}