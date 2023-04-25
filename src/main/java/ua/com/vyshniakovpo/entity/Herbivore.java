package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.field.Field;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(int speed) {
        super(speed);
    }

    @Override
    public void makeMove(Field field) {
        List<Entity> list = field.getEntities("Grass");
        // for(Entity grass: list) {
        // if (coordinates.horizontal < grass.coordinates)
        // do smth else {
        // do smth else....
        // }
        // }
    }
}
