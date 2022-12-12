package org.bravo;

import static org.bravo.Cell.alive;
import static org.bravo.Cell.dead;

public class Patterns {

    public static final Cell[][] SEED = {
            {alive(new Coordinates(0, 0)), dead(new Coordinates(1, 0)), alive(new Coordinates(2, 0))}, // 0 1 2 x
            {alive(new Coordinates(0, 1)), dead(new Coordinates(1, 1)), dead(new Coordinates(2, 1))},  // 1
            {dead(new Coordinates(0, 2)), alive(new Coordinates(1, 2)), alive(new Coordinates(2, 2))}  // 2
            // y
    };

    public static final Cell[][] BLINKER = {
            {dead(new Coordinates(0, 0)), alive(new Coordinates(1, 0)), dead(new Coordinates(2, 0))}, // 0 1 2 x
            {dead(new Coordinates(0, 1)), alive(new Coordinates(1, 1)), dead(new Coordinates(2, 1))},  // 1
            {dead(new Coordinates(0, 2)), alive(new Coordinates(1, 2)), dead(new Coordinates(2, 2))}  // 2
            // y
    };

    public static final Cell[][] TOAD = {
            {dead(new Coordinates(0, 0)), dead (new Coordinates(1, 0)), alive(new Coordinates(2, 0)), dead(new Coordinates(2, 0))}, // 0 1 2 x
            {alive(new Coordinates(0, 1)), dead(new Coordinates(1, 1)), dead(new Coordinates(2, 1)), alive(new Coordinates(2, 1))},  // 1
            {alive(new Coordinates(0, 2)), dead(new Coordinates(1, 2)), dead(new Coordinates(2, 2)), alive(new Coordinates(2, 2))} , // 2
            {dead(new Coordinates(0, 2)), alive(new Coordinates(1, 2)), dead(new Coordinates(2, 2)), dead(new Coordinates(2, 2))}  // 2
            // y
    };


}
