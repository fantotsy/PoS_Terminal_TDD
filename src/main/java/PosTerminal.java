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
        Map.Entry<Product, Integer> entry = getExistingProduct(product);
        if (entry != null) {
            int amountOfProduct = entry.getValue();
            entry.setValue(++amountOfProduct);
        } else {
            order.put(product, 1);
        }
    }

    public void cancelOrder(Product product) {
        Map.Entry<Product, Integer> entry = getExistingProduct(product);
        if (entry != null) {
            Product existingProduct = entry.getKey();
            int amountOfProduct = entry.getValue();
            if (amountOfProduct == 1) {
                order.remove(existingProduct);
            } else {
                entry.setValue(--amountOfProduct);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public int total() {
        int result = 0;
        for (Map.Entry<Product, Integer> entry : order.entrySet()) {
            result += entry.getKey().getPrice() * entry.getValue();
        }
        return result;
    }

    public boolean isEnoughBalanceToBuy() {
        int total = total();
        return (balance > total);
    }

    public Map.Entry<Product, Integer> getExistingProduct(Product product) {
        for (Map.Entry<Product, Integer> entry : order.entrySet()) {
            if (entry.getKey().getClass() == product.getClass()) {
                return entry;
            }
        }
        return null;
    }

    public void doTransaction() {
        if (isEnoughBalanceToBuy()) {
            balance -= total();
            order.clear();
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean isCoinAllowed(int coin) {
        return allowedCoins.contains(coin);
    }
}