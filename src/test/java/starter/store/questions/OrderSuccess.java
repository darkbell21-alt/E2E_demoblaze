package starter.store.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import starter.store.page.PageDemoSauce;

public class OrderSuccess {
    public static Question<String> message() {
        return actor -> Text.of(PageDemoSauce.CONFIRMATION_MESSAGE)
                .answeredBy(actor);
    }
}