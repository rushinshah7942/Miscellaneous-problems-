public class Piece {
    private int x;
    private int y;

    private boolean available; // mark the live or dead
    private int color; // mark the owner

    public Piece(boolean available, int x, int y, int color) {
        super();
        this.available = available;
        this.x = x;
        this.y = y;
        this.color = color;
    }


    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY){
        // different by character of piece
    }

}

public class King extends Piece{ 
    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
    }    
}
// ..... for Queen, Rook, Bishop, Pawn