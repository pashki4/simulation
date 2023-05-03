package ua.com.vyshniakovpo.bfs;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    private BreadthFirstSearch() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static Node<Coordinates> search(Node<Coordinates> root, Node<Coordinates> target, WorldMap map) {
        Queue<Node<Coordinates>> queue = new ArrayDeque<>();
        List<Node<Coordinates>> visitedNodes = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<Coordinates> currentNode = queue.poll();

            if (currentNode.getValue().equals(target.getValue())) {
                return Node.reverse(currentNode);
            }

            visitedNodes.add(currentNode);
            for (var neighbor : currentNode.getNeighbors()) {
                if (!visitedNodes.contains(neighbor) && map.isCellEmpty(neighbor.getValue())) {
                    neighbor.setParent(currentNode);
                    queue.offer(neighbor);
                }
            }
        }
        return null;
    }
}
