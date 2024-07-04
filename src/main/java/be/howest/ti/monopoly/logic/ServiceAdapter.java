package be.howest.ti.monopoly.logic;

import java.util.List;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getChance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String joinGame(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object rollDice(String gameID, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getCommunityChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getGameUnauthorized(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getOutOfJailFine(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyHouse(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object sellHouse(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object buyHotel(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object sellHotel(String gameId, String playerName, String propertyName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object declareBankruptcy(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }
}
