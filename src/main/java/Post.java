public class Post {
    //Post class to store JSON data
    private final String id;
    private final String title;
    private final String username;
    private final String body;

    Post(String id, String title, String username, String body) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.body = body;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getUsername() {
        return username;
    }
    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" + "title='" + title + '\'' + ", username='" + username + '\'' + ", body='" + body + '\'' + ", id=" + id + '}';
    }
}
