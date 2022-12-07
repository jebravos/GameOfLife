package org.bravo;

public class Cell {

    private int status = 0;

    private Cell(int status){
        this.status = status;
    }

    public static Cell alive(){
        return new Cell(1);
    }

    public static Cell dead(){
        return new Cell(0);
    }

    public boolean isAlive(){
        return this.status == 1;
    }

    public boolean isDead(){
        return !this.isAlive();
    }
}
