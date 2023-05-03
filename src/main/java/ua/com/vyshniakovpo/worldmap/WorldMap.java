package ua.com.vyshniakovpo.worldmap;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class WorldMap {
    private final Map<Coordinates, Entity> entities = new HashMap<>();
    public final int x;
    public final int y;

    public WorldMap(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public <T extends Entity> List<Entity> getEntitiesByType(Class<T> cls) {
        return entities.values()
                .stream()
                .filter(entity -> entity.getClass().equals(cls))
                .collect(Collectors.toList()
                );
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Coordinates getRandomEmptyCoordinates() {
        while (true) {
            int x = ThreadLocalRandom.current().nextInt(this.x);
            int y = ThreadLocalRandom.current().nextInt(this.y);
            Coordinates emptyCoordinates = new Coordinates(x, y);
            if (!entities.containsKey(emptyCoordinates)) {
                return emptyCoordinates;
            }
        }
    }
}
