package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Herbivore;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public class HerbivoreSpawnAction extends SpawnAction {

    public HerbivoreSpawnAction(WorldMap map) {
        super(map);
        this.rate = calculateRate();
    }

    @Override
    protected int getCurrentRate() {
        return (int) map.getEntities().stream()
                .filter(Herbivore.class::isInstance)
                .count();
    }

    @Override
    protected Entity spawnEntity() {
        return new Herbivore();
    }

    private int calculateRate() {
        return (int) (map.x * map.y * 0.05);
    }
}
