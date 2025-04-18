package website;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PricingTest  {

    String URL="https://ledsak.ai";
    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(URL);

        wait= new WebDriverWait(driver, Duration.ofSeconds(20));
    }

   @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    } 
    
    //function for overlapping check.
    public static boolean isOverlapping(Rectangle r1, Rectangle r2) {
        return r1.x < r2.x + r2.width
                && r1.x + r1.width > r2.x
                && r1.y < r2.y + r2.height
                && r1.y + r1.height > r2.y;
    }

  
    @Test
    public void priceBanner() throws InterruptedException {

        WebElement pricing = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Pricing ']")));
        pricing.click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Sales Execution LEDSAK AI Pricing");

        // Locate the first element by class (using XPath)
        WebElement element1 = driver.findElement(By.xpath("//*[contains(@class, 'lg:mt-28 mt-10 flex flex-col gap-5 max-w-2xl text-wrap')]"));

        // Locate the second element by alt attribute
        WebElement element2 = driver.findElement(By.xpath("//*[@alt='pricing-banner']"));

        // Get the bounding rectangles
        Rectangle rect1 = element1.getRect();
        Rectangle rect2 = element2.getRect();

        // Check overlap
        boolean isOverlapping = isOverlapping(rect1, rect2);

        System.out.println("Are elements overlapping? " + isOverlapping);

        WebElement bookDemoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Book Free Demo'])[2]")));
        bookDemoButton.click();

        Thread.sleep(1000);
         //check the book a demo form is open or not.
        WebElement popUpDemo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Ledsak - Schedule Demo']")));
        Assert.assertEquals(popUpDemo.getText(), "Ledsak - Schedule Demo");

        System.out.println("Ledsak Form Open Succesfull");
        Actions actions = new Actions(driver);
        actions.moveByOffset(87, 584).click().perform();

        WebElement talkToUsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Talk to us now!']")));
        actions.moveToElement(talkToUsButton).perform();
        System.out.println(talkToUsButton.isDisplayed());
        talkToUsButton.click();
        
    }

}
