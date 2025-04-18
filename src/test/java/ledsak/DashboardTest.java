package ledsak;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends LoginTest {

    @Test(priority = 1)
    public void notification() throws InterruptedException {

        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();

        WebElement notificationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'rounded-md text-sm font-medium')]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(notificationButton).perform();
        Thread.sleep(2000);

        WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class, 'rounded-md text-sm font-medium')]")));
        wait.until(ExpectedConditions.elementToBeClickable(notification)).click();
        System.out.println("Clicked Notification Button");

        WebElement notificationPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h2[contains(text(),'Notifications')])[2]")));
        Assert.assertTrue(notificationPage.isDisplayed(), "Element is appears");

        WebElement dashboardTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        actions.click(dashboardTab).perform();

        Boolean hideNotification = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//h2[contains(text(),'Notifications')])[2]")));
        Assert.assertTrue(hideNotification, "Notification page is not appear");
    }

    @Test(priority = 2)
    public void dashboardCard() {

        // Navigate to Dashboard
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();

        // All Leads Card
        WebElement leadsCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='All Leads']")));
        Assert.assertTrue(leadsCard.isDisplayed(), "Leads card is not displayed.");
        leadsCard.click();
        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class=\" font-bold \"]")));
        Assert.assertTrue(allLeads.getText().contains("All Leads"), "The Page is not found.");

    }

    //Follow up card
    @Test(priority = 2)
    public void followUpCard() {
        // Follow-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement followUpCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Follow Ups']")));
        Assert.assertTrue(followUpCard.isDisplayed(), "Follow Ups card is not displayed.");
        followUpCard.click();
        WebElement followUps = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Follow Ups']")));
        Assert.assertEquals(followUps.getText(), "Follow Ups", "The page is not Interact: Follow Ups");
    }

    //overdue Task Card
    @Test(priority = 4)
    public void overdueTaskCard() {
        // Overdue Task-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement overdueTaskCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Overdue Tasks']")));
        Assert.assertTrue(overdueTaskCard.isDisplayed(), "Overdue Tasks card is not displayed.");
        overdueTaskCard.click();
        WebElement overdue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Overdue Tasks']")));
        Assert.assertEquals(overdue.getText(), "Overdue Tasks", "The page is not Interact: Overdue Tasks");
    }

    //switch branch card
    @Test(priority = 5)
    public void SwithcBranchList() throws InterruptedException {

        //click on lead Management dropdown
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();

        //Take list from the setup branch tab.
        //Leads setup page
        Thread.sleep(1000);
        WebElement leadSetup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Setup']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", leadSetup);

        //Branch sections: 
        Thread.sleep(1000);
        WebElement branches = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Branches']")));
        js.executeScript("arguments[0].click()", branches);

        //list taken branch
        List<WebElement> branchElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@class='[&_tr:last-child]:border-0']/descendant::td")));

        List<String> branchList = new ArrayList<>();

        // Start from index 1 (2nd element), then fetch every 3rd element (5th, 8th, 11th, etc.)
        for (int i = 1; i < branchElements.size(); i += 3) {
            WebElement element = branchElements.get(i);
            branchList.add(element.getText()); // Add the text of the element to the branchList
        }

        //interact with the dashboard and then check the branch.
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();

        WebElement switchBranch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Switch Branches']")));
        switchBranch.click();

        //list of branch from swtich list
        List<WebElement> switchBranchElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@role=\"menuitem\"])[position()=1 < position()=3]")));

        List<String> switchBranchList = new ArrayList<>();
        for (WebElement switchBranches : switchBranchElements) {
            switchBranchList.add(switchBranches.getText().trim());
        }

        if (branchList.equals(switchBranchList)) {
            System.out.println("Both lists match exactly.");
        } else {
            System.out.println("Lists do not match.");
        }
        // Print the extracted branch names

        System.out.println("Available Branches:");
        for (String branch : switchBranchList) {
            System.out.println(branch);
        }
    }

}
