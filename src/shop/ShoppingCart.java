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
    DiscountContext discount = new DiscountContext(items);

    public void addCartItem(ShoppingCartItem item){
        items.add(item);
        addToStack(item);
    }

    public void addToStack(ShoppingCartItem item) {
        HistoryStack.addState(new HistoryState(() -> items.remove(item), () -> items.add(item)));
    }

    public BigDecimal calculatePrice(){
        DiscountStrategy calcDiscount = discount.choosenDiscount();

        return discount.checkDiscount(calcDiscount);
    }

    public void undo(HistoryStack stack){
        //Undo the latest change to the ShoppingCart
        stack.undoChanges();
    }


    public void redo(HistoryStack stack){
        //Redo the latest change to the ShoppingCart
        stack.redoChanges();
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
        sb.append(String.format("\n%24s", "DISCOUNT:" + discount.getName()));
        return sb.toString();
    }
}