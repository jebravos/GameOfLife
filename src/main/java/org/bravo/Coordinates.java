package org.bravo;

record Coordinates(int x, int y) {

    public Coordinates fromReference(int x, int y) {
        return new Coordinates(this.x + x, this.y + y);
    }
}