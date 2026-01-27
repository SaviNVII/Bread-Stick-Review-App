import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class GUI {
    public int screenWidth = 600;
    public int screenHeight = 600;

    public int secondaryScreenWidth = 300;
    public int secondaryScreenHeight = 150;

    JLabel messageLabel = new JLabel();

    GUI() {
        //region Main
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(buttonPanel);

        //region Sign Up
        JFrame signUpFrame = new JFrame();
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.PAGE_AXIS));

        JLabel signUpUsernameLabel = new JLabel("Username:");
        signUpPanel.add(signUpUsernameLabel);

        JTextField signUpUsernameField = new JTextField();
        signUpPanel.add(signUpUsernameField);

        JLabel signUpPasswordLabel = new JLabel("Password:");
        signUpPanel.add(signUpPasswordLabel);

        JPasswordField signUpPasswordField = new JPasswordField();
        signUpPanel.add(signUpPasswordField);

        JButton signUpSignUpButton = new JButton("Sign Up");
        signUpPanel.add(signUpSignUpButton);
        signUpSignUpButton.addActionListener(_ -> {
            System.out.println("Sign Up");
            String username = signUpUsernameField.getText();
            String password = new String(signUpPasswordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                displayMessage("Username/Password can not be empty.");
            } else {
                try {
                    Main.signUp(username, password);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        signUpFrame.add(signUpPanel);
        signUpFrame.setSize(secondaryScreenWidth, secondaryScreenHeight);
        signUpFrame.setResizable(false);
        //endregion

        //region Login
        JFrame loginFrame = new JFrame();
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));

        JLabel loginUsernameLabel = new JLabel("Username:");
        loginPanel.add(loginUsernameLabel);

        JTextField loginUsernameField = new JTextField();
        loginPanel.add(loginUsernameField);

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPanel.add(loginPasswordLabel);

        JPasswordField loginPasswordField = new JPasswordField();
        loginPanel.add(loginPasswordField);

        JButton loginLoginButton = new JButton("Login");
        loginPanel.add(loginLoginButton);
        loginLoginButton.addActionListener(_ -> {
            String username = loginUsernameField.getText();
            String password = new String(loginPasswordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                displayMessage("Username/Password can not be empty.");
            } else {
                try {
                    Main.login(username, password);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        loginFrame.add(loginPanel);
        loginFrame.setSize(secondaryScreenWidth, secondaryScreenHeight);
        loginFrame.setResizable(false);
        //endregion

        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        loginButton.addActionListener(_ -> loginFrame.setVisible(true));

        JButton logoutButton = new JButton("Logout");
        buttonPanel.add(logoutButton);
        logoutButton.addActionListener(_ -> Main.logout());

        JButton signUpButton = new JButton("Sign Up");
        buttonPanel.add(signUpButton);
        signUpButton.addActionListener(_ -> signUpFrame.setVisible(true));

        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        JButton newPostButton = new JButton("New Post");
        buttonPanel.add(newPostButton);

        mainPanel.add(messageLabel);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        //endregion
    }

    public void displayMessage(String message) {
        messageLabel.setText(message);
    }
}
