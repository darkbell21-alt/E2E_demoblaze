package starter.demoblaze.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.demoblaze.questions.OrderSuccess;
import starter.demoblaze.tasks.AddProductToCart;
import starter.demoblaze.tasks.PlaceOrder;
import starter.demoblaze.tasks.VerifyCartContains;
import starter.demoblaze.utils.DemoBlazeDataStore;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DemoblazeStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("I open demoblaze home")
    public void i_open_demoblaze() {
        theActorCalled("demo user").attemptsTo(
                // Clear cookies/localStorage before running to avoid stale state
                starter.demoblaze.tasks.ClearBrowserData.clear(),
                Open.url("https://www.demoblaze.com/")
        );
        // initialize selected products memory to prevent accidental duplicates
        theActorCalled("demo user").remember("selectedProducts", new java.util.ArrayList<String>());
    }

    @When("I add the listed products for demoblaze user {string}")
    public void i_add_products_for_user(String userKey) throws Exception {
        JsonNode userData = DemoBlazeDataStore.getUserData(userKey);
        ObjectMapper mapper = new ObjectMapper();
        List<String> products = mapper.convertValue(
                userData.get("products"),
                new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {}
        );

        for (String product : products) {
            theActorInTheSpotlight().attemptsTo(
                    AddProductToCart.product(product)
            );
        }
    }

    @When("I view my demoblaze cart")
    public void i_view_my_cart() {
        // left intentionally blank: flow opens cart when placing order
    }

    @When("I place order for demoblaze user {string} and index {int}")
    public void i_place_order_for_user(String userKey, int index) throws Exception {
        List<Map<String, String>> checkoutList = DemoBlazeDataStore.getCheckoutList(userKey);

        if (index < checkoutList.size()) {
            // Ensure cart contains the expected products before placing order
            JsonNode userData = DemoBlazeDataStore.getUserData(userKey);
            ObjectMapper mapper = new ObjectMapper();
            List<String> products = mapper.convertValue(
                    userData.get("products"),
                    new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {}
            );

            theActorInTheSpotlight().attemptsTo(
                    VerifyCartContains.products(products),
                    PlaceOrder.withInfo(checkoutList.get(index))
            );
        } else {
            throw new IllegalArgumentException("Invalid index for checkoutData");
        }
    }

    @Then("I should see the demoblaze confirmation for user {string}")
    public void i_should_see_confirmation(String userKey) throws Exception {
        JsonNode userData = DemoBlazeDataStore.getUserData(userKey);
        String expectedMessage = userData.get("confirmationMessage").asText();

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(OrderSuccess.message()).contains(expectedMessage)
        );
    }
}
