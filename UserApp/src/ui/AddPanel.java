package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.IDeptDao;
import dao.IUserDao;
import dao.impl.DeptDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Dept;
import entity.User;

public class AddPanel extends JPanel {
	private JTextField txtName;
	private JTextField txtBirth;
	private JComboBox cmbDept;
	private IUserDao userDao;
	private IDeptDao deptDao;
	private QueryPanel query;
	private MainFrame main;
	
	private JPanel addpanel;
	/**
	 * Create the panel.
	 */
	public AddPanel() {
		addpanel.setLayout(null);
		
		JPanel addpanel = new JPanel();
		
		addpanel.setBounds(30,30,300,400);
		addpanel.setBackground(Color.GREEN);
		
		JLabel label = new JLabel("User Name:");
		label.setBounds(86, 68, 54, 15);
		addpanel.add(label);
		
		txtName = new JTextField();
		txtName.setBounds(150, 65, 146, 18);
		addpanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel label_1 = new JLabel("Department:");
		label_1.setBounds(86, 114, 54, 15);
		addpanel.add(label_1);
		
		userDao=new UserDaoImpl();
		deptDao=new DeptDaoImpl();
		List<Dept> list=deptDao.queryAll();
		
		
		cmbDept = new JComboBox(list.toArray());
		cmbDept.setBounds(150, 110, 146, 23);
		addpanel.add(cmbDept);
		
		JLabel label_2 = new JLabel("Birthday:");
		label_2.setBounds(86, 163, 54, 15);
		addpanel.add(label_2);
		
		txtBirth = new JTextField();
		txtBirth.setBounds(150, 160, 146, 23);
		addpanel.add(txtBirth);
		txtBirth.setColumns(10);
		
		JButton btnNewButton = new JButton("Add user");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=txtName.getText();
				String birth=txtBirth.getText();
				Dept dept=(Dept) cmbDept.getSelectedItem();
				User user=new User();
				user.setBirth(birth);
				user.setName(name);
				user.setDeptid(dept.getDeptid());
				if(userDao.add(user)){
					JOptionPane.showMessageDialog(null, "Added Successfully");
					txtName.setText("");
					txtBirth.setText("");
				
				}else{
					JOptionPane.showMessageDialog(null, "Added fail");
				}
				
			}
		});
		btnNewButton.setBounds(150, 217, 116, 23);
		addpanel.add(btnNewButton);

	}
}
