package starter.demoblaze.page;

import net.serenitybdd.screenplay.targets.Target;

public class PageDemoblaze {

    public static Target PRODUCT_LINK(String productName) {
        return Target.the("product link for " + productName)
                .locatedBy("//a[text()='{0}']").of(productName);
    }

    public static final Target ADD_TO_CART = Target.the("add to cart button")
            .locatedBy("//a[text()='Add to cart' or contains(text(),'Add to cart')]");

    public static final Target CART_LINK = Target.the("cart link")
            .locatedBy("#cartur");

    public static final Target HOME_LINK = Target.the("home link")
            .locatedBy("//a[text()='Home']");

    public static final Target PRODUCT_LIST = Target.the("product list")
            .locatedBy("#tbodyid");

    public static Target CART_ITEM(String productName) {
        return Target.the("cart item for " + productName)
                .locatedBy("//td[text()='{0}']").of(productName);
    }

    public static final Target PLACE_ORDER_BUTTON = Target.the("place order button")
            .locatedBy("//button[text()='Place Order']");

    public static final Target NAME = Target.the("name field").locatedBy("#name");
    public static final Target COUNTRY = Target.the("country field").locatedBy("#country");
    public static final Target CITY = Target.the("city field").locatedBy("#city");
    public static final Target CARD = Target.the("card field").locatedBy("#card");
    public static final Target MONTH = Target.the("month field").locatedBy("#month");
    public static final Target YEAR = Target.the("year field").locatedBy("#year");

    public static final Target PURCHASE_BUTTON = Target.the("purchase button")
            .locatedBy("//button[text()='Purchase']");

    public static final Target CONFIRMATION_TEXT = Target.the("confirmation text")
            .locatedBy("//*[contains(text(),'Thank you for your purchase!')]");

}
