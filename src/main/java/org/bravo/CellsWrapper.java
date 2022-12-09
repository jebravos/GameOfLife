package org.bravo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class CellsWrapper {

    protected List<Cell> getNeighbors(Coordinates coordinates) {
        return Stream.of(getCell(coordinates.move(-1, -1)),
                getCell(coordinates.move(0, -1)),
                getCell(coordinates.move(1, -1)),
                getCell(coordinates.move(-1, 0)),
                getCell(coordinates.move(1, 0)),
                getCell(coordinates.move(-1, 1)),
                getCell(coordinates.move(0, 1)),
                getCell(coordinates.move(1, 1)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

    }

    public abstract Optional<Cell> getCell(Coordinates coordinates);
}
