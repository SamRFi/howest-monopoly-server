package be.howest.ti.monopoly.logic.implementation;

import java.util.*;

public class Turn {
    private List<Integer> roll;
    private String player;
    private String type;
    private List<Map<String, String>> moves;

    public Turn(int dice1, int dice2, String player) {
        this.roll = new ArrayList<>();
        this.moves = new ArrayList<>();

        this.roll.add(dice1);
        this.roll.add(dice2);
        this.player = player;
        this.type = "DEFAULT";
    }

    public Turn () {
        this(0, 0, "");
    }


    public void setRoll(int dice1, int dice2) {
        this.roll.add(dice1);
        this.roll.add(dice2);
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addMove(String tile, String description) {
        Map<String, String> movesMap = new HashMap<>();
        movesMap.put("tile", tile);
        movesMap.put("description", description);
        this.moves.add(movesMap);
    }



    public List<Integer> getRoll() {
        return roll;
    }

    public String getPlayer() {
        return player;
    }

    public String getType() {
        return type;
    }

    public List<Map<String, String>> getMoves() {
        return moves;
    }

    @Override
    public String toString() {
        return "{" +
                "roll=" + roll +
                ", player='" + player + '\'' +
                ", type='" + type + '\'' +
                ", moves=" + moves +
                '}';
    }
}
