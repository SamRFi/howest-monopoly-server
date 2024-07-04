package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiPrisonTests extends OpenApiTestsBase {

    @Test
    void getOutOfJailFine(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            public String getOutOfJailFine(String gameId, String playerName) {
                return "Successfully bought out of jail";
            }
        });
        post(
                testContext,
                "/games/000/prison/Alice/fine",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void getOutOfJailFineUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/prison/Alice/fine",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void getOutOfJailFree(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/prison/Alice/free",
                "some-token",
                response -> assertNotYetImplemented(response, "getOutOfJailFree")
        );
    }

    @Test
    void getOutOfJailFreeUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/prison/Alice/free",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
