
package dixtonchatting.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Server implements ActionListener {
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static JFrame f1 = new JFrame();
    
    static Box vertical = Box.createVerticalBox();
    
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    Boolean typing;
    
    Server(){
        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,400,70);
        f1.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("dixtonchatting/application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5,17,30,30);
        p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("dixtonchatting/application/icons/11.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40,5,60,60);
        p1.add(l2);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("dixtonchatting/application/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(260,20,30,30);
        p1.add(l5);
        
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("dixtonchatting/application/icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(310,20,35,30);
        p1.add(l6);
        
        
        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("dixtonchatting/application/icons/3icon.png"));
        Image i15 = i14.getImage().getScaledInstance(23, 25, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(360,20,23,25);
        p1.add(l7);
        
        JLabel l3 = new JLabel("Naruto");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD,18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110,15,100,18);
        p1.add(l3);
        
        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN,12));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110,35,100,20);
        p1.add(l4);
        
        Timer t = new Timer(1, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(!typing){
                    l4.setText("Axtive Now");
                    
                }
            }
        });
        
        t.setInitialDelay(2000);
      
        // text field,area start and button
        a1 = new JPanel();
        a1.setBounds(5,75,390,370);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
 
        f1.add(a1);
       
        t1 = new JTextField();
        t1.setBounds(5,455,280,45);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        f1.add(t1);
        
        t1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                l4.setText("typing....");
                
                t.stop();
                
                typing = true;
            }
            
            public void keyReleased(KeyEvent ke){
                typing = false;
                
                if(!t.isRunning()){
                    t.start();
                }
            }
        });
        
        b1 = new JButton("Send");
        b1.setBounds(290,455,123,40);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        b1.addActionListener(this);
        f1.add(b1);
        // ends
        
        
        f1.getContentPane().setBackground(Color.WHITE);
        f1.setLayout(null);
        f1.setSize(400,500);
        f1.setLocation(700,60);
        f1.setUndecorated(true);
        f1.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
           String out  = t1.getText();
           
           JPanel p2 =  formatLabel(out);
           
           a1.setLayout(new BorderLayout());
           
           JPanel right = new JPanel(new BorderLayout());
           right.add(p2, BorderLayout.LINE_END);
           vertical.add(right);
           vertical.add(Box.createVerticalStrut(5));
           
           a1.add(vertical, BorderLayout.PAGE_START);          
           //a1.add(p2);
           dout.writeUTF(out);
           t1.setText("");  
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        
        
        JLabel l1 = new JLabel("<html><p style =\"wodth : 50px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN,16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(5,5,5,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
                
        p3.add(l1);
        p3.add(l2);
        return p3;
    }
    
    public static void main(String[] args){
        new Server().f1.setVisible(true);
        
        
        String msginput = "";
        try{
            
            skt = new ServerSocket(6001);
            while(true){
                s =skt.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                
                
                while(true){
                    msginput = din.readUTF();
                    JPanel p2 = formatLabel(msginput);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(p2, BorderLayout.LINE_START);
                    vertical.add(left);
                    f1.validate();
                   // a1.setText(a1.getText()+"\n"+msginput);
                }
            }
            
	    
            

           // skt.close();
           // s.close();
            
            
        }catch(Exception e){}
    }
}
