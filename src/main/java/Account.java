import java.util.ArrayList;

public class Account {
    String username;
    String password;
    public ArrayList<String> posts = new ArrayList<>();

    Account() {}

    public void startSession(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void terminateSession() {
        username = null;
        password = null;
    }

    public void savePost(String post) {
        posts.add(post);
    }

}
