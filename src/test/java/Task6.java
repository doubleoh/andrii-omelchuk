/**
 * Created by andrii.omelchuk on 4/18/2017.
 */
import java.io.File;
import java.util.List;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Task6 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://localhost/litecart/admin");
        // login
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']"))
                .click();

        List<WebElement> numberOfItemsBeforeAdd = null;
        try{
            numberOfItemsBeforeAdd = driver.findElements(By.linkText("Disco Duck"));
        }catch(NoSuchElementException e){
            System.out.println("There were no such items before.");
            e.printStackTrace();
        }
        // fill out General page
        driver.findElement(By.linkText("Add New Product")).click();

        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("Disco Duck");
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("Disco Duck's code");
        driver.findElement(By.cssSelector("input[value='1-3']")).click();
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("11.01.2017");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("10.01.2018");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("disco-duck.jpg").getFile());
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(file.getAbsolutePath());

        // fill out Information page
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        Select sel = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        sel.selectByValue("1");

        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("disco duck");
        driver.findElement(By.cssSelector("input[name='short_description[en]']"))
                .sendKeys("TheBestShortDescriptionEvah");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Some description.");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("New toy's head");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("SomeMeta");

        // fill out Prices page

        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("2");
        sel = new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        sel.selectByValue("USD");
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("15");
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]'")).sendKeys("16");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("13");
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("14");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Disco Duck"))));
        if(!(numberOfItemsBeforeAdd == null)){
            assertEquals((numberOfItemsBeforeAdd.size()+1),driver.findElements(By.linkText("Disco Duck")).size());
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
