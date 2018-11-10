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
public class Vampire extends SpaceContent {

    public Vampire(int horLocation, int vertLocation) {
        super.setPosition(new Position(horLocation, vertLocation));
    }

    @Override
    public boolean isVampire() {
        return true;
    }

}
