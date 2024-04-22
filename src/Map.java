import java.util.ArrayList;
import java.util.List;

public class Map {
    private int height;
    private int width;
    private Cell[][] cells;
    private Cell start;
    private Cell end;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
        if (cell.getType() == 'S') {
            start = cell;
        } else if (cell.getType() == 'F') {
            end = cell;
        }
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    public List<Cell> getNeighbors(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        List<Cell> neighbors = new ArrayList<>();
        if (isValid(row - 1, col)) neighbors.add(cells[row - 1][col]); // Up
        if (isValid(row + 1, col)) neighbors.add(cells[row + 1][col]); // Down
        if (isValid(row, col - 1)) neighbors.add(cells[row][col - 1]); // Left
        if (isValid(row, col + 1)) neighbors.add(cells[row][col + 1]); // Right
        return neighbors;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width && cells[row][col].getType() != '0';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sb.append(cells[row][col].getType()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
