public class Position {
    private int x;
    private int y;
    private final Position prevPosition;


    public Position(int x, int y, Position prevPosition) {
        this.x = x;
        this.y = y;
        this.prevPosition = prevPosition;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPrevPosition() {
        return prevPosition;
    }

    public int getOneBasedX() {
        return x + 1;
    }

    public int getOneBasedY() {
        return y + 1;
    }
}
