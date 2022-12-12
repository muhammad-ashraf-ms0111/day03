package Day3Workshop.src;

public class MultiUserShoppingCart {

    
    public static void main(String[] args) // entry point
    {
        ShoppingCartDB shoppingCartDB = new ShoppingCartDB("newCart"); // default folder: "db"
        shoppingCartDB.startShell();
    }
}
