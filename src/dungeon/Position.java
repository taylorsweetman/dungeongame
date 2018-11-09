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

    private int xLocation;
    private int yLocation;

    public Position() {
        xLocation = 0;
        yLocation = 0;
    }

    public Position(int xLocation, int yLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    @Override
    public boolean equals(Object obj) {
        Position posObj = (Position) obj;
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            return this.xLocation == posObj.getxLocation() && this.yLocation == posObj.getyLocation();
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.xLocation;
        hash = 59 * hash + this.yLocation;
        return hash;
    }

    @Override
    public String toString() {
        return "x: " + xLocation + " / " + "y: " + yLocation;
    }

}
