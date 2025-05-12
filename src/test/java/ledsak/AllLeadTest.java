package ledsak;

import java.time.Duration;
import java.util.ArrayList;
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

public class AllLeadTest {

    private WebDriver driver;
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

    //Lead Create manually check
    @Test
    public void newLead() throws InterruptedException {
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        wait.until(ExpectedConditions.visibilityOf(dashboard));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not open");
        dashboard.click();
        Thread.sleep(100);
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        js.executeScript("arguments[0].click();", leadManagementDropdown);

        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        js.executeScript("arguments[0].click();", allLeads);

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
    @Test
    public void dateFilter() throws InterruptedException {
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
    }

    //filter select 
    @Test
    public void filterSelect() throws InterruptedException {
        driver.navigate().refresh();
        //click on lead Management dropdown
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();

        //Leads setup page opened
        Thread.sleep(1000);
        WebElement leadSetup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Setup']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", leadSetup);

        //sources sections: 
        Thread.sleep(1000);
        WebElement sources = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Lead Sources']")));
        js.executeScript("arguments[0].click()", sources);

        Thread.sleep(2000);
        List<WebElement> sourcesFetch = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@class='[&_tr:last-child]:border-0']/descendant::td")));
        List<String> sourcesList = new ArrayList<>();

        for (WebElement element : sourcesFetch) {
            sourcesList.add(element.getText().trim());
        }

        //--------------------------------------------------------------------------------------------------------------------//
        //All Lead Dropdown
        WebElement allLeads = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='All Leads']")));
        wait.until(ExpectedConditions.elementToBeClickable(allLeads));
        allLeads.click();
        WebElement allLeadsRefresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='All Leads']")));
        Assert.assertTrue(allLeadsRefresh.isDisplayed(), "All Leads page is not visible");

        //lead Source select dropdown
        WebElement leadSource = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lead Sources')]")));
        leadSource.click();

        // Fetch the elements from both lists
        List<WebElement> listSource = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'hover:bg-gray-100') and contains(@class, 'cursor-pointer')]")));
        List<String> selectSource = new ArrayList<>();  // list form the Select dropdown All lead page.

        for (WebElement listElementSource : listSource) {
            selectSource.add(listElementSource.getText().trim());
        }

        System.out.println(sourcesList);
        System.out.println(selectSource);

        // Check if "Facebook" is present in both lists
        if (sourcesList.contains("Facebook") && selectSource.contains("Facebook")) {
            System.out.println("Facebook found in both lists. Clicking...");

            for (WebElement listElement : listSource) {
                if (listElement.getText().trim().equalsIgnoreCase("Facebook")) {
                    listElement.click(); // Click on the Facebook element
                    System.out.println("Clicked on Facebook.");
                    break; // Stop after clicking the first match
                }
            }
        } else {
            System.out.println("Facebook not found in both lists.");
        }

    }
}
