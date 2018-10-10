package entity;

public class Color {

	private String ColorId;
	private String Color;
	
	public String getColorId() {
		return ColorId;
	}
	public void setColorId(String colorid) {
		this.ColorId = colorid;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	@Override
	public String toString() {
		return "Color [ColorId=" + ColorId + ", Color=" + Color + "]";
	}
	
	
}
