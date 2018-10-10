package dao.impl;

import java.util.List;

import dao.InventoryDao;
import entity.Inventory;
import util.DBCon;

public class InventoryDaoImpl implements InventoryDao{
	
	DBCon connection = new DBCon();

	@Override
	public boolean add(Inventory inventory) {
		// TODO Auto-generated method stub
		String sql = "insert into inventorytable(idInventoryTable, ClothesLabel, Quantity, Location) values (?,?,?,?)";
		return 
			connection.update(sql,inventory.getIdInventoryTable(), inventory.getClothesLabel(),inventory.getQuantity(),inventory.getLocation())>0;
		
	}

	@Override
		public boolean delete(String ClothesLabel) {
		// TODO Auto-generated method stub
/*		if(ClothesLabel.getClass().getQuantity()>1)
			String sql="update inventoryTable(idInventoryTable, ClothesLabel,Quantity, Location) where ClothesLabel = "ClothesLabel""
					+ ""
			else
		String sql ="delete from inventorytable(idInvenoryTable,ClothesLabel, Quantity, Location) where Clothes"
*/		return false;
	}

	@Override
	public boolean update(Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Inventory QueryByLabel(String ClothesLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inventory> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
