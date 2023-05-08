package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.action.*;
import ua.com.vyshniakovpo.exception.SleepException;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final WorldMap map;
    private final WorldMapRenderer renderer;

    private final List<Action> actions = new ArrayList<>();
    private int turnCount;

    public Simulation(WorldMap map, WorldMapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
        addActions();
    }

    public void nextTurn() {

    }

    public void startSimulation() {
        while (true) {
            for (Action action : actions) {
                action.perform();
            }
            renderer.render(map);
            System.out.println(++turnCount);
            threadSleep();
        }
    }

    private static void threadSleep() {
        try {
            Thread.sleep(1_500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SleepException("Trying to sleep, but was error", e);
        }
    }

    public void pauseSimulation() {
    }

    private void addActions() {
        actions.add(new GrassSpawnAction(map));
        actions.add(new HerbivoreSpawnAction(map));
        actions.add(new PredatorSpawnAction(map));
        actions.add(new RockSpawnAction(map));
        actions.add(new TreeSpawnAction(map));
        actions.add(new MoveAction(map));
    }
}