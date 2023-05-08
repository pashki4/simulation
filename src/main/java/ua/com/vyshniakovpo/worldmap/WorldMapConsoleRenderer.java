package ua.com.vyshniakovpo.worldmap;

import ua.com.vyshniakovpo.Coordinates;
import ua.com.vyshniakovpo.entity.Entity;
import ua.com.vyshniakovpo.entity.UnknownEntity;

import java.util.Objects;

public class WorldMapConsoleRenderer implements WorldMapRenderer {

    public void render(WorldMap map) {
        StringBuilder sb = new StringBuilder();
        for (int vertical = map.y - 1; vertical >= 0; vertical--) {
            for (int horizontal = 0; horizontal < map.x; horizontal++) {
                Entity entity = map.getEntity(new Coordinates(horizontal, vertical));
                if (Objects.isNull(entity)) {
                    sb.append("🟫");
                } else {
                    switch (entity.getClass().getSimpleName().toLowerCase()) {
                        case "grass" -> sb.append("🟩");
                        case "rock" -> sb.append("🪨");
                        case "tree" -> sb.append("🌳");
                        case "predator" -> sb.append("🦊");
                        case "herbivore" -> sb.append("🐓");
                        default -> throw new UnknownEntity("Unknown entity: "
                                + entity.getClass().getSimpleName());
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
