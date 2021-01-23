package shop.Discount;

import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CheapestForFree implements DiscountStrategy{

    private String discountName = "Cheapest item for free";
    double lowest = 0;

    @Override
    public String getDiscountName() {
        return discountName;
    }

    @Override
    public BigDecimal totalDiscount(ArrayList<ShoppingCartItem> items) {
        var sum = BigDecimal.ZERO;
        for(var item: items) {
            if(items.size() > 3 && lowest == 0 || item.itemCost().doubleValue() < lowest) {
                lowest = item.itemCost().doubleValue();
            }
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }
        return sum.subtract((new BigDecimal(lowest)));
    }
}
