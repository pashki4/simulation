package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.action.*;
import ua.com.vyshniakovpo.exception.SleepException;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {

    private final WorldMap map;
    private final WorldMapRenderer renderer;

    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> moveActions = new ArrayList<>();
    private int turnCount;

    private boolean running;

    public Simulation(WorldMap map, WorldMapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
        addActions();
    }

    @Override
    public void run() {
        while (running) {
            nextTurn();
            System.out.println("Turn count: " + ++turnCount);
            threadSleep();
        }
    }

    public void nextTurn() {
        initActions.forEach(Action::perform);
        moveActions.forEach(Action::perform);
        renderer.render(map);
    }

    public void startSimulation() {
        running = true;
        new Thread(this).start();
    }

    public void pauseSimulation() {
        running = false;
    }

    private static void threadSleep() {
        try {
            Thread.sleep(1_500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SleepException("Trying to sleep, but was error", e);
        }
    }

    private void addActions() {
        initActions.add(new GrassSpawnAction(map));
        initActions.add(new HerbivoreSpawnAction(map));
        initActions.add(new PredatorSpawnAction(map));
        initActions.add(new RockSpawnAction(map));
        initActions.add(new TreeSpawnAction(map));
        moveActions.add(new MoveAction(map));
    }
}