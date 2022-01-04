public class VehicleHiringManager {
	LocatorMap<String> LM;

	public VehicleHiringManager() {
		LM= new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return LM;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		LM=locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		if(LM.add(id, loc).first==true)
		return true;
		else
		return false;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		if(LM.move(id, loc).first==true)
			return true;
			else
			return false;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		if(LM.remove(id).first==true)
			return true;
			else
			return false;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		return LM.getLoc(id).first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		Pair<List<String>,Integer> pai=LM.getInRange(new Location(loc.x-r,loc.y-r),new Location(loc.x+r,loc.y+r));
		return pai.first;
				
		
	}
}
