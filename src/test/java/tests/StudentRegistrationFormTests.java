package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StudentRegistrationFormTests extends TestBase {
    Faker faker = new Faker();
    String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            mobile = faker.number().digits(10),
            address = faker.address().fullAddress(),
            birth = "07 March,1997",
            hobby1 = "Sports",
            hobby2 = "Music",
            state = "NCR",
            city = "Delhi",
            stateAndCity = "NCR Delhi",
            fullName = firstName + " " + lastName,
            hobbies = "Sports, Music",
            acceptFormText = "Thanks for submitting the form",
            acceptFormTextNegative = "Thanks for submitting the form1",
            sex = "Male",
            subject = "Physics";

    @Test
    @Tag("Positive Test")
    @Owner("Maksim D")
    public void openDemoQaSiteAndCheckForm() {
        step("Open students registration form", () -> open("https://demoqa.com/automation-practice-form"));

        step("Student Fill Data", () -> {
            step("Fill common data", () -> {
                $("#firstName").setValue(firstName);
                $("#lastName").setValue(lastName);
                $("#userEmail").setValue(userEmail);
                $(byText(sex)).click();
                $("#userNumber").setValue(mobile);
            });
            step("Set Birth Date", () -> {
                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOption("March");
                $(".react-datepicker__year-select").selectOption("1997");
                $(".react-datepicker__month").$(byText("7")).click();
            });
            step("Set Subject", () -> {
                $("#subjectsInput").setValue(subject).pressEnter();
            });
            step("Set Hobbies", () -> {
                $("#hobbiesWrapper").$(byText(hobby1)).click();
                $("#hobbiesWrapper").$(byText(hobby2)).click();
            });
            step("Upload Pic", () -> {
                $("#uploadPicture").uploadFromClasspath("123.jpg");
            });
            step("Set Address", () -> {
                $("#currentAddress").setValue(address);
                $("#stateCity-wrapper").scrollTo().$(byText("Select State")).click();
                $(byText(state)).click();
                $("#stateCity-wrapper").$(byText("Select City")).click();
                $(byText(city)).click();
            });
            step("Submit form", () ->
                    $("#submit").click());
        });
        step("Check Data Form", () -> {
            $(".modal-content").shouldHave(text(acceptFormText),
                    text(fullName),
                    text(userEmail),
                    text(mobile),
                    text("Male"),
                    text(birth),
                    text("Physics"),
                    text(hobbies),
                    text("123.jpg"),
                    text(address),
                    text(stateAndCity));
        });
    }

    @Test
    @Tag("Negative Test")
    @Owner("Maksim D")
    public void openDemoQaSiteAndCheckFormNegative() {
        step("Open students registration form", () -> open("https://demoqa.com/automation-practice-form"));

        step("Student Fill Data", () -> {
            step("Fill common data", () -> {
                $("#firstName").setValue(firstName);
                $("#lastName").setValue(lastName);
                $("#userEmail").setValue(userEmail);
                $(byText(sex)).click();
                $("#userNumber").setValue(mobile);
            });
            step("Set Birth Date", () -> {
                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOption("March");
                $(".react-datepicker__year-select").selectOption("1997");
                $(".react-datepicker__month").$(byText("7")).click();
            });
            step("Set Subject", () -> {
                $("#subjectsInput").setValue(subject).pressEnter();
            });
            step("Set Hobbies", () -> {
                $("#hobbiesWrapper").$(byText(hobby1)).click();
                $("#hobbiesWrapper").$(byText(hobby2)).click();
            });
            step("Upload Pic", () -> {
                $("#uploadPicture").uploadFromClasspath("123.jpg");
            });
            step("Set Address", () -> {
                $("#currentAddress").setValue(address);
                $("#stateCity-wrapper").scrollTo().$(byText("Select State")).click();
                $(byText(state)).click();
                $("#stateCity-wrapper").$(byText("Select City")).click();
                $(byText(city)).click();
            });
            step("Submit form", () ->
                    $("#submit").click());
        });
        step("Check Data Form", () -> {
            $(".modal-content").shouldHave(text(acceptFormTextNegative),
                    text(fullName),
                    text(userEmail),
                    text(mobile),
                    text("Male"),
                    text(birth),
                    text("Physics"),
                    text(hobbies),
                    text("123.jpg"),
                    text(address),
                    text(stateAndCity));
        });
    }
}