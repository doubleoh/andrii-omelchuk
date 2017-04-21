package Task10.pages;

/**
 * Created by andrii.omelchuk on 4/21/2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

    public void addToCart() {
        WebElement el = driver.findElement(By.cssSelector("div#cart")).findElement(By.cssSelector("span.quantity"));
        int itemsInCart = Integer.parseInt(el.getAttribute("textContent"));
        try {
            Select sel = new Select(driver.findElement(By.cssSelector("select")));
            sel.selectByValue("Small");
        } catch (Exception e) {
        }

        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
        wait.until(ExpectedConditions.attributeContains(el, "textContent", itemsInCart + 1 + ""));
    }

}
