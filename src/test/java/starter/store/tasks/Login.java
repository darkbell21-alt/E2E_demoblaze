package starter.store.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.store.page.PageDemoSauce;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login {

    public static Task withCredentials(String username, String password) {
        return Task.where("{0} logs in with username " + username,
                WaitUntil.the(PageDemoSauce.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(username).into(PageDemoSauce.USERNAME_FIELD),
                WaitUntil.the(PageDemoSauce.PASSWORD_FIELD, isVisible()).forNoMoreThan(5).seconds(),
                Enter.theValue(password).into(PageDemoSauce.PASSWORD_FIELD),
                WaitUntil.the(PageDemoSauce.LOGIN_BUTTON, isVisible()).forNoMoreThan(5).seconds(),
                Click.on(PageDemoSauce.LOGIN_BUTTON)


        );
    }
}