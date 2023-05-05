package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.bfs.BreadthFirstSearch;
import ua.com.vyshniakovpo.bfs.Node;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public abstract class Creature<T extends Entity> extends Entity {
    protected Class<T> targetClass;
    protected int movesCount;
    private int hp = 100;

    protected Creature(int movesCount) {
        this.movesCount = movesCount;
    }

    protected void move(WorldMap map) {
        Node<Coordinates> path = getPathToTarget(map);
        Node<Coordinates> target = path.getParent();
        map.moveEntity(coordinates, target.getValue());
    }

    private Node<Coordinates> getPathToTarget(WorldMap worldMap) {
        Coordinates closestTarget = worldMap.getClosestTargetByClass(this.coordinates, targetClass);
        Node<Coordinates> target = Node.valueOf(closestTarget);
        Node<Coordinates> root = Node.valueOf(coordinates);

        return BreadthFirstSearch.search(root, target, worldMap);
    }

    public abstract void makeMove(WorldMap worldMap);

}
