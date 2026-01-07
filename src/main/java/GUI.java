import javax.swing.*;
import java.awt.*;

public class GUI {
    public int screenWidth = 600;
    public int screenHeight = 600;

    GUI () {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JButton loginButton = new JButton("Login");
        mainPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        mainPanel.add(signUpButton);

        JButton refreshButton = new JButton("Refresh");
        mainPanel.add(refreshButton);

        JButton newPostButton = new JButton("New Post");
        mainPanel.add(newPostButton);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
    }
}
