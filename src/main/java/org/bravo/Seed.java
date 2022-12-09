package org.bravo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Seed  {

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

                yCells.add(cell);

            }

        }

        return mapOfCells;
    }

}
