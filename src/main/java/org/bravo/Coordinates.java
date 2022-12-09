package org.bravo;

record Coordinates(int x, int y) {

    public Coordinates move(int x, int y) {
        return new Coordinates(this.x + x, this.y + y);
    }
}