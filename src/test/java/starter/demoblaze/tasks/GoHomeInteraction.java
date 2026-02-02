package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.actions.Click;
import starter.demoblaze.page.PageDemoblaze;

public class GoHomeInteraction implements Interaction {

    public static GoHomeInteraction goHome() {
        return new GoHomeInteraction();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            actor.attemptsTo(
                    WaitUntil.the(PageDemoblaze.HOME_LINK, isVisible()).forNoMoreThan(Duration.ofSeconds(5)),
                    Click.on(PageDemoblaze.HOME_LINK),
                    WaitUntil.the(PageDemoblaze.PRODUCT_LIST, isVisible()).forNoMoreThan(Duration.ofSeconds(10))
            );
        } catch (Throwable t) {
            // fallback: navigate directly to the base URL and wait for product list
            driver.navigate().to("https://www.demoblaze.com");
            // small sleep as fallback to let page load before waiting (short explicit wait)
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(webDriver -> webDriver.findElements(org.openqa.selenium.By.cssSelector("#tbodyid")).size() > 0);
        }
    }
}
