package ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GetInventory extends JPanel {
	
	public GetInventory() {
		 JPanel panelInventory = new JPanel();
		       
		    String data[][]={ {"101","Amit","670000"},    
                    {"102","Jai","780000"},    
                    {"101","Sachin","700000"}};    
String column[]={"ID","NAME","SALARY"}; 
JTable table = new JTable(data,column);

		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    
		    JScrollPane PaneInventory = new JScrollPane(table);
		    panelInventory.add(PaneInventory);
		    System.out.println("ghjjkl");
	}
}
