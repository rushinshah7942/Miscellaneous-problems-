/*

Design a parking lot system where you need to provide a token with the parking space number on it to each new entry for the space closest to the entrance. 

When someone leave you need update this space as empty. 

What data structures will you use to perform the closest empty space tracking, plus finding what all spaces are occupied at a give time.

*/

/*
Solution
----------
For maintaining the nearest empty slot use min heap. Put the slot when someone leaves and remove and give when someone arrives. For maintaining filled slots use hash map. Remove when someone leaves and add when someone arrives.
*/

public class ParkingLot {
	
	private static final int maxFloors = 5;
	private static final int maxSlotsPerFloor = 10;
	
	// initially all slots can be empty 
	// handles empty parking spaces and slot
	private PriorityQueue<ParkingSpace> pq = new PriorityQueue<>((maxFloors * maxSlotsPerFloor), 
			new Comparator<ParkingSpace>() {
				@Override
				public int compare(ParkingSpace o1, ParkingSpace o2) {
					
					if(o1.getFloor() == o2.getFloor()) {
						return o1.getSlot() - o2.getSlot();
					}
					else 
						return o1.getFloor() - o2.getFloor();					
				}				
			}
	);
	
	class ParkingSpace {
	
		private int floor;
		private int slot;
		
		public int getFloor() {
			return floor;
		}
		
		public int getSlot() {
			return slot;
		}
		
		public ParkingSpace(int floor, int slot) {
		
			if(floor > maxFloors || slot > maxSlotsPerFloor) {
				throw new IllegalArgumentException("Capacity is 5 floors and 10 slots per floor.");
			}
			
			this.floor = floor;
			this.slot = slot;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof ParkingSpace) {
				ParkingSpace ps = (ParkingSpace) obj;
				return (this.getFloor() == ps.getFloor() &&
						this.getSlot() == ps.getSlot());
			}
			return false;
		}
	}	

	
	public ParkingSpace park(){
		ParkingSpace ps = getNextAvailable();
		if(ps == null) {
			throw new IllegalStateException("Parking Lot is Full.");
		}
		pq.remove(ps);
		return ps;
	}
	
	public void unpark(int floor, int slot) {
		ParkingSpace ps = new ParkingSpace(floor, slot);
		if(!pq.contains(ps)) {
			pq.add(ps);
		}
		else {
			throw new IllegalStateException("Invalid Parking Lot.");
		}
		
	}
	
	public void addParkingSpace (int floor, int slot) {
		ParkingSpace ps = new ParkingSpace(floor, slot);
		pq.add(ps);
	}
	
	public ParkingSpace getNextAvailable(){
		if(pq.size() > 0)
		{
			return pq.peek();	
		}
		return null;
	}
	
	public static void main(String[] args) {

		ParkingLot pl = new ParkingLot();
		
		pl.addParkingSpace(1, 1);
		pl.addParkingSpace(2, 1);
		pl.addParkingSpace(3, 1);
		pl.addParkingSpace(1, 2);
		pl.addParkingSpace(2, 2);
		pl.addParkingSpace(3, 2);
		
		ParkingSpace n = pl.getNextAvailable();
		System.out.println("Parked at Floor: " + n.getFloor() + ", Slot: " + n.getSlot());
		pl.unpark(2, n.getSlot());
		
	}
}