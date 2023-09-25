package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions_hindi extends JFrame implements ActionListener{
    JButton deposit,withdrawl,fastcash,mini,pinchange,balanceenquiry,exit;
    String pinnumber;
    Transactions_hindi(String pinnumber){
        this.pinnumber=pinnumber;
        setUndecorated(true);
        
        
        
        setSize(1300,850);
        setLocation(100,0);
        setLayout(null);
        setVisible(true);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(1300,850,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1300,850);
        add(image);
        
        JLabel text=new JLabel("कृपया कोई भी विकल्प चुनें");
        text.setBounds(500,300,700,35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("mangal",Font.BOLD,24));
        image.add(text);
        
        /*         left side         */
        JLabel text1=new JLabel("धन जमा");
        text1.setBounds(180,410,150,64);
        text1.setForeground(Color.BLACK);
        text1.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text1);
        
        deposit =new JButton("");
        deposit.setBounds(14,408,138,64);
        image.add(deposit);
        deposit.addActionListener(this);
        
        JLabel text3=new JLabel("फास्ट कैश");
        text3.setBounds(180,474,150,64);
        text3.setForeground(Color.BLACK);
        text3.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text3);
        
        fastcash =new JButton("");
        fastcash.setBounds(14,478,138,64);
        image.add(fastcash);
        fastcash.addActionListener(this);
        
        JLabel text5=new JLabel("पिन बदलें");
        text5.setBounds(180,540,150,64);
        text5.setForeground(Color.BLACK);
        text5.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text5);
        
        pinchange =new JButton("");
        pinchange.setBounds(14,550,138,64);
        image.add(pinchange);
        pinchange.addActionListener(this);
        
        /*                right side                 */
        JLabel text2=new JLabel("निकासी");
        text2.setBounds(900,410,150,64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text2);
        
        withdrawl =new JButton("");
        withdrawl.setBounds(1095,408,138,64);
        image.add(withdrawl);
        withdrawl.addActionListener(this);
        
        
        JLabel text4=new JLabel("मिनी स्टेटमेंट");
        text4.setBounds(900,474,150,64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text4);
        
        mini =new JButton("");
        mini.setBounds(1095,478,138,64);
        image.add(mini);
        mini.addActionListener(this);
        
        JLabel text6=new JLabel("बैलेंस पूछताछ");
        text6.setBounds(900,540,200,64);
        text6.setForeground(Color.BLACK);
        text6.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text6);
        
        balanceenquiry =new JButton("");
        balanceenquiry.setBounds(1095,550,138,64);
        image.add(balanceenquiry);
        balanceenquiry.addActionListener(this);
        
        JLabel text7=new JLabel("बाहर निकलें");
        text7.setBounds(900,618,150,64);
        text7.setForeground(Color.BLACK);
        text7.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text7);
        
        exit =new JButton("");
        exit.setBounds(1095,620,138,64);
        image.add(exit);
        exit.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==exit){
            System.exit(0);
        }else if(ae.getSource()==deposit){
            setVisible(false);
            new Deposite_hindi(pinnumber).setVisible(true);
        }else if(ae.getSource()==withdrawl){
            setVisible(false);
            new withdraw_hindi(pinnumber).setVisible(true);
        }
        else if(ae.getSource()==fastcash){
            setVisible(false);
            new FastCash_hindi(pinnumber).setVisible(true);
        }
        else if(ae.getSource()==pinchange){
            setVisible(false);
            new Pin_hindi(pinnumber).setVisible(true);
        }
        else if(ae.getSource()==balanceenquiry){
            setVisible(false);
            new BalanceEnquiry_hindi(pinnumber).setVisible(true);
        }
         else if(ae.getSource()==mini){
            new MiniStatement_hindi(pinnumber).setVisible(true);
        }
    }
    public static void main(String args[]){
        new Transactions_hindi("");
    }
}
