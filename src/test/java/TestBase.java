import browser.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import user.User;
import user.UserApi;
import user.UserGenerateData;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    User user;
    UserApi userApi = new UserApi();
    private String bearerToken;

    @Before
    @Step("Открытие браузера и страницы, авторизация")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = WebDriverFactory.get("chrome");


        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        userApi = new UserApi();
        user = new UserGenerateData().getRandomUser();
    }

    @After
    @Step ("Закрытие браузера, выход из аккаунта")
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.quit();
        if (user != null) {
            ValidatableResponse responseLogin = UserApi.userLogin(user);
            bearerToken = responseLogin.extract().path("accessToken");
            if (bearerToken == null) {
                return;
            }
            UserApi.deleteUser(bearerToken);
        }
    }
}











