package login;

import java.util.*;

public class AccountManager {
    public static final String ATTRIBUTE_NAME = "Account Manager";
    private HashMap<String, String> db;

    public AccountManager() {
        db = new HashMap<String, String>();
        createNewAccount("Patrick", "1234");
        createNewAccount("Molly", "FloPup");
    }

    public boolean isUsernameTaken(String username) {
        return db.containsKey(username);
    }

    public boolean isCorrectPassword(String username, String password) {
        return isUsernameTaken(username) && db.get(username).equals(password);
    }

    public void createNewAccount(String username, String password) {
        db.put(username, password);
    }
}