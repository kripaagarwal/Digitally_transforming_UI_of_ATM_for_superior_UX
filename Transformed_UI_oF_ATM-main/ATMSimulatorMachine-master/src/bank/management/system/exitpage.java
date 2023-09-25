package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class exitpage extends JFrame implements ActionListener{
    JButton exitButton;
    public exitpage() {
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

        JLabel text = new JLabel("YOUR TRANSACTION IS COMPLETED");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System", Font.BOLD, 30));
        text.setBounds(300, 100, 1000, 400);
        image.add(text);
        
        JLabel text1 = new JLabel("THANK YOU");
        text1.setForeground(Color.BLACK);
        text1.setFont(new Font("System", Font.BOLD, 30));
        text1.setBounds(500, 200, 1000, 400);
        image.add(text1);

        exitButton = new JButton("");
        exitButton.setBounds(1095, 408, 138, 64);
        image.add(exitButton);
        exitButton.addActionListener(this);

        //setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exitButton) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new exitpage();
    }
}
