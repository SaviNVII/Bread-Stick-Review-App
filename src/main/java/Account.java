import java.util.ArrayList;

public class Account {
    String username;
    String password;
    public ArrayList<String> posts = new ArrayList<>();

    Account() {}

    public void savePost(String post) {
        posts.add(post);
    }

}
