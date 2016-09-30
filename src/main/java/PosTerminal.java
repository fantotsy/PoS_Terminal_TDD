import products.Product;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PosTerminal {
    private static final Set<Integer> allowedCoins = new HashSet<Integer>() {{
        add(1);
        add(5);
        add(10);
        add(25);
        add(50);
    }};
    private Map<Product, Integer> order;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public PosTerminal() {
        order = new HashMap<Product, Integer>();
    }

    public Map<Product, Integer> getOrder() {
        return order;
    }

    public void insertCoin(int coin) {
        if (isCoinAllowed(coin)) {
            balance += coin;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addOrder(Product product) {
        boolean found = false;
        for (Map.Entry<Product, Integer> entry : order.entrySet()) {
            if (entry.getKey().getClass() == product.getClass()){
                Product existingProduct = entry.getKey();
                int amountOfProduct = entry.getValue();
                order.put(existingProduct, ++amountOfProduct);
                break;
            }
        }
        if(!found){
            order.put(product, 1);
        }
    }

    private boolean isCoinAllowed(int coin) {
        return allowedCoins.contains(coin);
    }
}