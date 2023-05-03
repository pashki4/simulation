package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(7, 7);
        Actions.initActions(worldMap);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(worldMap);
    }
}