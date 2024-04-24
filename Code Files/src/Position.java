public class Position {
    private int x;
    private int y;
    private final Position previousPosition;


    public Position(int x, int y, Position previousPosition) {
        this.x = x;
        this.y = y;
        this.previousPosition = previousPosition;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

}
