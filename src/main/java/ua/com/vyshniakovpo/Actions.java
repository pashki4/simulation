package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.EntityFactory;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.List;

public class Actions {


    private Actions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static void initActions(WorldMap worldMap) {
        List<Entity> listOfEntities = EntityFactory.getListOfEntities(
                List.of("predator", "herbivore", "herbivore",
                "herbivore", "tree", "tree", "tree", "rock", "rock", "rock",
                        "grass","grass","grass", "grass", "grass", "grass", "grass", "grass")
        );
        populateWorldMapByEntities(worldMap, listOfEntities);
    }

    private static void populateWorldMapByEntities(WorldMap worldMap, List<Entity> listOfEntities) {
        listOfEntities.forEach(entity -> {
                    Coordinates coordinates = worldMap.getRandomEmptyCoordinates();
                    entity.setCoordinates(coordinates);
                    worldMap.addEntity(coordinates, entity);
                }
        );
    }
}
