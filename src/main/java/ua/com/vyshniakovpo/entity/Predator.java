package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Actions;
import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.bfs.BreadthFirstSearch;
import ua.com.vyshniakovpo.bfs.Node;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

import java.util.List;

public class Predator extends Creature {

    public Predator() {
        super(2);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        Coordinates closestHerbivore = worldMap.getClosestHerbivore(this.coordinates);
        Node<Coordinates> target = Node.valueOf(closestHerbivore);
        Node<Coordinates> root = Node.valueOf(coordinates);

        Node<Coordinates> path = BreadthFirstSearch.search(root, target, worldMap);
        System.out.println(path);
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        Actions.initActions(map);

        renderer.render(map);

        List<Predator> predator = map.getEntitiesByType(Predator.class);
        predator.get(0).makeMove(map);
    }
}
