package store;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
        this(0, "Unknown Product", 0.0, 0);
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    public boolean take(int qty) {
        if (qty <= 0 || stock < qty) return false;
        stock -= qty;
        return true;
    }

    public void restock(int qty) {
        if (qty > 0) stock += qty;
    }


    public double getFinalPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + ") " + name + " | price=" + price + " | stock=" + stock;
    }
}