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

        for (int x = 0; x < seed.length; x++) {

            for (int y = 0; y < seed[x].length; y++) {

                List<Cell> yCells = mapOfCells.computeIfAbsent(y, k -> new LinkedList<>());

                final Cell cell = Cell.newCell(seed[y][x].getStatus(), new Coordinates(x, y));
                final List<Cell> neighbors = getNeighbors(cell.getCoordinates(), this.matrix);
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
        tick_();
    }

    private Cell newGenerationFromCell(Coordinates coordinates) {

        if (getCell_(coordinates).get().isAlive()) {
//        if (getCell(coordinates, this.matrix).get().isAlive()) {
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

    private List<Cell> getNeighbors(Coordinates coordinates, final Cell[][] matrix1) {
        List<Cell> neighbors = new ArrayList<>();
        addNeighbor(neighbors, getCell(coordinates.move(-1, -1), matrix1));
        addNeighbor(neighbors, getCell(coordinates.move(0, -1), matrix1));
        addNeighbor(neighbors, getCell(coordinates.move(1, -1), matrix1));

        addNeighbor(neighbors, getCell(coordinates.move(-1, 0), matrix1));
        addNeighbor(neighbors, getCell(coordinates.move(1, 0), matrix1));

        addNeighbor(neighbors, getCell(coordinates.move(-1, 1), matrix1));
        addNeighbor(neighbors, getCell(coordinates.move(0, 1), matrix1));
        addNeighbor(neighbors, getCell(coordinates.move(1, 1), matrix1));

        return neighbors;
    }
    private List<Cell> getNeighbors(Coordinates coordinates) {
        List<Cell> neighbors = new ArrayList<>();
        addNeighbor(neighbors, getCell_(coordinates.move(-1, -1)));
        addNeighbor(neighbors, getCell_(coordinates.move(0, -1)));
        addNeighbor(neighbors, getCell_(coordinates.move(1, -1)));

        addNeighbor(neighbors, getCell_(coordinates.move(-1, 0)));
        addNeighbor(neighbors, getCell_(coordinates.move(1, 0)));

        addNeighbor(neighbors, getCell_(coordinates.move(-1, 1)));
        addNeighbor(neighbors, getCell_(coordinates.move(0, 1)));
        addNeighbor(neighbors, getCell_(coordinates.move(1, 1)));

        return neighbors;
    }

    private void addNeighbor(final List<Cell> neighbors, final Optional<Cell> cell) {
        cell
                .ifPresent(neighbors::add);
    }

    public Optional<Cell> getCell(Coordinates coordinates, final Cell[][] matrix) {

        try {
            return Optional.of(matrix[coordinates.y()][coordinates.x()]);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public Optional<Cell> getCell(int x, int y) {
        return getCell_(new Coordinates(x,y));
    }

    public Optional<Cell> getCell_(Coordinates coordinates) {
        return Optional.ofNullable(this.mapOfCells.get(coordinates.y()))
                .orElse(List.of())
                .stream()
                .filter(cell -> cell.getCoordinates().equals(coordinates))
                .findAny();
    }

    public void print() {
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
