// src/test/java/starter/purchase/tasks/Checkout.java
package starter.store.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import starter.store.page.PageDemoSauce;

public class Checkout {
    public static Task proceed() {
        return Task.where("{0} proceeds to checkout",
                Click.on(PageDemoSauce.CART_BUTTON),
                Click.on(PageDemoSauce.CHECKOUT_BUTTON)
        );
    }
}