package shop;

import shop.Discount.DiscountContext;
import shop.Discount.DiscountStrategy;
import shop.History.HistoryStack;
import shop.History.HistoryState;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShoppingCart {

    private final ArrayList<ShoppingCartItem> items = new ArrayList<>();

    public void addCartItem(ShoppingCartItem item){
        items.add(item);
        addToStack(item);
    }

    public void addToStack(ShoppingCartItem item) {
        HistoryStack.addState(new HistoryState(() -> items.remove(item), () -> items.add(item)));
    }

    public BigDecimal calculatePrice(){

        DiscountContext discount = new DiscountContext(items);
        DiscountStrategy calcDiscount = discount.choosenDiscount();
        BigDecimal totalDiscount = discount.checkDiscount(calcDiscount);

        var sum = BigDecimal.ZERO;

        for (var item: items) {
            sum = item.itemCost().multiply(BigDecimal.valueOf(item.quantity())).add(sum);
        }
        return totalDiscount;
    }

    public void undo(HistoryStack stack){
        //Undo the latest change to the ShoppingCart
        stack.undoChanges();
    }


    public void redo(HistoryStack stack){
        //Redo the latest change to the ShoppingCart
        stack.undoChanges();
    }

    public String receipt() {
        String line = "--------------------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append(line);
        var list = items.stream()
                .sorted(Comparator.comparing(item -> item.product().name()))
                .collect(Collectors.toList());
        for (var each : list) {
            sb.append(String.format("%-24s % 7.2f %4d x\n", each.product().name(), each.itemCost(), each.quantity()));
        }
        sb.append(line);
        sb.append(String.format("%24s% 8.2f", "TOTAL:", calculatePrice()));
        return sb.toString();
    }
}