public class Session {
    public static String currentUsername = null;
    public static String currentPassword = null;

    public static void startSession(String username, String password) {
        currentUsername = username;
        currentPassword = password;
        System.out.println(currentUsername + " session started");
    }

    public static void terminateSession() {
        if (currentUsername == null && currentPassword == null) {
            currentUsername = null;
            currentPassword = null;
            System.out.println("Session terminated");
        }else {
            System.out.println("No session is active");
        }
    }
}
