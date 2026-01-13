package store;

public class GoldCustomer extends Customer {
    public GoldCustomer(int id, String name) {
        super(id, name);
    }

    @Override
    public double discountRate() {
        return 0.10;
    }
}