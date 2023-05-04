package ua.com.vyshniakovpo.worldmap;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Herbivore;

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

    public <T extends Entity> List<T> getEntitiesByType(Class<T> cls) {
        return entities.values()
                .stream()
                .filter(cls::isInstance)
                .map(cls::cast)
                .toList();
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Coordinates getClosestHerbivore(Coordinates coordinates) {
        TreeMap<Integer, List<Entity>> herbivores = entities.values().stream()
                .filter(Herbivore.class::isInstance)
                .collect(Collectors.groupingBy(e -> {
                            Coordinates current = e.getCoordinates();
                            int difference = (current.vertical() + current.horizontal()) -
                                    (coordinates.horizontal() + coordinates.vertical());
                            return Math.abs(difference);
                        }, TreeMap::new, Collectors.toList())
                );

        return herbivores.firstEntry().getValue().get(0).getCoordinates();
    }

    public <T extends Coordinates> List<T> validateCoordinates(List<T> list) {
        List<T> result = new ArrayList<>();
        for (T current : list) {
            if (current.horizontal() >= 0 && current.horizontal() < x &&
                    current.vertical() >= 0 && current.vertical() < y) {
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
