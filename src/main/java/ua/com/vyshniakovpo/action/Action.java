package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.worldmap.WorldMap;

public abstract class Action {

    protected WorldMap map;

    protected Action(WorldMap map) {
        this.map = map;
    }

    public abstract void perform();
}
