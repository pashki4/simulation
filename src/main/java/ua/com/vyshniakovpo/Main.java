package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Creature;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        Actions.initActions(map);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(map);

        for (Entity entity : map.getEntities()) {
            if (entity instanceof Creature creature) {
                creature.makeMove(map);
                renderer.render(map);
            }
        }
    }
}