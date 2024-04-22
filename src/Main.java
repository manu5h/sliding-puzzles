import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map map = parseMap("E:\\Sliding Puzzle\\src\\map.txt");
        System.out.println("Parsed Map:");
        System.out.println(map);

        List<Cell> shortestPath = dijkstraShortestPath(map);
        if (shortestPath != null) {
            System.out.println("\nShortest Path:");
            printShortestPath(shortestPath);
        } else {
            System.out.println("\nNo path found from start to finish!");
        }
    }

    private static Map parseMap(String filename) {
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

    private static List<Cell> dijkstraShortestPath(Map map) {
        Cell start = map.getStart();
        Cell end = map.getEnd();

        // Initialize distances map with maximum values
        java.util.Map<Cell, Integer> distances = new HashMap<>();
        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {
                distances.put(map.getCell(row, col), Integer.MAX_VALUE);
            }
        }
        distances.put(start, 0);

        // Initialize priority queue for Dijkstra's algorithm
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        pq.offer(start);

        // Initialize previous node map
        java.util.Map<Cell, Cell> prev = new HashMap<>();

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            if (current == end) break; // Reached the end point

            // Explore neighbors
            for (Cell neighbor : map.getNeighbors(current)) {
                int altDistance = distances.get(current) + 1; // Assuming all edges have weight of 1
                if (altDistance < distances.get(neighbor)) {
                    distances.put(neighbor, altDistance);
                    prev.put(neighbor, current);
                    pq.offer(neighbor);
                }
            }
        }

        // Reconstruct the shortest path
        List<Cell> shortestPath = new ArrayList<>();
        Cell current = end;
        while (prev.containsKey(current)) {
            shortestPath.add(current);
            current = prev.get(current);
        }
        Collections.reverse(shortestPath);

        return shortestPath.isEmpty() ? null : shortestPath;
    }

    private static void printShortestPath(List<Cell> path) {
        int step = 1;
        for (int i = 1; i < path.size(); i++) {
            Cell current = path.get(i);
            Cell previous = path.get(i - 1);
            System.out.print(step++ + ". ");

            if (current.getRow() > previous.getRow()) {
                System.out.print("Move down ");
            } else if (current.getRow() < previous.getRow()) {
                System.out.print("Move up ");
            } else if (current.getCol() > previous.getCol()) {
                System.out.print("Move right ");
            } else if (current.getCol() < previous.getCol()) {
                System.out.print("Move left ");
            }

            System.out.println("to (" + (current.getCol() + 1) + "," + (current.getRow() + 1) + ")");
        }
        System.out.println(step + ". Done!");
    }
}
