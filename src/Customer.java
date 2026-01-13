package store;

public class Customer {
    protected int id;
    protected String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }

    public double discountRate() {
        return 0.0;
    }

    @Override
    public String toString() {
        return id + ") " + name + " | discount=" + (discountRate() * 100) + "%";
    }
}