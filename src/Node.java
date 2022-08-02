/**
 * Node to hold easy-to-modify key and comparable value pairs
 */
public class Node implements Comparable<Node> {
    public String key;
    public int val;
    public Node(String key, int val) {
        this.key = key;
        this.val = val;
    }

    // prioritize higher values
    public int compareTo(Node other) {
        return other.val - this.val;
    }


}
