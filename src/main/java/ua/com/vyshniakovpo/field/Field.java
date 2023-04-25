package ua.com.vyshniakovpo.field;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private final Map<Coordinates, Entity> entities = new HashMap<>();
    private final FieldSize size;

    public Field(FieldSize size) {
        this.size = size;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    @Override
    public String toString() {
        return "Field{" +
                "entities=" + entities +
                '}';
    }

    public Map<Coordinates, Entity> getEntities() {
        Map<Coordinates, Entity> result = new HashMap<>();
        result.putAll(entities);
        return result;
    }

    public FieldSize getSize() {
        return size;
    }
}
