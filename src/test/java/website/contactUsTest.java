package website;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void LaunchURL() throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://ledsak.ai");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void contactUs() throws InterruptedException {
        waitForPageLoad(driver);
        WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact']")));
        contact.click();
        waitForPageLoad(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.ledsak.ai/contact-us");

        WebElement contactPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Contact Sales']")));
        if (contactPage.isDisplayed()) {
            System.out.println("Contact us page appears");
        } else {
            System.out.println("contact us page not appears");
        }

        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a Random object
        Random random = new Random();

        // StringBuilder to hold 3 random characters
        StringBuilder randomChars = new StringBuilder();

        // Loop to pick 3 characters
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(charSet.length());
            randomChars.append(charSet.charAt(index));
        }

        // Output the result
        System.out.println("Random 3 characters: " + randomChars.toString());

        WebElement nameBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"name\"]")));
        String namevalue="Test";
        nameBox.click();
        nameBox.clear(); // Optional: clear existing value
        nameBox.sendKeys(namevalue);

        // Validate the input's value
        String actualValue1 = nameBox.getAttribute("value");

        // Assertion / Validation
        if (namevalue.equals(actualValue1)) {
            System.out.println("✅ Input value set correctly: " + actualValue1);
        } else {
            System.out.println("❌ Value mismatch! Expected: " + actualValue1 + ", but got: " + namevalue);
        }


        WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"email\"]")));
        String emailValue2 = "test@gmail.com";

        emailBox.click();
        emailBox.clear(); // Optional: clear existing value
        emailBox.sendKeys(emailValue2);

        // Validate the input's value
        String actualValue2 = emailBox.getAttribute("value");

        // Assertion / Validation
        if (emailValue2.equals(actualValue2)) {
            System.out.println("✅ Input value set correctly: " + actualValue2);
        } else {
            System.out.println("❌ Value mismatch! Expected: " + emailValue2 + ", but got: " + actualValue2);
        }
        // Locate the input element
        WebElement companyBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='company']")));
       String expectedValue="ledsak";
        // Send value
        companyBox.click();
        companyBox.clear(); // Optional: clear existing value
        companyBox.sendKeys(expectedValue);

        // Validate the input's value
        String actualValue = companyBox.getAttribute("value");

        // Assertion / Validation
        if (expectedValue.equals(actualValue)) {
            System.out.println("✅ Input value set correctly: " + actualValue);
        } else {
            System.out.println("❌ Value mismatch! Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }

    public void waitForPageLoad(WebDriver driver) {
        wait.until((ExpectedCondition<Boolean>) wd
                -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
