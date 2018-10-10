package entity;

import java.io.Serializable;

public class Inventory implements Serializable {

	private String ClothesLabel;
	private int Quantity;
	private String Location;
	private int idInventoryTable;
	
	public String getClothesLabel() {
		return ClothesLabel;
	}
	public void setClothesLabel(String clothesLabel) {
		ClothesLabel = clothesLabel;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getIdInventoryTable() {
		return idInventoryTable;
	}
	public void setIdInventoryTable(int idInventoryTable) {
		this.idInventoryTable = idInventoryTable;
	}
	@Override
	public String toString() {
		return "Inventory [ClothesLabel=" + ClothesLabel + ", Quantity=" + Quantity + ", Location=" + Location
				+ ", idInventoryTable=" + idInventoryTable + "]";
	}
	
	
}
