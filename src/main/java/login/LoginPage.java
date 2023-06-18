package login;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginPage {
    private final By personalAccountBtn = By.xpath(".//p[contains(text(),'Личный Кабинет')]"); //кнопка личный кабинет
    private final By registrationBtn = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]"); //кнопка зарегистрироваться внизу
    private final By forgottPasswordBtn = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Восстановить пароль')]"); //кнопка восстановить пароль
    private final By singInBtn = By.xpath(".//button[text()='Войти']"); //кнопка войти
    private final By emailInput = By.xpath("//label[contains(text(),'Email')]/../input"); // поле емаил
    private final By passwordInput = By.xpath("//label[contains(text(),'Пароль')]/../input"); //поле пароль
    private final By constructorBtn = By.xpath(".//p[text()='Конструктор']"); //кнопка конструктор


    private final WebDriver driver;

    public LoginPage(WebDriver webDriver) {
        driver = webDriver;
    }
    @Step("Клик на кнопку войти в аккаунт")
    public void clickSingInBtn() {
        driver.findElement(singInBtn).click();
    }
    @Step("Клик на кнопку регистрация")
    public void clickRegistrationBtn() {
        driver.findElement(registrationBtn).click();
    }
    @Step("Клик на кнопку Восстановить пароль")
    public void clickForgottPasswordBtn() {
        driver.findElement(forgottPasswordBtn).click();
    }
    @Step("Клик на кнопку личный кабинет")
    public void clickPersonalAccountBtn() {
        driver.findElement(personalAccountBtn).click();
    }
    @Step("Ввод почты в поле почта")
    public void setEmailInput(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }
    @Step("Ввод пароля в поле пороль")
    public void setPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Установка учетной записи")
    public void setSingInUserAccount(String email, String password) {
        clickPersonalAccountBtn();
        setEmailInput(email);
        setPasswordInput(password);
        clickSingInBtn();
    }
    @Step("Проверка наличия кнопки Войти, после выхода")
    public void checkSingInBtnIsDisplayed() {
        assertThat("После выхода из профиля отображается кнопка Войти", true,
                equalTo(driver.findElement(personalAccountBtn).isDisplayed()));
    }
    @Step("Клик на кнопку конструктор")
    public void clickConstructorBtn() {
        driver.findElement(constructorBtn).click();
    }
    @Step("Найти кнопку восстановление пароля")
    public void findForgottPassword() {
        WebElement element = driver.findElement(forgottPasswordBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}




