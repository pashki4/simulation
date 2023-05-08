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

    private final int strength;

    public Predator() {
        super(2);
        this.strength = 50;
        this.food = Herbivore.class;
    }

    @Override
    public void makeMove(WorldMap map) {
        while (movesCount != 0) {
            Coordinates closestTarget = map.getClosestFoodByClass(this.coordinates, food);
            Node start = Node.valueOf(coordinates);
            Node target = Node.valueOf(closestTarget);
            Node fullPath = BreadthFirstSearch.search(start, target, map);

            Node nextMove = fullPath.getParent();

            List<Coordinates> neighbors = start.getCoordinates().getNeighbors();
            List<Coordinates> validatedCoordinates = map.validateCoordinates(neighbors);

            if (validatedCoordinates.contains(target.getCoordinates())) {
                attack(map.getEntity(nextMove.getCoordinates()));
            } else {
                map.moveEntity(coordinates, nextMove.getCoordinates());
            }
            movesCount--;
            checkIfTheTargetWasKilled(map, target);
            threadSleep();
        }
        resetMovesCount();
    }

    private static void checkIfTheTargetWasKilled(WorldMap map, Node target) {
        if (((Creature) map.getEntity(target.getCoordinates())).getHp() == 0) {
            map.deleteEntity(target.getCoordinates());
        }
    }

    private static void threadSleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SleepException("Trying to sleep, but was error", e);
        }
    }

    private void attack(Entity herbivore) {
        Herbivore herb = (Herbivore) herbivore;
        int hp = herb.getHp();
        if (hp > 0) {
            herb.setHp(herb.getHp() - strength);
        }
    }

    private void resetMovesCount() {
        movesCount = 2;
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        Actions.initActions(map);
        renderer.render(map);

        List<Creature> predator = map.getEntitiesByType(Predator.class);
        for (int i = 0; i < 15; i++) {
            predator.get(0).makeMove(map);
            renderer.render(map);
        }
    }
}
