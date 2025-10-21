package user;
import java.util.*;

public class User {
    private final Map<String, String> users = new HashMap<>();

    public void addUser(String login, String password) {
        if (users.containsKey(login)) {
            System.out.println(login + " already exists");
        }
        else {
            users.put(login, password);
        }
    }

    public void signIn(String login, String password) {
        if (users.containsKey(login) && users.get(login).equals(password)) {
            System.out.println(login + " successfully signed in");
        } else {
            System.out.println(login + " not found");
        }
    }

}

