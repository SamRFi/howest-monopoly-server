package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiImprovingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyHouse(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public String buyHouse(String gameId, String playerName, String propertyName) {
                return "successfully bought a house";
            }
        });
        post(
                testContext,
                "/games/000/players/Alice/properties/some-property/houses",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void buyHouseUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void sellHouse(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public String sellHouse(String gameId, String playerName, String propertyName) {
                return "successfully sold a house";
            }
        });
        delete(
                testContext,
                "/games/000/players/Alice/properties/some-property/houses",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void sellHouseUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    //
    @Test
    void buyHotel(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public String buyHotel(String gameId, String playerName, String propertyName) {
                return "successfully bought a hotel";
            }
        });
        post(
                testContext,
                "/games/000/players/Alice/properties/some-property/hotel",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void buyHotelUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void sellHotel(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public String sellHotel(String gameId, String playerName, String propertyName) {
                return "successfully sold a hotel";
            }
        });
        delete(
                testContext,
                "/games/000/players/Alice/properties/some-property/hotel",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void sellHotelUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
