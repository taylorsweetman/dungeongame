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
    private Map<Position, SpaceContent> dungeonMap;
    private Display display;
    private Movement movementLogic;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        this.vampires = vampires;
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        dungeonMap = new HashMap<Position, SpaceContent>();
        generatePositions();
        display = new Display(this);
        movementLogic = new Movement(this);
    }

    //creates HashMap of all possible positions of dungeon
    public final void generatePositions() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                dungeonMap.put(new Position(i, j), new EmptySpace());
            }
        }
        placeVamps();
    }

    public final void placeVamps() {
        Random myRan = new Random();
        Position origin = new Position(0, 0);
        int unallocatedVamps = vampires;

        while (unallocatedVamps > 0) {

            int ranX = myRan.nextInt(length);
            int ranY = myRan.nextInt(height);
            Position ranPos = new Position(ranX, ranY);

            if (!positionContainsVamp(ranPos) && !ranPos.equals(origin)) {
                dungeonMap.put(ranPos, new Vampire());
                unallocatedVamps--;
            }

        }
    }

    //might be a redundant method
    public boolean positionContainsVamp(Position pos) {
        SpaceContent creatureAtPos = dungeonMap.get(pos);

        return creatureAtPos.isVampire();

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

    public boolean winCondition() {
        for (Position pos : dungeonMap.keySet()) {
            if (positionContainsVamp(pos)) {
                return false;
            }
        }
        return true;
    }

    public boolean lostCondition() {
        return moves == 0;
    }

    public void run() {

        display.show();
        if (!winCondition() && !lostCondition()) {
            movementLogic.getDeltaPositionFromText();
            moves--;
        }
    }

}
