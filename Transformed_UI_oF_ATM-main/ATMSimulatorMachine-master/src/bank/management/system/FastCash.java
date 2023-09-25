package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
//import java.util.HashMap;

public class FastCash extends JFrame implements ActionListener {
    JButton deposit, withdrawl, fastcash, mini, pinchange, balanceenquiry, exit;
    String pinnumber;
    //HashMap<JLabel, Integer> labelAmountMap = new HashMap<>();

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setUndecorated(true);

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

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        /*              left side              */
        JLabel text1 = new JLabel("Rs 100");
        text1.setBounds(180, 410, 150, 64);
        text1.setForeground(Color.BLACK);
        text1.setFont(new Font("System", Font.BOLD, 20));
        image.add(text1);

        deposit = new JButton();
        deposit.setBounds(14, 408, 138, 64);
        deposit.setActionCommand("100");
        image.add(deposit);
        deposit.addActionListener(this);

        JLabel text2 = new JLabel("Rs 500");
        text2.setBounds(900, 410, 150, 64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("System", Font.BOLD, 20));
        image.add(text2);

        withdrawl = new JButton();
        withdrawl.setBounds(1095, 408, 138, 64);
        image.add(withdrawl);
        withdrawl.setActionCommand("500");
        withdrawl.addActionListener(this);

        JLabel text3 = new JLabel("Rs 1000");
        text3.setBounds(180, 474, 150, 64);
        text3.setForeground(Color.BLACK);
        text3.setFont(new Font("System", Font.BOLD, 20));
        image.add(text3);

        fastcash = new JButton();
        fastcash.setBounds(14, 478, 138, 64);
        image.add(fastcash);
        fastcash.setActionCommand("1000");
        fastcash.addActionListener(this);

        JLabel text4 = new JLabel("Rs 2000");
        text4.setBounds(900, 474, 150, 64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("System", Font.BOLD, 20));
        image.add(text4);

        mini = new JButton();
        mini.setBounds(1095, 478, 138, 64);
        image.add(mini);
        mini.setActionCommand("2000");
        mini.addActionListener(this);

        JLabel text5 = new JLabel("Rs 5000");
        text5.setBounds(180, 540, 150, 64);
        text5.setForeground(Color.BLACK);
        text5.setFont(new Font("System", Font.BOLD, 20));
        image.add(text5);

        pinchange = new JButton();
        pinchange.setBounds(14, 550, 138, 64);
        image.add(pinchange);
        pinchange.setActionCommand("5000");
        pinchange.addActionListener(this);

        JLabel text6 = new JLabel("Rs 10000");
        text6.setBounds(900, 540, 200, 64);
        text6.setForeground(Color.BLACK);
        text6.setFont(new Font("System", Font.BOLD, 20));
        image.add(text6);

        balanceenquiry = new JButton();
        balanceenquiry.setBounds(1095, 550, 138, 64);
        balanceenquiry.setActionCommand("10000");
        image.add(balanceenquiry);
        balanceenquiry.addActionListener(this);

        JLabel text7 = new JLabel("Back");
        text7.setBounds(900, 618, 150, 64);
        text7.setForeground(Color.BLACK);
        text7.setFont(new Font("System", Font.BOLD, 20));
        image.add(text7);

        exit = new JButton("");
        exit.setBounds(1095, 620, 138, 64);
        image.add(exit);
        exit.addActionListener(this);

        /*labelAmountMap.put(text1, 100);
        labelAmountMap.put(text2, 500);
        labelAmountMap.put(text3, 1000);
        labelAmountMap.put(text4, 2000);
        labelAmountMap.put(text5, 5000);
        labelAmountMap.put(text6, 10000);*/
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    } else {
        String actionCommand = ae.getActionCommand(); // Retrieve the custom action command

        // Convert the action command to an integer (withdrawal amount)
        int amount = Integer.parseInt(actionCommand);

        conn c = new conn();
        try {
            ResultSet rs = c.s.executeQuery("select * from bank where pin='" + pinnumber + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            Date date = new Date();
            String query = "insert into bank values('" + pinnumber + "','" + date + "','withdrawal','" + amount + "')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
    public static void main(String args[]) {
        new FastCash("");
    }
}
