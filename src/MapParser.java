import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapParser {
    public static Map parseMap(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            // Read the dimensions of the map
            int height = 0;
            int width = -1; // Initialize to -1 to ensure consistency check

            List<String> lines = new ArrayList<>();

            // Read all lines from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);

                // Update width if needed
                if (width == -1 || line.length() > width) {
                    width = line.length();
                }

                height++; // Increment height for each line read
            }

            Map map = new Map(height, width);

            // Populate the map with the lines read
            for (int row = 0; row < height; row++) {
                String line = lines.get(row);
                for (int col = 0; col < line.length(); col++) {
                    char type = line.charAt(col);
                    map.setCell(row, col, new Cell(row, col, type));
                }
            }

            scanner.close();
            return map;
        } catch (FileNotFoundException e) {
            System.err.println("Error reading map file: " + filename + " (" + e.getMessage() + ")");
            System.exit(1);
        }
        return null;
    }
}
