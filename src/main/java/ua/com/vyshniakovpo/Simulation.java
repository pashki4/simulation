package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Creature;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.field.Field;
import ua.com.vyshniakovpo.field.FieldRenderer;

import java.util.Map;

public class Simulation {

    private final Field field;
    private int turnCount;
    private final FieldRenderer renderer;

    public Simulation(Field field, FieldRenderer renderer) {
        this.field = field;
        this.renderer = renderer;
    }

    public void nextTurn() {
        field.getEntities().entrySet()
                .stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .forEach(creature -> creature.makeMove(field));
    }

    public void startSimulation() {

    }

    public void pauseSimulation() {

    }

    public void initActions() {
        Map<Coordinates, Entity> entities = field.getEntities();
    }
}
