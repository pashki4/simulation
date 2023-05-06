package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.worldmap.WorldMap;

public abstract class Creature extends Entity {
    protected int movesCount;
    protected int hp = 100;

    protected Creature(int movesCount) {
        this.movesCount = movesCount;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    public abstract void makeMove(WorldMap worldMap);
}
