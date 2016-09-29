import org.junit.Before;
import org.junit.Test;

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
        org.junit.Assert.assertEquals(5, balance);
    }
}