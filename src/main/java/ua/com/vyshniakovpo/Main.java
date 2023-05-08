package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;
import ua.com.vyshniakovpo.worldmap.WorldMapRenderer;

public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(10, 10);
        WorldMapRenderer renderer = new WorldMapConsoleRenderer();
        Simulation simulation = new Simulation(map, renderer);
        simulation.startSimulation();
    }
}