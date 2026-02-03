package grocerystore.database;

import grocerystore.model.FoodProduct;
import grocerystore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

              //CREATE (INSERT)
    public boolean insertProduct(Product p) {
        String sql = """
                INSERT INTO products
                (product_id, name, price, stock_quantity, discount_percent, category, expiration_date)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            fillStatementFromProduct(ps, p);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
            return false;
        }
    }

                         //  READ (SELECT ALL)
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products ORDER BY product_id";

        List<Product> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            System.out.println("Select failed: " + e.getMessage());
        }
        return list;
    }

                 //  READ (SELECT BY ID)
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToProduct(rs);
            }

        } catch (SQLException e) {
            System.out.println("Select by id failed: " + e.getMessage());
        }
        return null;
    }

    // ---------- UPDATE (FULL UPDATE) ----------
    public boolean updateProduct(Product p) {
        String sql = """
                UPDATE products
                SET name = ?,
                    price = ?,
                    stock_quantity = ?,
                    discount_percent = ?,
                    category = ?,
                    expiration_date = ?
                WHERE product_id = ?
                """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

                           // UPDATE
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStockQuantity());
            ps.setDouble(4, p.getDiscountPercent());
            ps.setString(5, p.getCategory());

                                       // expiration_date может быть null
            if (p instanceof FoodProduct fp && fp.getExpirationDate() != null && !fp.getExpirationDate().isBlank()) {
                ps.setDate(6, Date.valueOf(fp.getExpirationDate()));
            } else {
                ps.setNull(6, Types.DATE);
            }

            ps.setInt(7, p.getProductId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

                      // DELETE
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, productId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

                            //SEARCH: by NAME
    public List<Product> searchByName(String keyword) {
        String sql = "SELECT * FROM products WHERE name ILIKE ? ORDER BY product_id";

        List<Product> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRowToProduct(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by name failed: " + e.getMessage());
        }
        return list;
    }

                      //  SEARCH: price BETWEEN
    public List<Product> searchByPriceRange(double min, double max) {
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ? ORDER BY price";

        List<Product> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRowToProduct(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by price range failed: " + e.getMessage());
        }
        return list;
    }

                        // SEARCH: price >= min
    public List<Product> searchByMinPrice(double min) {
        String sql = "SELECT * FROM products WHERE price >= ? ORDER BY price";

        List<Product> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, min);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRowToProduct(rs));
            }

        } catch (SQLException e) {
            System.out.println("Search by min price failed: " + e.getMessage());
        }
        return list;
    }

                       //  helpers

    private void fillStatementFromProduct(PreparedStatement ps, Product p) throws SQLException {
        ps.setInt(1, p.getProductId());
        ps.setString(2, p.getName());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getStockQuantity());
        ps.setDouble(5, p.getDiscountPercent());
        ps.setString(6, p.getCategory());

                            // expiration_date
        if (p instanceof FoodProduct fp && fp.getExpirationDate() != null && !fp.getExpirationDate().isBlank()) {
            ps.setDate(7, Date.valueOf(fp.getExpirationDate()));
        } else {
            ps.setNull(7, Types.DATE);
        }
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock_quantity");
        double discount = rs.getDouble("discount_percent");
        String category = rs.getString("category");
        Date exp = rs.getDate("expiration_date");


        String expStr = (exp == null) ? null : exp.toString();

        Product p;
        if ("Food".equalsIgnoreCase(category)) {
            p = new FoodProduct(id, name, price, stock, expStr);
        } else {

            p = new FoodProduct(id, name, price, stock, expStr);
        }

        if (discount > 0) p.applyDiscount(discount);
        return p;
    }
}
