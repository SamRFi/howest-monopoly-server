package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiInteractionWithOtherPlayerTests extends OpenApiTestsBase {

    @Test
    void collectDebt(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            public String collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
                return "Successfully collected debt";
            }
        });
        delete(
                testContext,
                "/games/000/players/Alice/properties/some-property/visitors/Bob/rent",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void collectDebtUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/visitors/Bob/rent",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }


}
