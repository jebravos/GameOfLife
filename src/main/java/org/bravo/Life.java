package org.bravo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Life extends CellsWrapper{


    private Map<Integer, List<Cell>> mapOfCells;

    public Life(Seed seed) {
        mapOfCells = seed.toMap();
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
                                newGenerationMapOfCells.get(y).add(newGenerationFromCell(cell));

                            });

                });

        mapOfCells = newGenerationMapOfCells;
    }
    public void tick() {
        tick_();
    }

    private Cell newGenerationFromCell(Cell cell) {

        if (findCell(cell).get().isAlive()) {
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

    private long countAliveNeighbors(Cell cell) {
        return getNeighbors(cell)
                .stream()
                .filter(Cell::isAlive)
                .count();
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
        return findCell(new Coordinates(x,y));
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
