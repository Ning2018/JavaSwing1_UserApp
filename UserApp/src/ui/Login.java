package ui;

import java.applet.Applet;
import java.awt.*;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel {
	
	private String username;
	private String password;
	
	private JLabel lb_username;
	private JLabel lb_password;
	private JTextField txt_username;
	private JTextField txt_password;
	private JButton btn_login;
	private JButton btn_reset;
//	private boolean isLoggedin;
	
  public Login() {
	  
	    setBounds(100,200,500,400);
	    setVisible(true);
	    
	    GridBagLayout grid = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    setLayout(grid);
	    
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(55,0,0,0);
	    gbc.gridx=0;
	    gbc.gridy=0;
	    lb_username = new JLabel("User Name:");
	    add(lb_username,gbc);
	    
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(55,0,0,0);
	    gbc.ipadx=30;
	    gbc.gridx=1;
	    gbc.gridy=0;
	    txt_username = new JTextField();
	    add(txt_username,gbc);
	    txt_username.setColumns(10);
	    
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(35,0,0,0);
	    gbc.gridx=0;
	    gbc.gridy=1;
	    lb_password = new JLabel("Password:");
	    add(lb_password,gbc);
	    
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.insets = new Insets(35,0,0,0);
	//   gbc.ipady=30;
	    gbc.gridx=1;
	    gbc.gridy=1;
	    txt_password = new JTextField();
	    txt_password.setColumns(10);
	    add(txt_password,gbc);
	    
	    gbc.ipady=0;
	    btn_reset=new JButton("Reset");
	 //   gbc.weighty=0.5;
	    gbc.insets=new Insets(80,0,0,0);
	    gbc.gridx=0;
	    gbc.gridy=2;
	    add(btn_reset,gbc);
	    
	    gbc.ipady=0;
	    btn_login=new JButton("Login");
	 //   gbc.weightx=1;
	    gbc.insets=new Insets(80,0,0,0);
	 //   btn_login.setBounds(600, 500, 100, 30);
	    gbc.gridx=2;
	    gbc.gridy=2;
	    add(btn_login,gbc);
	    
	    btn_login.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		setUsername(txt_username.getText());
	 		    setPassword(txt_password.getText());
	
	    	//	Authenticate(username, password);
	    		
	    	}
	    });
	  
	    btn_reset.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	
	    		txt_username.setText("");
	    		txt_password.setText("");
	    
	    	}
	    });
	    
	}
 /* 
  public static final String DRIVER = "com.mysql.jdbc.Driver";
  public static final String URL = "jdbc:mysql://localhost:3306/javaswing";
  public static final String USER = "root";
  public static final String PWD = "root";
  
  private static Connection con;
  private static PreparedStatement pre;
  private ResultSet rs;
 
  public Connection getCon() {
	  
	  try {
		  Class.forName(DRIVER);
	  } catch (ClassNotFoundException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  try {
		  con = DriverManager.getConnection(URL,USER,PWD);
	  } catch (SQLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  return con;
  }
  
  
  public void CloseAll() {
	  
	  if(rs!=null) {
		  try {
			  rs.close();
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }	
	  }
	  
	  if(pre!=null) {
		  try {
			  pre.close();
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }
	  
	  if(con!=null) {
		  try {
			  con.close();
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }
  }
  
	public synchronized void Authenticate (String username, String password) {
		
		setLoggedin(false);
		Connection con = getCon();
		String sql = "SELECT * FROM userform WHERE Username = ? and Password = ? ";
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, username);
			pre.setString(2, password);
			
			ResultSet res = pre.executeQuery();
			
			if(res.next()) {
				
			JOptionPane.showMessageDialog(null, "Welcome. ");
			
			//	System.out.println(res.getString("Username"));
				txt_username.setText("");
				txt_password.setText("");
				setLoggedin(true);
							   System.out.println("in Login.java"+isLoggedin);
							   return;
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid username or password.");		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {CloseAll();}   
	}


	public boolean isLoggedin() {
		return isLoggedin;
	}


	public void setLoggedin(boolean isLoggedin) {
		this.isLoggedin = isLoggedin;
	}
 */

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
	
}
