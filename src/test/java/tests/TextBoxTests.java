package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Demo text box")
public class TextBoxTests extends TestBase {

    @Test
    @Story("User can fill and submit text box form")
    @Tags({@Tag("regression"), @Tag("ui")})
    @DisplayName("Text box can be filled")
    void fillFormTest() {
        step("Open page /text-box", () -> {
            open("/text-box");
            if ($(".fc-dialog-content").isDisplayed())
                $(byText("Consent")).click();
        });

        step("Fill form", () -> {
            $("#userName").setValue("Alex");
            $("#userEmail").setValue("alex@egorov.com");
            $("#currentAddress").setValue("Some street 1");
            $("#permanentAddress").setValue("Another street 1");
            $("#submit").click();
        });

        step("Check results", () -> {
            $("#output #name").shouldHave(text("Alex"));
            $("#output #email").shouldHave(text("alex@egorov.com"));
            $("#output #currentAddress").shouldHave(text("Some street 1"));
            $("#output #permanentAddress").shouldHave(text("Another street 1"));
        });
    }

    @Test
    @Story("User can fill and submit text box form manually")
    @Tags({@Tag("manual"), @Tag("ui")})
    @DisplayName("Text box can be filled (Manual)")
    void fillFormManualTest() {
        step("Open page /text-box");
        step("Fill form");
        step("Check results");
    }

    @Test
    @Story("User cannot submit form with invalid email")
    @Tags({@Tag("regression"), @Tag("ui"), @Tag("negative")})
    @DisplayName("Text box cannot be submitted with invalid email")
    void invalidEmailTest() {
        step("Open page /text-box", () -> open("/text-box"));
        step("Enter invalid email", () -> {
            $("#userEmail").setValue("invalid-email");
            $("#submit").click();
        });
        step("Check error message", () -> {
            $("#userEmail").shouldHave(text("Invalid email format"));
        });
    }

    @Test
    @Story("User cannot submit empty form")
    @Tags({@Tag("regression"), @Tag("ui"), @Tag("negative")})
    @DisplayName("Text box cannot be submitted empty")
    void emptyFormTest() {
        step("Open page /text-box", () -> open("/text-box"));
        step("Click submit without filling fields", () -> $("#submit").click());
        step("Check error messages", () -> {
            $("#userName").shouldHave(text("Required field"));
            $("#userEmail").shouldHave(text("Required field"));
        });
    }

    @Test
    @Story("Manual - Check text box layout")
    @Tags({@Tag("manual"), @Tag("ui")})
    @DisplayName("Check text box layout (Manual)")
    void checkLayoutManualTest() {
        step("Open page /text-box");
        step("Verify all input fields and labels are displayed");
    }

    @Test
    @Story("Manual - Verify text box submission message")
    @Tags({@Tag("manual"), @Tag("ui")})
    @DisplayName("Verify submission message (Manual)")
    void verifySubmissionMessageManualTest() {
        step("Open page /text-box");
        step("Submit the form");
        step("Check that success message appears");
    }
}

