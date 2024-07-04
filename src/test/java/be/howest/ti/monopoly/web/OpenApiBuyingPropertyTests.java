package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;


class OpenApiBuyingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyProperty(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public String buyProperty(String gameId, String playerName, String propertyName) {
                return "succesfully added property";
            }
        });
        post(
                testContext,
                "/games/game01/players/Alice/properties/some-property",
                "game01-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void buyPropertyUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void dontBuyProperty(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public String dontBuyProperty(String gameId, String playerName, String propertyName) {
                return "successfully declined property";
            }
        });
        delete(
                testContext,
                "/games/000/players/Alice/properties/some-property",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void dontBuyPropertyUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
