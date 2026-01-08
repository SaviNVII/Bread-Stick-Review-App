import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static GUI gui = new GUI();
    public static String savePath = "AccountData/";
    public static Account account = new Account();
    public static void main(String[] args) {
    }

    public static void signUp(String username, String password) throws IOException {
        account.startSession(username, password);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(username + ".json")) {
            gson.toJson(account, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}