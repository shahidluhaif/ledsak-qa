package ledsak;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * DashboardTest Class
 * 
 * This test validates different dashboard cards in the application.
 * Each section clicks on a specific card, verifies it, and asserts the expected page.
 * 
 * Priority: Every test should start from the dashboard and ensure correct navigation.
 */
public class DashboardTest extends LoginTest {

    @Test(priority=1)
    public void dashboardCard() {

        // Navigate to Dashboard
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();

        // Leads Card
        WebElement leadsCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Leads']")));
        Assert.assertTrue(leadsCard.isDisplayed(), "Leads card is not displayed.");
        leadsCard.click();
        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-2xl font-bold my-4']")));
        Assert.assertTrue(allLeads.getText().contains("All Leads"), "The Page is not found.");

    }

    @Test(priority=2)
    public void followUpCard(){
        // Follow-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement followUpCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Follow Ups']")));
        Assert.assertTrue(followUpCard.isDisplayed(), "Follow Ups card is not displayed.");
        followUpCard.click();
        WebElement followUps = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Follow Ups']")));
        Assert.assertEquals(followUps.getText(), "Follow Ups", "The page is not Interact: Follow Ups");
    }

    @Test(priority=3)
    public void overdueTaskCard(){
        // Overdue Task-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement overdueTaskCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Overdue Tasks']")));
        Assert.assertTrue(overdueTaskCard.isDisplayed(), "Overdue Tasks card is not displayed.");
        overdueTaskCard.click();
        WebElement overdue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Overdue Tasks']")));
        Assert.assertEquals(overdue.getText(), "Overdue Tasks", "The page is not Interact: Overdue Tasks");
    }

    @Test(priority=4)
    public void staffCard(){
        // Staffs-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement staffCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Staffs']")));
        Assert.assertTrue(staffCard.isDisplayed(), "Staffs card is not displayed.");
        staffCard.click();
        WebElement staff = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class=\"text-xl font-bold flex items-center space-x-2\"]")));
        Assert.assertTrue(staff.getText().contains("Staff"), "The page is not Interact: Staff");
    }

    @Test(priority=5)
    public void branchesCard(){
        // Branches-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement branchesCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Branches']")));
        Assert.assertTrue(branchesCard.isDisplayed(), "Branches card is not displayed.");
        branchesCard.click();
        WebElement branch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='text-md font-semibold']")));
        Assert.assertTrue(branch.getText().contains("Branch"), "The page is not Interact: Branch");
    }

    @Test(priority=6)
    public void sourcesCard(){
        // Branches-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement sourcesCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Sources']")));
        Assert.assertTrue(sourcesCard.isDisplayed(), "Sources card is not displayed.");
        sourcesCard.click();
        WebElement sources = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='text-xl font-bold mt-4']")));
        Assert.assertTrue(sources.getText().contains("Sources"), "The page is not Interact: Source");
    }

    @Test(priority=7)
    public void stagesCard(){
        // Branches-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement stagesCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Stages']")));
        Assert.assertTrue(stagesCard.isDisplayed(), "Stages card is not displayed.");
        stagesCard.click();
        WebElement stages = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='text-xl font-bold mt-4']")));
        Assert.assertTrue(stages.getText().contains("Stages"), "The page is not Interact: Stages");
    }

    
    @Test(priority=8)
    public void groupsCard(){
        // Branches-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement groupsCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Groups']")));
        Assert.assertTrue(groupsCard.isDisplayed(), "Groups card is not displayed.");
        groupsCard.click();
        WebElement groups = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='text-xl font-bold mt-4']")));
        Assert.assertTrue(groups.getText().contains("Groups"), "The page is not Interact: Groups");
    }

    @Test(priority=9)
    public void leadFieldCard(){
        // Branches-ups Card
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();
        WebElement leadFieldCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Lead Fields']")));
        Assert.assertTrue(leadFieldCard.isDisplayed(), "Lead Fields card is not displayed.");
        leadFieldCard.click();
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class=\"text-xl font-bold \"]")));
        Assert.assertTrue(field.getText().contains("Fields"), "The page is not Interact: Lead Fields");
    }

    
    @Test(priority=10)
    public void SwithcBranchList(){

        //click on lead Management dropdown
        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();

        //Take list from the setup branch tab.
          //Leads setup page
        WebElement leadSetup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Setup']")));
        leadSetup.click();
        Assert.assertTrue(leadSetup.isDisplayed(),"Leads setup page is not Interact");
        Assert.assertEquals(leadSetup.getText(), "Lead Setup");

        //Branch sections: 
        WebElement branches = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Branches']")));
        branches.click();

        //list taken branch
         List<WebElement> branchElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//h3[@class=\"text-muted-foreground\"]")));

        List<String> branchList = new ArrayList<>();
        for (WebElement branch : branchElements) {
            branchList.add(branch.getText().trim());
        }

        //interact with the dashboard and then check the branch.
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dashboard']")));
        dashboard.click();

        WebElement switchBranch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Switch Branches']")));
        switchBranch.click();

        //list of branch from swtich list
        List<WebElement> switchBranchElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@role=\"menuitem\"])[position()=1 < position()=3]")));
            
            List<String> switchBranchList = new ArrayList<>();
            for (WebElement switchBranches : switchBranchElements) {
             switchBranchList.add(switchBranches.getText().trim());
            }

            if (branchList.equals(switchBranchList)) {
                System.out.println("Both lists match exactly.");
            } else 
                System.out.println("Lists do not match.");
             // Print the extracted branch names
             
        System.out.println("Available Branches:");
        for (String branch : switchBranchList) {
            System.out.println(branch);
        }
    }
    
}
