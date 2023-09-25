package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pin extends JFrame implements ActionListener{
    JTextField pin,repin;
    JButton change,back;
    String pinnumber;
    Pin(String pinnumber){
        this.pinnumber=pinnumber;
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
        
        JLabel text=new JLabel("Change Your Pin");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System",Font.BOLD,24));
        text.setBounds(500,150,500,35);
        image.add(text);
        
         JLabel pintext=new JLabel("New PIN: ");
        pintext.setForeground(Color.BLACK);
        pintext.setFont(new Font("System",Font.BOLD,18));
        pintext.setBounds(250,250,180,25);
        image.add(pintext);
        
        pin=new JTextField();
        pin.setFont(new Font("Raleway",Font.BOLD,27));
        pin.setBounds(520,250,100,25);
        image.add(pin);
        
         JLabel repintext=new JLabel("Re-Enter New PIN: ");
        repintext.setForeground(Color.BLACK);
        repintext.setFont(new Font("System",Font.BOLD,18));
        repintext.setBounds(250,290,180,25);
        image.add(repintext);
        
        repin=new JTextField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(520,290,100,27);
        image.add(repin);
        
        JLabel text2=new JLabel("Change");
        text2.setBounds(900,410,150,64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("System",Font.BOLD,20));
        image.add(text2);
        
        change=new JButton("");
        change.setBounds(1095,408,138,64);
        image.add(change);
        change.addActionListener(this);
        
        JLabel text4=new JLabel("Back");
        text4.setBounds(900,474,150,64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("System",Font.BOLD,20));
        image.add(text4);
        
        back=new JButton("");
        back.setBounds(1095,478,138,64);
        image.add(back);
        back.addActionListener(this);
        
       
    }
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource()==change){
                 try{
                String npin=pin.getText();
                String nrepin=repin.getText();
                    if(!npin.equals(nrepin)){
                        JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                        return;
                    }
                    
                    if(npin.equals("")){
                         JOptionPane.showMessageDialog(null,"Please Enter PIN");
                        return;
                    }
                     if(nrepin.equals("")){
                         JOptionPane.showMessageDialog(null,"Please Re-Enter PIN");
                        return;
                    }
                     
                     conn c=new conn();
                     String query1="update bank set pin= '"+nrepin+"' where pin='"+pinnumber+"'";
                     String query3="update signupthree set pin= '"+nrepin+"' where pin='"+pinnumber+"'";
                     String query2="update login set pin= '"+nrepin+"' where pin='"+pinnumber+"'";
                     
                     c.s.executeUpdate(query1);
                     c.s.executeUpdate(query2);
                     c.s.executeUpdate(query3);
                     JOptionPane.showMessageDialog(null," PIN Updated Succesfully");
                     
                     setVisible(false);
                     new Transactions(nrepin).setVisible(true);
                 }catch(Exception  e){
                    System.out.println(e);
                }
                
            }else{
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
           
        }
    public static void main(String args[]){
        new Pin("").setVisible(true);
    }
}
