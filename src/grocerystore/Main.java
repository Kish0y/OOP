package grocerystore;

import grocerystore.menu.Menu;
import grocerystore.menu.Store;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Store();
        menu.run();
    }
}