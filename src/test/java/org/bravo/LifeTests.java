package org.bravo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.bravo.Cell.alive;
import static org.bravo.Cell.dead;
import static org.bravo.Patterns.BLINKER;

class LifeTests {


    private Cell[][] seed = {
            {alive(new Coordinates(0, 0)), dead(new Coordinates(1, 0)), alive(new Coordinates(2, 0))}, // 0 1 2 x
            {alive(new Coordinates(0, 1)), dead(new Coordinates(1, 1)), dead(new Coordinates(2, 1))},  // 1
            {dead(new Coordinates(0, 2)), alive(new Coordinates(1, 2)), alive(new Coordinates(2, 2))}  // 2
            // y
    };


    @Nested
    class SuccessfulTests {
        @Test
        @DisplayName("Any live cell with two or three live neighbours survives.")
        void t0() {
            // Given seed
            Life life = new Life(new Seed(seed));
            // When
            life.print();
            System.out.println("------------------------------");
            life.tick();
            life.print();
            System.out.println("------------------------------");
            // then
            Assertions.assertTrue(life.findCell(0, 1).isPresent());
            Assertions.assertTrue(life.findCell(0, 1).get().isAlive());

            Assertions.assertTrue(life.findCell(1, 2).isPresent());
            Assertions.assertTrue(life.findCell(1, 2).get().isAlive());

        }

        @Test
        @DisplayName("Any dead cell with three live neighbours becomes a live cell.")
        public void t1() {
            // Given seed
            Life life = new Life(new Seed(seed));
            // When
            life.print();
            System.out.println("------------------------------");
            life.tick();
            life.print();
            System.out.println("------------------------------");
            // then
            Assertions.assertTrue(life.findCell(1, 0).isPresent());
            Assertions.assertTrue(life.findCell(1, 0).get().isAlive());

            Assertions.assertTrue(life.findCell(2, 1).isPresent());
            Assertions.assertTrue(life.findCell(2, 1).get().isAlive());
        }

        @Test
        @DisplayName("All other live cells die in the next generation. Similarly, all other dead cells stay dead.")
        public void t2() {
            // Given seed
            Life life = new Life(new Seed(seed));
            // When
            life.print();
            System.out.println("------------------------------");
            life.tick();
            life.print();
            System.out.println("------------------------------");
            // then
            Assertions.assertTrue(life.findCell(0, 0).isPresent());
            Assertions.assertTrue(life.findCell(0, 0).get().isDead());

            Assertions.assertTrue(life.findCell(0, 2).isPresent());
            Assertions.assertTrue(life.findCell(0, 2).get().isDead());

            Assertions.assertTrue(life.findCell(1, 1).isPresent());
            Assertions.assertTrue(life.findCell(1, 1).get().isDead());

            Assertions.assertTrue(life.findCell(2, 0).isPresent());
            Assertions.assertTrue(life.findCell(2, 0).get().isDead());

            Assertions.assertTrue(life.findCell(2, 2).isPresent());
            Assertions.assertTrue(life.findCell(2, 2).get().isDead());
        }

    }

    @Nested
    class ValidationTests {

        @Test
        @DisplayName("Illegal argument exception when invalid seed")
        void t0() {

            Stream.of((Seed) null)
                    .forEach(seed -> Assertions.assertThrows(IllegalArgumentException.class, () -> new Life(seed)));

        }

    }

    @Nested
    class Print {
        @Test
        void printSeed() {
            Life life = new Life(new Seed(BLINKER));
            // When
            IntStream.range(0, 10)
                    .forEach(i -> {
                        tick(life);
                    });

        }

        private void tick(final Life life)  {
            life.print();
            System.out.println("------------------------------");
            life.tick();
        }
    }

}
