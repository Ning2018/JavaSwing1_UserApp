package dao;

import java.util.List;

import entity.Inventory;

public interface InventoryDao {
	
	boolean add(Inventory inventory);
	boolean delete(String ClothesLabel);
	boolean update(Inventory inventory);
	
	Inventory QueryByLabel(String ClothesLabel);

	List<Inventory> queryAll();
}
