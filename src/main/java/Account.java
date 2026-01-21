
public class Account {
    private final String id;
    private final String password;
    private final String username;

    Account(String id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Account{" + "username='" + username + '\'' + ", id=" + id + '}';
    }
}
