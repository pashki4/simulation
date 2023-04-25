package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.field.Field;

public abstract class Creature extends Entity {
    private final Integer speed;
    private Integer hp = 100;

    protected Creature(Integer speed) {
        this.speed = speed;
    }

    public abstract void makeMove(Field field);

}
