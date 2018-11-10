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
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.*;

public class Dungeon {

    private int length;
    private int height;
    private int vampires;
    private int moves;
    private boolean vampiresMove;
    private Human player;
    private List<Vampire> vampList;
    private Scanner myScanner;
    private int maxHorValue;
    private int maxVertValue;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        this.vampires = vampires;
        this.moves = moves;
        maxHorValue = length - 1;
        maxVertValue = height - 1;
        myScanner = new Scanner(System.in);
        this.vampiresMove = vampiresMove;
        player = new Human();
        vampList = new ArrayList<Vampire>();
        placeVamps();
    }

    public final void placeVamps() {
        Random myRan = new Random();
        Position playerLocation = player.getPosition();
        int unallocatedVamps = vampires;

        while (unallocatedVamps > 0) {
            int ranX = myRan.nextInt(length);
            int ranY = myRan.nextInt(height);
            Position ranPos = new Position(ranX, ranY);

            if (!getVampirePositions().contains(ranPos) && !player.getPosition().equals(ranPos)) {
                Vampire newVamp = new Vampire(ranX, ranY);
                vampList.add(newVamp);
                unallocatedVamps--;
            }

        }
    }

    public ArrayList<Position> getVampirePositions() {
        ArrayList<Position> returnList = new ArrayList<Position>();

        for (Vampire vampire : vampList) {
            returnList.add(vampire.getPosition());
        }
        return returnList;
    }

    public boolean winCondition() {
        return vampList.isEmpty();
    }

    public boolean lostCondition() {
        return moves == 0;
    }

    public int givePlayerNewLocationFromText() {
        String inputString = myScanner.nextLine();
        int newHorPos = player.getPosition().getHorLocation();
        int newVertPos = player.getPosition().getVertLocation();
        int count = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == 'd' && newHorPos + 1 <= maxHorValue) {
                newHorPos++;
                Position newPos = new Position(newHorPos, newVertPos);
                player.setPosition(newPos);
                if (getVampirePositions().contains(getPlayer().getPosition())) {
                    vampList.remove(getVampireAtPosition(newPos));
                }
                count++;
            }
            if (inputString.charAt(i) == 'w' && newVertPos - 1 >= 0) {
                newVertPos--;
                Position newPos = new Position(newHorPos, newVertPos);
                player.setPosition(newPos);
                if (getVampirePositions().contains(getPlayer().getPosition())) {
                    vampList.remove(getVampireAtPosition(newPos));
                }
                count++;
            }
            if (inputString.charAt(i) == 'a' && newHorPos - 1 >= 0) {
                newHorPos--;
                Position newPos = new Position(newHorPos, newVertPos);
                player.setPosition(newPos);
                if (getVampirePositions().contains(getPlayer().getPosition())) {
                    vampList.remove(getVampireAtPosition(newPos));
                }
                count++;
            }
            if (inputString.charAt(i) == 's' && newVertPos + 1 <= maxVertValue) {
                newVertPos++;
                Position newPos = new Position(newHorPos, newVertPos);
                player.setPosition(newPos);
                if (getVampirePositions().contains(getPlayer().getPosition())) {
                    vampList.remove(getVampireAtPosition(newPos));
                }
                count++;
            }
        }
        Position newPositionForPlayer = new Position(newHorPos, newVertPos);
        player.setPosition(newPositionForPlayer);
        return count;
    }

    public void randomlyMoveVampire(int numberOfTimes, Vampire vampire) {
        //cant cross border
        //cant hit another vampire
        Random myRandom = new Random();

        int newHorPos = vampire.getPosition().getHorLocation();
        int newVertPos = vampire.getPosition().getVertLocation();

        if (vampiresMove == true) {

            for (int i = 0; i < numberOfTimes; i++) {

                //move to right
                if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                    if (newHorPos + 1 <= maxHorValue && !getVampirePositions().contains(new Position(newHorPos + 1, newVertPos))) {
                        newHorPos++;
                    }
                } //move to left
                else if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                    if (newHorPos - 1 >= 0 && !getVampirePositions().contains(new Position(newHorPos - 1, newVertPos))) {
                        newHorPos--;
                    }
                } //move up
                else if (myRandom.nextBoolean() == true && myRandom.nextBoolean() == true) {
                    if (newVertPos + 1 <= 0 && !getVampirePositions().contains(new Position(newHorPos, newVertPos + 1))) {
                        newVertPos++;
                    }
                } //move down
                else {
                    if (newVertPos - 1 >= 0 && !getVampirePositions().contains(new Position(newHorPos, newVertPos - 1))) {
                        newVertPos--;
                    }
                }
                vampire.setPosition(new Position(newHorPos, newVertPos));
            }
        }
    }

    public void show() {
        if (winCondition()) {
            System.out.println("YOU WIN");
        } else if (lostCondition()) {
            System.out.println("YOU LOSE");
        } else {
            System.out.println(moves + "\n");
            System.out.println(currentList());
            System.out.println(mapInString());
        }
    }

    public String currentList() {
        String returnString = "";

        returnString += "@ " + player.getPosition().getHorLocation() + " " + player.getPosition().getVertLocation() + "\n";

        for (Position pos : getVampirePositions()) {

            returnString += "v " + pos.getHorLocation() + " " + pos.getVertLocation() + "\n";

        }
        return returnString;
    }

    public String mapInString() {
        String returnString = "";

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                Position positionInMap = new Position(j, i);

                if (player.getPosition().equals(positionInMap)) {
                    returnString += "@";
                    if (positionInMap.getHorLocation() == length - 1) {
                        returnString += "\n";
                    }
                } else if (getVampirePositions().contains(positionInMap)) {
                    returnString += "v";
                    if (positionInMap.getHorLocation() == length - 1) {
                        returnString += "\n";
                    }
                } else {
                    returnString += ".";
                    if (positionInMap.getHorLocation() == length - 1) {
                        returnString += "\n";
                    }
                }

            }

        }
        return returnString;
    }

    public void run() {
        show();

        while (!winCondition() && !lostCondition()) {
            int numberOfVampMoves = givePlayerNewLocationFromText();

            for (Vampire vampire : vampList) {
                randomlyMoveVampire(numberOfVampMoves, vampire);
            }

            moves--;
            show();
        }
    }

    public Dungeon getDungeon() {
        return this;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getMoves() {
        return moves;
    }

    public Human getPlayer() {
        return player;
    }

    public Vampire getVampireAtPosition(Position pos) {
        for (Vampire vampire : vampList) {
            if (vampire.getPosition().equals(pos)) {
                return vampire;
            }
        }
        return null;
    }

}
