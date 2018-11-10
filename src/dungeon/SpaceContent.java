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
public class SpaceContent {

    private Position position;

    public SpaceContent() {
        position = new Position();
    }

    public boolean isVampire() {
        return false;
    }

    public boolean isHuman() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        SpaceContent SCobj = (SpaceContent) obj;

        if (!this.getClass().equals(SCobj.getClass())) {
            return false;
        }

        return this.getPosition() == (SCobj.getPosition());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.position != null ? this.position.hashCode() : 0);
        return hash;
    }

}
