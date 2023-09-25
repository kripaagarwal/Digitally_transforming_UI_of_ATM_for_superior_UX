package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pin_hindi extends JFrame implements ActionListener{
    JTextField pin,repin;
    JButton change,back;
    String pinnumber;
    Pin_hindi(String pinnumber){
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
        
        JLabel text=new JLabel("अपना पिन बदलें");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("mangal",Font.BOLD,24));
        text.setBounds(500,150,500,35);
        image.add(text);
        
         JLabel pintext=new JLabel("नया पिन: ");
        pintext.setForeground(Color.BLACK);
        pintext.setFont(new Font("mangal",Font.BOLD,18));
        pintext.setBounds(250,250,180,25);
        image.add(pintext);
        
        pin=new JTextField();
        pin.setFont(new Font("Raleway",Font.BOLD,27));
        pin.setBounds(520,250,100,25);
        image.add(pin);
        
         JLabel repintext=new JLabel("नया पिन दोबारा दर्ज करें: ");
        repintext.setForeground(Color.BLACK);
        repintext.setFont(new Font("mangal",Font.BOLD,18));
        repintext.setBounds(250,290,180,25);
        image.add(repintext);
        
        repin=new JTextField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(520,290,100,27);
        image.add(repin);
        
        JLabel text2=new JLabel("परिवर्तन");
        text2.setBounds(900,410,150,64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text2);
        
        change=new JButton("");
        change.setBounds(1095,408,138,64);
        image.add(change);
        change.addActionListener(this);
        
        JLabel text4=new JLabel("पीछे जाए");
        text4.setBounds(900,474,150,64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("mangal",Font.BOLD,20));
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
                        JLabel label = new JLabel("दर्ज पिन मेल नहीं खाता");
                        Font customFont = new Font("mangal", Font.BOLD, 16); // You can change the font properties here
                        label.setFont(customFont);
                        JOptionPane.showMessageDialog(null,label);
                        return;
                    }
                    
                    if(npin.equals("")){
                        JLabel label1 = new JLabel("कृपया पिन दर्ज करें");
                        Font customFont = new Font("mangal", Font.BOLD, 16); // You can change the font properties here
                        label1.setFont(customFont);
                         JOptionPane.showMessageDialog(null,label1);
                        return;
                    }
                     if(nrepin.equals("")){
                          JLabel label2 = new JLabel("कृपया पिन दोबारा दर्ज करें");
                        Font customFont = new Font("mangal", Font.BOLD, 16); // You can change the font properties here
                        label2.setFont(customFont);
                         JOptionPane.showMessageDialog(null,label2);
                        return;
                    }
                     
                     conn c=new conn();
                     String query1="update bank set pin= '"+nrepin+"' where pin='"+pinnumber+"'";
                     String query3="update signupthree set pin= '"+nrepin+"' where pin='"+pinnumber+"'";
                     String query2="update login set pin= '"+nrepin+"' where pin='"+pinnumber+"'";
                     
                     c.s.executeUpdate(query1);
                     c.s.executeUpdate(query2);
                     c.s.executeUpdate(query3);
                     JLabel label3 = new JLabel("पिन सफलतापूर्वक अपडेट किया गया");
                        Font customFont = new Font("mangal", Font.BOLD, 16); // You can change the font properties here
                        label3.setFont(customFont);
                     JOptionPane.showMessageDialog(null,label3);
                     
                     setVisible(false);
                     new Transactions_hindi(nrepin).setVisible(true);
                 }catch(Exception  e){
                    System.out.println(e);
                }
                
            }else{
                setVisible(false);
                new Transactions_hindi(pinnumber).setVisible(true);
            }
           
        }
    public static void main(String args[]){
        new Pin_hindi("").setVisible(true);
    }
}
