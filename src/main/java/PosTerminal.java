import java.util.HashSet;
import java.util.Set;

public class PosTerminal {
    private static final Set<Integer> allowedCoins = new HashSet<Integer>() {{
        add(1);
        add(5);
        add(10);
        add(25);
        add(50);
    }};
    private int balance;

    public int getBalance() {
        return balance;
    }

    public PosTerminal() {

    }

    public void insertCoin(int coin) {
        if (isCoinAllowed(coin)) {
            balance += coin;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addOrder(Product product) {

    }

    private boolean isCoinAllowed(int coin) {
        return allowedCoins.contains(coin);
    }
}