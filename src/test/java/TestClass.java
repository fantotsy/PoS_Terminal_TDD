import org.junit.Before;
import org.junit.Test;
import products.Coffee;
import products.Tea;

import static org.junit.Assert.assertEquals;

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
    public void testAddTwoSameOrders(){
        Tea tea1 = new Tea();
        Tea tea2 = new Tea();
        terminal.addOrder(tea1);
        terminal.addOrder(tea2);
        int teaQuantity = terminal.getOrder().get(tea1);
        assertEquals(2, teaQuantity);
    }

    @Test
    public void testAddTwoDifferentProducts(){
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
    public void testEnoughBalanceToBuy(){
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
    public void testNotEnoughBalanceToBuy(){
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


}