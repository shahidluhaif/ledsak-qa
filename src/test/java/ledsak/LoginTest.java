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

    String url="https://testing.ledsak.ai"; //change with actual or testign url 

 @BeforeClass
    public void setup() {

        ChromeOptions options= new ChromeOptions();
        options.addArguments("--headless");  // Run Chrome in headless mode
        options.addArguments("--disable-gpu");  // Recommended for headless mode
        options.addArguments("--no-sandbox");  // Bypass OS security model
    
        driver = new ChromeDriver(options);

        
        driver.get(url);  
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait= new WebDriverWait(driver, Duration.ofSeconds(20));

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
       //Find Dashboard page for login confirm
       WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
       Assert.assertTrue(dashboard.isDisplayed());
       Assert.assertEquals(dashboard.getText(), "Dashboard", "The Dashboard is not open");        
    }

     @AfterClass
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
           
}
