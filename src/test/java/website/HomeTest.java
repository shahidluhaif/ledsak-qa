package website;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class HomeTest  {

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

    //homepage title
    @Test(priority = 2)
    public void title() throws InterruptedException {

        Assert.assertTrue(driver.getTitle().contains("Ledsak"));
        WebElement bookFreeDemo = driver.findElement(By.xpath("(//button[text()='Book Free Demo'])[1]"));
        WebElement bookbutton = wait.until(ExpectedConditions.elementToBeClickable(bookFreeDemo));
        bookbutton.click();

        Thread.sleep((1000));
        //check the book a demo form is open or not.
        WebElement popUpDemo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Ledsak - Schedule Demo']")));
        Assert.assertEquals(popUpDemo.getText(), "Ledsak - Schedule Demo");

        Actions actions = new Actions(driver);
        actions.moveByOffset(87, 584).click().perform();

        WebElement contactUsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//h3[text()='Contact Our'])[1]")));
        contactUsButton.click();
        Thread.sleep(100);

        WebElement contactPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Stay Connected with Our Team')]")));
        Assert.assertEquals(contactPage.getText(), "Stay Connected with Our Team");
    }

    //contact our button at home page.
    /**
     * @throws InterruptedException
     */
    @Test(priority = 3)
    public void contactOur() throws InterruptedException {
        WebElement homePage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
        homePage.click();
        Actions actions = new Actions(driver);
        List<WebElement> contactUs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3[text()='Contact Our']")));
        for (WebElement contactButtons : contactUs) {
            actions.keyDown(Keys.CONTROL).click(contactButtons).perform();
        }

        Set<String> windows = driver.getWindowHandles();
         ArrayList<String> windowList = new ArrayList<>(windows);
        for (int i = 0; i <= windowList.size() - 1; i++) {
            driver.switchTo().window(windowList.get(i));

            if (driver.getTitle().contains("Ledsak Website")) {
                System.out.println("Contact Button clicked succesfull and page is appears");
            } else {
                System.out.println("Contact Button is not clicked.");
            }
            
        }
    }

    @Test(priority = 1)
    public void headerContent() {
      
        driver.navigate().refresh();
        
        WebElement homePage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
        homePage.click();
        WebElement content1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'LEDSAK is a comprehensive')]")));
        WebElement image1 = driver.findElement(By.xpath("//img[@alt=\"AI Business\"]"));

        Rectangle rect1 = content1.getRect();
        Rectangle rect2 = image1.getRect();

        // Check overlapping the image
        boolean isOverlapping = isOverlapping(rect1, rect2);
        System.out.println("content is overlapping check" + isOverlapping);
    }

    public static boolean isOverlapping(Rectangle rect1, Rectangle rect2) {
        return rect1.x < rect2.x + rect2.width
                && rect1.x + rect1.width > rect2.x
                && rect1.y < rect2.y + rect2.height
                && rect1.y + rect1.height > rect2.y;
    }

}
