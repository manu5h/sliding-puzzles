import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map map = MapParser.parseMap("Puzzle.txt");
        if (map != null) {
            System.out.println("Parsed Map:");
            System.out.println(map);

            List<Position> shortestPath = findShortestPath(map);
            if (shortestPath != null) {
                System.out.println("\nShortest Path:");
                printShortestPath(map.getStartX(),map.getStartY(), shortestPath);
            } else {
                System.out.println("\nNo path found from start to finish!");
            }
        } else {
            System.out.println("Failed to parse the map.");
        }
    }

    // Finding the shortest path using DIJKSTRA's Algorithm
    public static List<Position> findShortestPath(Map map) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Start BFS from the initial position
        queue.add(new Position(map.getStartX(),map.getStartY(), null));
        visited[map.getStartY()][map.getStartX()] = true;

        // Continue until all reachable positions are processed
        while (!queue.isEmpty()) {
            Position currentPosition = queue.poll();
            int x = currentPosition.getX();
            int y = currentPosition.getY();

            if (map.getCell(x, y) == 'F') {
                Position lastPosition = currentPosition;
                List<Position> path = new ArrayList<>();
                // Traverse backwards from the last position to the start position
                while (lastPosition != null) {
                    path.addFirst(lastPosition); // Add the current position to the front of the list
                    lastPosition = lastPosition.getPreviousPosition(); // Move to the previous position
                }

                return path;
            }

            for (int[] dir : directions) {
                int newX = x;
                int newY = y;

                while (map.isPassable( newX + dir[0], newY + dir[1])) {
                    newX += dir[0];
                    newY += dir[1];
                    if (map.getCell(newX, newY) != '.') {
                        break;
                    }
                }

                if (!visited[newY][newX]) {
                    queue.add(new Position(newX, newY, currentPosition));
                    visited[newY][newX] = true;
                }
            }
        }

        return null; // No path found
    }

    // represent a node on the map with its coordinates and distance from the start
    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    //print the shortest path
    private static void printShortestPath(int startX, int startY, List<Position> path) {
        System.out.println("1. Start at (" + (startY + 1) + "," + (startX + 1) + ")");
        int step = 2;

        for (int i = 1; i < path.size(); i++) {
            Position current = path.get(i);
            Position previous = path.get(i - 1);

            System.out.print(step++ + ". ");

            int currentX = current.getX();
            int currentY = current.getY();
            int prevX = previous.getX();
            int prevY = previous.getY();

            if (currentX > prevX) {
                System.out.print("Move down ");
            } else if (currentX < prevX) {
                System.out.print("Move up ");
            } else if (currentY > prevY) {
                System.out.print("Move right ");
            } else if (currentY < prevY) {
                System.out.print("Move left ");
            }

            System.out.println("to (" + (currentY + 1) + "," + (currentX + 1) + ")");
        }

        System.out.println(step + ". Done!");
    }


}
