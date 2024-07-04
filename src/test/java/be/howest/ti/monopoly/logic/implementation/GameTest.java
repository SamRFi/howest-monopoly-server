package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void findPlayer() {
        Game game = new Game(3, false, "random", 1);
        game.addPlayer("Bob");
        game.addPlayer("Sam");
        game.addPlayer("Ash");
        game.findPlayer("Sam").addMoney(1000);
        assertEquals("Player{name='Sam', currentTile='Go', jailed=false, money=2500" +
                ", bankrupt=false, getOutOfJailFreeCards=0" +
                ", properties=[], debt=0}", game.findPlayer("Sam").toString());
    }

    @Test
    void getGameId() {
        Game game = new Game(3, false, "random", 2);
        Game game2 = new Game(3, false, "random", 3);
        Game game3 = new Game(3, false, "random", 4);

        assertEquals("random_3", game2.getId());
        assertEquals("random_4", game3.getId());
    }

    @Test
    void buyingPropertyAsYouRoll() {
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Bob");
        game.addPlayer("Sam");
        game.addPlayer("Ash");


        game.movePlayerWithPreDefinedDices("Bob", 2, 3);
        assertEquals(false , game.isCanRoll());
        game.purchasePropertyForPlayer("Bob", game.findPlayer("Bob").getCurrentTile());
        assertEquals(true, game.isCanRoll());
        assertEquals("Reading RR", game.findPlayer("Bob").getProperties().get(0).getProperty());

        assertEquals("Sam", game.getCurrentPlayer());
        game.movePlayerWithPreDefinedDices("Sam", 2, 1);
        assertEquals("Baltic", game.findPlayer("Sam").getCurrentTile());
        game.purchasePropertyForPlayer("Sam", game.findPlayer("Sam").getCurrentTile());
        assertEquals("Baltic", game.findPlayer("Sam").getProperties().get(0).getProperty());
        game.movePlayerWithPreDefinedDices("Ash", 6, 2);
        game.purchasePropertyForPlayer("Ash", game.findPlayer("Ash").getCurrentTile());
        assertEquals("Vermont", game.findPlayer("Ash").getProperties().get(0).getProperty());

        //check if property stays out of directSale and doesnt stop the turn anymore
        game.movePlayerWithPreDefinedDices("Bob", 36, 1 );
        assertEquals("Baltic", game.findPlayer("Bob").getCurrentTile());
        assertEquals(true, game.isCanRoll());
        game.movePlayerWithPreDefinedDices("Sam", 1, 3);

        assertEquals(1440, game.findPlayer("Sam").getMoney());
        assertEquals(1500, game.findPlayer("Bob").getMoney());
    }

    @Test
    void turnManagementWithThrowingDoubleDices() {
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Bob");
        game.addPlayer("Sam");
        game.addPlayer("Ash");


        game.movePlayerWithPreDefinedDices("Bob", 4, 4);
        assertEquals(false, game.isCanRoll());
        assertEquals("Vermont", game.findPlayer(game.getCurrentPlayer()).getCurrentTile() );
        assertEquals("Bob", game.getCurrentPlayer());
        game.purchasePropertyForPlayer("Bob", "Vermont");

        game.movePlayerWithPreDefinedDices("Bob", 4, 4);
        game.purchasePropertyForPlayer("Bob", game.findPlayer("Bob").getCurrentTile());
        assertEquals("Saint James", game.findPlayer("Bob").getProperties().get(1).getProperty());

        game.movePlayerWithPreDefinedDices("Bob", 2, 4);
        game.movePlayerWithPreDefinedDices("Sam", 1, 1);
        game.movePlayerWithPreDefinedDices("Sam", 1, 1);
        game.movePlayerWithPreDefinedDices("Sam", 2, 2);
        game.movePlayerWithPreDefinedDices("Ash", 3, 5);
        assertEquals(true, game.findPlayer("Sam").isJailed());
        assertEquals("Jail", game.findPlayer("Sam").getCurrentTile());

    }
    @Test
    void dontBuyProperty(){
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Bob");
        game.addPlayer("Sam");
        game.addPlayer("Ash");


        game.movePlayerWithPreDefinedDices(game.getCurrentPlayer(),1,2);
        assertEquals(false, game.isCanRoll());
        game.dontPurchasePropertyForPlayer("Bob", "Baltic");
        assertEquals(true, game.isCanRoll());
        assertEquals("Sam", game.getCurrentPlayer());
        game.movePlayerWithPreDefinedDices("Sam",1,2);
        game.movePlayerWithPreDefinedDices("Ash",4,2);
        assertEquals("Ash", game.getCurrentPlayer());
    }

    @Test
    void startGameWhenFull(){
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Bob");
        game.addPlayer("Sam");

        assertEquals(false, game.isStarted());
        game.addPlayer("Kilian");
        assertEquals(true, game.isStarted());
    }

    @Test
    void addTurns() {
        Game game = new Game(2, false, "random", 2);
        game.addPlayer("Bob");
        game.addPlayer("Sam");

        game.movePlayerWithPreDefinedDices("Bob",1,2);
        assertEquals("[{roll=[1, 2], player='Bob', type='DEFAULT', moves=[{tile=Baltic, description=can buy this property in direct}]}]"
                , game.getTurns().toString());
        game.dontPurchasePropertyForPlayer("Bob", "Baltic");
        game.movePlayerWithPreDefinedDices("Sam",1,2);
        assertEquals("[{roll=[1, 2], player='Bob', type='DEFAULT', moves=[{tile=Baltic, description=can buy this property in direct}]}," +
                " {roll=[1, 2], player='Sam', type='DEFAULT', moves=[{tile=Baltic, description=is resting}]}]"
                , game.getTurns().toString());
    }

    @Test
    void collectDebt() {
        Game game = new Game(2, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");

        game.movePlayerWithPreDefinedDices("Sam",1,2);
        game.purchasePropertyForPlayer("Sam", "Baltic");
        game.movePlayerWithPreDefinedDices("Bob",1,2);
        game.playerCollectsDebtFromDebtor("Sam", "Baltic", "Bob");
        assertEquals(1444, game.findPlayer("Sam").getMoney());
        assertEquals(1496, game.findPlayer("Bob").getMoney());
    }

    @Test
    void collectDebtWithImprovements() {
        Game game = new Game(2, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");

        game.movePlayerWithPreDefinedDices("Sam",1,2);
        game.purchasePropertyForPlayer("Sam", "Baltic");
        game.findPlayer("Sam").addProperty("Mediterranean");
        game.findPlayer("Sam").buildHouse("Baltic");
        game.findPlayer("Sam").buildHouse("Mediterranean");
        game.findPlayer("Sam").buildHouse("Baltic");
        game.findPlayer("Sam").buildHouse("Mediterranean");
        game.movePlayerWithPreDefinedDices("Bob",1,2);
        game.playerCollectsDebtFromDebtor("Sam", "Baltic", "Bob");
        assertEquals(1300, game.findPlayer("Sam").getMoney());
        assertEquals(1440, game.findPlayer("Bob").getMoney());
    }

    @Test
    void makePlayerBankrupt() {
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");
        game.addPlayer("Ash");

        game.movePlayerWithPreDefinedDices("Sam",1,2);
        game.makePlayerBankrupt("Sam");
        game.movePlayerWithPreDefinedDices("Bob",1,2);
        game.purchasePropertyForPlayer("Bob", "Baltic");
        game.movePlayerWithPreDefinedDices("Ash",1,2);
        game.movePlayerWithPreDefinedDices("Bob",1,2);
        assertEquals(true, game.findPlayer("Sam").isBankrupt());
    }

    @Test
    void getOutOfJailByDoubleThrow() {
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");
        game.addPlayer("Ash");

        game.movePlayerWithPreDefinedDices("Sam",2,2);
        game.movePlayerWithPreDefinedDices("Sam",3,3);
        assertEquals(false, game.findPlayer("Sam").isJailed());
        game.movePlayerWithPreDefinedDices("Sam",5,5);
        assertEquals(true, game.findPlayer("Sam").isJailed());
        game.movePlayerWithPreDefinedDices("Bob", 1, 3);
        game.movePlayerWithPreDefinedDices("Ash", 1, 3);
        game.movePlayerWithPreDefinedDices("Sam",2,5);
        assertEquals("Jail", game.findPlayer("Sam").getCurrentTile());
        game.movePlayerWithPreDefinedDices("Bob", 5, 1);
        game.movePlayerWithPreDefinedDices("Ash", 2, 4);
        game.movePlayerWithPreDefinedDices("Sam", 4, 4);
        assertNotEquals("Jail", game.findPlayer("Sam").getCurrentTile());
    }

    @Test
    void getOutOfJailByBuying() {
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");
        game.addPlayer("Ash");

        game.movePlayerWithPreDefinedDices("Sam",2,2);
        game.movePlayerWithPreDefinedDices("Sam",3,3);
        game.movePlayerWithPreDefinedDices("Sam",5,5);
        game.movePlayerWithPreDefinedDices("Bob", 1, 3);
        game.movePlayerWithPreDefinedDices("Ash", 1, 3);
        game.movePlayerWithPreDefinedDices("Sam",2,5);
        assertEquals("Jail", game.findPlayer("Sam").getCurrentTile());
        game.movePlayerWithPreDefinedDices("Bob", 5, 1);
        game.movePlayerWithPreDefinedDices("Ash", 2, 4);
        game.findPlayer("Sam").buyOutOfJail();
        game.movePlayerWithPreDefinedDices("Sam", 2, 4);
        assertNotEquals("Jail", game.findPlayer("Sam").getCurrentTile());
        assertEquals(1450, game.findPlayer("Sam").getMoney());
    }

    @Test
    void getOutOfJailAfterThreeFailingTurns() {
        Game game = new Game(3, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");
        game.addPlayer("Ash");

        game.movePlayerWithPreDefinedDices("Sam",2,2);
        game.movePlayerWithPreDefinedDices("Sam",3,3);
        game.movePlayerWithPreDefinedDices("Sam",5,5);
        game.movePlayerWithPreDefinedDices("Bob", 1, 3);
        game.movePlayerWithPreDefinedDices("Ash", 1, 3);
        game.movePlayerWithPreDefinedDices("Sam",2,5);
        assertEquals("Jail", game.findPlayer("Sam").getCurrentTile());
        game.movePlayerWithPreDefinedDices("Bob", 5, 1);
        game.movePlayerWithPreDefinedDices("Ash", 2, 4);
        game.movePlayerWithPreDefinedDices("Sam", 3, 6);
        game.movePlayerWithPreDefinedDices("Bob", 5, 15);
        game.movePlayerWithPreDefinedDices("Ash", 5, 15);
        game.movePlayerWithPreDefinedDices("Sam", 3, 6);
        assertEquals(false, game.findPlayer("Sam").isJailed());
        assertEquals(1450, game.findPlayer("Sam").getMoney());
        assertEquals("Jail", game.findPlayer("Sam").getCurrentTile());
        game.movePlayerWithPreDefinedDices("Bob", 5, 34);
        game.movePlayerWithPreDefinedDices("Ash", 5, 34);
        game.movePlayerWithPreDefinedDices("Sam", 6, 4);
        assertNotEquals("Jail", game.findPlayer("Sam").getCurrentTile());
    }

    @Test
    void getDescriptionOfSpecialCard() {
        Game game = new Game(2, false, "random", 2);
        game.addPlayer("Sam");
        game.addPlayer("Bob");

        game.movePlayerWithPreDefinedDices("Sam", 29, 1);
        assertEquals("Bob", game.getCurrentPlayer());
        assertEquals("Jail", game.findPlayer("Sam").getCurrentTile());
        assertEquals(true, game.findPlayer("Sam").isJailed());
    }

}

