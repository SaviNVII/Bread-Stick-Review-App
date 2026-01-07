import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static GUI gui = new GUI();
    public static void main(String[] args) {
    }

    public static void signUp(String username, String password) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("AccountData.txt"));
        writer.write(username);
        writer.write(" ");
        writer.write(password);
        writer.newLine();
        writer.close();
    }
}