package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Grass;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public class GrassSpawnAction extends SpawnAction {

    public GrassSpawnAction(WorldMap map) {
        super(map);
        this.rate = calculateRate();
    }

    @Override
    protected int getCurrentRate() {
        return (int) map.getEntities().stream()
                .filter(Grass.class::isInstance)
                .count();
    }

    @Override
    protected Entity spawnEntity() {
        return new Grass();
    }

    private int calculateRate() {
        return (int) (map.x * map.y * 0.1);
    }
}
