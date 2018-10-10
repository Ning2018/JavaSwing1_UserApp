package demo;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

public class LoginForm extends JFrame {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginForm form = new LoginForm();
				form.setVisible(true);
			}
		});
	}
	
	private static String userName;
	private static String passWord;
	private static JLabel lblWelcome;
	
	
	public static void LoginDialog() {

		JLabel title = new JLabel("Login Username and Password");
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		final JComponent[] inputs = new JComponent[] {
				title,
				new JLabel("Username"),
				username,
				new JLabel("Password"),
				password
		};
		JOptionPane.showMessageDialog(null, inputs, "Login", JOptionPane.PLAIN_MESSAGE);
		
		userName = username.getText();
		passWord =  new String(password.getPassword()); 
		
		// Check Login
		if(!getLogin())
		{
			LoginDialog();
		}
		
	}
	
	public LoginForm() {
            
		// Create Form Frame
		super("LoginForm");
		setSize(679, 385);
		setLocation(500, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		
		// Label Welcome
		lblWelcome = new JLabel("lblWelcome",JLabel.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcome.setBounds(168, 153, 336, 25);
		getContentPane().add(lblWelcome);
		
		// When Frame Loaded
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LoginDialog();
			}
		});
					
	}
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/javaswing";
	public static final String USER="root";
	public static final String PWD="root";
	
	public static Boolean getLogin() {
		
		Connection connect = null;
		PreparedStatement pre = null;
		Boolean status = false;
		
		try {
			Class.forName(DRIVER);

			connect =  DriverManager.getConnection(URL,USER,PWD);	
			
			String sql = " SELECT * FROM  member " +
					" WHERE Username = ? " +
					" AND Password = ? ";
			pre = connect.prepareStatement(sql);
			pre.setString(1, userName);
			pre.setString(2, passWord);

			ResultSet rec = pre.executeQuery();
			if(rec.next()) {
				lblWelcome.setText("Welcome : " + rec.getString("Username"));
				status = true;
			} else {
     		JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
			}
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
		try {
			if(pre != null) {
				pre.close();
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}

}
