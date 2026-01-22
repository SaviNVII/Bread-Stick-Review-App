import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Main {
    public static GUI gui = new GUI();

    public static String url = "https://7pivrgulp3.execute-api.us-east-2.amazonaws.com/production/accounts";

    public static void main(String[] args) {
    }

    public static List<Account> getAccounts() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();

            Type listType = new TypeToken<List<Account>>() {}.getType();
            List<Account> accountList = gson.fromJson(response.body(), listType);

            // Output the results
            for (Account account : accountList) {
                System.out.println(account);
            }

            return accountList;
        } else {
            System.out.println("GET request failed: " + response.statusCode());
            return null;
        }
    }

    public static boolean checkUsername(String username) throws IOException, InterruptedException {
        List<Account> accounts = getAccounts();

        for(int i = 0; i < Objects.requireNonNull(accounts).size(); i++) {
            Account accountAtIndex = accounts.get(i);
            if (username.equals(accountAtIndex.getUsername())) return true;
        }
        return false;
    }

    public static void login(String username, String password) {
        
    }

    public static void signUp(String username, String password) throws Exception {
        if (checkUsername(username)) {
            gui.displayMessage("Username is Taken");
            return;
        }
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