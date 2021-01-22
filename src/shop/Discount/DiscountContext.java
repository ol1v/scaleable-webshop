package shop.Discount;

import shop.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DiscountContext {
    private DiscountStrategy strategy;


    public ArrayList<ShoppingCartItem> items; // ShoppingCart items

    public DiscountContext(ArrayList<ShoppingCartItem> items) {
        this.items = items;
    }

    public BigDecimal checkDiscount(DiscountStrategy strategy) {
        this.strategy = strategy;
        BigDecimal sum = this.strategy.totalDiscount(items);

        return sum;
    }

    public DiscountStrategy choosenDiscount() {
        DiscountStrategy activeDiscount = new CheapestForFree();

        ArrayList<DiscountStrategy> discounts = new ArrayList<DiscountStrategy>();
        discounts.add(new ThreeForTwo());
        discounts.add(new CheapestForFree());
        discounts.add(new TenPercent());


        for(DiscountStrategy discount: discounts) {
            double totDiscount = discount.totalDiscount(items).doubleValue();

            if(activeDiscount.totalDiscount(items).doubleValue() > totDiscount) {
                activeDiscount = discount;
            }
        }

        return activeDiscount;
    }
}
