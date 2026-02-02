package starter.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Arrays;

public class SelectAnyDifferentProduct implements Interaction {

    private final Collection<String> excludedNames;

    public SelectAnyDifferentProduct(Collection<String> excludedNames) {
        this.excludedNames = excludedNames;
    }

    public static SelectAnyDifferentProduct notIn(Collection<String> excludedNames) {
        return new SelectAnyDifferentProduct(excludedNames);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        if (!(driver instanceof JavascriptExecutor)) {
            throw new IllegalStateException("Driver does not support JS execution");
        }

        String script = "var items = Array.from(document.querySelectorAll('#tbodyid h4 a, #tbodyid h2.name, #tbodyid a'));"
                + "var texts = items.map(function(e){return e.textContent.trim();});" 
                + "return texts.join('||');";

        Object result = ((JavascriptExecutor) driver).executeScript(script);
        if (result == null) throw new IllegalStateException("Could not retrieve product names from page");

        String joined = result.toString();
        String[] names = joined.split("\\\\|\\\\|");

        String toClick = Arrays.stream(names)
                .map(String::trim)
                .filter(n -> !n.isEmpty())
                .filter(n -> !excludedNames.contains(n))
                .findFirst()
                .orElse(null);

        if (toClick == null) {
            throw new IllegalStateException("No alternative product found on the listing that is not excluded: " + excludedNames);
        }

        // Click the product by finding anchor with matching text
        String clickScript = "var target = Array.from(document.querySelectorAll('#tbodyid a, #tbodyid h4 a, #tbodyid h2.name')).find(function(e){return e.textContent.trim()==arguments[0];}); if(target){ target.click(); return true;} else { return false;}";
        Object clicked = ((JavascriptExecutor) driver).executeScript(clickScript, toClick);
        if (!(clicked instanceof Boolean) || !((Boolean) clicked)) {
            throw new IllegalStateException("Failed to click the alternative product: " + toClick);
        }

        // small wait for navigation
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        // Store selected product name in actor memory for later verification
        actor.remember("lastSelectedProduct", toClick);
    }
}
