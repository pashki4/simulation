package ua.com.vyshniakovpo.bfs;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    private BreadthFirstSearch() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static Node search(Node root, Node target, WorldMap map) {
        Queue<Node> queue = new LinkedList<>();
        List<Node> visitedNodes = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.getCoordinates().equals(target.getCoordinates())) {
                return Node.reverse(currentNode);
            }
            List<Coordinates> neighborsCoordinates = currentNode.getCoordinates().getNeighbors();
            List<Coordinates> validatedCoordinates = map.validateCoordinates(neighborsCoordinates);

            for (var neighbor : Node.valueOf(validatedCoordinates)) {
                if (!visitedNodes.contains(neighbor) && !queue.contains(neighbor)) {
                    if (target.equals(neighbor)) {
                        neighbor.setParent(currentNode);
                        return Node.reverse(neighbor);
                    } else if (map.isCellExists(neighbor.getCoordinates())) {
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
