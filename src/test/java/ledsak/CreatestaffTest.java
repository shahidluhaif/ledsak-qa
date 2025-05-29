package ledsak;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

public class CreatestaffTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080"); // Simulate real screen
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        Thread.sleep(1000);
        driver.get("https://testing.ledsak.ai");

        // Login process
        WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailBox.sendKeys("demo@ledsak.ai", Keys.ENTER);

        WebElement otpBox = wait.until(ExpectedConditions.elementToBeClickable(By.id(":r0:-form-item")));
        otpBox.sendKeys("987654", Keys.ENTER);

        //sider check open or close
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
                System.out.println("Thread was intreppted");
            }
        }

        WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Dashboard']")));
        wait.until(ExpectedConditions.visibilityOf(dashboard));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not open");
        dashboard.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //create staff and delete check
    @Test
    public void createStaff() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //staff management dropdown open
        WebElement staffMangement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Staff Management']")));
        js.executeScript("arguments[0].scrollIntoView(true);", staffMangement);
        staffMangement.click();

        //staff page
        WebElement staff = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Staff']")));
        js.executeScript("arguments[0].scrollIntoView(true);", staff);
        staff.click();
        WebElement staffTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Staff']")));
        Assert.assertEquals(staffTab.getText(), "Staff");
        System.out.println("Staff page open: Successfull");

        Thread.sleep(2000);
        WebElement createStaff = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        js.executeScript("arguments[0].click();", createStaff);

        Actions action = new Actions(driver);
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"name\"]")));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"email\"]")));
        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"phone\"]")));
        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Role']")));
        WebElement branchDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Select Branches']")));

        action.click(nameField).sendKeys("Test").perform();
        Thread.sleep(200);
        action.click(emailField).sendKeys("test@gmail.com").perform();
        Thread.sleep(200);
        action.click(phoneField).sendKeys("8765456275").perform();
        Thread.sleep(200);
        action.click(roleDropdown).keyDown(Keys.ENTER).perform();
        Thread.sleep(200);
        action.click(branchDropdown).keyDown(Keys.ENTER).perform();

        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Create'])[2]")));
        action.click(createButton).perform();
        System.out.println("Staff created succesfull");
        Thread.sleep(3000);

        List<WebElement> rowsStaff = driver.findElements(By.xpath("//tr[@class='border-b transition-colors group hover:bg-muted/50 data-[state=selected]:bg-muted']"));

        for (WebElement row : rowsStaff) {
            if (row.getText().toLowerCase().contains("test")) {
                // Find all matching buttons inside this row
                List<WebElement> buttons = row.findElements(By.xpath(".//button[@class='inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring disabled:pointer-events-none disabled:opacity-50 hover:bg-accent hover:text-accent-foreground h-5 bg-slate-100 w-5 p-0']"));

                // Click the second button if it exists
                if (buttons.size() >= 2) {
                    buttons.get(1).click(); // index 1 is the second element
                }
                break; // exit loop if you only want to do this for the first matching row
            }
        }
        Thread.sleep(2000);
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']")));
        js.executeScript("arguments[0].click();", deleteButton);
        System.out.println("Staff deleted succesfully");

    }
}
