public class Node {
    double val;
    int n;
    public int compare(Node node) {
        return Double.compare(node.val, this.val);
    }

    public int compareTo(Object var1) {
        return this.compare((Node) var1);
    }

    public Node(double val, int index) {
        this.val = val;
        this.n = index;
    }
}
