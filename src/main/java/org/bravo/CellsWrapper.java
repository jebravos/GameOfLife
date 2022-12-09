package org.bravo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class CellsWrapper {

    protected List<Cell> getNeighbors(Cell reference) {
        final Optional<Cell> cell = findCell(reference.getCoordinates());

        return cell.map(Cell::getCoordinates)
                .map(this::getNeighbors)
                .orElse(List.of());
    }

    protected List<Cell> getNeighbors(Coordinates referencePosition) {
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

    public Optional<Cell> findCell(final Cell cell) {
        return Optional.ofNullable(cell)
                .flatMap(c -> findCell(c.getCoordinates()));
    }

    public abstract Optional<Cell> findCell(Coordinates coordinates);
}
