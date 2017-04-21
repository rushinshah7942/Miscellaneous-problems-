public class Vehicle {
    private final int size;
    private final int lisense;
    private boolean status;
    private Lot lot;

    public Vehicle(int size) {
        this.size = size;
        lisense = this.hashCode();
        lot = Lot.getInstance(); // singleton object
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private Slot findSlot() {

        Slot slot;
        switch (this.size) {
        case 1:
            slot = lot.getSmallSlots().remove(0);
        case 2:
            slot = lot.getCompactSlots().remove(0);
        case 3:
            slot = lot.getLargeSlots().remove(0);
        default:
            slot = null;
        }
        return slot;
    }

    public void park() {
        Slot slot = findSlot();
        if (slot != null) {
            lot.occupiedSlots.put(this.lisense, slot);
            slot.occupy(this);
        }
    }

    public void leave() {
        Slot slot = lot.occupiedSlots.remove(this.lisense);
        slot.release();
        switch (this.size) {
        case 1:
            lot.getSmallSlots().add(slot);
        case 2:
            lot.getCompactSlots().add(slot);
        case 3:
            lot.getLargeSlots().add(slot);
        }
    }
}

public class Car extends Vehicle{
    public Car(){
        super(1);        
    }
}
public class Truck extends Vehicle{
    public Truck(){
        super(2);        
    }
}
// ... other type of vehicle