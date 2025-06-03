package ledsak;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/* this file written as the all lead date filter for custom select is working or not and 
check with login and then go for all lead and then check select custom date select as current date and check 
current date is selected or not.
  */
public class DatefilterTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

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

    @Test
    public void dateFilterCheck() throws InterruptedException {
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        js.executeScript("arguments[0].click();", dashboard);
        driver.navigate().refresh();
        Thread.sleep(1000);
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"text-sm font-medium  text-paragraphColor whitespace-nowrap hover:text-purple\"][text()='Leads Management']")));
        js.executeScript("arguments[0].click();", leadManagementDropdown);

        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        js.executeScript("arguments[0].click();", allLeads);

        WebElement dateFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), \"All Time\")]")));
        dateFilter.click();
        Thread.sleep(100);

        // WebElement todayFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), \"Today\")]")));
        // todayFilter.click();
        // WebElement closeFilter = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class=\"h-4 cursor-pointer w-4 text-blue-700\"]")));
        // closeFilter.click();
        WebElement customFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Custom']")));
        js.executeScript("arguments[0].click();", customFilter);

        List<WebElement> dates = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//tbody[@class='rdp-tbody']//td[not(contains(@class, 'rdp-day_disabled'))]")));

        // Click on the date "1"
        for (WebElement date : dates) {
            String dateText = date.getText().trim();
            if (dateText.equals("1")) {
                date.click();
                break;
            }
        }

        // Click on another date, say "15"
        String today = String.valueOf(LocalDate.now().getDayOfMonth());

        // Wait for visible date elements
        // Loop through dates and click today's date
        for (WebElement date : dates) {
            if (date.getText().trim().equals(today)) {
                date.click();
                break;
            }
        }

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
        submitButton.click();

        Thread.sleep(3000);

        WebElement selectedcustomDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-blue-700 text-xs']")));
        String dateText = selectedcustomDate.getText();
        if(dateText.contains(today)){
            System.out.println("Custom date select Succesfull");
        }
        else System.out.println("Failed to select custom Date");
    }
}
