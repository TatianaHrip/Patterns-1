package ru.netology.data;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

class OrderDataUserTest {

    void dataInput(int days) {
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(DataUserTest.dataInput(days));
    }

    @BeforeEach
    void before() {
        open("http://localhost:9999");
    }

    @Test
    void getTrueInputValidForm() {
        $("[placeholder=Город]").setValue(DataUserTest.cityForInput());
        int inDays = 5;
        dataInput(inDays);
        $("[data-test-id=phone]").$("[name=phone]").setValue(DataUserTest.dataPhone());
        $("[data-test-id=name].input_type_text .input__control").setValue(DataUserTest.dataName());
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + DataUserTest.dataInput(inDays)));
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $$("[class=button__text]").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + DataUserTest.dataInput(inDays)));
    }

    @Test
    void errorExpectedWhenInputIncorrectCity() {
        $("[placeholder=Город]").setValue(DataUserTest.cityNoVal());
        int inDays = 1;
        dataInput(inDays);
        $("[data-test-id=phone]").$("[name=phone]").setValue(DataUserTest.dataPhone());
        $("[data-test-id=name].input_type_text .input__control").setValue(DataUserTest.dataName());
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactTextCaseSensitive("Доставка в выбранный город недоступна"));
    }

    @Test
    void errorExpectedWhenEmptyFieldDate() {
        $("[placeholder=Город]").setValue(DataUserTest.cityForInput());
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=phone]").$("[name=phone]").setValue(DataUserTest.dataPhone());
        $("[data-test-id=name].input_type_text .input__control").setValue(DataUserTest.dataName());
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave
                (exactTextCaseSensitive("Неверно введена дата"));
    }

    @Test
    void getTrueWishLetterEBriefInName() {
        $("[placeholder=Город]").setValue(DataUserTest.cityForInput());
        int inDays = 5;
        dataInput(inDays);
        $("[data-test-id=phone]").$("[name=phone]").setValue(DataUserTest.dataPhone());
        $("[data-test-id=name].input_type_text .input__control").setValue(DataUserTest.dataNameWishLetterEBrief());
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + DataUserTest.dataInput(inDays)));
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $$("[class=button__text]").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + DataUserTest.dataInput(inDays)));
    }
}