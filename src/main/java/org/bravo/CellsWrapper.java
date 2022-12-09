package org.bravo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CellsWrapper {

    protected List<Cell> getNeighbors(Coordinates coordinates) {
        List<Cell> neighbors = new ArrayList<>();
        addNeighbor(neighbors, getCell(coordinates.move(-1, -1)));
        addNeighbor(neighbors, getCell(coordinates.move(0, -1)));
        addNeighbor(neighbors, getCell(coordinates.move(1, -1)));

        addNeighbor(neighbors, getCell(coordinates.move(-1, 0)));
        addNeighbor(neighbors, getCell(coordinates.move(1, 0)));

        addNeighbor(neighbors, getCell(coordinates.move(-1, 1)));
        addNeighbor(neighbors, getCell(coordinates.move(0, 1)));
        addNeighbor(neighbors, getCell(coordinates.move(1, 1)));

        return neighbors;
    }

    private void addNeighbor(final List<Cell> neighbors, final Optional<Cell> cell) {
        cell
                .ifPresent(neighbors::add);
    }

    public abstract Optional<Cell> getCell(Coordinates coordinates);
}
