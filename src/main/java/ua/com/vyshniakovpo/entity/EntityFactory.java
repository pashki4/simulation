package ua.com.vyshniakovpo.entity;

import java.util.List;

public class EntityFactory {

    private EntityFactory() {
        throw new UnsupportedOperationException("It's a utility class and cannot be instantiated");
    }

    public static List<Entity> getListOfEntities(List<String> names) {
        return names.stream()
                .map(EntityFactory::create)
                .toList();
    }

    private static Entity create(String entity) {
        return switch (entity.toLowerCase()) {
            case "predator" -> new Predator();
            case "herbivore" -> new Herbivore();
            case "grass" -> new Grass();
            case "rock" -> new Rock();
            case "tree" -> new Tree();
            default -> throw new UnknownEntity("Unexpected value: " + entity.toLowerCase());
        };
    }
}
