package grocerystore.database;

import java.sql.Connection;

public class DbTest {
    public static void main(String[] args) {
        try (Connection c = DatabaseConnection.getConnection()) {
            System.out.println("CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}