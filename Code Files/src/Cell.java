public class Cell {
    private int row;
    private int col;
    private char type;

    public Cell(int row, int col, char type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }



    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getType() {
        return type;
    }
}
