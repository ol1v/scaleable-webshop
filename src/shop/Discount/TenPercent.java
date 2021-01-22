package shop.Discount;

import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TenPercent implements DiscountStrategy {

    private String discountName = "10% off";

    @Override
    public String getDiscountName() {
        return discountName;
    }

    @Override
    public BigDecimal totalDiscount(ArrayList<ShoppingCartItem> items) {
        var sum = BigDecimal.ZERO;

        for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }

        if (sum.doubleValue() > 500) {
            return sum.multiply(new BigDecimal(0.9));
        } else {
            return sum;
        }
    }
    }

