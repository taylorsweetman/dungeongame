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
    private int maxHorValue;
    private int maxVertValue;

    public Movement(Dungeon myDungeon) {
        this.myScanner = new Scanner(System.in);
        this.myDungeon = myDungeon;
        maxHorValue = myDungeon.getLength() - 1;
        maxVertValue = myDungeon.getHeight() - 1;
    }

    public int givePlayerNewLocationFromText() {
        String inputString = myScanner.nextLine();
        int newHorPos = myDungeon.getPlayer().getPosition().getHorLocation();
        int newVertPos = myDungeon.getPlayer().getPosition().getVertLocation();
        int count = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == 'd' && newHorPos + 1 <= maxHorValue) {
                newHorPos++;
                count++;
            }
            if (inputString.charAt(i) == 's' && newVertPos - 1 >= 0) {
                newVertPos--;
                count++;
            }
            if (inputString.charAt(i) == 'a' && newHorPos - 1 >= 0) {
                newHorPos--;
                count++;
            }
            if (inputString.charAt(i) == 'w' && newVertPos + 1 <= maxVertValue) {
                newVertPos++;
                count++;
            }
        }
        Position newPositionForPlayer = new Position(newHorPos, newVertPos);
        myDungeon.getPlayer().setPosition(newPositionForPlayer);
        return count;
    }

    public void randomlyMoveVampire(int numberOfTimes, Vampire vampire) {
        //cant cross border
        //cant hit another vampire
        Random myRandom = new Random();
        Position currentPosition = vampire.getPosition();
        int newHorPos = currentPosition.getHorLocation();
        int newVertPos = currentPosition.getVertLocation();
        ArrayList<Position> currentVampPositions = myDungeon.getVampirePositions();

        for (int i = 0; i < numberOfTimes; i++) {

            //move to right
            if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                if (newHorPos + 1 <= maxHorValue && !currentVampPositions.contains(new Position(newHorPos + 1, newVertPos))) {
                    newHorPos++;
                }
            } //move to left
            else if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                if (newHorPos - 1 >= 0 && !currentVampPositions.contains(new Position(newHorPos - 1, newVertPos))) {
                    newHorPos--;
                }
            } //move up
            else if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                if (newVertPos + 1 <= 0 && !currentVampPositions.contains(new Position(newHorPos, newVertPos + 1))) {
                    newVertPos++;
                }
            } //move down
            else {
                if (newVertPos - 1 >= 0 && !currentVampPositions.contains(new Position(newHorPos, newVertPos - 1))) {
                    newVertPos--;
                }
            }
            Position newPosition = new Position(newHorPos, newVertPos);
            myDungeon.getVampirePositions().remove(currentPosition);
            myDungeon.getVampirePositions().add(newPosition);
        }
    }

}
