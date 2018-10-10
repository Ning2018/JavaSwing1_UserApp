package entity;

import java.util.Date;

public class Customer {

	private String Name;
	private int CustomerId;
	
	private Date Birth;
	private String Address;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public Date getBirth() {
		return Birth;
	}
	public void setBirth(Date birth) {
		Birth = birth;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "Customer [Name=" + Name + ", CustomerId=" + CustomerId + ", Birth=" + Birth + ", Address=" + Address
				+ "]";
	}
	
	
}
