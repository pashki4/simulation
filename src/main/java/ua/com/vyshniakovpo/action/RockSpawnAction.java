package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Rock;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public class RockSpawnAction extends SpawnAction {
    public RockSpawnAction(WorldMap map) {
        super(map);
        this.rate = calculateRate();
    }

    @Override
    protected int getCurrentRate() {
        return (int) map.getEntities().stream()
                .filter(Rock.class::isInstance)
                .count();
    }

    @Override
    protected Entity spawnEntity() {
        return new Rock();
    }

    private int calculateRate() {
        return (int) (map.x * map.y * 0.06);
    }
}
