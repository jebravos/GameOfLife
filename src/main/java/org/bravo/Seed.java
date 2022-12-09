package org.bravo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Seed {

    private Cell[][] matrix;

    public Seed(Cell[][] seed) {
        this.matrix = seed;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public Map<Integer, List<Cell>> toMap(){
        Map<Integer, List<Cell>> mapOfCells = new LinkedHashMap();
        for (int x = 0; x < this.getMatrix().length; x++) {

            for (int y = 0; y < this.getMatrix()[x].length; y++) {

                List<Cell> yCells = mapOfCells.computeIfAbsent(y, k -> new LinkedList<>());

                final Cell cell = Cell.newCell(this.getMatrix()[y][x].getStatus(), new Coordinates(x, y));
                final List<Cell> neighbors = this.getNeighbors(cell.getCoordinates());
                cell.setNeighbors(neighbors);
                yCells.add(cell);

            }

        }

        return mapOfCells;
    }

    public List<Cell> getNeighbors(Coordinates coordinates) {
        List<Cell> neighbors = new ArrayList<>();
        addNeighbor(neighbors, getCell(coordinates.move(-1, -1), this.matrix));
        addNeighbor(neighbors, getCell(coordinates.move(0, -1), this.matrix));
        addNeighbor(neighbors, getCell(coordinates.move(1, -1), this.matrix));

        addNeighbor(neighbors, getCell(coordinates.move(-1, 0), this.matrix));
        addNeighbor(neighbors, getCell(coordinates.move(1, 0), this.matrix));

        addNeighbor(neighbors, getCell(coordinates.move(-1, 1), this.matrix));
        addNeighbor(neighbors, getCell(coordinates.move(0, 1), this.matrix));
        addNeighbor(neighbors, getCell(coordinates.move(1, 1), this.matrix));

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
}
