package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposite_hindi extends JFrame implements ActionListener {
    JButton deposit, back;
    JTextField amount;
    String pinnumber;
    private JPanel keyboardPanel;

    public Deposite_hindi(String pinnumber) {
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

        JLabel text = new JLabel("वह राशि दर्ज करें जिसे आप जमा करना चाहते हैं");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("mangal", Font.BOLD, 16));
        text.setBounds(250, 250, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(250, 280, 320, 25);
        image.add(amount);

        JLabel text2 = new JLabel("यहां जमा करें");
        text2.setBounds(900, 410, 150, 64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("mangal", Font.BOLD, 20));
        image.add(text2);

        deposit = new JButton("");
        deposit.setBounds(1095, 408, 138, 64);
        image.add(deposit);
        deposit.addActionListener(this);

        JLabel text4 = new JLabel("पीछे जाए");
        text4.setBounds(900, 474, 150, 64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("mangal", Font.BOLD, 20));
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
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JLabel label4 = new JLabel("कृपया वह राशि दर्ज करें जिसे आप जमा करना चाहते हैं");
                        Font customFont = new Font("mangal", Font.BOLD, 16); // You can change the font properties here
                        label4.setFont(customFont);
                JOptionPane.showMessageDialog(null, label4);
            } else {
                try {
                    conn c = new conn();
                    String q = "insert into bank values('" + pinnumber + "','" + date + "','Deposit','" + number + "')";
                    c.s.executeUpdate(q);
                    //JOptionPane.showMessageDialog(null, "Rs " + number + " Deposited Successfully");
                    setVisible(false);
                    new enterpin_hindi(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions_hindi(pinnumber).setVisible(true);
        } else {
            // Handle button clicks from the keyboard panel
            String buttonText = ((JButton) ae.getSource()).getText();
            if (buttonText.equals("Clear")) {
                amount.setText(""); // Clear the amount field
            } else {
                // Append the button text to the amount field
                String currentAmount = amount.getText();
                amount.setText(currentAmount + buttonText);
            }
        }
    }

    public static void main(String args[]) {
        new Deposite_hindi("");
    }
}
