package ex;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class UITest {
    public static final String FIRST_NAME = "Andrey";
    public static final String SECOND_NAME = "NEVERMIND";
    public static final String USER_EMAIL = "QATEST@QA.RU";
    public static final String USER_NUMBER = "8005553535";
    public static final String SUBJECTS = "English";
    public static final String CURRENT_ADDRESS = "NEVERMIND 541231";
    public static final String GENDER = "Male";
    public static final String HOBBY = "Music";


    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

    }

    @Test
    void testUI() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(SECOND_NAME);
        $("#userEmail").setValue(USER_EMAIL);

        $("#genterWrapper").$(byText(GENDER)).click();

        $("#userNumber").setValue(USER_NUMBER);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(6);
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--029").click();

        $("#subjectsInput").setValue(SUBJECTS).pressEnter();

        $("#hobbiesWrapper").$(byText(HOBBY)).click();

        $("#uploadPicture").uploadFromClasspath("image/with_day_of_day.jpg");

        $("#currentAddress").setValue(CURRENT_ADDRESS);

        $("#react-select-3-input").setValue("ut").pressEnter();
        $("#react-select-4-input").setValue("luc").pressEnter();

        $("#submit").click();


        $(".modal-dialog").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(
                text(FIRST_NAME),
                text(SECOND_NAME),
                text(USER_EMAIL),
                text(USER_NUMBER),
                text(SUBJECTS),
                text(CURRENT_ADDRESS),
                text(GENDER),
                text(HOBBY),
                text("with_day_of_day.jpg"),
                text("Uttar Pradesh"),
                text("Lucknow"),
                text("29 June,2000")
        );
    }

}
