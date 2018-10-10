package entity;

public class clothesinfo {
	
	private String ClothesLabel;
	private String Category;
	private String Color;
	private String Size;
	private int Price;
	
	public String getClothesLabel() {
		return ClothesLabel;
	}
	public void setClothesLabel(String clothesLabel) {
		ClothesLabel = clothesLabel;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "clothesinfo [ClothesLabel=" + ClothesLabel + ", Category=" + Category + ", Color=" + Color + ", Size="
				+ Size + ", Price=" + Price + "]";
	}
	
	

}
