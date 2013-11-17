package login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountTest {
	public static void main(String[] args) {
		AccountManager manager = new AccountManager();
		
		assertTrue(manager.isUsernameTaken("Patrick"));
        assertTrue(manager.isUsernameTaken("Molly"));
        assertFalse(manager.isUsernameTaken("patrick"));
        assertFalse(manager.isUsernameTaken("molly"));
        assertFalse(manager.isUsernameTaken("1234"));
        assertFalse(manager.isUsernameTaken("FloPup"));
        
        assertTrue(manager.isCorrectPassword("Patrick", "1234"));
        assertTrue(manager.isCorrectPassword("Molly", "FloPup"));
        assertFalse(manager.isCorrectPassword("Patrick", "234"));
        assertFalse(manager.isCorrectPassword("Molly", ""));
        assertFalse(manager.isCorrectPassword("Molly", "flopup"));
        
        manager.createNewAccount("George", "louis");
        assertTrue(manager.isUsernameTaken("George"));
        assertFalse(manager.isUsernameTaken("george"));
        assertTrue(manager.isCorrectPassword("George", "louis"));
        assertFalse(manager.isCorrectPassword("George", "LOUIS"));
        
        System.out.println("Done!");      
	}
}
