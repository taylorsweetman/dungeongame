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
            System.out.println(dungeon.getMoves() + "\n");
            System.out.println(currentList());
            System.out.println(mapInString());

        }
    }

    public String currentList() {
        String returnString = "";

        returnString += "@ " + dungeon.getPlayer().getCurrentPosition().getyLocation() + " " + dungeon.getPlayer().getCurrentPosition().getxLocation() + "\n";

        for (Position pos : dungeon.getDungeonMap().keySet()) {
            if (dungeon.positionContainsVamp(pos)) {
                returnString += "v " + pos.getVertLocation() + " " + pos.getHorLocation() + "\n";
            }
        }
        return returnString;
    }

    public String mapInString() {
        int height = dungeon.getHeight();
        int length = dungeon.getLength();
        Position currentPosition = new Position();
        String returnString = "";

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                currentPosition = new Position(i, j);
                if (dungeon.positionContainsHuman(currentPosition)) {
                    returnString += "@";
                    if (currentPosition.getVertLocation() == length - 1) {
                        returnString += "\n";
                    }
                } else if (dungeon.positionContainsVamp(currentPosition)) {
                    returnString += "v";
                    if (currentPosition.getVertLocation() == length - 1) {
                        returnString += "\n";
                    }
                } else {
                    returnString += ".";
                    if (currentPosition.getVertLocation() == length - 1) {
                        returnString += "\n";
                    }
                }

            }

        }
        return returnString;
    }

}
