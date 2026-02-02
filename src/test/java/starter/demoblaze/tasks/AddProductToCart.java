package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.demoblaze.tasks.GoHomeInteraction;
import starter.demoblaze.tasks.EnsureDistinctSelection;
import starter.demoblaze.tasks.RememberSelectedProduct;
import starter.demoblaze.page.PageDemoblaze;
import java.time.Duration;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart {

    public static Performable product(String productName) {
        return Task.where("{0} adds product to cart",
                actor -> actor.attemptsTo(
                        // Ensure we don't select the same product twice — choose an alternative if already chosen
                        EnsureDistinctSelection.ensure(productName),
                        Click.on(PageDemoblaze.PRODUCT_LINK(productName)),
                        Click.on(PageDemoblaze.ADD_TO_CART),
                        // Accept the browser alert after adding to cart
                        // Custom interaction implemented below
                        AcceptAlertInteraction.acceptAlert(),
                        // Return to home to select next product and wait shortly for listing
                        GoHomeInteraction.goHome(),
                        WaitUntil.the(PageDemoblaze.PRODUCT_LIST, isVisible()).forNoMoreThan(Duration.ofSeconds(5)),
                        // Remember the selected product for this actor
                        RememberSelectedProduct.remember(productName)
                )
        );
    }

}
