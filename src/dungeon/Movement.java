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

public class Movement {

    private Scanner myScanner;
    private Dungeon myDungeon;
    private int maxX;
    private int maxY;

    public Movement(Dungeon myDungeon) {
        this.myScanner = new Scanner(System.in);
        this.myDungeon = myDungeon;
        maxX = myDungeon.getLength() - 1;
        maxY = myDungeon.getHeight() - 1;
    }

    public Position getDeltaPositionFromText() {
        String inputString = myScanner.nextLine();
        int deltaX = 0;
        int deltaY = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == 'd' && deltaX + 1 <= maxX) {
                deltaX++;
            }
            if (inputString.charAt(i) == 's' && deltaY - 1 >= 0) {
                deltaY--;
            }
            if (inputString.charAt(i) == 'a' && deltaX - 1 >= 0) {
                deltaX--;
            }
            if (inputString.charAt(i) == 'w' && deltaY + 1 <= maxY) {
                deltaY++;
            }
        }
        return new Position(deltaX, deltaY);
    }

    public Position randomlyMoveVampire(int numberOfTimes, Vampire vampire) {
        //cant cross border
        //cant hit another vampire
        Random myRandom = new Random();
        Position currentPosition = vampire.getCurrentPosition();
        int finalX = currentPosition.getxLocation();
        int finalY = currentPosition.getyLocation();

        for (int i = 0; i < numberOfTimes; i++) {

            //move to right
            if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                if (finalX + 1 <= maxX && !myDungeon.positionContainsVamp(new Position(finalX + 1, finalY))) {
                    finalX++;
                }
            } //move to left
            else if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                if (finalX - 1 >= 0 && !myDungeon.positionContainsVamp(new Position(finalX - 1, finalY))) {
                    finalX--;
                }
            } //move up
            else if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                if (finalY + 1 <= 0 && !myDungeon.positionContainsVamp(new Position(finalX, finalY + 1))) {
                    finalY++;
                }
            } //move down
            else {
                if (finalY - 1 >= 0 && !myDungeon.positionContainsVamp(new Position(finalX, finalY - 1))) {
                    finalY--;
                }
            }
        }
        return new Position(finalX, finalY);
    }

}
