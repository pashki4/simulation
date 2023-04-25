package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.field.Field;
import ua.com.vyshniakovpo.field.FieldUtil;

import java.util.List;
import java.util.Map;

public class Actions {

    public static void initActions(Field field) {
        List<Entity> listOfEntities = EntityFactory.getListOfEntities(
                List.of("predator", "herbivore", "herbivore",
                "herbivore", "tree", "tree", "tree", "rock", "rock", "rock")
        );
        populateFieldByEntities(field, listOfEntities);

    }

    private static void populateFieldByEntities(Field field, List<Entity> listOfEntities) {
        listOfEntities.forEach(entity -> {
                    Coordinates coordinates = field.getEmptyCoordinates();
                    entity.setCoordinates(coordinates);
                    field.addEntity(coordinates, entity);
                }
        );
    }
}
