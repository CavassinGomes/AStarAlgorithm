import java.util.Objects;

class Node implements Comparable<Node> {
    public int x, y;
    public int gCost, hCost, fCost;
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void calculateCosts(Node endNode) {
        this.gCost = (parent != null) ? parent.gCost + 1 : 0;
        this.hCost = Math.abs(x - endNode.x) + Math.abs(y - endNode.y); // Distância Manhattan
        this.fCost = gCost + hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fCost, other.fCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}