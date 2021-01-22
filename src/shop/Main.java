package shop;

public class Main {

    public static void main(String[] args) {
        Product cheese = new Product("Cheese");
        Product carrots = new Product("Carrots");
        Product sausage = new Product("Sausage");

        ShoppingCartItem test1 = new ShoppingCartItem(cheese, 499.50, 1);
        ShoppingCartItem test2 = new ShoppingCartItem(carrots, 12.99, 1);
        ShoppingCartItem test3 = new ShoppingCartItem(sausage, 45.00, 1);

        ShoppingCart cart = new ShoppingCart();

        cart.addCartItem(test1);
        cart.addCartItem(test2);
        cart.addCartItem(test3);

        System.out.println(cart.receipt());
    }
}
