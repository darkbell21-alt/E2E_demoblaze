package starter.store.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import starter.store.page.PageDemoSauce;

import java.util.List;

public class AddToCart {

    public static Performable fromList(List<String> productNames) {
        return Task.where("{0} adds products to cart",
                actor -> productNames.forEach(product ->
                        actor.attemptsTo(
                                Click.on(PageDemoSauce.addToCartButtonFor(product))
                        )
                )
        );
    }
}
