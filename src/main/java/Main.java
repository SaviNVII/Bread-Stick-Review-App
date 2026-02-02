import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Main {
    public static GUI gui = new GUI();

    public static String accountsUrl = "https://7pivrgulp3.execute-api.us-east-2.amazonaws.com/production/accounts";
    public static String postsUrl = "https://s4rcckro2g.execute-api.us-east-2.amazonaws.com/production/posts";

    public static void main(String[] args) {
    }

    /**
     * Makes a get request to the account database for a list of the accounts.
     * @return  A list of the accounts.
     * @throws IOException If there is an input/output error.
     * @throws InterruptedException If the request is interrupted.
     */
    public static List<Account> getAccounts() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(accountsUrl)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Account>>() {}.getType();
            return gson.fromJson(response.body(), listType);
        } else {
            System.out.println("GET request failed: " + response.statusCode());
            return null;
        }
    }

    /**
     * Checks for a username in the database.
     * Calls {@link Main#getAccounts()} to get a list of accounts to check from.
     * @param username The username to check.
     * @return True if the username is in the database, false if it is not.
     * @throws IOException If there is an error with input/output.
     * @throws InterruptedException If the request called is interrupted.
     */
    public static boolean checkUsername(String username) throws IOException, InterruptedException {
        List<Account> accounts = getAccounts();

        for(int i = 0; i < Objects.requireNonNull(accounts).size(); i++) {
            Account accountAtIndex = accounts.get(i);
            if (username.equals(accountAtIndex.getUsername())) return true;
        }
        return false;
    }

    /**
     * Checks the password under a given username and compares it to a given password.
     * Calls {@link Main#getAccounts()} to get a list of accounts to check from.
     * Calls {@link Hasher#verifyPassword(String, String)} to check if the password is correct.
     * @param username The username to check.
     * @param password The password to compare.
     * @return True if the passwords match, false if they don't.
     * @throws Exception If an error occurs in the process.
     */
    public static boolean checkPassword(String username, String password) throws Exception {
        List<Account> accounts = getAccounts();

        for(int i = 0; i < Objects.requireNonNull(accounts).size(); i++) {
            Account accountAtIndex = accounts.get(i);
            if (username.equals(accountAtIndex.getUsername())
            && Hasher.verifyPassword(password, accountAtIndex.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Terminates the current session if one is active.
     * Calls {@link Session#terminateSession()} to terminate the session.
     */
    public static void logout() {
        if (Session.currentUsername == null || Session.currentPassword == null) {
            gui.displayMessage("No session is active");
        } else {
            gui.displayMessage("Session terminated");
        }
        Session.terminateSession();
    }

    /**
     * Calls {@link Main#checkPassword(String, String)}
     * and starts a new session if it returns true, does nothing if it returns false.
     * @param username The username to use.
     * @param password The password to use.
     * @throws Exception If an error occurs in the process.
     */
    public static void login(String username, String password) throws Exception {
        if (checkPassword(username, password)) {
            Session.startSession(username, password);
            gui.displayMessage("Logged in as " + username);
        } else {
            gui.displayMessage("Invalid login information");
        }
    }

    /**
     * Calls{@link Main#checkUsername(String)} to check if the username is taken.
     * Calls {@link GUI#displayMessage(String)} checkUsername returns true, and then stops.
     * Makes a post request with the given username and password to the accounts database.
     * @param username The username to use.
     * @param password The password to use.
     * @throws Exception If an error occurs in the process.
     */
    public static void signUp(String username, String password) throws Exception {
        if (checkUsername(username)) {
            gui.displayMessage("Username is Taken");
            return;
        }
        String hashedPassword = Hasher.hashPassword(password);
        String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + hashedPassword + "\"}";
        System.out.println(jsonInputString);
        try {
            URL obj = new URI(accountsUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            con.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newPost(String title, String body) {
        if (Session.currentUsername == null || Session.currentPassword == null) {
            gui.displayMessage("Please Login");
            return;
        }
        String jsonInputString = "{\"title\": \"" + title + "\", \"username\": \"" + Session.currentUsername + "\", \"body\": \"" + body + "\"}";
        System.out.println(jsonInputString);
        try {
            URL obj = new URI(postsUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            con.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}