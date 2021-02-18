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
    static Faker faker = new Faker(new Locale("ru"));

    private DataUserTest() {
    }

    public static String cityForInput() {
        Random random = new Random();
        String city[] = {"Калининград", "Санкт-Петербург", "Москва", "Воронеж"};
        int rand = random.nextInt(city.length);
        return city[rand];
    }
    static String cityNoVal() {
        Random random = new Random();
        int rand = random.nextInt(3);
        String cityNoVal[] = {"Ставрополь", "Ростов-на-Дону","Сеул"};
        return cityNoVal[rand];
    }

    static String dataPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    static String dataName() {
        String name = faker.name().lastName();
        name = name + " " + faker.name().firstName();
        return name;
    }

    static String dataNameWishLetterEBrief() {
        Random random = new Random();
        int rand = random.nextInt(4);
        String nameWishEBrief[] = {"Александра", "Анастасия", "Татьяна", "Ёнхи" };
        return nameWishEBrief[rand];
    }

    static String dateMeeting(int days) {
        String dateMeeting = LocalDate.now().plusDays(17).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dateMeeting;
    }

}