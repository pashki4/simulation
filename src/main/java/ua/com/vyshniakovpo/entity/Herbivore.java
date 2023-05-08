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
        food = Grass.class;
    }

    @Override
    public void makeMove(WorldMap map) {
        while (map.isCellOccupied(coordinates) && movesCount != 0) {
            Node path = getPathToTheFood(map);
            Node nextMove = path.getParent();
            map.moveEntity(coordinates, nextMove.getCoordinates());
            movesCount--;
            threadSleep();
        }
        resetMovesCount();
    }

    private static void threadSleep() {
        try {
            Thread.sleep(1_500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SleepException("Trying to sleep, but was error", e);
        }
    }

    private Node getPathToTheFood(WorldMap map) {
        Coordinates closestGrass = map.getClosestFoodByClass(this.coordinates, food);
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

        List<Creature> creatures = map.getEntitiesByType(Creature.class);
        for (int i = 0; i < 4; i++) {
            for (Creature creature : creatures) {
                if (creature.hp != 0) {
                    creature.makeMove(map);
                    renderer.render(map);
                }
            }
        }
    }
}
