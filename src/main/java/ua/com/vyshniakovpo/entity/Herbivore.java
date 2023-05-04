package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore() {
        super(1);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        List<Grass> list = worldMap.getEntitiesByType(Grass.class);

        // for(Entity grass: list) {
        // if (coordinates.horizontal < grass.coordinates)
        // do smth else {
        // do smth else....
        // }
        // }
    }
}
