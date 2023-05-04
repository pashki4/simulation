package ua.com.vyshniakovpo.bfs;

import ua.com.vyshniakovpo.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<T extends Coordinates> {
    private List<Node<T>> neighbors;
    private final T value;

    private Node<T> parent;

    public Node(T value) {
        this.value = value;
    }

    public static <T extends Coordinates> Node<T> valueOf(T value) {
        return new Node<>(value);
    }

    public static <T extends Coordinates> List<Node<T>> valueOf(List<T> validatedCoordinates) {
        return validatedCoordinates.stream()
                .map(Node::valueOf)
                .toList();
    }

    public List<Node<T>> getNeighbors() {
        neighbors = Node.valueOf(value.getNeighbors());
        return neighbors;
    }

    public T getValue() {
        return value;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public static <T extends Coordinates> Node<T> reverse(Node<T> node) {
        Node<T> current = node;
        Node<T> prev = null;
        Node<T> next;
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
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + value +
                ", parent=" + parent +
                '}';
    }
}
