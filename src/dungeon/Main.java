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
import java.util.*;

public class Main {
    

    public static void main(String[] args) {
        Dungeon gameDungeon = new Dungeon(5, 5, 1, 10, true);
        gameDungeon.run();
    }
    

}
