package ua.com.vyshniakovpo.worldmap;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Creature;
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

    public List<Entity> getEntities() {
        return entities.values().stream()
                .toList();
    }

    //TODO for testing purpose, remove later
    public <T extends Creature> List<Creature> getEntitiesByType(Class<T> clazz) {
        return entities.values()
                .stream()
                .filter(clazz::isInstance)
                .map(Creature.class::cast)
                .toList();
    }

    public boolean isCellOccupied(Coordinates coordinates) {
        return entities.containsKey(coordinates);
    }

    public <T extends Entity> Coordinates getClosestFoodByClass(Coordinates coordinates, Class<T> clazz) {
        TreeMap<Double, List<Entity>> targets = entities.values().stream()
                .filter(clazz::isInstance)
                .collect(Collectors.groupingBy(entity -> {
                            Coordinates current = entity.getCoordinates();
                            return Coordinates.getLengthBetween(coordinates, current);
                        }, TreeMap::new, Collectors.toList())
                );

        return targets.firstEntry().getValue().get(0).getCoordinates();
    }

    public List<Coordinates> validateCoordinates(List<Coordinates> list) {
        List<Coordinates> result = new ArrayList<>();
        for (Coordinates current : list) {
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
        Entity entity = entities.remove(from);
        entity.setCoordinates(too);
        entities.put(too, entity);
    }

    public void deleteEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }
}
