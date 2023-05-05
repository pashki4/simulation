package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Actions;
import ua.com.vyshniakovpo.exception.SleepException;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapConsoleRenderer;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore() {
        super(1);
        this.targetClass = Grass.class;
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        while (movesCount != 0) {
            movesCount--;
            move(worldMap);
            try {
                Thread.sleep(1_500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SleepException("Trying to sleep, but was error", e);
            }
        }
        resetMovesCount();
    }

    private void resetMovesCount() {
        movesCount = 1;
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap(7, 7);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        Actions.initActions(map);

        renderer.render(map);

        List<Herbivore> herbivore = map.getEntitiesByType(Herbivore.class);

        for (int i = 0; i < 2; i++) {
            herbivore.get(0).makeMove(map);
            renderer.render(map);
            herbivore.get(1).makeMove(map);
            renderer.render(map);
            herbivore.get(2).makeMove(map);
            renderer.render(map);
        }
    }
}
