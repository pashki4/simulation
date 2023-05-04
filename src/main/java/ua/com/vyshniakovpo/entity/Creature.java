package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.worldmap.WorldMap;

public abstract class Creature extends Entity {
    protected int movesCount;
    private int hp = 100;

    protected Creature(int movesCount) {
        this.movesCount = movesCount;
    }

    public abstract void makeMove(WorldMap worldMap);

}
