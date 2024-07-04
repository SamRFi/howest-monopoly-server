package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;

import java.util.List;

import be.howest.ti.monopoly.logic.implementation.Tile;



public interface IService {
    String getVersion();
    List<Tile> getTiles();

    Tile getTile(int position);

    Tile getTile(String name);

    List<String> getChance();

    Game createGame(String prefix, int numberOfPlayers);

    Object buyProperty(String gameId, String playerName, String propertyName);

    List<Game> getGames(boolean started, int numberOfPlayers, String prefix);

    Object rollDice(String gameID, String playerName);

    List<String> getCommunityChest();

    String joinGame(String gameId, String playerName);

    Game getGame(String gameId);

    String getGameUnauthorized(String pathGameId);

    Object dontBuyProperty(String gameId, String playerName, String propertyName);

    Object getOutOfJailFine(String gameId, String playerName);

    Object collectDebt(String gameId, String playerName, String propertyName, String debtorName);

    Object buyHouse(String gameId, String playerName, String propertyName);

    Object sellHouse(String gameId, String playerName, String propertyName);

    Object buyHotel(String gameId, String playerName, String propertyName);

    Object sellHotel(String gameId, String playerName, String propertyName);

    Object declareBankruptcy(String gameId, String playerName);
}
