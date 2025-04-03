package ledsak;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllLeadTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
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

        // Login process
        WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailBox.sendKeys("demo@ledsak.ai", Keys.ENTER);

        WebElement otpBox = wait.until(ExpectedConditions.elementToBeClickable(By.id(":r0:-form-item")));
        otpBox.sendKeys("987654", Keys.ENTER);

        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not open");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Lead Create manually check
    @Test(priority = 1)
    public void newLead() throws InterruptedException {

        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();

        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        allLeads.click();
        WebElement allLeadsRefresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='All Leads']")));
        Assert.assertTrue(allLeadsRefresh.isDisplayed(), "All Leads page is not visible");

        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        createButton.click();

        WebElement nameBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Name']/../child::input")));
        WebElement phoneBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Phone']/../child::input")));
        WebElement branchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@role=\"combobox\"])[9]")));
        WebElement createButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Create'])[2]")));

        String leadName = "Test Lead";
        nameBox.click();
        nameBox.sendKeys(leadName);
        Thread.sleep(1000);
        phoneBox.click();
        phoneBox.sendKeys("1234567892");

        branchBox.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.DOWN, Keys.ENTER).perform();

        Thread.sleep(1000);
        actions.moveToElement(createButton2);
        createButton2.click();
        actions.moveToLocation(650, 200).click().perform();

        Thread.sleep(3000);
        // Verify lead creation
        WebElement leadNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tbody[@class='[&_tr:last-child]:border-0']/tr)[1]/descendant::p")));
        Assert.assertTrue(leadName.contains(leadNameElement.getText()), "Lead was not created!");
        System.out.println("lead Created Succesfully");
        Thread.sleep(5000);

        // Delete lead
        //click on tree dot
        WebElement firstLeadDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[text()=\"Test Lead\"]/following::button)[1]")));
        firstLeadDelete.click();
        //click on delete button
        Thread.sleep(1000);
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='menuitem'][2]")));
        deleteButton.click();
        //click on continue button
        Thread.sleep(1000);
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
        continueButton.click();
        Thread.sleep(5000);

        By leadLocator = By.xpath("//table[@class='w-full caption-bottom text-sm']//tr[2]//p[1]");

        WebElement firstLeadTable = wait.until(ExpectedConditions.presenceOfElementLocated(leadLocator));
        String actualText = firstLeadTable.getText();

        System.out.println("Actual text in table: " + actualText);
        Assert.assertFalse(actualText.contains(leadName), "Lead was not deleted!");

        System.out.println("Element not found, assuming lead is deleted.");
    }

    //Date Filter 
    @Test(priority = 2)
    public void dateFilter() throws InterruptedException {
        // WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        // leadManagementDropdown.click();

        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        allLeads.click();
        WebElement allLeadsRefresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='All Leads']")));
        Assert.assertTrue(allLeadsRefresh.isDisplayed(), "All Leads page is not visible");

        WebElement dateFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), \"All Time\")]")));
        dateFilter.click();
        Thread.sleep(100);

        WebElement todayFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), \"Today\")]")));
        todayFilter.click();

        WebElement closeFilter = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class=\"h-4 cursor-pointer w-4 text-blue-700\"]")));
        closeFilter.click();

        //Filter select
        WebElement newLead = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'New Lead')]")));
        newLead.click();
        Thread.sleep(2000);

        WebElement allLeadsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        allLeadsOption.click();
        WebElement allLeadsRefreshed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='All Leads']")));
        Assert.assertTrue(allLeadsRefreshed.isDisplayed(), "All Leads page is not visible");

    }
}
