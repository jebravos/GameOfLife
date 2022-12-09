package org.bravo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Seed extends CellsWrapper {

    private Cell[][] matrix;

    public Seed(Cell[][] seed) {
        this.matrix = seed;
    }

    public Map<Integer, List<Cell>> toMap(){
        Map<Integer, List<Cell>> mapOfCells = new LinkedHashMap();
        for (int x = 0; x < this.matrix.length; x++) {

            for (int y = 0; y < this.matrix[x].length; y++) {

                List<Cell> yCells = mapOfCells.computeIfAbsent(y, k -> new LinkedList<>());

                final Cell cell = Cell.newCell(this.matrix[y][x].getStatus(), new Coordinates(x, y));
                final List<Cell> neighbors = this.getNeighbors(cell.getCoordinates());
                cell.setNeighbors(neighbors);
                yCells.add(cell);

            }

        }

        return mapOfCells;
    }

    @Override
    public Optional<Cell> getCell(Coordinates coordinates) {

        try {
            return Optional.of(this.matrix[coordinates.y()][coordinates.x()]);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
