package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MainFrame extends JFrame implements ActionListener {

	 JPanel contentPane;
	 JPanel panelBottom;
	private JButton btnDel;
	private JButton btnAdd;
	private AddPanel add;
	private QueryPanel query;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
    	setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnLoadData = new JButton("GetData");
		btnLoadData.setActionCommand("loaddata");
		panel.add(btnLoadData);
		
		btnDel = new JButton("Delete selected");
		panel.add(btnDel);
		btnDel.setActionCommand("del");
		
		btnAdd = new JButton("Add User");
		panel.add(btnAdd);
		btnAdd.setActionCommand("add");
		
		panelBottom = new JPanel();
	//	JScrollPane sp = new JScrollPane(panelBottom);
		panelBottom.setSize(new Dimension(300,200));
	   
		panelBottom.setBackground(Color.GRAY);
		add(panelBottom);
	//	panelBottom.setLayout(new BorderLayout(0,0));
		
		btnDel.addActionListener(this);
		btnLoadData.addActionListener(this);
		btnAdd.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("add")){
			panelBottom.removeAll();
			add=new AddPanel();
			panelBottom.add(add,BorderLayout.CENTER);
			panelBottom.updateUI();
		}else if(command.equals("loaddata")){
			panelBottom.removeAll();
			query=new QueryPanel();
			panelBottom.add(query);
			panelBottom.updateUI();
		}
		
		else if(command.equals("del")){
			if(query==null){
				JOptionPane.showMessageDialog(null, "No data.");
				return;
			}
			query.del();
		}  
		
	}

}
