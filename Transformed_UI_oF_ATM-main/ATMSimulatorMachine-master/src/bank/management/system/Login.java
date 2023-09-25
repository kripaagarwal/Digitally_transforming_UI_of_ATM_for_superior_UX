package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel text, l2, l3,label5,label6,label7;
    JTextField tf1, tf2;
    JPasswordField pf1;
    JButton b1, b2;//
    String pinnumber;
    boolean ishindi=false;
    HindiKeyboard hindiKeyboard;
    EnglishKeyboard englishKeyboard;
    int wrongAttempts = 0; // Counter for wrong PIN attempts
    boolean isFirstWrongAttempt = true; // Flag to track the first wrong attempt
    boolean isKeyboardOpen;
    
    Login() {
        setTitle("AUTOMATED TELLER MACHINE");
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
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/card1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(800, 300, 200, 300);
        image.add(image2);

        text = new JLabel("WELCOME TO ATM");
        text.setBounds(250, 200, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("osward", Font.BOLD, 38));
        image.add(text);

        text = new JLabel("एटीएम में आपका स्वागत है");
        text.setBounds(250, 280, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("mangal", Font.BOLD, 38));
        image.add(text);

        l2 = new JLabel("NAME:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setForeground(Color.BLACK);
        l2.setBounds(250, 450, 375, 30);
        image.add(l2);
        
        tf2 = new JTextField(15);
        tf2.setBounds(410, 450, 230, 40);
        tf2.setFont(new Font("arial", Font.BOLD, 14));
        add(tf2);

        l2 = new JLabel("नाम:");
        l2.setFont(new Font("mangal", Font.BOLD, 28));
        l2.setForeground(Color.BLACK);
        l2.setBounds(250, 490, 375, 30);
        image.add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(410, 490, 230, 40);
        tf1.setFont(new Font("mangal", Font.BOLD, 14));
        add(tf1);
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setForeground(Color.BLACK);
        l3.setBounds(250, 540, 375, 30);
        image.add(l3);

        pf1 = new JPasswordField(15);
        pf1.setBounds(410, 540, 230, 40);
        pf1.setFont(new Font("arial", Font.BOLD, 14));
        add(pf1);
        
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBounds(490, 600, 125, 40);
        image.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setBounds(640, 600, 125, 40);
        image.add(b2);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.BLACK);

        // Initialize the HindiKeyboard and EnglishKeyboard
        hindiKeyboard = new HindiKeyboard(tf1,this);
        englishKeyboard = new EnglishKeyboard(tf2,this);

        tf1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Show the Hindi keyboard when the text field is clicked
                if (!isKeyboardOpen) {
                    hindiKeyboard.setVisible(true);
                    ishindi=true;
                    isKeyboardOpen = false;
                }
            }
        });

        tf2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Show the English keyboard when the text field is clicked
                if (!isKeyboardOpen) {
                    englishKeyboard.setVisible(true);
                    isKeyboardOpen =false;
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            tf1.setText("");
            tf2.setText("");
            pf1.setText("");
        } else if (ae.getSource() == b1) {
            conn c = new conn();
            String Name = tf2.getText();
            String Hname = tf1.getText();
            String pin = new String(pf1.getPassword()); // Get the PIN as a String
            String q1 = "select * from login where (name='" + Name + "' OR Hname='" + Hname + "') AND pin='" + pin + "'";
            try {
                ResultSet rs = c.s.executeQuery(q1);
                if (rs.next()) {
                    setVisible(false);
                    if(ishindi)
                    new Transactions_hindi(pinnumber).setVisible(true);
                    else 
                    new Transactions(pinnumber).setVisible(true);
                }else {
            wrongAttempts++; // Increment the wrong attempts counter
            if (wrongAttempts == 1) {
                // Show a custom message dialog for the first wrong attempt
                if(ishindi){
                label5 = new JLabel("गलत पिन या नाम, ध्यानपूर्वक दर्ज करें");
                        Font customFont1 = new Font("mangal", Font.BOLD, 16);
                        label5.setFont(customFont1);
                label6 = new JLabel("आखिरी मौका, इसके बाद अकाउंट हो जाएगा ब्लॉक");
                        Font customFont2 = new Font("mangal", Font.BOLD, 16);
                        label6.setFont(customFont2);
                label7 = new JLabel("लगातार तीन गलत प्रयास, अकाउंट ब्लॉक");
                        Font customFont3 = new Font("mangal", Font.BOLD, 16);
                        label7.setFont(customFont3);
                }
                else{
                label5 = new JLabel("Incorrect PIN or name, enter carefully");
                        Font customFont1 = new Font("Osward", Font.BOLD, 16);
                        label5.setFont(customFont1);
                label6 = new JLabel("Last chance, after this the account will be blocked");
                        Font customFont2 = new Font("Osward", Font.BOLD, 16);
                        label6.setFont(customFont2);
                label7 = new JLabel("Three consecutive wrong attempts, account blocked");
                        Font customFont3 = new Font("Osward", Font.BOLD, 16);
                        label7.setFont(customFont3);
                }
                        
                int option = JOptionPane.showOptionDialog(this, label5, "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Retry", "Exit"}, "Retry");

                if (option == JOptionPane.YES_OPTION) {
                    // User clicked "Retry," clear the fields
                    tf1.setText("");
                    tf2.setText("");
                    pf1.setText("");
                    pf1.setBackground(Color.YELLOW); 
                } else {
                    System.exit(0); // Exit the application if the user clicks "Cancel"
                }
            } else if (wrongAttempts == 2) {
                // Show a custom message dialog for the second wrong attempt
                      int option = JOptionPane.showOptionDialog(this, label6, "Error", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Retry", "Exit"}, "Retry");

                if (option == JOptionPane.YES_OPTION) {
                    // User clicked "Retry," clear the fields
                    tf1.setText("");
                    tf2.setText("");
                    pf1.setText("");
                    pf1.setBackground(Color.YELLOW); 
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
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
