public class Session {
    public static String currentUsername = null;
    public static String currentPassword = null;

    /**
     * Starts a new session. Sets the currentUsername and currentPassword to the given inputs.
     * @param username The username to use.
     * @param password The password to use.
     */
    public static void startSession(String username, String password) {
        currentUsername = username;
        currentPassword = password;
        System.out.println(currentUsername + " session started");
    }

    /**
     * Terminates the current session. Sets currentUsername and currentPassword to null.
     */
    public static void terminateSession() {
        currentUsername = null;
        currentPassword = null;
    }
}
