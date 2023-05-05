package ua.com.vyshniakovpo.worldmap;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;

import java.util.*;
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

    public <T extends Entity> List<T> getEntitiesByType(Class<T> clazz) {
        return entities.values()
                .stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public <T extends Entity> Coordinates getClosestTargetByClass(Coordinates coordinates, Class<T> clazz) {
        TreeMap<Double, List<Entity>> herbivores = entities.values().stream()
                .filter(clazz::isInstance)
                .collect(Collectors.groupingBy(entity -> {
                            Coordinates current = entity.getCoordinates();
                            return Coordinates.getLengthBetween(coordinates, current);
                        }, TreeMap::new, Collectors.toList())
                );

        return herbivores.firstEntry().getValue().get(0).getCoordinates();
    }

    public <T extends Coordinates> List<T> validateCoordinates(List<T> list) {
        List<T> result = new ArrayList<>();
        for (T current : list) {
            if (current.x() >= 0 && current.x() < x &&
                    current.y() >= 0 && current.y() < y) {
                result.add(current);
            }
        }
        return result;
    }

    public Coordinates getRandomEmptyCoordinates() {
        while (true) {
            int randomX = ThreadLocalRandom.current().nextInt(this.x);
            int randomY = ThreadLocalRandom.current().nextInt(this.y);
            Coordinates emptyCoordinates = new Coordinates(randomX, randomY);
            if (!entities.containsKey(emptyCoordinates)) {
                return emptyCoordinates;
            }
        }
    }

    public void moveEntity(Coordinates from, Coordinates too) {
        Entity entity = entities.get(from);
        entities.remove(from);
        entity.setCoordinates(too);
        entities.put(too, entity);
    }
}
