public class Map {
    private char[][] map;
    private int width;
    private int height;
    private int startX;
    private int startY;


    // Constructor to initialize the map
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new char[height][width];
    }

    // Method to set a cell's content at specific coordinates
    public void setCell(int x, int y, char content) {
        map[y][x] = content;
        if (content == 'S') {
            startX = x;
            startY = y;
        }
    }

    // Method to check if a cell is passable (not a wall or obstacle)
    public boolean isPassable(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false; // Out of bounds
        }
        char cellContent = map[y][x];
        return cellContent == '.' || cellContent == 'S' || cellContent == 'F';
    }

    public char getCell(int x, int y) {
        return map[y][x];
    }

    // Getter methods for map dimensions and positions
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
