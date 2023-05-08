package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.bfs.BreadthFirstSearch;
import ua.com.vyshniakovpo.bfs.Node;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private final int strength;

    public Predator() {
        super(2);
        this.food = Herbivore.class;
        this.strength = 50;
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
        }
        resetMovesCount();
    }

    private static void checkIfTheTargetWasKilled(WorldMap map, Node target) {
        if (((Creature) map.getEntity(target.getCoordinates())).getHp() == 0) {
            map.deleteEntity(target.getCoordinates());
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


}
