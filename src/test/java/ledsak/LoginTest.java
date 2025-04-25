package ledsak;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://testing.ledsak.ai"; //change with actual or testign url 

    @BeforeClass
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //Enter email 
        WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailBox.click();
        emailBox.sendKeys("demo@ledsak.ai");
        // wait.until(ExpectedConditions.textToBePresentInElement(emailBox, "demo@ledsak.ai"));
        emailBox.sendKeys(Keys.ENTER);

        //otp enter
        WebElement otpBox = wait.until(ExpectedConditions.elementToBeClickable(By.id(":r0:-form-item")));
        otpBox.click();
        otpBox.sendKeys("987654");
        otpBox.sendKeys(Keys.ENTER);

        //sider check open 
        try {
            WebElement firstElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='h-full no-scrollbar overflow-y-visible']")));

            if (firstElement.isDisplayed()) {
                System.out.println("Sider is closed try to open...");

                // Wait for the second XPath element to be clickable and click it
                WebElement secondElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='w-6 h-6 md:w-7 md:h-7 z-40 flex fixed justify-center items-center top-16 left-12 cursor-pointer bg-white border rounded-full']")));
                secondElement.click();
                System.out.println("sider open succesfully.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Wait for a few seconds before closing the browser (optional)
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
               System.out.println("find the interruped occured");
            }
        }

        //Find Dashboard page for login confirm
        WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Dashboard']")));
        wait.until(ExpectedConditions.visibilityOf(dashboard));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not open");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

}
