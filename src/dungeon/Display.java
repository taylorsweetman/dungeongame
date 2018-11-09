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
public class Display {

    private Dungeon dungeon;

    public Display(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void show() {
        if (dungeon.winCondition()) {
            System.out.println("YOU WIN");
        } else if (dungeon.lostCondition()) {
            System.out.println("YOU LOSE");
        } else {
            System.out.println(dungeon.getMoves());
        }
    }

}
