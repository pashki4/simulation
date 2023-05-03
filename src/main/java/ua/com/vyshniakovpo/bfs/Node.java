package ua.com.vyshniakovpo.bfs;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    private final List<Node<T>> neighbors = new LinkedList<>();
    private T value;

    private Node<T> parent;

    public List<Node<T>> getNeighbors() {
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

    public static <T> Node<T> reverse(Node<T> node) {
        Node<T> current = node.getParent();
        Node<T> prev = null;
        Node<T> next = null;
        while (current != null) {
            next = current.getParent();
            current.setParent(prev);
            prev = current;
            current = next;
        }
        return prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + value +
                ", neighbors=" + neighbors +
                '}';
    }

}
