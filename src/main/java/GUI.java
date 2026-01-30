import javax.swing.*;
import java.awt.*;

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

        //region New Post
        JFrame newPostFrame = new JFrame();
        JPanel newPostPanel = new JPanel();
        newPostPanel.setLayout(new BoxLayout(newPostPanel, BoxLayout.PAGE_AXIS));

        JLabel newPostNameLabel = new JLabel("Title");
        newPostPanel.add(newPostNameLabel);

        JTextField newPostNameField = new JTextField();
        newPostPanel.add(newPostNameField);

        JLabel newPostBodyLabel = new JLabel("Body");
        newPostPanel.add(newPostBodyLabel);

        JTextField newPostBodyField = new JTextField();
        newPostPanel.add(newPostBodyField);

        JButton newPostNewPostButton = new JButton("Post");
        newPostPanel.add(newPostNewPostButton);

        newPostFrame.add(newPostPanel);
        newPostFrame.setSize(secondaryScreenWidth, secondaryScreenHeight);
        newPostFrame.setResizable(false);
        //endregion

        //region Edit Post
        JFrame editPostFrame = new JFrame();
        JPanel editPostPanel = new JPanel();
        editPostPanel.setLayout(new BoxLayout(editPostPanel, BoxLayout.PAGE_AXIS));

        JLabel editPostNameLabel = new JLabel("Title");
        editPostPanel.add(editPostNameLabel);

        JTextField editPostNameField = new JTextField();
        editPostPanel.add(editPostNameField);

        JLabel editPostBodyLabel = new JLabel("Body");
        editPostPanel.add(editPostBodyLabel);

        JTextField editPostBodyField = new JTextField();
        editPostPanel.add(editPostBodyField);

        JButton editPostEditButton = new JButton("Edit");
        editPostPanel.add(editPostEditButton);

        editPostFrame.add(editPostPanel);
        editPostFrame.setSize(secondaryScreenWidth, secondaryScreenHeight);
        editPostFrame.setResizable(false);
        //endregion

        //region Delete Post
        JFrame deletePostFrame = new JFrame();
        JPanel deletePostPanel = new JPanel();
        deletePostPanel.setLayout(new BoxLayout(deletePostPanel, BoxLayout.PAGE_AXIS));

        JLabel deletePostNameLabel = new JLabel("Title");
        deletePostPanel.add(deletePostNameLabel);

        JTextField deletePostNameField = new JTextField();
        deletePostPanel.add(deletePostNameField);

        JButton deletePostDeleteButton = new JButton("Delete");
        deletePostPanel.add(deletePostDeleteButton);

        deletePostFrame.add(deletePostPanel);
        deletePostFrame.setSize(secondaryScreenWidth, secondaryScreenHeight);
        deletePostFrame.setResizable(false);
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
        newPostButton.addActionListener(_ -> newPostFrame.setVisible(true));

        JButton editPostButton = new JButton("Edit Post");
        buttonPanel.add(editPostButton);
        editPostButton.addActionListener(_ -> editPostFrame.setVisible(true));

        JButton deletePostButton = new JButton("Delete Post");
        buttonPanel.add(deletePostButton);
        deletePostButton.addActionListener(_ -> deletePostFrame.setVisible(true));

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
