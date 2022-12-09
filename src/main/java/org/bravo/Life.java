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
                                newGenerationMapOfCells.get(y).add(newGenerationFromCell(cell.getCoordinates()));

                            });

                });

        mapOfCells = newGenerationMapOfCells;
    }
    public void tick() {
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


    @Override
    public Optional<Cell> getCell(final Coordinates coordinates) {
        return Optional.ofNullable(this.mapOfCells.get(coordinates.y()))
                .orElse(List.of())
                .stream()
                .filter(cell -> cell.getCoordinates().equals(coordinates))
                .findAny();
    }


    public Optional<Cell> getCell(int x, int y) {
        return getCell(new Coordinates(x,y));
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
