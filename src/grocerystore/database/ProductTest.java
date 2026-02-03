package grocerystore.database;

import grocerystore.model.FoodProduct;

public class ProductTest {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        FoodProduct milk = new FoodProduct(
                2,
                "Milk",
                600,
                20,
                "2026-01-01"
        );

        dao.insertProduct(milk);
    }
}