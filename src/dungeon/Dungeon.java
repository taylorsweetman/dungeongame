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

public class Dungeon {

    private int length;
    private int height;
    private int vampires;
    private int moves;
    private boolean vampiresMove;
    private Display display;
    private Movement movementLogic;
    private Human player;
    private List<Vampire> vampList;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        this.vampires = vampires;
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        player = new Human();
        vampList = new ArrayList<Vampire>();
        placeVamps();
        display = new Display(this);
        movementLogic = new Movement(this);
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

    public void run() {

        display.show();
        while (!winCondition() && !lostCondition()) {
            Position deltaPlayerPos = movementLogic.givePlayerNewLocationFromText();
            moves--;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getMoves() {
        return moves;
    }

    public Human getPlayer() {
        return player;
    }


}
