import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static GUI gui = new GUI();
    public static Account account = new Account();

    public static String url = "https://7pivrgulp3.execute-api.us-east-2.amazonaws.com/production/accounts";

    public static void main(String[] args) {
    }

    public static void signUp(String username, String password) throws Exception {
        String hashedPassword = Hasher.hashPassword(password);
        String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + hashedPassword + "\"}";
        System.out.println(jsonInputString);
        try {
            URL obj = new URI(url).toURL();
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
}