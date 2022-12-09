package org.bravo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Life {

    private Cell[][] matrix;


    Map<Integer, List<Cell>> mapOfCells = new LinkedHashMap();

    public Life(Cell[][] seed) {
        this.matrix = seed;

        for (int x = 0; x < matrix.length; x++) {

//            List<Cell> cells = new LinkedList<>();

            for (int y = 0; y < matrix[x].length; y++) {

                List<Cell> yCells = mapOfCells.computeIfAbsent(y, k -> new LinkedList<>());

                final Cell cell = Cell.newCell(seed[y][x].getStatus(), new Coordinates(x, y));
                final List<Cell> neighbors = getNeighbors(cell.getCoordinates());
                cell.setNeighbors(neighbors);
                yCells.add(cell);

            }

        }

    }

    public void tick_() {

        Map<Integer, List<Cell>> newGenerationMapOfCells = new LinkedHashMap();

        this.mapOfCells.keySet().stream()
                .sorted(Comparator.naturalOrder())
                .forEach(y -> {
                    this.mapOfCells.get(y)
                            .stream()
                            .forEach(cell -> {

                                List<Cell> rowCells = newGenerationMapOfCells.computeIfAbsent(y, k -> new LinkedList<>());
                                newGenerationMapOfCells.get(y).add(newGenerationFromCell(cell.getCoordinates()));

                            });

                });

        mapOfCells = newGenerationMapOfCells;
    }
    public void tick() {
//
//        final Cell[][] newMatrix = new Cell[this.matrix.length][this.matrix[0].length];
//
//        for (int x = 0; x < matrix.length; x++) {
//            for (int y = 0; y < matrix[x].length; y++) {
//                newMatrix[x][y] = newGenerationFromCell(new Coordinates(x, y));
//            }
//        }
//        this.matrix = newMatrix;

        tick_();
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
            return Cell.alive(coordinates);
        } else {
            return Cell.dead(coordinates);
        }
    }

    private Cell newGenerationFromAliveCell(Coordinates coordinates) {
        final long aliveNeighbors = countAliveNeighbors(coordinates);
        if (aliveNeighbors == 2 || aliveNeighbors == 3) {
            return Cell.alive(coordinates);
        } else {
            return Cell.dead(coordinates);
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
        addNeighbor(neighbors, coordinates.move(-1, -1));
        addNeighbor(neighbors, coordinates.move(0, -1));
        addNeighbor(neighbors, coordinates.move(1, -1));

        addNeighbor(neighbors, coordinates.move(-1, 0));
        addNeighbor(neighbors, coordinates.move(1, 0));

        addNeighbor(neighbors, coordinates.move(-1, 1));
        addNeighbor(neighbors, coordinates.move(0, 1));
        addNeighbor(neighbors, coordinates.move(1, 1));

        return neighbors;
    }

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

    public Optional<Cell> getCell_(Coordinates coordinates) {
        return this.mapOfCells.get(coordinates.y())
                .stream()
                .filter(cell -> cell.getCoordinates().equals(coordinates))
                .findAny();
    }

    public Optional<Cell> getCell(int x, int y) {
        return getCell_(new Coordinates(x,y));
//        return getCell(new Coordinates(x, y));
    }

    public void print() {
//        for (final Cell[] cells : matrix) {
//            for (final Cell cell : cells) {
//                cell.print();
//                System.out.print(' ');
//            }
//            System.out.println();
//        }

        print_();

    }
    public void print_() {
        this.mapOfCells.keySet().stream()
                .sorted(Comparator.naturalOrder())
                .forEach(y -> {
                    this.mapOfCells.get(y)
                            .stream()
                            .forEach(cell -> {
                                cell.print();
                                System.out.print(' ');
                            });

                    System.out.println();
                });
    }
}
