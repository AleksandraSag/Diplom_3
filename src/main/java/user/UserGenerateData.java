package user;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerateData extends User {
    @Step("Создание пользователя с рандомными данными")
    public static User getRandomUser() {
        String email = RandomStringUtils.randomAlphabetic(9) + "@bk.ru";
        String password = RandomStringUtils.randomAlphabetic(9);
        String name = RandomStringUtils.randomAlphabetic(9);
        return new User(email, password, name);
    }
}
