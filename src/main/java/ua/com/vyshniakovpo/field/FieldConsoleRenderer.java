package ua.com.vyshniakovpo.field;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;

import java.util.Map;
import java.util.Objects;

public class FieldConsoleRenderer implements FieldRenderer {

    public void render(Field field) {
        Map<Coordinates, Entity> entities = field.getEntities();
        FieldSize size = field.getSize();
        StringBuilder sb = new StringBuilder();
        for (int vertical = size.vertical; vertical > 0; vertical--) {
            for (int horizontal = 1; horizontal <= size.horizontal; horizontal++) {
                Entity entity = entities.get(new Coordinates(horizontal, vertical));
                if (Objects.isNull(entity)) {
                    sb.append("🟩");
                } else {
                    switch (entity.getClass().getSimpleName()) {

                        case "Grass" -> sb.append("🟩");
                        case "Rock" -> sb.append("🪨");
                        case "Tree" -> sb.append("🌳");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
