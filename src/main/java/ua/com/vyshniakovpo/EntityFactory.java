package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityFactory {
    public static List<Entity> getListOfEntities(List<String> names) {
        return names.stream()
                .map(EntityFactory::create)
                .collect(Collectors.toList());
    }

    private static Entity create(String entity) {
        return switch (entity.toLowerCase()) {
            case "predator" -> new Predator(2);
            case "herbivore" -> new Herbivore(1);
            case "grass" -> new Grass();
            case "rock" -> new Rock();
            case "tree" -> new Tree();
            default -> throw new UnknownEntity("Unexpected value: " + entity.toLowerCase());
        };
    }
}
