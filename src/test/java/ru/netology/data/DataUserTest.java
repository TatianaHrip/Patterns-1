package ru.netology.data;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


class DataUserTest {
    private static Random random = new Random();
    static Faker faker = new Faker(new Locale("ru"));

    private DataUserTest() {
    }

    public static String cityForInput() {
        String city[] = {"Калининград", "Санкт-Петербург", "Москва", "Воронеж"};
        int rand = random.nextInt(city.length);
        return city[rand];
    }

    static String cityNoVal() {
        String cityNoVal[] = {"Сеул", "Банког", "Париж", "Гусу Лань"};
        int rand = random.nextInt(cityNoVal.length);
        return cityNoVal[rand];
    }

    static String dataNameWishLetterEBrief() {
        String nameWishEBrief[] = {"Ёнхи", "Лёша", "Ёнджун", "Доён"};
        int rand = random.nextInt(nameWishEBrief.length);
        return nameWishEBrief[rand];
    }

    static String dataInput(int days) {
        String dataInput = LocalDate.now().plusDays(17).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dataInput;
    }

}