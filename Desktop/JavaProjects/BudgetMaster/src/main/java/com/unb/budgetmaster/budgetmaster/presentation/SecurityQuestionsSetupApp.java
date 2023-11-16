package com.unb.budgetmaster.budgetmaster.presentation;

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityQuestionsSetupApp extends JFrame {

    private JTextField answer1Field, answer2Field;

    public SecurityQuestionsSetupApp() {
        setTitle("Security Questions Setup");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Security Questions Setup");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Question 1
        JLabel questionLabel1 = new JLabel("What is your childhood pet's name?");
        answer1Field = new JTextField(20);
        panel.add(createQuestionPanel(questionLabel1, answer1Field));

        // Question 2
        JLabel questionLabel2 = new JLabel("What is your grandfather's name?");
        answer2Field = new JTextField(20);
        panel.add(createQuestionPanel(questionLabel2, answer2Field));

         //back and submit buttons 
        panel.add(createButtonPanel());

        add(panel);
    }

        private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton saveButton = new JButton("Save");


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Need: go back to the login page
                JOptionPane.showMessageDialog(SecurityQuestionsSetupApp.this, "Redirecting to login...");
            }
        });


        saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Need: save security answers to DB
                    String answer1 = answer1Field.getText();
                    String answer2 = answer2Field.getText();
            
                    // Take to history page 
                    JOptionPane.showMessageDialog(SecurityQuestionsSetupApp.this,"Security answers saved! Completing Sign Up...");
            
                }
            });

        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        return buttonPanel;
        

       
    }

    private JPanel createQuestionPanel(JLabel questionLabel, JTextField answerField) {
        JPanel questionPanel = new JPanel();
        questionPanel.add(questionLabel);
        questionPanel.add(answerField);
        return questionPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() {
                new SecurityQuestionsSetupApp().setVisible(true);
            }
        });
    }
}*/