package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.field.Field;
import ua.com.vyshniakovpo.field.FieldConsoleRenderer;
import ua.com.vyshniakovpo.field.FieldRenderer;

public class Simulation {

    private final Field field;
    private int turnCount;

    private final FieldRenderer renderer;

    public Simulation(Field field, FieldRenderer renderer) {
        this.field = field;
        this.renderer = renderer;
    }
}
