package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Actions;
import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.bfs.BreadthFirstSearch;
import ua.com.vyshniakovpo.bfs.Node;
import ua.com.vyshniakovpo.exception.SleepException;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

import java.util.List;

public class Predator extends Creature {

    public Predator() {
        super(2);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        while (movesCount != 0) {
            movesCount--;
            move(worldMap);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SleepException("Trying to sleep, but was error", e);
            }
        }
        resetMovesCount();
    }

    private void resetMovesCount() {
        movesCount = 2;
    }

    private void move(WorldMap map) {
        Node<Coordinates> path = getPathToTarget(map);
        Node<Coordinates> target = path.getParent();
        map.moveEntity(coordinates, target.getValue());
    }

    private Node<Coordinates> getPathToTarget(WorldMap worldMap) {
        Coordinates closestHerbivore = worldMap.getClosestHerbivore(this.coordinates);
        Node<Coordinates> target = Node.valueOf(closestHerbivore);
        Node<Coordinates> root = Node.valueOf(coordinates);

        return BreadthFirstSearch.search(root, target, worldMap);
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        Actions.initActions(map);

        renderer.render(map);

        List<Predator> predator = map.getEntitiesByType(Predator.class);
        predator.get(0).makeMove(map);
        renderer.render(map);
    }
}
