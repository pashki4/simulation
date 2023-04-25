package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;

public abstract class Entity {

    private Coordinates coordinates;

    protected Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
