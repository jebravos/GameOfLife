package org.bravo;

import static org.bravo.Cell.Status.ALIVE;
import static org.bravo.Cell.Status.DEAD;

public class Cell {

    enum Status {
        ALIVE, DEAD
    }

    private Status status;

    private Cell(int status){
        this.status = status == 0 ? DEAD : ALIVE;
    }

    private Cell(Status status){
        this.status = status;
    }

    public static Cell alive(){
        return new Cell(1);
    }

    public static Cell dead(){
        return new Cell(0);
    }

    public boolean isAlive(){
        return ALIVE.equals(this.status);
    }

    public boolean isDead(){
        return !this.isAlive();
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
