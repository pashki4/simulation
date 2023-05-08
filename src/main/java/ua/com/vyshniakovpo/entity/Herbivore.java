package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.bfs.BreadthFirstSearch;
import ua.com.vyshniakovpo.bfs.Node;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public class Herbivore extends Creature {

    public Herbivore() {
        super(1);
        food = Grass.class;
    }

    @Override
    public void makeMove(WorldMap map) {
        while (map.isCellExists(coordinates) && movesCount != 0) {
            Node path = getPathToTheFood(map);
            Node nextMove = path.getParent();
            map.moveEntity(coordinates, nextMove.getCoordinates());
            movesCount--;
        }
        resetMovesCount();
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
}
