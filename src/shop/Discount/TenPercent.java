package shop.Discount;

import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TenPercent implements DiscountStrategy {
    @Override
    public String getDiscountName() {
        return null;
    }

    @Override
    public BigDecimal totalDiscount(ArrayList<ShoppingCartItem> items) {
        return null;
    }
}
