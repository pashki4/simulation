package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.EntityFactory;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import javax.xml.stream.events.EndElement;
import java.util.List;

public class Actions {


    private Actions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void initActions(WorldMap worldMap) {
        List<Entity> listOfEntities = EntityFactory.getListOfEntities(
                List.of("predator", "herbivore", "rock", "rock", "herbivore", "herbivore")
//                List.of("predator", "herbivore", "herbivore",
//                "herbivore", "tree", "tree", "tree", "rock", "rock", "rock",
//                        "grass","grass","grass", "grass", "grass", "grass", "grass", "grass")
        );
//        setRandomPositionForEntitiesOnMap(worldMap, listOfEntities);
        setPositionForEntitiesOnMap(worldMap, listOfEntities);
    }

    private static void setRandomPositionForEntitiesOnMap(WorldMap map, List<Entity> listOfEntities) {
        listOfEntities.forEach(entity -> {
                    Coordinates coordinates = map.getRandomEmptyCoordinates();
                    entity.setCoordinates(coordinates);
                    map.addEntity(coordinates, entity);
                }
        );
    }

    private static void setPositionForEntitiesOnMap(WorldMap map, List<Entity> listOfEntities) {
        Entity predator = listOfEntities.get(0);
        predator.setCoordinates(new Coordinates(0, 0));
        Entity herbivore = listOfEntities.get(1);
        herbivore.setCoordinates(new Coordinates(0, 1));
        Entity rock = listOfEntities.get(2);
        rock.setCoordinates(new Coordinates(3, 4));
        Entity rock1 = listOfEntities.get(3);
        rock1.setCoordinates(new Coordinates(4, 3));
        Entity herbivore1 = listOfEntities.get(4);
        herbivore1.setCoordinates(new Coordinates(5, 5));
        Entity herbivore2 = listOfEntities.get(5);
        herbivore2.setCoordinates(new Coordinates(5, 0));


        map.addEntity(predator.getCoordinates(), predator);
        map.addEntity(herbivore.getCoordinates(), herbivore);
        map.addEntity(rock.getCoordinates(), rock);
        map.addEntity(rock1.getCoordinates(), rock1);
        map.addEntity(herbivore1.getCoordinates(), herbivore1);
        map.addEntity(herbivore2.getCoordinates(), herbivore2);
    }
}
