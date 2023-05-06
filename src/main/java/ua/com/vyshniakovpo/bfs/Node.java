package ua.com.vyshniakovpo.bfs;

import ua.com.vyshniakovpo.Coordinates;

import java.util.List;
import java.util.Objects;

public class Node {

    private final Coordinates coordinates;

    private Node parent;

    public Node(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public static Node valueOf(Coordinates value) {
        return new Node(value);
    }

    public static List<Node> valueOf(List<Coordinates> validatedCoordinates) {
        return validatedCoordinates.stream()
                .map(Node::valueOf)
                .toList();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public static Node reverse(Node node) {
        Node current = node;
        Node prev = null;
        Node next;
        while (current != null) {
            next = current.getParent();
            current.setParent(prev);
            prev = current;
            current = next;
        }
        return prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(coordinates, node.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + coordinates +
                ", parent=" + parent +
                '}';
    }
}
