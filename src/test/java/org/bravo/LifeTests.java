package org.bravo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.bravo.Cell.alive;
import static org.bravo.Cell.dead;

class LifeTests {

    private Cell[][] seed = {
            {alive(), dead(),  alive()}, // 0 1 2 x
            {alive(), dead(),  dead()},  // 1
            {dead(),  alive(), alive()}, // 2
                                         // y
    };


    @Test
    @DisplayName("Any live cell with two or three live neighbours survives.")
    void t0(){
        // Given seed
        Life life = new Life(seed);
        // When
        life.tick();
        life.print();
        // then
        Assertions.assertTrue(life.getCell(0,1).isPresent());
        Assertions.assertTrue(life.getCell(0,1).get().isAlive());

        Assertions.assertTrue(life.getCell(1,2).isPresent());
        Assertions.assertTrue(life.getCell(1,2).get().isAlive());

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
