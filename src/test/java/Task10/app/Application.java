package Task10.app;

/**
 * Created by andrii.omelchuk on 4/21/2017.
 */
import Task10.pages.CartPage;
import Task10.pages.MainPage;
import Task10.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {

    ProductPage pp;
    WebDriverWait wait;
    MainPage mp;
    String baseUrl;
    WebDriver driver;
    CartPage cp;

    public Application(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        mp = new MainPage(this.driver);
        cp = new CartPage(this.driver);
        wait = new WebDriverWait(this.driver, 10);
        pp = new ProductPage(this.driver);
    }

    public void start() {
        driver.get(baseUrl);
    }
    public void goToMainPage() {
        mp.goToMain();
    }

    public String getQuantityOfItemsInCart() {
        return driver.findElement(By.cssSelector("div#cart")).findElement(By.cssSelector("span.quantity"))
                .getAttribute("textContent");
    }

    public void goToCart() {
        driver.findElement(By.cssSelector("div#cart")).click();;
    }

    public void removeItem() {
        cp.removeItem();
    }

    public void addFirstItem() {
        if(!driver.getTitle().equals("Online Store | My Store")){
            driver.get(baseUrl);
        }
        mp.clickFirstProduct();
        pp.addToCart();
    }

    public void removeAllFromCart() {
        if(!driver.getTitle().equals("Checkout | My Store")){
            driver.get("http://localhost/litecart/en/checkout");
        }
        int c = driver.findElements(By.cssSelector("button[name='remove_cart_item']")).size();
        for (int i = 0; i<c; i++){
            cp.removeItem();
        }
        driver.get(baseUrl);
    }
}
