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
    }
}