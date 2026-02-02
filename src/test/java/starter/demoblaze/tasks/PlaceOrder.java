package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.demoblaze.page.PageDemoblaze;

import java.util.Map;

public class PlaceOrder {

    public static Performable withInfo(Map<String, String> info) {
        return Task.where("{0} places an order",
                actor -> actor.attemptsTo(
                        Click.on(PageDemoblaze.CART_LINK),
                        Click.on(PageDemoblaze.PLACE_ORDER_BUTTON),
                        Enter.theValue(info.get("name")).into(PageDemoblaze.NAME),
                        Enter.theValue(info.get("country")).into(PageDemoblaze.COUNTRY),
                        Enter.theValue(info.get("city")).into(PageDemoblaze.CITY),
                        Enter.theValue(info.get("card")).into(PageDemoblaze.CARD),
                        Enter.theValue(info.get("month")).into(PageDemoblaze.MONTH),
                        Enter.theValue(info.get("year")).into(PageDemoblaze.YEAR),
                        Click.on(PageDemoblaze.PURCHASE_BUTTON)
                )
        );
    }
}
