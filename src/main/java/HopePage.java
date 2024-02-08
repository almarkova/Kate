import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class HopePage {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Users/aleksa/Automation/chromedriver_mac64/chromedriver");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        driver.get("https://www.apple.com/");

        driver.findElement(By.id("globalnav-menubutton-link-search")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input.globalnav-searchfield-input")));
        driver.findElement(By.cssSelector("input.globalnav-searchfield-input")).sendKeys("iPhone 15");
        driver.findElement(By.cssSelector("input.globalnav-searchfield-input")).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.urlContains("/us/search"));

//        try {
//            driver.findElement(By.cssSelector("div.rf-serp-resultcount"));
//            System.out.println("Passed: found in Result Label");
//
//            driver.quit();
//            return;
//        }
//        catch (NoSuchElementException error)
//        {
//            System.out.println("Failed: Element not found");
//            driver.quit();
//
//            return;
//        }
//        catch (Exception error) {
//            driver.quit();
//            System.out.println(error.getMessage());
//
//            return;
//        }

        var productItems = driver.findElements(By.cssSelector("a.rf-serp-productname-link"));
        for (WebElement productItem: productItems) {
            var title = productItem.getText();
            if (title.contains("iPhone 15")) {
                System.out.println("Passed: found in ProductItems");
                driver.quit();
                return;
            }
        }

        var productLinks = driver.findElements(By.cssSelector("a.rf-serp-name-link"));

        for (WebElement productLink: productLinks) {
            var title = productLink.getText();
            if (title.contains("iPhone 15")) {
                System.out.println("Passed: found in ProductLinks");
                driver.quit();
                return;
            }
        }

//        var foundInProducts = CheckInElements(driver, "a.rf-serp-productname-link", "Passed: found in ProductItems");
//
//        if (foundInProducts)
//        {
//            return;
//        }
//
//        CheckInElements(driver, "a.rf-serp-name-link", "Passed: found in ProductLinks");
    }

    private static boolean CheckInElements(WebDriver driver, String selector, String message)
    {
        var productLinks = driver.findElements(By.cssSelector(selector));
        for (WebElement productLink: productLinks) {
            var title = productLink.getText();
            if (title.contains("iPhone 15")) {
                System.out.println(message);
                driver.quit();

                return true;
            }
        }

        return false;
    }
}
