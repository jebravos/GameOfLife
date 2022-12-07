package org.bravo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.bravo.Cell.alive;
import static org.bravo.Cell.dead;

public class LifeTests {

    private Cell[][] seed = {
            {alive(), dead(),  alive()},
            {alive(), dead(),  dead()},
            {dead(),  alive(), alive()},
    };


    @Test
    @DisplayName("Any live cell with two or three live neighbours survives.")
    public void t0(){
        // Given seed
        Life life = new Life(seed);
        // When
        life.tick();
        // then
        Assertions.assertTrue(false);
    }

    @Test
    @DisplayName("Any dead cell with three live neighbours becomes a live cell.")
    public void t1(){
        // Given seed
        Life life = new Life(seed);
        // When
        life.tick();
        // then
        Assertions.assertTrue(false);
    }
    @Test
    @DisplayName("All other live cells die in the next generation. Similarly, all other dead cells stay dead.")
    public void t2(){
        // Given seed
        Life life = new Life(seed);
        // When
        life.tick();
        // then
        Assertions.assertTrue(false);
    }


}
