package login;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AccountManagerTest {
	private AccountManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new AccountManager();
    }

    @Test
    public void isUsernameTakenTest(){
        assertTrue(manager.isUsernameTaken("Patrick"));
        assertTrue(manager.isUsernameTaken("Molly"));
        assertFalse(manager.isUsernameTaken("patrick"));
        assertFalse(manager.isUsernameTaken("molly"));
        assertFalse(manager.isUsernameTaken("1234"));
        assertFalse(manager.isUsernameTaken("FloPup"));
    }

    @Test
    public void isCorrectPasswordText(){
        assertTrue(manager.isCorrectPassword("Patrick", "1234"));
        assertTrue(manager.isCorrectPassword("Molly", "FloPup"));
        assertFalse(manager.isCorrectPassword("Patrick", "234"));
        assertFalse(manager.isCorrectPassword("Molly", ""));
        assertFalse(manager.isCorrectPassword("Molly", "flopup"));
    }

    @Test
    public void createNewAccountTest(){
        manager.createNewAccount("George", "louis");
        assertTrue(manager.isUsernameTaken("George"));
        assertFalse(manager.isUsernameTaken("george"));
        assertTrue(manager.isCorrectPassword("George", "louis"));
        assertFalse(manager.isCorrectPassword("George", "LOUIS"));
    }
}
