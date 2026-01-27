public class Session {
    public static String currentUsername = null;
    public static String currentPassword = null;

    public static void startSession(String username, String password) {
        currentUsername = username;
        currentPassword = password;
        System.out.println(currentUsername + " session started");
    }

    public static void terminateSession() {
        currentUsername = null;
        currentPassword = null;
    }
}
