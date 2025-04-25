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

public class LeadSetupTest {

    private WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
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

    /**
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void leadset() throws InterruptedException, IOException {

        //Field create and delete check
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();

        //Take list from the setup branch tab.
        //Leads setup page
        Thread.sleep(1000);
        WebElement leadSetup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Setup']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", leadSetup);
        WebElement leadField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Lead Fields']")));

        js.executeScript("arguments[0].click();", leadField);
        WebElement createField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        js.executeScript("arguments[0].click();", createField);

        //create a field that show in table.
        WebElement fieldName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"field_name\"]")));
        // WebElement fieldIdentifier = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"field_identifier\"]")));
        WebElement fieldType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@role=\"combobox\"]")));
        List<WebElement> checksField = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@class=\"flex items-center gap-2\"])[position() >= 11 and position() <= 15]")));

        Actions actions = new Actions(driver);
        actions.click(fieldName).sendKeys("Test").perform();
        actions.click(fieldType).perform();
        actions.keyDown(Keys.DOWN).keyDown(Keys.ENTER).keyUp(Keys.DOWN).perform();
        WebElement create2Button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Create'])[2]")));
        Thread.sleep(1000);
        for (WebElement element : checksField) {
            js.executeScript("arguments[0].click();", element);
        }
        create2Button.click();
        Thread.sleep(3000);
        System.out.println("Field created succesfull");

        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='border-b transition-colors group hover:bg-muted/50 data-[state=selected]:bg-muted']"));

        for (WebElement row : rows) {
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
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
        js.executeScript("arguments[0].click();", continueButton);
        System.out.println("Field deleted succesfull");

        //stage create and delete check 
        Thread.sleep(2000);
        WebElement leadStages = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Lead Stages']")));
        js.executeScript("arguments[0].click();", leadStages);

        Thread.sleep(2000);
        WebElement createStage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        js.executeScript("arguments[0].click();", createStage);

        WebElement stageName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"stage_name\"]")));
        WebElement saveStage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
        actions.click(stageName).sendKeys("Test").perform();
        Thread.sleep(200);
        actions.click(saveStage).perform();
        System.out.println("Stage created succesfully");

        Thread.sleep(3000);

        List<WebElement> rowsStage = driver.findElements(By.xpath("//tr[@class='border-b transition-colors group hover:bg-muted/50 data-[state=selected]:bg-muted']"));

        for (WebElement row : rowsStage) {
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
        WebElement continueButtonStage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
        js.executeScript("arguments[0].click();", continueButtonStage);
        System.out.println("Stage deleted succesfully");
        Thread.sleep(5000);

        //Source created and delete check
        WebElement leadSources = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Lead Sources']")));
        js.executeScript("arguments[0].click();", leadSources);
        Thread.sleep(2000);
        WebElement createSources = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        js.executeScript("arguments[0].click();", createSources);

        WebElement sourceName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"source_name\"]")));
        WebElement saveSource = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
        actions.click(sourceName).sendKeys("Test").perform();
        Thread.sleep(200);
        actions.click(saveSource).perform();
        System.out.println("Source created succesfully");

        Thread.sleep(3000);

        List<WebElement> rowsSource = driver.findElements(By.xpath("//tr[@class='border-b transition-colors group hover:bg-muted/50 data-[state=selected]:bg-muted']"));

        for (WebElement row : rowsSource) {
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
        WebElement continueButtonSource = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
        js.executeScript("arguments[0].click();", continueButtonSource);
        System.out.println("Source deleted succesfully");

        //Group create and delete check
        WebElement leadGroups = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Lead Groups']")));
        js.executeScript("arguments[0].click();", leadGroups);

        Thread.sleep(2000);
        WebElement createGroup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        js.executeScript("arguments[0].click();", createGroup);

        WebElement groupName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"group_name\"]")));
        WebElement chooseColor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Choose color']")));
        WebElement savegroup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
        actions.click(groupName).sendKeys("Test").perform();
        Thread.sleep(300);
        actions.click(chooseColor).perform();
        Thread.sleep(1000);
        actions.keyDown(Keys.UP).keyDown(Keys.ENTER).perform();
        Thread.sleep(200);
        actions.click(savegroup).perform();
        System.out.println("group created succesfully");

        Thread.sleep(3000);

        List<WebElement> rowsgroup = driver.findElements(By.xpath("//tr[@class='border-b transition-colors group hover:bg-muted/50 data-[state=selected]:bg-muted']"));

        for (WebElement row : rowsgroup) {
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
        WebElement continueButtonGroup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
        js.executeScript("arguments[0].click();", continueButtonGroup);
        System.out.println("Group deleted succesfully");
        Thread.sleep(2000);

        //Branch create and delete check
        WebElement branches = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Branches']")));
        js.executeScript("arguments[0].click();", branches);
        Thread.sleep(2000);
        WebElement createBranch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        js.executeScript("arguments[0].click();", createBranch);

        WebElement branchName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name=\"branch_name\"]")));
        WebElement saveBranch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
        actions.click(branchName).sendKeys("Test").perform();
        Thread.sleep(200);
        actions.click(saveBranch).perform();
        System.out.println("Branch created succesfully");

        Thread.sleep(3000);

        List<WebElement> rowsBranch = driver.findElements(By.xpath("//tr[@class='border-b transition-colors group hover:bg-muted/50 data-[state=selected]:bg-muted']"));

        for (WebElement row : rowsBranch) {
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
        WebElement continueButtonBranch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
        js.executeScript("arguments[0].click();", continueButtonBranch);
        System.out.println("Branch deleted succesfully");

    }
}
