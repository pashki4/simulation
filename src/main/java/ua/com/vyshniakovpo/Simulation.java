package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Creature;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.worldmap.WorldMap;
import ua.com.vyshniakovpo.worldmap.WorldMapRenderer;

import java.util.Map;

public class Simulation {

    private final WorldMap worldMap;
    private final WorldMapRenderer renderer;
    private int turnCount;

    public Simulation(WorldMap worldMap, WorldMapRenderer renderer) {
        this.worldMap = worldMap;
        this.renderer = renderer;
    }

    public void nextTurn() {
        worldMap.getEntities().entrySet()
                .stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .forEach(creature -> creature.makeMove(worldMap));
    }

    public void startSimulation() {

    }

    public void pauseSimulation() {

    }

    public void initActions() {
        Map<Coordinates, Entity> entities = worldMap.getEntities();
    }
}