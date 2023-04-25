package ua.com.vyshniakovpo.field;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Grass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Field {
    private final Map<Coordinates, Entity> entities = new HashMap<>();
    public final int x;
    public final int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public List<Entity> getEntities(String name) {
        return entities.values()
                .stream()
                .filter(Grass.class::isInstance)
                .collect(Collectors.toList());
    }

    public Coordinates getEmptyCoordinates() {
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
