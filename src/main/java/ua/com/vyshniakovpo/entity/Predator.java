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

    private int strengh;

    public Predator() {
        super(1);
        this.strengh = 50;
    }

    @Override
    public void makeMove(WorldMap map) {
        while (movesCount != 0) {

            Coordinates closestTarget = map.getClosestTargetByClass(this.coordinates, Herbivore.class);
            Node target = Node.valueOf(closestTarget);
            Node root = Node.valueOf(coordinates);
            Node fullPath = BreadthFirstSearch.search(root, target, map);

            //may be null
            Node nextMove = fullPath.getParent();

            List<Coordinates> neighbors = root.getCoordinates().getNeighbors();
            List<Coordinates> validatedCoordinates = map.validateCoordinates(neighbors);

            if (validatedCoordinates.contains(target.getCoordinates())) {
                attack(map.getEntityByCoordinates(nextMove.getCoordinates()));
            } else {
                map.moveEntity(coordinates, nextMove.getCoordinates());
            }
            movesCount--;
            map.refresh();
            try {
                Thread.sleep(1_500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SleepException("Trying to sleep, but was error", e);
            }
        }
        resetMovesCount();
    }

    private void attack(Entity herbivore) {
        Herbivore herb = Herbivore.class.cast(herbivore);
        int hp = herb.getHp();
        if (hp > 0) {
            herb.setHp(herb.getHp() - strengh);
        }
    }

    private void resetMovesCount() {
        movesCount = 1;
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        Actions.initActions(map);

        renderer.render(map);

        List<Creature> predator = map.getCreaturesByType(Predator.class);

        for (int i = 0; i < 15; i++) {
            predator.get(0).makeMove(map);
            renderer.render(map);
        }
    }
}
