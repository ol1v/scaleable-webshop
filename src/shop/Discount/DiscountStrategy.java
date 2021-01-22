package shop.Discount;

import shop.ShoppingCartItem;
import java.math.BigDecimal;
import java.util.ArrayList;


public interface DiscountStrategy {

    String getDiscountName();

    BigDecimal totalDiscount(ArrayList<ShoppingCartItem> items);
}
