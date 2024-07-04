package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;


public class TestService implements IService {

    IService delegate = new ServiceAdapter();

    void setDelegate(IService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getVersion() {
        return delegate.getVersion();
    }

    @Override
    public List<Tile> getTiles() {
        return delegate.getTiles();
    }

    @Override
    public Tile getTile(int position) {
        return delegate.getTile(position);
    }

    @Override
    public Tile getTile(String name) {
        return delegate.getTile(name);
    }

    @Override
    public List<String> getChance() {
        return delegate.getChance();
    }

    @Override
    public Game createGame(String prefix, int numberOfPlayers) {
        return delegate.createGame(prefix, numberOfPlayers);
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        return delegate.buyProperty(gameId, playerName, propertyName);
    }

    @Override
    public String joinGame(String gameId, String playerName) {
        return delegate.joinGame(gameId, playerName);
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        return delegate.getGames(started, numberOfPlayers, prefix);
    }

    @Override
    public Object rollDice(String gameID, String playerName) {
        return delegate.rollDice(gameID, playerName);
    }

    @Override
    public List<String> getCommunityChest() {
        return delegate.getCommunityChest();
    }

    @Override
    public Game getGame(String gameId) {
        return delegate.getGame(gameId);
    }

    @Override
    public String getGameUnauthorized(String gameId) {
        return delegate.getGameUnauthorized(gameId);
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
        return delegate.dontBuyProperty(gameId,playerName,propertyName);
    }

    @Override
    public Object getOutOfJailFine(String gameId, String playerName) {
        return delegate.getOutOfJailFine(gameId, playerName);
    }

    @Override
    public Object collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        return delegate.collectDebt(gameId, playerName, propertyName, debtorName);
    }

    @Override
    public Object buyHouse(String gameId, String playerName, String propertyName) {
        return delegate.buyHouse(gameId, playerName, propertyName);
    }

    @Override
    public Object sellHouse(String gameId, String playerName, String propertyName) {
        return delegate.sellHouse(gameId, playerName, propertyName);
    }

    @Override
    public Object buyHotel(String gameId, String playerName, String propertyName) {
        return delegate.buyHotel(gameId, playerName, propertyName);
    }

    @Override
    public Object sellHotel(String gameId, String playerName, String propertyName) {
        return delegate.sellHotel(gameId, playerName, propertyName);
    }

    @Override
    public Object declareBankruptcy(String gameId, String playerName) {
        return delegate.declareBankruptcy(gameId, playerName);
    }

}
