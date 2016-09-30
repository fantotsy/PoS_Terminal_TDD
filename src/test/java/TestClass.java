import org.junit.Before;
import org.junit.Test;
import products.Coffee;
import products.Tea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestClass {
    PosTerminal terminal;

    @Before
    public void initializeComponents() {
        terminal = new PosTerminal();
    }

    @Test
    public void testAllowedCoinInsertion() {
        terminal.insertCoin(5);
        int balance = terminal.getBalance();
        assertEquals(5, balance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotAllowedCoinInsertion() {
        terminal.insertCoin(3);
    }

    @Test
    public void testAddOneOrder() {
        Tea tea = new Tea();
        terminal.addOrder(tea);
        int teaQuantity = terminal.getOrder().get(tea);
        assertEquals(1, teaQuantity);
    }

    @Test
    public void testAddTwoSameOrders() {
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        int teaQuantity = terminal.getOrder().get(tea1);
        assertEquals(2, teaQuantity);
    }

    @Test
    public void testAddTwoDifferentProducts() {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        terminal.addOrder(tea);
        terminal.addOrder(coffee);
        int teaQuantity = terminal.getOrder().get(tea);
        assertEquals(1, teaQuantity);
        int coffeeQuantity = terminal.getOrder().get(coffee);
        assertEquals(1, coffeeQuantity);
    }

    @Test
    public void testEnoughBalanceToBuy() {
        terminal.insertCoin(25);
        terminal.insertCoin(25);
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        Coffee coffee = new Coffee();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        terminal.addOrder(coffee);
        boolean isEnoughBalanceToBuy = terminal.isEnoughBalanceToBuy();
        assertEquals(true, isEnoughBalanceToBuy);
    }

    @Test
    public void testNotEnoughBalanceToBuy() {
        terminal.insertCoin(25);
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        Coffee coffee = new Coffee();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        terminal.addOrder(coffee);
        boolean isEnoughBalanceToBuy = terminal.isEnoughBalanceToBuy();
        assertEquals(false, isEnoughBalanceToBuy);
    }

    @Test
    public void testDoTransaction() {
        terminal.insertCoin(25);
        terminal.insertCoin(25);
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        Coffee coffee = new Coffee();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        terminal.addOrder(coffee);
        terminal.doTransaction();
        assertEquals(10, terminal.getBalance());
    }

    @Test(expected = IllegalStateException.class)
    public void testDoImpossibleTransaction() {
        terminal.insertCoin(25);
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        Coffee coffee = new Coffee();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        terminal.addOrder(coffee);
        terminal.doTransaction();
    }

    @Test
    public void testCancelOrderFromSingle() {
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        terminal.addOrder(tea1);
        terminal.cancelOrder(tea2);
        assertTrue(terminal.getOrder().isEmpty());
    }

    @Test
    public void testCancelOrderFromMultiple() {
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        Tea tea3 = new Tea();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        terminal.cancelOrder(tea3);
        assertTrue(!terminal.getOrder().isEmpty());
        int amountOfProduct = terminal.getExistingProduct(tea1).getValue();
        assertEquals(1, amountOfProduct);
    }

}