package ledsak;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SidebarTest extends LoginTest {

    //Lead Mangement Dropdown
    @Test(priority = 1)
    public void leadManagement() {

        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();
        System.out.println("Lead Management Dropdown open.");

        //All Leads page
        // Click on All Leads after waiting for it to be clickable
        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        allLeads.click();

        // Re-locate the element again to avoid stale reference
        WebElement refreshedAllLeads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='All Leads']")));

        // Assert after re-locating
        Assert.assertTrue(refreshedAllLeads.isDisplayed(), "All Leads page is not interactable");
        Assert.assertEquals(refreshedAllLeads.getText(), "All Leads");
        System.out.println("All Lead page is Interact");

        //Leads setup page
        WebElement leadSetup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Setup']")));
        leadSetup.click();

        WebElement leadSetupRefresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Lead Setup']")));
        Assert.assertTrue(leadSetupRefresh.isDisplayed(), "Leads setup page is not Interact");
        Assert.assertEquals(leadSetupRefresh.getText(), "Lead Setup");
        System.out.println("Lead setup page is Interact");

        //Lead Distribution page
        WebElement leadDistribution = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Distribution']")));
        leadDistribution.click();
        WebElement leadDistributionTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Distribution']")));
        Assert.assertTrue(leadDistributionTab.isDisplayed(), "Leads Distribution page is not Interact");
        Assert.assertEquals(leadDistributionTab.getText(), "Lead Distribution");
        System.out.println("Lead Distribution page is Interact");

        //Export History page
        WebElement exportHistory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Export History']")));
        exportHistory.click();
        WebElement exportHistoryTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Export History']")));
        Assert.assertTrue(exportHistoryTab.isDisplayed(), "Export History page is not Interact");
        Assert.assertEquals(exportHistoryTab.getText(), "Export History");
        System.out.println("Export History page is Interact");
    }

    //Deal Management Dropdown
    @Test(priority = 2)
    public void dealManagement() {

        WebElement dealManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Deal Management']")));
        dealManagementDropdown.click();
        System.out.println("Lead Management Dropdown open.");

        //All Deals page
        WebElement allDeals = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Deals']")));
        allDeals.click();
        WebElement allDealsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Deals']")));
        Assert.assertEquals(allDealsTab.getText(), "All Deals");
        System.out.println("All Deals page is Interact");

        //Won Deals page
        WebElement wonDeals = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Won Deals']")));
        wonDeals.click();
        WebElement wonDealsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Won Deals']")));
        Assert.assertTrue(wonDealsTab.isDisplayed(), "Export History page is not Interact");
        Assert.assertEquals(wonDealsTab.getText(), "Won Deals");
        System.out.println("Won Deals page is Interact");
    }

    //Opportunities section
    @Test(priority = 3)
    public void opportunities() {

        WebElement opportunities = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Actions']")));
        opportunities.click();
        System.out.println("Actions Page open: Successfull");
    }

    //Automation section
    @Test(priority = 4)
    public void automation() {

        WebElement autoamtion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Automation']")));
        autoamtion.click();
        System.out.println("Automation Page open: Successfull");
    }

    //Integration section
    @Test(priority = 5)
    public void integrations() {

        WebElement integration = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Integrations']")));
        integration.click();
        System.out.println("Integrations Page open: Successfull");
    }

    //Report Section
    @Test(priority = 6)
    public void report() {
        //Lead Distribution page
        WebElement reports = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Reports']")));
        reports.click();
        Assert.assertTrue(reports.isDisplayed(), "Report page is not Interact");
        Assert.assertEquals(reports.getText(), "Reports");
        System.out.println("Reports page is Interact");

        WebElement graphData = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Graph Data']")));
        graphData.click();

        WebElement tableData = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Table Data']")));
        tableData.click();
    }

    //Staff Management dropdown
    @Test(priority = 7)
    public void staffManagement() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

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

        //Attendance page
        WebElement attendance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Attendance']")));
        js.executeScript("arguments[0].scrollIntoView(true);", attendance);
        attendance.click();
        WebElement attendanceTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Attendance']")));
        Assert.assertTrue(attendanceTab.isDisplayed());
        Assert.assertEquals(attendanceTab.getText(), "Attendance");
        System.out.println("Attendance page open: Successfull");

        //Role and permission page
        WebElement roleAndPermission = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Roles & Permissions']")));
        js.executeScript("arguments[0].scrollIntoView(true);", roleAndPermission);
        roleAndPermission.click();
        WebElement roleAndPermissionTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Roles & Permissions']")));
        Assert.assertTrue(roleAndPermissionTab.isDisplayed());
        Assert.assertEquals(roleAndPermissionTab.getText(), "Roles & Permissions");
        System.out.println("Roles & Permissions page open: Successfull");

        //Login History page
        WebElement loginHistory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Login History']")));
        js.executeScript("arguments[0].scrollIntoView(true);", loginHistory);
        loginHistory.click();
        WebElement loginHistoryTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Login History']")));
        Assert.assertTrue(loginHistoryTab.isDisplayed());
        Assert.assertEquals(loginHistoryTab.getText(), "Login History");
        System.out.println("Login History page open: Successfull");
    }

    //Settings dropdown
    @Test(priority = 8)
    public void settings() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement setting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Settings']")));
        js.executeScript("arguments[0].scrollIntoView(true);", setting);
        setting.click();

        //staff page
        WebElement customize = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Customize']")));
        js.executeScript("arguments[0].scrollIntoView(true);", customize);
        customize.click();
        WebElement customizeTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Customize']")));
        Assert.assertEquals(customizeTab.getText(), "Customize");
        System.out.println("Customize page open: Successfull");

        //Attendance page
        WebElement template = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Template']")));
        js.executeScript("arguments[0].scrollIntoView(true);", template);
        template.click();
        WebElement templateTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Template']")));
        Assert.assertTrue(templateTab.isDisplayed());
        Assert.assertEquals(templateTab.getText(), "Template");
        System.out.println("Template page open: Successfull");
    }
}
