package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.IDeptDao;
import dao.IUserDao;
import dao.impl.DeptDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Dept;
import entity.User;

public class QueryPanel extends JPanel implements ActionListener {
	private JTable table;
	private DefaultTableModel model;// 鐢ㄤ簬瀛樺偍琛ㄦ牸鏁版嵁
	private IDeptDao deptDao;
	private IUserDao userDao;
	private String oldValue = "";// 淇濆瓨鍗曞厓鏍肩紪杈� 鍓嶇殑鍊�
	private JButton btnDelete;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private AddPanel2 addUserPanel;
	private JButton btnUpdate;
	private JPanel btnPanel;
	/**
	 * Create the panel.
	 */
	public QueryPanel() {
		
		setLayout(new BorderLayout(2,2));
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
      
		scrollPane = new JScrollPane();
		
	    btnDelete = new JButton("Delete");
        btnDelete.setActionCommand("Delete");
        btnDelete.addActionListener(this);
        btnAdd = new JButton("Add User");
        btnAdd.setActionCommand("AddUser");
        btnAdd.addActionListener(this);
        btnUpdate = new JButton("Update");
        btnUpdate.setActionCommand("Update");
        btnUpdate.addActionListener(this);
        
        btnPanel.add(btnDelete);
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        
        add(btnPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
   //     scrollPane.add(btnDelete);
   //     scrollPane.add(btnAdd); 
		table = new JTable();

		scrollPane.setColumnHeaderView(table);
		// 鍒濆鍖栧瓨鍌ㄨ〃鏍兼暟鎹殑瀵硅薄
		model = new DefaultTableModel(new Object[][] {}, new String[] { "User Id",
				"User Name", "Department", "Date of Birth" });
		// 灏嗘暟鎹粦瀹氬埌瀵硅薄涓�
		table.setModel(model);
		table.setRowHeight(30);

		scrollPane.setViewportView(table);

		deptDao = new DeptDaoImpl();
		userDao = new UserDaoImpl();
		loadData();
		// 涓鸿〃鏍肩粦瀹氫慨鏀瑰�煎悗鐨勪簨浠�
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getColumn() < 0)
					return;
				String nVal = table.getValueAt(e.getLastRow(), e.getColumn())
						.toString();
				// 濡傛灉鏃х殑鍊� 鍜屾柊鐨勫�间竴鏍凤紝鐩存帴 杩斿洖
				if (nVal.equals(oldValue)) {
					return;
				}
				// 鍒ゆ柇褰撳墠缂栬緫鐨勫崟鍏冩牸鏄惁鏄富閿垪
				if (e.getColumn() == 0) {
					// 杩樺師鏃х殑鍊�
					table.setValueAt(oldValue, e.getLastRow(), e.getColumn());
					return;
				}
				// 鏇存柊鏁版嵁
				User user = new User();
				user.setUserid(Integer.valueOf(table.getValueAt(e.getLastRow(),
						0).toString()));
				user.setName(table.getValueAt(e.getLastRow(), 1).toString());
				user.setBirth(table.getValueAt(e.getLastRow(), 3).toString());
				Dept dept = (Dept) table.getValueAt(e.getLastRow(), 2);
				user.setDeptid(dept.getDeptid());

				userDao.update(user);

				loadData();
			}
		});
	}

	public void loadData() {
		// 娓呴櫎鏃х殑鏁版嵁
		model.getDataVector().clear();
		// 鏌ヨ閮ㄩ棬鏁版嵁
		List<Dept> deptList = deptDao.queryAll();
		
		JComboBox cob = new JComboBox(deptList.toArray());
		// 鍒涘缓涓�涓娇鐢ㄤ笅鎷夋浠ｆ浛缂栬緫妗嗙殑鍗曞厓鏍煎璞�
		DefaultCellEditor dept = new DefaultCellEditor(cob);
		// 鑾峰彇琛ㄦ牸鐨勫垪model瀵硅薄
		TableColumnModel col = table.getColumnModel();
		// 鑾峰彇閮ㄩ棬鐨勫垪锛岃缃繖涓垪涓轰笅鎷夋鍒楃被鍨�
		col.getColumn(2).setCellEditor(dept);
		// 鏌ヨ鎵�鏈夌殑鐢ㄦ埛淇℃伅
		List<User> list = userDao.queryAll();
		// 閬嶅巻姣忎竴鏉℃暟鎹紝娣诲姞鍒癿odel涓�
		int i = 0;
		if(list.size()==0) {
			table.setModel(model);
			scrollPane.setViewportView(table);
		}
		for (User user : list) {
			// 娣诲姞 琛屾暟鎹�
			cob.setSelectedItem(new Dept(user.getDeptid()));
			model.addRow(new Object[] { user.getUserid(), user.getName(),
					cob.getSelectedItem(), user.getBirth() });
		}
	}

	
	public void del() {
		if (table.getSelectedRowCount() < 0) {
			JOptionPane.showMessageDialog(null, "Please select a valid row to be deleted.");
			System.out.println(table.getSelectedRow());
			return;
		}
		int result = JOptionPane.showConfirmDialog(null, "Are you sure to delete this row?");
		// 鍒ゆ柇鐢ㄦ埛鏄惁鐐瑰嚮
		if (result == JOptionPane.OK_OPTION) {
			int userid = Integer.valueOf(table.getValueAt(
					table.getSelectedRow(), 0).toString());
			System.out.println(table.getSelectedRow());
			userDao.delete(userid);
			loadData();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		System.out.println(command);
		if(command.equals("Delete"))
			del();
		else if(command.equals("AddUser")) {
		
			addUserPanel = new AddPanel2();
		//	add(addUserPanel,BorderLayout.SOUTH);
			updateUI();
		}
		else if(command.equals("Update")) {
			
			loadData();
		}   
	}

}
