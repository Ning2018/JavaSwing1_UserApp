package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

public class AddPanel2 extends JFrame {

	private JLabel UserName, DeptName, DateOfBirth ;
	private JTextField txtName, txtBirth;
	private IUserDao userDao;
	private IDeptDao deptDao;
	private JComboBox cmbDept;
	private JButton btnAddUser;
	private JPanel panel;
	
	public AddPanel2() {
		
		setBounds(50,50,400,400);
	//	setLayout()
	//	setSize(300,400);
		setVisible(true);
		toFront();
		panel = new JPanel();
		
		getContentPane().add(panel);
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(gridbag);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20,15,20,0);
		gbc.gridx=0;
		gbc.gridy=0;
		UserName = new JLabel("Username");
		panel.add(UserName,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
	//	gbc.insets = new Insets();
		gbc.gridx=1;
		gbc.gridy=0;
		txtName = new JTextField();
		panel.add(txtName,gbc);
		txtName.setColumns(10);
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
		gbc.gridy=1;
		DeptName = new JLabel("Department");
		panel.add(DeptName,gbc);
		
	    userDao = new UserDaoImpl();
	    deptDao = new DeptDaoImpl();
	    List<Dept> list = deptDao.queryAll();
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx=1;
	    gbc.gridy=1;
	    cmbDept = new JComboBox(list.toArray());
	    panel.add(cmbDept,gbc);
		
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx=0;
	    gbc.gridy=2;
		DateOfBirth = new JLabel("Date of Birth");
		panel.add(DateOfBirth,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=2;
		txtBirth = new JTextField();
		panel.add(txtBirth,gbc);
		txtBirth.setColumns(10);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=3;
		btnAddUser = new JButton("Add");
		panel.add(btnAddUser,gbc);
		
		btnAddUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = txtName.getText();
				Dept DeptName = (Dept) cmbDept.getSelectedItem();
				String dateofbirth = txtBirth.getText();
				User user = new User();
				user.setName(username);
				user.setDeptid(DeptName.getDeptid());
				user.setBirth(dateofbirth);
				
				if(userDao.add(user)) {
					JOptionPane.showMessageDialog(null, "Added Successfully");
					txtName.setText("");
					txtBirth.setText("");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Insertion Failure");
				}
				}
		});
	}
	
}
