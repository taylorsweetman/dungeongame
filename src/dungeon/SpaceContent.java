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
    
    private Position currentPosition;
    
    public SpaceContent() {
        currentPosition = new Position();
    }
    
    public boolean isVampire() {
        return false;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
    
    
    
}
