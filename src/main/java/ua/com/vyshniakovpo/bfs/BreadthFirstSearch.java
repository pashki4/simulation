package ua.com.vyshniakovpo.bfs;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.*;

public class BreadthFirstSearch {

    private BreadthFirstSearch() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static Node<Coordinates> search(Node<Coordinates> root, Node<Coordinates> target, WorldMap map) {
        Queue<Node<Coordinates>> queue = new LinkedList<>();
        List<Node<Coordinates>> visitedNodes = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<Coordinates> currentNode = queue.poll();
            if (currentNode.getValue().equals(target.getValue())) {
                return Node.reverse(currentNode);
            }
            List<Coordinates> neighborsCoordinates = currentNode.getValue().getNeighbors();
            List<Coordinates> validatedCoordinates = map.validate(neighborsCoordinates);

            for (var neighbor : Node.valueOf(validatedCoordinates)) {
                if (!visitedNodes.contains(neighbor) && !queue.contains(neighbor)) {
                    if (target.equals(neighbor)) {
                        neighbor.setParent(currentNode);
                        return Node.reverse(neighbor);
                    } else if (!map.isCellEmpty(neighbor.getValue())) {
                        visitedNodes.add(neighbor);
                        continue;
                    }
                    neighbor.setParent(currentNode);
                    queue.offer(neighbor);
                }
            }
            visitedNodes.add(currentNode);
        }
        return null;
    }
}
