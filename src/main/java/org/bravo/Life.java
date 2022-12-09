package org.bravo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Life {

    private Cell[][] matrix;

    public Life(Cell[][] seed) {
        this.matrix = seed;
    }

    public void tick() {
        // TODO implement

        final Cell[][] newMatrix = new Cell[this.matrix.length][this.matrix[0].length];

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if(getCell(x,y).get().isAlive()){
                    final long aliveNeighbors = countAliveNeighbors(x, y);

                    if(aliveNeighbors == 2 || aliveNeighbors == 3) {
                        newMatrix[x][y] = Cell.alive(); // or Cell.alive();
                    } else {
                        newMatrix[x][y] = Cell.dead();
                    }

                } else {
                    final long aliveNeighbors = countAliveNeighbors(x, y);

                    if(aliveNeighbors == 3) {
                        newMatrix[x][y] = Cell.alive(); // or Cell.alive();
                    } else {
                        newMatrix[x][y] = Cell.dead();
                    }
                }

            }
        }

        this.matrix = newMatrix;
    }

    private long countAliveNeighbors(final int x, final int y) {
        return getNeighbors(x, y)
                .stream()
                .filter(Cell::isAlive)
                .count();
    }

    private List<Cell> getNeighbors(final int x, final int y) {
        List<Cell> neighbors = new ArrayList<>();
        addNeighbor(neighbors, x - 1, y - 1);
        addNeighbor(neighbors, x, y - 1);
        addNeighbor(neighbors, x + 1, y - 1);

        addNeighbor(neighbors, x - 1, y);
        addNeighbor(neighbors, x + 1, y);

        addNeighbor(neighbors, x - 1, y + 1);
        addNeighbor(neighbors, x, y + 1);
        addNeighbor(neighbors, x + 1, y + 1);

        return neighbors;
    }

    private void addNeighbor(final List<Cell> neighbors, final int x, final int y) {
        getCell(x, y)
                .ifPresent(neighbors::add);
    }

    public Optional<Cell> getCell(int x, int y){

        try{
            return Optional.of(this.matrix[y][x]);
        } catch (IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }

    public void print(){
        for (final Cell[] cells : matrix) {
            for (final Cell cell : cells) {
                cell.print();
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
