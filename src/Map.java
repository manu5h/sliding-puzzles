import java.util.*;

public class Map {
    private final int height;
    private final int width;
    private final Cell[][] cells;
    private Cell start;
    private Cell end;

    // Constructor to initialize the map with specified height and width
    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
    }

    // Getter methods for the height and width of the map
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // Getter method to retrieve the cell at the specified row and column
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    // Setter method to set the cell at the specified row and column
    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
        char type = cell.getType();
        if (type == 'S') {
            start = cell;
        }
        else if (type == 'F') {
            end = cell;
        }
    }

    // Getter methods to retrieve the start and end cells of the map
    public Cell getStart() {
        return start;
    }
    public Cell getEnd() {
        return end;
    }

    // Method to check if a cell at the specified row and column is passable
    public boolean isPassable(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width && cells[row][col] != null && cells[row][col].getType() != '0';
    }

    // Method to generate a string representation of the map
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
