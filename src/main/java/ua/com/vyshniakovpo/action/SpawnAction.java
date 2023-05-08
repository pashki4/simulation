package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.worldmap.WorldMap;

public abstract class SpawnAction extends Action {
    protected int rate;

    protected SpawnAction(WorldMap map) {
        super(map);
    }

    @Override
    public void perform() {
        int currentRate = getCurrentRate();
        while (currentRate < rate) {
            Coordinates emptyCoordinates = map.getRandomEmptyCoordinates();
            Entity entity = spawnEntity();
            entity.setCoordinates(emptyCoordinates);
            map.addEntity(emptyCoordinates, entity);
            currentRate++;
        }
    }

    protected abstract int getCurrentRate();

    protected abstract Entity spawnEntity();
}