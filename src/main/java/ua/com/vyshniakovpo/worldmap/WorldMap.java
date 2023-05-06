package ua.com.vyshniakovpo.worldmap;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Creature;
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
        return new HashMap<>(entities);
    }

    public <T extends Creature> List<Creature> getCreaturesByType(Class<T> clazz) {
        return entities.values()
                .stream()
                .filter(clazz::isInstance)
                .map(Creature.class::cast)
                .toList();
    }

    public boolean isCellOccupied(Coordinates coordinates) {
        return entities.containsKey(coordinates);
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
        Entity entity = entities.get(from);
        entities.remove(from);
        entity.setCoordinates(too);
        entities.put(too, entity);
    }

    public Entity getEntityByCoordinates(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void cleanKilledEntities() {
        Optional<Coordinates> coordinates = entities.values().stream()
                .filter(Herbivore.class::isInstance)
                .filter(entity -> ((Herbivore) entity).getHp() == 0)
                .findAny()
                .map(Entity::getCoordinates);

        coordinates.ifPresent(entities::remove);
    }
}
