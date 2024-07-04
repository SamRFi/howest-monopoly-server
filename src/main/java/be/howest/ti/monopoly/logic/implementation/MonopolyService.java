package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;

import java.util.ArrayList;

import java.util.List;


public class MonopolyService extends ServiceAdapter {

    private List<Game> games = new ArrayList<>();

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<Tile> getTiles() {
        return new Board().returnTiles();
    }

    @Override
    public Tile getTile(int position){
        for (Tile tile : getTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public Tile getTile(String name) {
        for (Tile tile : getTiles()){
            if (tile.getName().equals(name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public List<String> getChance() {
       return List.of(
               new SpecialCard("Advance to Boardwalk", "Boardwalk").getDescription(),
               new SpecialCard("Advance to Go (Collect $200)", "Go").getDescription(),
               new SpecialCard("Advance to Illinois Avenue. If you pass Go, collect $200", "Illinois Avenue").getDescription(),
               new SpecialCard("Advance to St. Charles Place. If you pass Go, collect $200", "St. Charles Place").getDescription(),
               new SpecialCard("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled", "Railroad").getDescription(),
               new SpecialCard("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.", "Utility").getDescription(),
               new SpecialCard("Bank pays you dividend of $50)", "+50").getDescription(),
               new SpecialCard("Get Out of Jail Free", "+1").getDescription(),
               new SpecialCard("Go Back 3 Spaces", "-3").getDescription(),
               new SpecialCard("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200", "True").getDescription(),
               new SpecialCard("Make general repairs on all your property. For each house pay $25. For each hotel pay $100", "Geen idee").getDescription(),
               new SpecialCard("Speeding fine $15", "-15").getDescription(),
               new SpecialCard("Take a trip to Reading Railroad. If you pass Go, collect $200", "+200").getDescription(),
               new SpecialCard("You have been elected Chairman of the Board. Pay each player $50", "+50").getDescription(),
               new SpecialCard("Your building loan matures. Collect $150", "+150").getDescription()
       );


    }

    @Override
    public List<String> getCommunityChest() {
        return List.of(
                new SpecialCard("Advance to Go (Collect $200)", "Go").getDescription(),
                new SpecialCard("Bank error in your favor. Collect $200", "+200").getDescription(),
                new SpecialCard("Doctor's fee. Pay $50", "-50").getDescription(),
                new SpecialCard("From sale of stock you get $50", "+50").getDescription(),
                new SpecialCard("Get Out of Jail Free", "+1").getDescription(),
                new SpecialCard("Go to Jail. Go directly to jail, do not pass Go, do not collect $200", "True").getDescription(),
                new SpecialCard("Holiday fund matures. Receive $100", "+100").getDescription(),
                new SpecialCard("Income tax refund. Collect $20", "+20").getDescription(),
                new SpecialCard("It is your birthday. Collect $10 from every player", "-10 * (amountOfPlayers-1)").getDescription(),
                new SpecialCard("Life insurance matures. Collect $100", "+100").getDescription(),
                new SpecialCard("Pay hospital fees of $100", "-100").getDescription(),
                new SpecialCard("Pay school fees of $50", "+50").getDescription(),
                new SpecialCard("Receive $25 consultancy fee", "+25").getDescription(),
                new SpecialCard("You are assessed for street repair. $40 per house. $115 per hotel", "geen idee").getDescription(),
                new SpecialCard("You have won second prize in a beauty contest. Collect $10", "+10").getDescription(),
                new SpecialCard("You inherit $100", "+100").getDescription()
        );
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        Game game = new Game(numberOfPlayers, false, prefix, games.size());

        this.games.add(game);

        return game;
    }

    @Override
    public String buyProperty(String gameId, String playerName, String propertyName) {
        getGame(gameId).purchasePropertyForPlayer(playerName, propertyName);
        return "succesfully added property";
    }

    @Override
    public String dontBuyProperty(String gameId, String playerName, String propertyName) {
        getGame(gameId).dontPurchasePropertyForPlayer(playerName, propertyName);
        return "successfully declined property";
    }

    @Override
    public String buyHouse(String gameId, String playerName, String propertyName) {
        getGame(gameId).findPlayer(playerName).buildHouse(propertyName);
        return "successfully bought a house";
    }

    @Override
    public String sellHouse(String gameId, String playerName, String propertyName) {
        getGame(gameId).findPlayer(playerName).removeHouse(propertyName);
        return "successfully sold a house";
    }

    @Override
    public String buyHotel(String gameId, String playerName, String propertyName) {
        getGame(gameId).findPlayer(playerName).buildHouse(propertyName);
        return "successfully bought a hotel";
    }

    @Override
    public String sellHotel(String gameId, String playerName, String propertyName) {
        getGame(gameId).findPlayer(playerName).removeHouse(propertyName);
        return "successfully sold a hotel";
    }

    @Override
    public Game getGame(String gameId) {
        for (int i = 0; i < this.games.size(); i = i + 1) {
            if (this.games.get(i).getId().equals(gameId)) {
                return this.games.get(i);
            }
        }
        throw new MonopolyResourceNotFoundException("game doesn't exist");
    }


    @Override
    public String joinGame(String gameId, String playerName) {
        Game game = getGame(gameId);
        if(game == null){
            throw new MonopolyResourceNotFoundException("No such game: " + gameId);
        }
        else {
            if (game.findPlayer(playerName) == null) {
                getGame(gameId).addPlayer(playerName);
                return "You have successfully joined the game.";
            }
            // en nog iets zodatje niet metdezelfde naam kan spelen.
        }
        return null;
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix){
        if (numberOfPlayers == 0){
            return games;
        }
        else{
            List<Game> gamesWithParams = new ArrayList<>();
            for(Game game : games){
                if(game.getNumberOfPlayers() == numberOfPlayers && game.isStarted() == started){
                    gamesWithParams.add(game);
                }
            }
            return gamesWithParams;
        }
    }

    @Override
    public String rollDice(String gameId, String playerName) {
        getGame(gameId).diceRoll(playerName);
        return "successfully rolled dice";
    }

    @Override
    public String getGameUnauthorized(String gameId) {
        return "You have the wrong player token.";
    }

    @Override
    public String getOutOfJailFine(String gameId, String playerName) {
        getGame(gameId).findPlayer(playerName).buyOutOfJail();
        return "Successfully bought out of jail";
    }

    @Override
    public String collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        getGame(gameId).playerCollectsDebtFromDebtor(playerName, propertyName, debtorName);
        return "Successfully collected debt";
    }

    @Override
    public String declareBankruptcy(String gameId, String playerName) {
        getGame(gameId).makePlayerBankrupt(playerName);
        return "you are bankrupt now";
    }
}

