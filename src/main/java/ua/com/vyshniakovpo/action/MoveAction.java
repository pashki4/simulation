package ua.com.vyshniakovpo.action;

import ua.com.vyshniakovpo.entity.Creature;
import ua.com.vyshniakovpo.worldmap.WorldMap;

import java.util.List;

public class MoveAction extends Action {
    public MoveAction(WorldMap map) {
        super(map);
    }

    @Override
    public void perform() {
        List<Creature> entitiesByType = map.getEntitiesByType(Creature.class);
        entitiesByType.forEach(creature -> creature.makeMove(map));
    }
}
