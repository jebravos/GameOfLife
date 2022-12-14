package org.bravo;

import java.util.List;

import static org.bravo.Cell.Status.ALIVE;
import static org.bravo.Cell.Status.DEAD;

public class Cell implements Comparable<Cell> {

    @Override
    public int compareTo(final Cell o) {
        return Integer.compare(this.getCoordinates().y(), o.getCoordinates().y());
    }

    enum Status {
        ALIVE, DEAD
    }

    private Status status;
    private Coordinates coordinates;

    public static Cell newCell(Status status, Coordinates coordinates){
        return new Cell(status, coordinates);
    }

    private Cell(Status status, Coordinates coordinates){
        this.status = status;
        this.coordinates = coordinates;
    }

    public static Cell alive(Coordinates coordinates){
        return new Cell(ALIVE, coordinates);
    }

    public static Cell dead(Coordinates coordinates){
        return new Cell(DEAD, coordinates);
    }

    public boolean isAlive(){
        return ALIVE.equals(this.status);
    }

    public boolean isDead(){
        return !this.isAlive();
    }

    public Status getStatus(){
        return this.status;
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }
    public void print(){
        char c = this.isAlive() ? 'X' : 'O';
        System.out.print(c);
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}
