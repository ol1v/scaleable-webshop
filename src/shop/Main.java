package shop;

import shop.History.HistoryStack;

public class Main {

    public static void main(String[] args) {
        Product cheese = new Product("Cheese");
        Product carrots = new Product("Carrots");
        Product sausage = new Product("Sausage");

        ShoppingCartItem test1 = new ShoppingCartItem(cheese, 499.50, 1);
        ShoppingCartItem test2 = new ShoppingCartItem(carrots, 12.99, 1);
        ShoppingCartItem test3 = new ShoppingCartItem(sausage, 45.00, 1);

        ShoppingCart cart = new ShoppingCart();

            /** ##############################################
             *  ##### Discount 10% if price is over 500 ######
             *  ############################################## **/

        cart.addCartItem(test1);
        cart.addCartItem(test2);
        cart.addCartItem(test3);

        System.out.println(cart.receipt());


            // Undo redo

        HistoryStack stack = new HistoryStack();

        cart.undo(stack);
        cart.redo(stack);
        cart.undo(stack);
        cart.undo(stack);
        cart.undo(stack);


        /** ##############################################
         *  ############# Discount 3 for 2 ###############
         *  ############################################## **/

        Product apple = new Product("Apple");
        ShoppingCartItem test4 = new ShoppingCartItem(apple, 10, 3 );

        cart.addCartItem(test4);

        System.out.println("\n\n\n NEW RECEIPT");
        System.out.println(cart.receipt());

        /** ##############################################
         *  ############# Cheapest item for free #########
         *  ############################################## **/

        cart.undo(stack); // removing apples

        cart.addCartItem(test2);
        cart.addCartItem(test3);
        cart.addCartItem(test3);
        cart.addCartItem(test2);

        System.out.println("\n\n\n NEW RECEIPT");
        System.out.println(cart.receipt());
    }
}
