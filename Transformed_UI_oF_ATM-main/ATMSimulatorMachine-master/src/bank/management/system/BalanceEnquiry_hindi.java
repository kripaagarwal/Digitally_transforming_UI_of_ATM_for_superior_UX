package bank.management.system;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class BalanceEnquiry_hindi extends JFrame implements ActionListener{
    JButton back;
    String pinnumber;
    BalanceEnquiry_hindi(String pinnumber){
        this.pinnumber=pinnumber;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(1300,850,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1300,850);
        add(image);
        
        JLabel text2=new JLabel("पीछे जाए");
        text2.setBounds(900,410,150,64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text2);
        
        back=new JButton("");
        back.setBounds(1095,408,138,64);
        image.add(back);
        back.addActionListener(this);
        int balance=0;
        conn c=new conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where pin='"+pinnumber+"'");
                
                while(rs.next()){
                    if(rs.getString("type").equals("Deposit")){
                        balance+=Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance-=Integer.parseInt(rs.getString("amount"));
                    }
                }     
            }catch(Exception e){
                System.out.println(e);
            }
        
        JLabel text=new JLabel("आपके चालू खाते का शेष रु. है "+balance);
        text.setBounds(250,300,400,30);
        text.setFont(new Font("mangal",Font.BOLD,20));
        text.setForeground(Color.BLACK);
        image.add(text);
        
        setSize(1300,850);
        setLocation(100,0);
        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions_hindi(pinnumber).setVisible(true);
    }
    public static void main(String args[]){
        new BalanceEnquiry_hindi("");
    }
    
}
