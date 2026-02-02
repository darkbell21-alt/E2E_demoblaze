package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import java.util.Collection;

public class RememberSelectedProduct implements Interaction {

    private final String productName;

    public RememberSelectedProduct(String productName) {
        this.productName = productName;
    }

    public static RememberSelectedProduct remember(String productName) {
        return new RememberSelectedProduct(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Object recalled = actor.recall("selectedProducts");
        Collection selected = null;
        if (recalled instanceof Collection) {
            selected = (Collection) recalled;
        }
        if (selected == null) selected = new java.util.ArrayList();
        if (!selected.contains(productName)) selected.add(productName);
        actor.remember("selectedProducts", selected);
    }
}
