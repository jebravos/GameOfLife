package org.bravo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class CellsWrapper {

    public long countAliveNeighbors(Cell cell) {
        return getNeighbors(cell)
                .stream()
                .filter(Cell::isAlive)
                .count();
    }

    protected List<Cell> getNeighbors(Cell reference) {
        final Optional<Cell> cell = findCell(reference.getCoordinates());

        return cell.map(Cell::getCoordinates)
                .map(this::getNeighbors)
                .orElse(List.of());
    }

    private List<Cell> getNeighbors(Coordinates referencePosition) {
        return Stream.of(findCell(referencePosition.fromReference(-1, -1)),
                findCell(referencePosition.fromReference(0, -1)),
                findCell(referencePosition.fromReference(1, -1)),
                findCell(referencePosition.fromReference(-1, 0)),
                findCell(referencePosition.fromReference(1, 0)),
                findCell(referencePosition.fromReference(-1, 1)),
                findCell(referencePosition.fromReference(0, 1)),
                findCell(referencePosition.fromReference(1, 1)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

    }

    public abstract Optional<Cell> findCell(Coordinates coordinates);
}
