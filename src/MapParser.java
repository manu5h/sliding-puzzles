import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapParser {
    public static Map parseMap(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int height = Integer.parseInt(scanner.nextLine());
            int width = Integer.parseInt(scanner.nextLine());

            Map map = new Map(height, width);

            for (int row = 0; row < height; row++) {
                String line = scanner.nextLine();
                for (int col = 0; col < width; col++) {
                    char type = line.charAt(col);
                    map.setCell(row, col, new Cell(row, col, type));
                }
            }

            scanner.close();
            return map;
        } catch (FileNotFoundException e) {
            System.err.println("Error reading map file: " + filename + " (" + e.getMessage() + ")");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Invalid map format: " + filename);
            System.exit(1);
        }
        return null;
    }
}
