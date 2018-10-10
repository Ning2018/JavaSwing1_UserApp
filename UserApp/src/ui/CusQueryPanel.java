package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDao;
import dao.impl.CustomerDaoImpl;
import entity.Customer;

public class CusQueryPanel extends JPanel implements ActionListener {

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private CustomerDao customerDao;
	private JButton btnDelete;
	private JButton btnAdd;
	private AddPanel2 addPanel;

	public CusQueryPanel() {

		setLayout(new BorderLayout(2, 2));
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		scrollPane = new JScrollPane();

		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("Delete");
		btnDelete.addActionListener(this);

		btnAdd = new JButton("Add User");
		btnAdd.setActionCommand("AddUser");
		btnAdd.addActionListener(this);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setActionCommand("Update");
		btnUpdate.addActionListener(this);

		btnPanel.add(btnDelete);
		btnPanel.add(btnAdd);
		btnPanel.add(btnUpdate);


		table = new JTable();
		scrollPane.setColumnHeaderView(table);

		add(btnPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "CustomerId", "Name", "Birth", "Address" });

		customerDao = new CustomerDaoImpl();
		loadData();

		table.setModel(model);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
	}

	public void loadData() {

		model.getDataVector().clear();
		List<Customer> customerlist = customerDao.queryAll();
		if (customerlist.size() == 0) {
			System.out.println("No data. ");
			table.setModel(model);
			scrollPane.setViewportView(table);
		}

		for (Customer customer : customerlist) {
			model.addRow(new Object[] { customer.getCustomerId(), customer.getName(), customer.getBirth(),
					customer.getAddress() });
			System.out.println(customer.toString());
		}
	}

	public void delete() {
		if (table.getSelectedRow() < 0) {
			System.out.println(table.getSelectedRow());
			JOptionPane.showMessageDialog(null, "Please select a valid row to be deleted.");
			System.out.println(table.getSelectedRow());
			return;
		}
		int result = JOptionPane.showConfirmDialog(null, "Are you sure to delete this row?");
		if (result == JOptionPane.OK_OPTION) {

			customerDao.delete(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
			System.out.println(table.getSelectedRow());
			loadData();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Delete")) {
			if (model == null) {
				JOptionPane.showMessageDialog(null, "No data.");
				return;
			}
			delete();
		}
		if (command.equals("AddUser")) {
			addPanel = new AddPanel2();
			add(addPanel);
			updateUI();
			loadData();
		}
       else if(command.equals("Update")) {
			
			loadData();
		}
	}
}