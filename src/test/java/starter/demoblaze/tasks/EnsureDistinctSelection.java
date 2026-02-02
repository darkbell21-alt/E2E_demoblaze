package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;

import java.util.Collection;

public class EnsureDistinctSelection implements Interaction {

    private final String productName;

    public EnsureDistinctSelection(String productName) {
        this.productName = productName;
    }

    public static EnsureDistinctSelection ensure(String productName) {
        return new EnsureDistinctSelection(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Object recalled = actor.recall("selectedProducts");
        if (recalled instanceof Collection) {
            Collection selected = (Collection) recalled;
            if (selected.contains(productName)) {
                // choose alternative product while keeping selected set
                actor.attemptsTo(SelectAnyDifferentProduct.notIn(selected));
                // after clicking alternative, we assume SelectAnyDifferentProduct stores lastSelectedProduct
                Object last = actor.recall("lastSelectedProduct");
                if (last != null) {
                    selected.add((String) last);
                    actor.remember("selectedProducts", selected);
                }
            }
        }
    }
}
