package electricity.billing.system;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton login, cancel, signup;
    JTextField username, password;
    Choice logginin;
    Login(){
        super("Login page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300,20,100,20);
        add(lblusername);

        username = new JTextField();
        username.setBounds(400,20,150,20);
        add(username);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);

        password = new JTextField();
        password.setBounds(400,60,150,20);
        add(password);

        JLabel loggininas = new JLabel("Logging in as: ");
        loggininas.setBounds(300,100,100,20);
        add(loggininas);

        logginin = new Choice();
        logginin.add("Customer");
        logginin.add("Admin");
        logginin.setBounds(400,100,150,20);
        add(logginin);

        login = new JButton("Login");
        login.setBounds(330,160,100,20);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(450,160,100,20);
        cancel.addActionListener(this);
        add(cancel);

        signup = new JButton("Signup");
        signup.setBounds(380,200,100,20);
        signup.addActionListener(this);
        add(signup);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0,0,250,250);
        add(image);

        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
      if(ae.getSource() == login){
          String susername = username.getText();
          String spassword = password.getText();
          String user = logginin.getSelectedItem();

          try{
            Conn c = new Conn();
            String query = "select * from login where user = '"+susername+"' and password = '"+spassword+"' and account_type = '"+user+"'";
            ResultSet rs = c.s.executeQuery(query);

            if(rs.next()){
                String meter = rs.getString("meter_no");
                setVisible(false);
                new Project(user, meter);

            } else{
                JOptionPane.showMessageDialog(null, "Invalid login");
                username.setText("");
                password.setText("");
            }

          }catch(Exception e){
              e.printStackTrace();
          }
      } else if (ae.getSource()==  cancel) {
          setVisible(false);
      } else if (ae.getSource() == signup) {
          setVisible(false);
          new Signup();
      }
    }

    public static void main(String arg[]){
        new Login();
    }
}
