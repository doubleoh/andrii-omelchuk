package Task10.pages;

/**
 * Created by andrii.omelchuk on 4/21/2017.
 */

        import java.util.List;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;
    public CartPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver,10);
    }

    public void removeItem(){

        try{
            List<WebElement> removeButtons;
            List<WebElement> skus;
            skus = driver.findElements(By.cssSelector("td.sku"));
            removeButtons = driver.findElements(By.cssSelector("button[name='remove_cart_item']"));
            wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0)));
            removeButtons.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(skus.get(0)));
            removeButtons.remove(0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("There are no more items in your cart.");
        }
    }
}
