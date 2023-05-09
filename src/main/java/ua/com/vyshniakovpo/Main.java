package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(new WorldMap(10, 10), new WorldMapConsoleRenderer());

        simulation.startSimulation();

        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            if (input != null) {
                simulation.pauseSimulation();
            }
        }
    }
}
