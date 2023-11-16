import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordApp extends JFrame {

    private JTextField answerField1, answerField2;

    public ForgotPasswordApp() {
        setTitle("Forgot Password");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Forgot Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Question 1
        JLabel questionLabel1 = new JLabel("What is your childhood pet's name?");
        answerField1 = new JTextField(20);
        panel.add(createQuestionPanel(questionLabel1, answerField1));

        // Question 2
        JLabel questionLabel2 = new JLabel("What is your grandfather's name?");
        answerField2 = new JTextField(20);
        panel.add(createQuestionPanel(questionLabel2, answerField2));

        //back and submit buttons 
        panel.add(createButtonPanel());

        add(panel);
    }

    private JPanel createQuestionPanel(JLabel questionLabel, JTextField answerField) {
        JPanel questionPanel = new JPanel();
        questionPanel.add(questionLabel);
        questionPanel.add(answerField);
        return questionPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Need: go back to the login page
                JOptionPane.showMessageDialog(ForgotPasswordApp.this, "Redirecting to login...");
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Need: validate answers and password reset
                String answer1 = answerField1.getText();
                String answer2 = answerField2.getText();
                //What happens after forgot password? Send where? Return to login 
                JOptionPane.showMessageDialog(ForgotPasswordApp.this, "Answers submitted! Verifying if matching... Redirecting to Log In page");
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);
        return buttonPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ForgotPasswordApp().setVisible(true);
            }
        });
    }
}
