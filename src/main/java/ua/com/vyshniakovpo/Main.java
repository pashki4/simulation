package ua.com.vyshniakovpo;

import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.Grass;
import ua.com.vyshniakovpo.entity.Rock;
import ua.com.vyshniakovpo.entity.Tree;
import ua.com.vyshniakovpo.field.Field;
import ua.com.vyshniakovpo.field.FieldConsoleRenderer;
import ua.com.vyshniakovpo.field.FieldSize;

public class Main {
    public static void main(String[] args) {
        Entity grass = new Grass(new Coordinates(2,2));
        Entity rock = new Rock(new Coordinates(2,2));
        Entity rock1 = new Rock(new Coordinates(3,2));
        Entity tree = new Tree(new Coordinates(1,1));

        Field field = new Field(new FieldSize(5, 5));

        field.addEntity(grass.getCoordinates(), grass);
        field.addEntity(rock.getCoordinates(), rock);
        field.addEntity(rock1.getCoordinates(), rock1);
        field.addEntity(tree.getCoordinates(), tree);
        FieldConsoleRenderer renderer = new FieldConsoleRenderer();
        renderer.render(field);

    }
}