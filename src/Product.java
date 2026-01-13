package store;

public class Product {
    protected int id;
    protected String name;
    protected double price;
    protected int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public boolean take(int qty) {
        if (qty <= 0 || stock < qty) return false;
        stock -= qty;
        return true;
    }

    public double finalPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + ") " + name + " | price=" + price + " | stock=" + stock + " | final=" + finalPrice();
    }
}