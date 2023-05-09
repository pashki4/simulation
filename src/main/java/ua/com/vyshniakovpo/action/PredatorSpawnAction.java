package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Predator;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public class PredatorSpawnAction extends SpawnAction {

    public PredatorSpawnAction(WorldMap map) {
        super(map);
        this.rate = calculateRate();
    }

    @Override
    protected int getCurrentRate() {
        return (int) map.getEntities().stream()
                .filter(Predator.class::isInstance)
                .count();
    }

    @Override
    protected Entity spawnEntity() {
        return new Predator();
    }

    private int calculateRate() {
        return (int) (map.x * map.y * 0.02);
    }
}
