package entity;

import java.io.Serializable;

public class Location implements Serializable {

	private String LocationId;
	private String Location;
	
	public String getLocationId() {
		return LocationId;
	}
	public void setLocationId(String locationId) {
		LocationId = locationId;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	@Override
	public String toString() {
		return "Location [LocationId=" + LocationId + ", Location=" + Location + "]";
	}
	
	
}
