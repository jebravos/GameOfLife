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
                if(matrix[x][y].isAlive()){
                    final long aliveNeighbors = getNeighbors(x, y)
                            .stream()
                            .filter(Cell::isAlive)
                            .count();

                    if(aliveNeighbors == 2 || aliveNeighbors == 3) {
                        newMatrix[x][y] = this.matrix[x][y]; // or Cell.alive();
                    } else {
                        newMatrix[x][y] = Cell.dead();
                    }

                } else {
                    newMatrix[x][y] = this.matrix[x][y];
                }

            }
        }

        this.matrix = newMatrix;
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
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y].print();
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
