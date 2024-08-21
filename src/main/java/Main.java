import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        Node startNode = new Node(0, 0);
        Node endNode = new Node(4, 4);

        List<Node> path = AStarAlgorithm.findPath(startNode, endNode, grid);

        if (!path.isEmpty()) {
            for (Node node : path) {
                System.out.println("Path: (" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("Caminho não encontrado!");
        }
    }}
