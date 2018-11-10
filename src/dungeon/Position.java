/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

/**
 *
 * @author taylo
 */
public class Position {

    private int horLocation;
    private int vertLocation;

    public Position() {
        horLocation = 0;
        vertLocation = 0;
    }

    public Position(int horLocation, int vertLocation) {
        this.horLocation = horLocation;
        this.vertLocation = vertLocation;
    }

    public int getHorLocation() {
        return horLocation;
    }

    public int getVertLocation() {
        return vertLocation;
    }

    @Override
    public boolean equals(Object obj) {
        Position posObj = (Position) obj;
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            return this.horLocation == posObj.getHorLocation() && this.vertLocation == posObj.getVertLocation();
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.horLocation;
        hash = 59 * hash + this.vertLocation;
        return hash;
    }

    //explore deletion
    @Override
    public String toString() {
        return "x: " + horLocation + " / " + "y: " + vertLocation;
    }

}
