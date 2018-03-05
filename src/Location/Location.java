package Location;

public class Location {

	private String locationName;
	private String isReachableWhenRain;
	private String isAirCon;
	private String distanceFromMom;
	private int noOfPeople;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getIsReachableWhenRain() {
		return isReachableWhenRain;
	}

	public void setIsReachableWhenRain(String isReachableWhenRain) {
		this.isReachableWhenRain = isReachableWhenRain;
	}

	public String getIsAirCon() {
		return isAirCon;
	}

	public void setIsAirCon(String isAirCon) {
		this.isAirCon = isAirCon;
	}

	public String getDistanceFromMom() {
		return distanceFromMom;
	}

	public void setDistanceFromMom(String distanceFromMom) {
		this.distanceFromMom = distanceFromMom;
	}

	public int getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	public String toString() {

		String output = "";

		output += locationName + ":";
		output += isReachableWhenRain + ":";
		output += isAirCon + ":";
		output += distanceFromMom + ":";
		output += noOfPeople;

		return output;

	}
}
