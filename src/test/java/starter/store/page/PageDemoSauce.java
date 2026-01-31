package starter.store.page;

import net.serenitybdd.screenplay.targets.Target;

public class PageDemoSauce {
    public static final Target USERNAME_FIELD = Target.the("username field")
            .locatedBy("#user-name");
    public static final Target PASSWORD_FIELD = Target.the("password field")
            .locatedBy("#password");
    public static final Target LOGIN_BUTTON = Target.the("login button")
            .locatedBy("#login-button");

    public static Target addToCartButtonFor(String productName) {
        return Target.the("Add to cart button for " + productName)
                .locatedBy("//div[text()='{0}']/ancestor::div[@class='inventory_item']//button")
                .of(productName);
    }
    public static final Target CART_BUTTON = Target.the("cart button")
            .locatedBy(".shopping_cart_link");
    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .locatedBy("//button[@id='checkout']");

    public static final Target FIRST_NAME = Target.the("first name field")
            .locatedBy("//input[@id='first-name']");

    public static final Target LAST_NAME = Target.the("last name field")
            .locatedBy("//input[@id='last-name']");

    public static final Target ZIP_CODE = Target.the("zip code field")
            .locatedBy("//input[@id='postal-code']");

    public static final Target CONTINUE_BUTTON = Target.the("continue button")
            .locatedBy("//input[@id='continue']");

    public static final Target FINISH_BUTTON = Target.the("finish button")
            .locatedBy("//button[@id='finish']");
    public static final Target CONFIRMATION_MESSAGE = Target.the("confirmation message")
            .locatedBy(".complete-header");
}