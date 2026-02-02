package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.demoblaze.page.PageDemoblaze;

import java.time.Duration;
import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VerifyCartContains {

    public static Performable products(List<String> names) {
        return Task.where("{0} verifies cart contains products",
                actor -> {
                    for (String name : names) {
                        actor.attemptsTo(
                                WaitUntil.the(PageDemoblaze.CART_ITEM(name), isVisible()).forNoMoreThan(Duration.ofSeconds(5))
                        );
                    }
                }
        );
    }

}
