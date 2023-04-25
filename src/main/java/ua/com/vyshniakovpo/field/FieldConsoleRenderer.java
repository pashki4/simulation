package ua.com.vyshniakovpo.field;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;

import java.util.Map;
import java.util.Objects;

public class FieldConsoleRenderer implements FieldRenderer {

    public void render(Field field) {
        Map<Coordinates, Entity> entities = field.getEntities();
        StringBuilder sb = new StringBuilder();
        for (int vertical = field.y - 1; vertical >= 0; vertical--) {
            for (int horizontal = 0; horizontal < field.x; horizontal++) {
                Entity entity = entities.get(new Coordinates(horizontal, vertical));
                if (Objects.isNull(entity)) {
                    sb.append("🟫");
                } else {
                    switch (entity.getClass().getSimpleName().toLowerCase()) {
                        case "grass" -> sb.append("🟩");
                        case "rock" -> sb.append("🪨");
                        case "tree" -> sb.append("🌳");
                        case "predator" -> sb.append("🦊");
                        case "herbivore" -> sb.append("🐓");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
