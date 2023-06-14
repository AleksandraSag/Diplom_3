package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class WebDriverFactory {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";
    public static WebDriver get(String browserName) {

        WebDriver driver;

        switch (browserName) {

            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);

                break;

            case "edge":
                EdgeOptions options2 = new EdgeOptions();
                options2.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(options2);

                break;

            default: throw new RuntimeException("Browser " + browserName + " not exist");

        }

        driver.manage().window();//.maximize();

        driver.navigate().to(URL);

        return driver;

    }

}