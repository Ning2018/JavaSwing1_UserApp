package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
	
	private static String username;
	private static String password;
    private JLabel lb_username;
	private JLabel lb_password;
	private JTextField txt_username;
	private JTextField txt_password;
	
	
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginForm form = new LoginForm();
				form.setVisible(true);
			}
		});
	}   */
	
	public LoginForm() {
		
		lb_username = new JLabel("User Name");
		lb_password = new JLabel("Password");
		setTitle("Login Form");
		setBounds(40,40,700,600);
		setVisible(true);
		toFront();
		add(lb_username);
		add(txt_username);
		add(lb_password);
		add(txt_password);
		
	}

}
