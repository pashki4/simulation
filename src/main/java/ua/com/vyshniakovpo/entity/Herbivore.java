package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Actions;
import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.bfs.BreadthFirstSearch;
import ua.com.vyshniakovpo.bfs.Node;
import ua.com.vyshniakovpo.exception.SleepException;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore() {
        super(1);
    }

    @Override
    public void makeMove(WorldMap map) {
        while (map.isCellOccupied(coordinates) && movesCount != 0) {
            Node path = getPathToTarget(map);
            Node nextMove = path.getParent();
            map.moveEntity(coordinates, nextMove.getCoordinates());
            movesCount--;
            try {
                Thread.sleep(1_500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SleepException("Trying to sleep, but was error", e);
            }
        }
        resetMovesCount();
    }

    private Node getPathToTarget(WorldMap map) {
        Coordinates closestGrass = map.getClosestTargetByClass(this.coordinates, Grass.class);
        Node target = Node.valueOf(closestGrass);
        Node root = Node.valueOf(coordinates);

        return BreadthFirstSearch.search(root, target, map);
    }

    private void resetMovesCount() {
        movesCount = 1;
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        Actions.initActions(map);
        renderer.render(map);

        List<Creature> herbivore = map.getCreaturesByType(Herbivore.class);

        for (int i = 0; i < 2; i++) {
           for(Creature creature : herbivore) {
               creature.makeMove(map);
               renderer.render(map);
           }
        }
    }
}
