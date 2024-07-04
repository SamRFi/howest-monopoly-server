package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.web.tokens.MonopolyUser;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;

import java.util.Objects;


/**
 * The Request class is responsible for translating information that is part of the
 * request into Java.
 *
 * For every piece of information that you need from the request, you should provide a method here.
 * You can find information in:
 * - the request path: params.pathParameter("some-param-name")
 * - the query-string: params.queryParameter("some-param-name")
 * Both return a `RequestParameter`, which can contain a string or an integer in our case.
 * The actual data can be retrieved using `getInteger()` or `getString()`, respectively.
 * You can check if it is an integer (or not) using `isNumber()`.
 *
 * Finally, some requests have a body. If present, the body will always be in the json format.
 * You can acces this body using: `params.body().getJsonObject()`.
 *
 * **TIP:** Make sure that al your methods have a unique name. For instance, there is a request
 * that consists of more than one "player name". You cannot use the method `getPlayerName()` for both,
 * you will need a second one with a different name.
 */
public class Request {
    private final RoutingContext ctx;
    private final RequestParameters params;
    private final MonopolyUser user;
    private static final String TILEID = "tileId";
    private static final String NUMBEROFPLAYERS = "numberOfPlayers";
    private static final String STARTED = "started";

    private Request(RoutingContext ctx) {
        this.ctx = ctx;
        this.params = ctx.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        this.user = (MonopolyUser) ctx.user();
    }

    public static Request from(RoutingContext ctx) {
        return new Request(ctx);
    }

    public RoutingContext getRoutingContext() {
        return ctx;
    }

    public RequestParameters getRequestParameters() {
        return params;
    }

    public boolean isAuthorized(String expectedGameId, String expectedPlayerName) {
        return Objects.equals(expectedGameId, user.getGameId()) &&
                Objects.equals(expectedPlayerName, user.getPlayerName());
    }
    public boolean isAuthorized(String expectedGameId) {
        return Objects.equals(expectedGameId, user.getGameId());
    }

    public String getPlayerName(){
        return params.pathParameter("playerName").getString();
    }

    public String getDebtorName(){
        return params.pathParameter("debtorName").getString();
    }

    public String getPathGameId(){
        return params.pathParameter("gameId").getString();
    }


    public String getGameId(){
        return params.pathParameter("gameId").getString();
    }

    public String getPropertyName(){
        return params.pathParameter("propertyName").getString();
    }


    public int getTilePosition() {
        return params.pathParameter(TILEID).getInteger();
    }

    public boolean hasTilePosition() {
        return params.pathParameter(TILEID).isNumber();
    }

    public String getTileName() {
        return params.pathParameter(TILEID).getString();
    }

    public String getGamePrefix() {
        return params.body().getJsonObject().getString("prefix");
    }
    public String getBodyPlayerName() {
        return params.body().getJsonObject().getString("playerName");
    }
    public int getNumberOfPlayers() {
        return params.body().getJsonObject().getInteger(NUMBEROFPLAYERS);
    }




    public boolean getStarted() {
        if (params.queryParameter(STARTED) == null){
            return false;

        }
        else{
            return params.queryParameter(STARTED).getBoolean();
        }
    }

    public int getQueryNumberOfPlayers() {
        if (params.queryParameter(NUMBEROFPLAYERS) == null){
            return 0;
        }
        else{
            return params.queryParameter(NUMBEROFPLAYERS).getInteger();
        }

    }

    public String getQueryGamePrefix() {
        if (params.queryParameter(STARTED) == null){
            return "group";
        }
        else{
            return params.queryParameter("prefix").getString();
        }
    }

    public MonopolyUser getUser() {
        return user;
    }
}
