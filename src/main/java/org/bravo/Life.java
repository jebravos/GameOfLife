package org.bravo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Life {



    private Cell[][] matrix;

    public Life(Cell[][] seed) {
        this.matrix = seed;
    }

    public void tick() {

        final Cell[][] newMatrix = new Cell[this.matrix.length][this.matrix[0].length];

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                newMatrix[x][y] = newGenerationFromCell(new Coordinates(x, y));
            }
        }
        this.matrix = newMatrix;
    }

    private Cell newGenerationFromCell(Coordinates coordinates) {

        if (getCell(coordinates).get().isAlive()) {
            return newGenerationFromAliveCell(coordinates);
        } else {
            return newGenerationFromDeadCell(coordinates);
        }
    }

    private Cell newGenerationFromDeadCell(Coordinates coordinates) {
        final long aliveNeighbors = countAliveNeighbors(coordinates);

        if (aliveNeighbors == 3) {
            return Cell.alive(); // or Cell.alive();
        } else {
            return Cell.dead();
        }
    }

    private Cell newGenerationFromAliveCell(Coordinates coordinates) {
        final long aliveNeighbors = countAliveNeighbors(coordinates);
        if (aliveNeighbors == 2 || aliveNeighbors == 3) {
            return Cell.alive(); // or Cell.alive();
        } else {
            return Cell.dead();
        }
    }

    private long countAliveNeighbors(Coordinates coordinates) {
        return getNeighbors(coordinates)
                .stream()
                .filter(Cell::isAlive)
                .count();
    }

    private List<Cell> getNeighbors(Coordinates coordinates) {
        List<Cell> neighbors = new ArrayList<>();
        addNeighbor(neighbors, coordinates.move( - 1,  - 1));
        addNeighbor(neighbors, coordinates.move(0 , - 1));
        addNeighbor(neighbors, coordinates.move( 1,  - 1));

        addNeighbor(neighbors, coordinates.move( - 1, 0));
        addNeighbor(neighbors, coordinates.move( 1, 0));

        addNeighbor(neighbors, coordinates.move( - 1,  1));
        addNeighbor(neighbors, coordinates.move(0,  1));
        addNeighbor(neighbors, coordinates.move( 1,  1));

        return neighbors;
    }

//    private List<Cell> getNeighbors(final int x, final int y) {
//        List<Cell> neighbors = new ArrayList<>();
//        addNeighbor(neighbors, x - 1, y - 1);
//        addNeighbor(neighbors, x, y - 1);
//        addNeighbor(neighbors, x + 1, y - 1);
//
//        addNeighbor(neighbors, x - 1, y);
//        addNeighbor(neighbors, x + 1, y);
//
//        addNeighbor(neighbors, x - 1, y + 1);
//        addNeighbor(neighbors, x, y + 1);
//        addNeighbor(neighbors, x + 1, y + 1);
//
//        return neighbors;
//    }

    private void addNeighbor(final List<Cell> neighbors, Coordinates coordinates) {
        getCell(coordinates)
                .ifPresent(neighbors::add);
    }

    public Optional<Cell> getCell(Coordinates coordinates) {

        try {
            return Optional.of(this.matrix[coordinates.y()][coordinates.x()]);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public Optional<Cell> getCell(int x, int y) {

        return getCell(new Coordinates(x,y));
    }

    public void print() {
        for (final Cell[] cells : matrix) {
            for (final Cell cell : cells) {
                cell.print();
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
