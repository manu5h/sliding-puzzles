import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map map = MapParser.parseMap("Puzzle.txt");
        if (map != null) {
            System.out.println("Parsed Map:");
            System.out.println(map);

            List<Cell> shortestPath = findShortestPath(map);
            if (shortestPath != null) {
                System.out.println("\nShortest Path:");
                printShortestPath(map.getStart(), shortestPath);
            } else {
                System.out.println("\nNo path found from start to finish!");
            }
        } else {
            System.out.println("Failed to parse the map.");
        }
    }

    // Finding the shortest path using DIJKSTRA's Algorithm
    private static List<Cell> findShortestPath(Map map) {
        Cell start = map.getStart();
        Cell end = map.getEnd();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Right, Left, Down, Up

        // Priority queue to prioritize nodes with the shortest known distance
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start.getRow(), start.getCol(), 0));

        // Array to keep track of the shortest distance to each cell
        int[][] distance = new int[map.getHeight()][map.getWidth()];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start.getRow()][start.getCol()] = 0;

        // Array to keep track of the parent node for each cell to reconstruct the path
        Cell[][] parent = new Cell[map.getHeight()][map.getWidth()];
        .
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            Cell currentCell = map.getCell(current.x, current.y);

            if (currentCell == end) {
                break; // Reached the finish, stop the algorithm
            }

            for (int[] dir : directions) {
                int nextX = current.x;
                int nextY = current.y;
                int steps = 0;

                // Simulate sliding until hitting a wall or obstacle
                while (map.isPassable(nextX + dir[0], nextY + dir[1])) {
                    nextX += dir[0];
                    nextY += dir[1];
                    steps++;
                }

                // Calculate the total distance to reach the next cell
                int nextDistance = current.distance + steps;
                if (nextDistance < distance[nextX][nextY]) {
                    distance[nextX][nextY] = nextDistance;
                    parent[nextX][nextY] = currentCell;
                    pq.offer(new Node(nextX, nextY, nextDistance));
                }
            }
        }

        // Reconstruct the path from finish to start using the parent array
        List<Cell> shortestPath = new ArrayList<>();
        Cell current = end;

        while (current != null) {
            shortestPath.add(current);
            current = parent[current.getRow()][current.getCol()];
        }

        Collections.reverse(shortestPath);
        return shortestPath.isEmpty() ? null : shortestPath;
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
    private static void printShortestPath(Cell start, List<Cell> path) {
        System.out.println("1. Start at (" + (start.getCol() + 1) + "," + (start.getRow() + 1) + ")");
        int step = 2;
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
