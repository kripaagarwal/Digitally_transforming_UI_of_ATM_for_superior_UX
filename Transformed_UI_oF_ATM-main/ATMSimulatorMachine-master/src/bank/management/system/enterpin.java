package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.BorderFactory; // Import the BorderFactory class

public class enterpin extends JFrame implements ActionListener {
    JButton withdraw, back;
    JLabel label5,label6,label7;
    JTextField amount;
    JTextField pinField; // Add a pin text field variable
    String pinnumber;
    private JPanel keyboardPanel;
    private int wrongAttempts = 0; // Counter for wrong PIN attempts

    
    public enterpin(String pinnumber) {
        this.pinnumber = pinnumber;
        setSize(1300, 850);
        setLocation(100, 0);
        setLayout(null);
        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1300, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1300, 850);
        add(image);
        
         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/pin_protect.jpg"));
        Image i5 = i4.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(800, 150, 200, 250);
        image.add(image2);

        JLabel text = new JLabel("Enter your Pin Number");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 250, 400, 20);
        image.add(text);

        pinField = new JTextField(); // Initialize the pin text field
        pinField.setFont(new Font("Raleway", Font.BOLD, 22));
        pinField.setBounds(250, 280, 320, 25);
        pinField.setBackground(Color.WHITE); // Set the initial background color to white
        image.add(pinField); // Add the pin text field

        JLabel text2 = new JLabel("ENTER");
        text2.setBounds(900, 550, 150, 64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("System", Font.BOLD, 20));
        image.add(text2);

        withdraw = new JButton("");
        withdraw.setBounds(1095, 550, 138, 64);
        image.add(withdraw);
        withdraw.addActionListener(this);

        JLabel text4 = new JLabel("Back");
        text4.setBounds(900, 474, 150, 64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("System", Font.BOLD, 20));
        image.add(text4);

        back = new JButton("");
        back.setBounds(1095, 478, 138, 64);
        image.add(back);
        back.addActionListener(this);

        // Create and configure the keyboard panel
        keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(4, 3)); // 4 rows and 3 columns
        keyboardPanel.setBounds(250, 320, 320, 320); // Position and size of the keyboard

        // Define the ATM keyboard buttons
        String[] buttonLabels = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "", "0", "Clear"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this); // Add action listener to handle button clicks
            keyboardPanel.add(button);
        }

        image.add(keyboardPanel); // Add the keyboard panel to the main image panel
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String number = pinField.getText(); // Use pinField instead of amount
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter your Pin Number");
            } else {
                conn c = new conn();
                String q1 = "select * from login where pin='" + number + "'";
                try {
                    ResultSet rs = c.s.executeQuery(q1);
                    if (rs.next()) {
                        setVisible(false);
                        new exitpage().setVisible(true);
                    } else {
                       
                        wrongAttempts++; // Increment the wrong attempts counter
                        if (wrongAttempts == 1) {
                            label5 = new JLabel("Incorrect PIN or name, enter carefully");
                            Font customFont1 = new Font("Osward", Font.BOLD, 16);
                            label5.setFont(customFont1);
                            label6 = new JLabel("Last chance, after this the account will be blocked");
                            Font customFont2 = new Font("Osward", Font.BOLD, 16);
                            label6.setFont(customFont2);
                            label7 = new JLabel("Three consecutive wrong attempts, account blocked");
                            Font customFont3 = new Font("Osward", Font.BOLD, 16);
                            label7.setFont(customFont3);
                            
                            int option = JOptionPane.showOptionDialog(this, label5, "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Retry", "Exit"}, "Retry");

                            if (option == JOptionPane.YES_OPTION) {
                            pinField.setText("");
                            pinField.setBackground(Color.YELLOW); 
                            } else {
                                    System.exit(0); // Exit the application if the user clicks "Cancel"
                            }
                        } else if (wrongAttempts == 2) {
                        // Show a custom message dialog for the second wrong attempt
                            int option = JOptionPane.showOptionDialog(this, label6, "Error", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Retry", "Exit"}, "Retry");

                            if (option == JOptionPane.YES_OPTION) {
                            // User clicked "Retry," clear the fields
                            pinField.setText("");
                            pinField.setBackground(Color.YELLOW); 
                            } else {
                                System.exit(0); // Exit the application if the user clicks "Cancel"
                            }
                        } else if (wrongAttempts >= 3) {
                            // Show a custom message dialog for the third wrong attempt
                            JOptionPane.showMessageDialog(this, label7, "Account Blocked", JOptionPane.ERROR_MESSAGE);
                            System.exit(0); // Exit the application after three wrong attempts
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            // Handle button clicks from the keyboard panel
            String buttonText = ((JButton) ae.getSource()).getText();
            if (buttonText.equals("Clear")) {
                pinField.setText(""); // Clear the pinField
            } else {
                // Append the button text to the pinField
                String currentPin = pinField.getText();
                pinField.setText(currentPin + buttonText);
            }
        }
    }

    public static void main(String args[]) {
        new enterpin("");
    }
}
