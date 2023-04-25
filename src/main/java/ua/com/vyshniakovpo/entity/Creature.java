package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;

public abstract class Creature extends Entity {
    private final Integer speed;
    private Integer hp = 100;

    protected Creature(Coordinates coordinates, Integer speed) {
        super(coordinates);
        this.speed = speed;
    }

    public abstract void makeMove();

}
