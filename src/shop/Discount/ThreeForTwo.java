package shop.Discount;

import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ThreeForTwo implements DiscountStrategy{
    @Override
    public String getDiscountName() {
        return null;
    }

    @Override
    public BigDecimal totalDiscount(ArrayList<ShoppingCartItem> items) {
        var sum = BigDecimal.ZERO;

        for (var item: items) {
            if(item.quantity() > 2 && item.quantity() < 4) {
                sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum).subtract(item.itemCost());
                
            } else {
                sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
            }
        }

        return sum;
    }
}
