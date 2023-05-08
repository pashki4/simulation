package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Tree;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public class TreeSpawnAction extends SpawnAction {
    public TreeSpawnAction(WorldMap map) {
        super(map);
        this.rate = calculateRate();
    }

    @Override
    protected int getCurrentRate() {
        return (int) map.getEntities().stream()
                .filter(Tree.class::isInstance)
                .count();
    }

    @Override
    protected Entity spawnEntity() {
        return new Tree();
    }

    private int calculateRate() {
        return (int) (map.x * map.y * 0.06);
    }
}
