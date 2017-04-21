public class Slot {
    private final int id;
    private final int size;
    private boolean available;
    private Vehicle vehicle;

    public Slot(int id, int size) {
        this.id = id;
        this.size = size;
        this.available = true;
    }

    public void occupy(Vehicle v) {
        this.vehicle = v;
        this.available = false;
    }

    public void release() {
        this.vehicle = null;
        this.available = true;
    }
}