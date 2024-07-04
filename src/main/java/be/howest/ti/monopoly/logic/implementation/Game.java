package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game extends Board {
    private final List<Player> players;
    private final int numberOfPlayers;
    private boolean started;
    private String currentPlayer;
    private final String prefix;
    private final int iteration;
    private boolean canRoll;
    private int doubleDiceStreakCounter;
    private String directSale;
    private List<Turn> turns;

    private Random dice = new SecureRandom();


    public Game(int numberOfPlayers, boolean started, String prefix, int iteration) {
        players = new ArrayList<>(numberOfPlayers);
        turns = new ArrayList<>();
        this.numberOfPlayers = numberOfPlayers;
        this.started = started;
        this.prefix = prefix;
        this.iteration = iteration;
        this.canRoll = true;
        this.doubleDiceStreakCounter = 0;
        this.directSale = null;
    }

    public void addPlayer(String playerName) {
        if (players.size() < numberOfPlayers) {
            players.add(new Player(playerName));
            if (players.size() == numberOfPlayers) {
                startGame();
            }
        } else {
            throw new IllegalMonopolyActionException("game is full");
        }
    }

    public void removePlayer(String playerName) {
        if (currentPlayer.equals(playerName)) {
            if (isOnDirectSale()) {
                allowDiceRolling();
                this.directSale = null;
            }
            turnToNextPlayer(playerName);
        }
        players.remove(findPlayer(playerName));
    }

    public void makePlayerBankrupt(String playerName) {
        if (currentPlayer.equals(playerName)) {
            allowDiceRolling();
            this.directSale = null;
            turnToNextPlayer(playerName);
        }
        findPlayer(playerName).makeBankrupt();
    }

    public void purchasePropertyForPlayer(String playerName, String propertyName) {
        findPlayer(playerName).addProperty(propertyName);
        transactionWithBank(playerName, getPropertyPrice(propertyName));
        allowDiceRolling();
        this.directSale = null;
        putThisTileNameOutOfDirectSale(propertyName);
        if (doubleDiceStreakCounter == 0) {
            turnToNextPlayer(playerName);
        }
    }

    public void dontPurchasePropertyForPlayer(String playerName,String propertyName) {
        allowDiceRolling();
        this.directSale = null;
        putThisTileNameOutOfDirectSale(propertyName);
        if (doubleDiceStreakCounter == 0){
            turnToNextPlayer(playerName);
        }
    }

    public void buyHouse(String playerName, String propertyName) {
        findPlayer(playerName).findProperty(propertyName).addHouse();
    }

    public void transactionWithBank(String playerName, int amount) {
        Player player = findPlayer(playerName);
        if (player.getMoney() < amount) {
            throw new InsufficientFundsException("player has insufficient funds");
        }
        player.withdrawMoney(amount);
    }


    public Player findPlayer(String playerName) {
        for (int i = 0; i < this.players.size(); i = i + 1) {
            if (this.players.get(i).getName().equals(playerName)) {
                return this.players.get(i);
            }
        }
        return null;
    }

    private int findPlayerIndexInList(String playerName) {
        for (int i = 0; i < this.players.size(); i = i + 1) {
            if (this.players.get(i).getName().equals(playerName)) {
                return i;
            }
        }
        return -1;
    }

    public void startGame() {
        this.started = true;
        this.currentPlayer = players.get(0).getName();
    }

    public void allowDiceRolling() {
        this.canRoll = true;
    }

    public void diceRoll(String playerName) {
        if (this.canRoll && playerName.equals(this.currentPlayer)) {
            if (!findPlayer(playerName).isJailed()) {
                int roll1 = 1 + dice.nextInt(5);
                int roll2 = 1 + dice.nextInt(5);
                this.turns.add(new Turn(roll1, roll2, playerName));

                if (roll1 == roll2 && this.doubleDiceStreakCounter == 2) {
                    thirdDoubleRoll();
                } else {
                    movePlayerWithDiceRoll(roll1, roll2);
                }
            } else {
                diceRollJail(playerName);
            }
        } else {
            throw new IllegalMonopolyActionException("This player can't throw dice right now");
        }
    }

    private void diceRollJail(String playerName) {
        int roll1 = 1 + dice.nextInt(5);
        int roll2 = 1 + dice.nextInt(5);
        this.turns.add(new Turn(roll1, roll2, playerName));

        if (roll1 == roll2) {
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "you are released from jail by double throw");
            findLastTurn().setType("JAIL_DOUBLE_FREE");
            findPlayer(playerName).putOutOfJail();
            findPlayer(playerName).setJailSittingCounter(0);
            movePlayerWithDiceRoll(roll1, roll2);
        } else if (findPlayer(playerName).getJailSittingCounter() == 2) {
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "you were forced to buy out of jail after 3 missed turns");
            findLastTurn().setType("JAIL_PAY_FINE");
            findPlayer(playerName).buyOutOfJail();
            findPlayer(playerName).setJailSittingCounter(0);
            movePlayerWithDiceRoll(roll1, roll2);
        } else {
            findPlayer(playerName).addJailSittingCount();
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "this is turn " + findPlayer(playerName).getJailSittingCounter() + " in jail and you will remain in jail");
            this.findLastTurn().setType("JAIL_STAY");
            turnToNextPlayer(currentPlayer);
        }
    }

    private void movePlayerWithDiceRoll(int roll1, int roll2) {
        int rolledDices = roll1 + roll2;
        findPlayer(currentPlayer).movePosition(rolledDices);
        if (roll1 == roll2 && isOnDirectSale()) {
            this.doubleDiceStreakCounter +=1;
            landedOnDirectSale();
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "can buy this property in direct");
        } else if (roll1 == roll2) {
            if (!isLandedOnSpecialTile()) {
                findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "is resting");}
            this.doubleDiceStreakCounter +=1;
        } else if (isOnDirectSale()) {
            this.doubleDiceStreakCounter = 0;
            landedOnDirectSale();
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "can buy this property in direct");
        } else {
            if (!isLandedOnSpecialTile()) {
                findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "is resting");}
            this.doubleDiceStreakCounter = 0;
            turnToNextPlayer(currentPlayer);
        }
    }

    private void specialTileAction(String type) {
        if (type.contains("hance")) {
            SpecialCard randomPick = findChanceCard(dice.nextInt(14));
            String description = randomPick.getDescription();
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), description);
        } else if (type.contains("Community")) {
            SpecialCard randomPick = findCommunityCard(dice.nextInt(14));
            String description = randomPick.getDescription();
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), description);
        } else {
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "you go to jail");
            findPlayer(currentPlayer).putOnTile("Jail");
            findPlayer(currentPlayer).putInJail();
        }
    }

    private boolean isLandedOnSpecialTile() {
        String typeOfTile = super.findTile(findPlayer(currentPlayer).getCurrentTile()).getType();
        if (typeOfTile.contains("hance") || typeOfTile.contains("Community") || typeOfTile.equals("Go to Jail")) {
            specialTileAction(typeOfTile);
            return true;
        }
        return false;
    }

    private boolean isOnDirectSale() {
        return isThisTileNameDirectSale(findPlayer(currentPlayer).getCurrentTile());
    }

    private void thirdDoubleRoll() {
        findPlayer(currentPlayer).putOnTile("Jail");
        findPlayer(currentPlayer).putInJail();
        findLastTurn().addMove("Jail", "goes to jail due to throwing double three times");
        turnToNextPlayer(currentPlayer);
    }

    private void landedOnDirectSale() {
        this.directSale = findPlayer(currentPlayer).getCurrentTile();
        this.canRoll = false;
    }

    public void movePlayerWithPreDefinedDices(String playerName, int amountDice1, int amountDice2) {
        if (this.canRoll && playerName.equals(this.currentPlayer)) {
            if (!findPlayer(playerName).isJailed()) {
                int roll1 = amountDice1;
                int roll2 = amountDice2;
                this.turns.add(new Turn(roll1, roll2, playerName));

                if (roll1 == roll2 && this.doubleDiceStreakCounter == 2) {
                    thirdDoubleRoll();
                } else {
                    movePlayerWithDiceRoll(roll1, roll2);
                }
            } else {
                predefinedDiceRollJail(playerName, amountDice1, amountDice2);
            }
        } else {
            throw new IllegalMonopolyActionException("This player can't throw dice right now");
        }
    }

    private void predefinedDiceRollJail(String playerName, int diceRoll1, int diceRoll2) {
        int roll1 = diceRoll1;
        int roll2 = diceRoll2;
        this.turns.add(new Turn(roll1, roll2, playerName));

        if (roll1 == roll2) {
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "you are released from jail by double throw");
            findPlayer(playerName).putOutOfJail();
            findPlayer(playerName).setJailSittingCounter(0);
            movePlayerWithDiceRoll(roll1, roll2);
        } else if (findPlayer(playerName).getJailSittingCounter() == 2) {
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "you were forced to buy out of jail after 3 missed turns");
            findPlayer(playerName).buyOutOfJail();
            findPlayer(playerName).setJailSittingCounter(0);
            turnToNextPlayer(currentPlayer);
        } else {
            findPlayer(playerName).addJailSittingCount();
            findLastTurn().addMove(findPlayer(currentPlayer).getCurrentTile(), "this is turn " + findPlayer(playerName).getJailSittingCounter() + " in jail and you will remain in jail");
            turnToNextPlayer(currentPlayer);
        }
    }

    private void turnToNextPlayer(String playerName) {
        if (this.directSale == null) {
            int playerIndexToTurnTo;
            int currentPlayerIndex = findPlayerIndexInList(playerName);
            if (currentPlayerIndex == players.size()-1) {
                playerIndexToTurnTo = 0;
            } else {
                playerIndexToTurnTo = currentPlayerIndex + 1;
            }
            if (players.get(playerIndexToTurnTo).isBankrupt()) {
                turnToNextPlayer(players.get(playerIndexToTurnTo).getName());
            } else {
                this.currentPlayer = players.get(playerIndexToTurnTo).getName();
            }

        } else {
            throw new IllegalMonopolyActionException("Property must be bought or declined first");
        }
    }

    public void playerCollectsDebtFromDebtor(String playerName, String propertyName, String debtorName) {
        if (findPlayer(playerName).findListOfOwnedPropertyNames().contains(propertyName)) {
            int lastRoll = findLastTurn().getRoll().get(0) + findLastTurn().getRoll().get(1);
            int houseCount = findPlayer(playerName).findProperty(propertyName).getHouseCount();
            int hotelCount = findPlayer(playerName).findProperty(propertyName).getHotelCount();
            int cost = findPlayer(playerName).findProperty(propertyName).calculateRentCost(lastRoll, propertyName, houseCount, hotelCount);
            if (findPlayer(debtorName).getMoney() > cost) {
                findPlayer(debtorName).withdrawMoney(cost);
                findPlayer(playerName).addMoney(cost);
            } else {
                throw new InsufficientFundsException("debtor doesn't have enough to pay the rent");
            }
        } else {
            throw new MonopolyResourceNotFoundException("You don't own this property!");
        }

    }

    public Turn findLastTurn() {
        return this.turns.get(turns.size()-1);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public boolean isStarted() {
        return started;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<Integer> getLastDiceRoll() {
        if (!this.turns.isEmpty()) {
            return findLastTurn().getRoll();
        } else {
            return Collections.emptyList();
        }
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getId() {
        return prefix + "_" + iteration;
    }

    public boolean isCanRoll() {
        return canRoll;
    }

    public String getDirectSale() {
        return directSale;
    }

    public List<Turn> getTurns() {
        return turns;
    }
}
