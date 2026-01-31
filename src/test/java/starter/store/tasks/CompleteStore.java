package starter.store.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.store.page.PageDemoSauce;

import java.util.Map;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompleteStore {
    public static Task withInfo(Map<String, String> info) {
        return Task.where("{0} completes purchase information",
                WaitUntil.the(PageDemoSauce.FIRST_NAME, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(info.get("firstName")).into(PageDemoSauce.FIRST_NAME),
                Enter.theValue(info.get("lastName")).into(PageDemoSauce.LAST_NAME),
                Enter.theValue(info.get("zipCode")).into(PageDemoSauce.ZIP_CODE),
                Click.on(PageDemoSauce.CONTINUE_BUTTON),
                WaitUntil.the(PageDemoSauce.FINISH_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(PageDemoSauce.FINISH_BUTTON)
        );
    }
}