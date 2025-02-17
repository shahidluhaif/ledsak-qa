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
import org.testng.annotations.Test;

public class AllLeadTest {
     
    private  WebDriver driver;
    @Test
    public void newLead() throws InterruptedException {
        
        try{
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--headless");    
        driver = new ChromeDriver(options);  // Always initialize a new instance
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.get("https://testing.ledsak.ai");

        // Login process
        WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailBox.sendKeys("demo@ledsak.ai", Keys.ENTER);

        WebElement otpBox = wait.until(ExpectedConditions.elementToBeClickable(By.id(":r0:-form-item")));
        otpBox.sendKeys("987654", Keys.ENTER);

        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard is not open");

       
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();

        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        allLeads.click();
        Assert.assertTrue(allLeads.isDisplayed(), "All Leads page is not visible");

        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create']")));
        createButton.click();

        WebElement nameBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Phone']/following::input[@placeholder='Name']")));
        WebElement phoneBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Phone']/following::input[@placeholder='Phone']")));
        WebElement branchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@role='combobox'])[3]")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));

        String leadName = "Test Lead";
        nameBox.sendKeys(leadName);
        phoneBox.sendKeys("1234567892");

        branchBox.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.DOWN, Keys.ENTER).perform();

        Thread.sleep(1000);
        actions.moveToElement(submitButton);
        submitButton.click();
        actions.moveToLocation(650 , 200).click().perform();

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
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='menuitem'][3]")));
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
        finally{
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

