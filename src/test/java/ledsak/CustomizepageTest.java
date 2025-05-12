package ledsak;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomizepageTest extends CustomizeBase {

    WebDriverWait wait;

    public CustomizepageTest() {
        super(null); // temporarily call parent constructor; real driver will be set up in @BeforeMethod
    }

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        driver.get("https://testing.ledsak.ai");

        WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailBox.sendKeys("demo@ledsak.ai", Keys.ENTER);

        WebElement otpBox = wait.until(ExpectedConditions.elementToBeClickable(By.id(":r0:-form-item")));
        otpBox.sendKeys("987654", Keys.ENTER);

        try {
            WebElement firstElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='h-full no-scrollbar overflow-y-visible']")));

            if (firstElement.isDisplayed()) {
                System.out.println("Sider is closed try to open...");
                WebElement secondElement = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='w-6 h-6 md:w-7 md:h-7 z-40 flex fixed justify-center items-center top-16 left-12 cursor-pointer bg-white border rounded-full']")));
                secondElement.click();
                System.out.println("Sider opened successfully.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }

        WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[text()='Dashboard']")));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not open");
        dashboard.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void CheckAttribut() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement setting = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Settings']")));
        js.executeScript("arguments[0].scrollIntoView(true);", setting);
        setting.click();

        WebElement customizePage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Customize']")));
        js.executeScript("arguments[0].scrollIntoView(true);", customizePage);
        customizePage.click();

        WebElement customizeTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Customize']")));
        Assert.assertEquals(customizeTab.getText(), "Customize");
        System.out.println("Customize page open: Successful");

        CustomizeBase customize = new CustomizeBase(driver);
        customize.callTextBox();
        customize.emailTextBox();
        customize.meetingTextBox();
        customize.taskTextBox();
        customize.branchTextBox();
        customize.leadFieldTextBox();
        customize.groupFieldTextBox();
        customize.sourceFieldTextBox();
        customize.StageieldTextBox();

        // customize.updateButton();
        Thread.sleep(3000);
        // System.out.println("Updated the cusotmize field. ");

    }
}
