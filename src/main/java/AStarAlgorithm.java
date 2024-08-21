import java.util.*;

public class AStarAlgorithm {

    private static final int[][] DIRECTIONS = {
            {0, -1}, {-1, 0}, {1, 0}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonais
    };

    public static List<Node> findPath(Node startNode, Node endNode, int[][] grid) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();

        startNode.calculateCosts(endNode);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedList.add(currentNode);

            if (currentNode.equals(endNode)) {
                return reconstructPath(currentNode);
            }

            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];

                if (isValidPosition(newX, newY, grid) && grid[newX][newY] == 0) {
                    Node neighbor = new Node(newX, newY);

                    if (closedList.contains(neighbor)) {
                        continue;
                    }

                    int tentativeGCost = currentNode.gCost + 1;

                    if (tentativeGCost < neighbor.gCost || !openList.contains(neighbor)) {
                        neighbor.parent = currentNode;
                        neighbor.calculateCosts(endNode);

                        if (!openList.contains(neighbor)) {
                            openList.add(neighbor);
                        }
                    }
                }
            }
        }

        return Collections.emptyList(); // Caminho não encontrado
    }

    private static boolean isValidPosition(int x, int y, int[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }

    private static List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = endNode;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        Collections.reverse(path);
        return path;
    }
}