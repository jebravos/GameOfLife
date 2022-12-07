package org.bravo;

public class Life {

    private Cell[][] matrix;

    public Life(Cell[][] seed) {
        this.matrix = seed;
    }

    public void tick() {
        // TODO implement
    }

    public Cell getCell(int x, int y){

        // TODO handle illegal arguments

        return this.matrix[x][y];
    }
}
