package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainFrame2 implements ActionListener {

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame2();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	private QueryPanel query;
	private CusQueryPanel cusquery;
	private JPanel panel;
	
	private AddPanel2 add;
	private Login login;
//	private Log log;
	private JLabel lblogin;
	private boolean isLoggedin;
	
	public boolean isLoggedin() {
		return isLoggedin;
	}

	public void setLoggedin(boolean isLoggedin) {
		this.isLoggedin = isLoggedin;
	}


/*	public synchronized void authen() {
		while(login.isLoggedin()==false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("in MainFrame"+login.isLoggedin());
		if(login.isLoggedin()) {
			login.removeAll();
			lblogin.setText("You are logged in."); 
			panel.add(lblogin,BorderLayout.EAST);
		}
	}
	
	public synchronized void notice() {
		while(login.isLoggedin()==true) {
			notify();
		}
	}
	*/
	
	
	public MainFrame2() {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(70,70,600,500);
		frame.setVisible(true);
		
		JMenuBar mb;
		JMenu Inventory, Employees, Customer, Edit, Login, Logout;
	    JMenuItem InventoryTable, EmployeeInfo, Salary, SalesRecord, Customers,User;
	    JMenuItem InventoryTable2, EmployeeInfo2, Salary2, SalesRecord2, Customers2,User2;
	    
        mb = new JMenuBar();
        
        //set up the menu
        Inventory = new JMenu("Inventory");
        InventoryTable = new JMenuItem("InventoryTable");
        InventoryTable.addActionListener(this);
        SalesRecord = new JMenuItem("SalesRecord");
        
        Employees = new JMenu("Employees");
        EmployeeInfo = new JMenuItem("EmployeeInfo");
        Salary = new JMenuItem("Salary");
        
        Customer = new JMenu("Customer");
        Customers = new JMenuItem("Customers");
        Customers.addActionListener(this);
        User = new JMenuItem("User");
        User.addActionListener(this);
        
        Edit = new JMenu("Edit");
        InventoryTable2 = new JMenuItem("InventoryTable");
        SalesRecord2 = new JMenuItem("SalesRecord");
        EmployeeInfo2 = new JMenuItem("EmployeeInfo");
        Salary2 = new JMenuItem("Salary");
        Customers2 = new JMenuItem("Customers");
        User2 = new JMenuItem("User");
        Login = new JMenu("Login");
        Logout = new JMenu("Logout");
        
        login = new Login();
        Login.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
			
				if(isLoggedin()==true) {
					JOptionPane.showMessageDialog(null, "You are already logged in.");
					return;
				}
				panel.removeAll();	
			    	SwingUtilities.invokeAndWait(new Runnable() {
			    		public void run() {
			    			panel.add(login);
			    			
			    		}
			    	});
			    	}
                      Authenticate(login.getUsername(), login.getPassword());
                //        notice();
			    		panel.updateUI();
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        Logout.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
				if(isLoggedin()==false)
					{
					JOptionPane.showMessageDialog(null, "You have not logged in.");
					panel.updateUI();
					return;
					}
				int result= JOptionPane.showConfirmDialog(null, "Are you sure to logout?");
				if(result==JOptionPane.NO_OPTION||result==JOptionPane.CANCEL_OPTION)
				{
					return;
				}
			    setLoggedin(false);
				JOptionPane.showMessageDialog(null, "You are Logged out.");
				panel.removeAll();
				panel.updateUI();
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        //set up the menu items  
        mb.add(Inventory); mb.add(Employees); mb.add(Customer);mb.add(Edit); 
        mb.add(Box.createHorizontalGlue());
        mb.add(Login);
		mb.add(Logout);
        Inventory.add(InventoryTable); Inventory.add(SalesRecord);
        Employees.add(EmployeeInfo);Employees.add(Salary);
        Customer.add(Customers);Customer.add(User);
        Edit.add(InventoryTable2);Edit.add(SalesRecord2);Edit.add(EmployeeInfo2);Edit.add(Salary2);Edit.add(Customers2);Edit.add(User2);
        
        frame.add(mb);
        frame.setJMenuBar(mb);
		
    //    JTextPane textpane = new JTextPane();
        panel = new JPanel();
     //   panel.setBackground(Color.gray);
        JScrollPane scrollpane = new JScrollPane(panel); // the menu needs to be built first before the scrollpane
    
        lblogin = new JLabel("Login check");
        
       panel.add(lblogin,BorderLayout.NORTH);
        scrollpane.setPreferredSize(new Dimension(300,400));
        frame.getContentPane().add(scrollpane,BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		System.out.println(command);
		if(command.equals("User")) {
		
			panel.removeAll();
			if(isLoggedin()) {
				JOptionPane.showMessageDialog(null, "Please login first."); return;
			}
			query = new QueryPanel();
			panel.add(query);
			panel.updateUI();
		}

		else if(command.equals("Customers")) {
			
			panel.removeAll();
			if(isLoggedin()) {
				JOptionPane.showMessageDialog(null, "Please login first.");return;
			}
			cusquery = new CusQueryPanel();
			panel.add(cusquery);
			panel.updateUI();
					
		}
	/*	else if(command.equals("AddUser")) {
			panel.removeAll();
		add = new AddPanel2();
		query.removeAll();
		panel.add(add);
		panel.updateUI();
		System.out.println("added");
		}  */
	    
	}
public synchronized void Authenticate (String username, String password) {
		
		setLoggedin(false);
		while(isLoggedin) {
			
		}
		Connection con = getCon();
		String sql = "SELECT * FROM userform WHERE Username = ? and Password = ? ";
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, username);
			pre.setString(2, password);
			
			ResultSet res = pre.executeQuery();
			
			if(res.next()) {
				
			JOptionPane.showMessageDialog(null, "Welcome. ");
			
				setLoggedin(true);
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
}
