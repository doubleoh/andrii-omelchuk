package Task10.tests;

/**
 * Created by andrii.omelchuk on 4/21/2017.
 */
import Task10.app.Application;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class GenericTest {

    Application app;
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        app = new Application(driver, "http://localhost/litecart/");
        app.start();

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
