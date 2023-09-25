package bank.management.system;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class EnglishKeyboard extends JFrame implements ActionListener {
    JTextField textField2;
    JTextField displayField; // Text field to display typed characters
    private Login login;

    EnglishKeyboard(JTextField textField2,Login login) {
        this.textField2 = textField2;
        this.login = login;
        
        setTitle("English Keyboard");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel keyBoardPanel = new JPanel(new GridLayout(7, 5));

        String[] englishCharacters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", " ", ".", ",",
            "\u2190" // Backspace button with Unicode arrow character
        };

        for (String character : englishCharacters) {
            JButton button = new JButton(character);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            keyBoardPanel.add(button);
        }

        // Create and add the display field outside the grid
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));
        displayField.setEditable(false);

        add(displayField, BorderLayout.NORTH);
        add(keyBoardPanel, BorderLayout.CENTER);

        // Create and add the "OK" button at the bottom
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 20));
        okButton.addActionListener(this);
        add(okButton, BorderLayout.SOUTH);

        // Remove the default close operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
        public void resetFocus() {
        textField2.requestFocusInWindow(); // Set focus to the text field
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        String character = buttonClicked.getText();

        if ("OK".equals(character)) {
            login.isKeyboardOpen = false;
            setVisible(false);
            login.setVisible(true);// Hide the keyboard
            resetFocus();
            //dispose();// Close the keyboard when "OK" button is clicked
        } else if ("\u2190".equals(character)) { // Check for backspace button
            String currentText = displayField.getText();
            if (currentText.length() > 0) {
                currentText = currentText.substring(0, currentText.length() - 1);
                displayField.setText(currentText);
                textField2.setText(currentText);
            }
        } else {
            String currentText = displayField.getText();
            displayField.setText(currentText + character);
            textField2.setText(currentText + character);
        }
    }
}
