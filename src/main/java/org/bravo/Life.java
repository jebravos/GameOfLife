package org.bravo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Life extends CellsWrapper {


    private Map<Integer, List<Cell>> mapOfCells;

    public Life(Seed seed) {

        if (seed == null) {
            throw new IllegalArgumentException("Seed must not be empty");
        }


        mapOfCells = seed.toMap();
    }

    public void tick() {
        tick_();
    }

    private void tick_() {

        Map<Integer, List<Cell>> newGenerationMapOfCells = new LinkedHashMap<>();

        this.mapOfCells.keySet()
                .forEach(y -> this.mapOfCells.get(y)
                        .stream()
                        .map(this::newGenerationFromCell)
                        .forEach(cell -> newGenerationMapOfCells.computeIfAbsent(y, k -> new LinkedList<>())
                                .add(cell)
                        )
                );

        mapOfCells = newGenerationMapOfCells;
    }

    private Cell newGenerationFromCell(Cell cell) {

        if (cell.isAlive()) {
            return newGenerationFromAliveCell(cell);
        } else {
            return newGenerationFromDeadCell(cell);
        }

    }

    private Cell newGenerationFromDeadCell(Cell cell) {
        final long aliveNeighbors = countAliveNeighbors(cell);

        if (aliveNeighbors == 3) {
            return Cell.alive(cell.getCoordinates());
        } else {
            return Cell.dead(cell.getCoordinates());
        }
    }

    private Cell newGenerationFromAliveCell(Cell cell) {
        final long aliveNeighbors = countAliveNeighbors(cell);
        if (aliveNeighbors == 2 || aliveNeighbors == 3) {
            return Cell.alive(cell.getCoordinates());
        } else {
            return Cell.dead(cell.getCoordinates());
        }
    }


    @Override
    public Optional<Cell> findCell(final Coordinates coordinates) {
        return Optional.ofNullable(this.mapOfCells.get(coordinates.y()))
                .orElse(List.of())
                .stream()
                .filter(cell -> cell.getCoordinates().equals(coordinates))
                .findAny();
    }


    public Optional<Cell> findCell(int x, int y) {
        return findCell(new Coordinates(x, y));
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
