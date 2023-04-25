package ua.com.vyshniakovpo.field;

import ua.com.vyshniakovpo.Coordinates;

import java.util.concurrent.ThreadLocalRandom;

public class FieldUtil {

    public static Coordinates getRandomCoordinates(int x, int y) {
        ThreadLocalRandom.current().nextInt(x);
        return null;
    }
}
