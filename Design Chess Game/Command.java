public class Command {
    Piece piece;
    int curX, curY, desX, desY;
    public Commanc(Piece piece, int curX, int curY, int desX, int desY) {
        this.piece = piece; 
        this.curX = curX;
        this.curY = curY;
        this.desX = desX;
        this.desY = desY;
    }
}