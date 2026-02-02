package starter.demoblaze.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import starter.demoblaze.page.PageDemoblaze;

public class OrderSuccess {
    public static Question<String> message() {
        return actor -> Text.of(PageDemoblaze.CONFIRMATION_TEXT)
                .answeredBy(actor);
    }
}
