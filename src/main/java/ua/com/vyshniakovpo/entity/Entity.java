package ua.com.vyshniakovpo.entity;

import ua.com.vyshniakovpo.Coordinates;

public abstract class Entity {

    protected Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
