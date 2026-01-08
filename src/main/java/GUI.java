import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {
    public int screenWidth = 600;
    public int screenHeight = 600;

    public int secondaryScreenWidth = 300;
    public int secondaryScreenHeight = 150;

    JLabel messageLabel = new JLabel();

    GUI() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(buttonPanel);

        JFrame signUpFrame = new JFrame();
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.PAGE_AXIS));

        JLabel signUpUsernameLabel = new JLabel("Username:");
        signUpPanel.add(signUpUsernameLabel);

        JTextField signUpUsernameField = new JTextField();
        signUpPanel.add(signUpUsernameField);

        JLabel signUpPasswordLabel = new JLabel("Password:");
        signUpPanel.add(signUpPasswordLabel);

        JTextField signUpPasswordField = new JTextField();
        signUpPanel.add(signUpPasswordField);

        JButton signUpSignUpButton = new JButton("Sign Up");
        signUpPanel.add(signUpSignUpButton);
        signUpSignUpButton.addActionListener(_ -> {
            String username = signUpUsernameField.getText();
            String password = signUpPasswordField.getText();
            try {
                Main.signUp(username, password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        buttonPanel.add(signUpButton);
        signUpButton.addActionListener(_ -> signUpFrame.setVisible(true));

        JButton refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        JButton newPostButton = new JButton("New Post");
        buttonPanel.add(newPostButton);

        mainPanel.add(messageLabel);

        signUpFrame.add(signUpPanel);
        signUpFrame.setSize(secondaryScreenWidth, secondaryScreenHeight);
        signUpFrame.setResizable(false);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
    }

    public void displayMessage(String message) {
        messageLabel.setText(message);
    }
}
