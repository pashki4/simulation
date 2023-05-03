package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.worldmap.WorldMap;

public abstract class Creature extends Entity {
    protected final int speed;
    private int hp = 100;

    protected Creature(int speed) {
        this.speed = speed;
    }

    public abstract void makeMove(WorldMap worldMap);

}
