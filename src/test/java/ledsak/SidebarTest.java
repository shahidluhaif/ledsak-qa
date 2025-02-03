package ledsak;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SidebarTest extends LoginTest{

    //Lead Mangement Dropdown
    @Test (priority=1)
    public void leadManagement(){

        WebElement leadManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Leads Management']")));
        leadManagementDropdown.click();
        System.out.println("Lead Management Dropdown open.");

        //All Leads page
        WebElement allLeads = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Leads']")));
        allLeads.click();
        Assert.assertTrue(allLeads.isDisplayed(),"All Leads page is not Interact");
        Assert.assertEquals(allLeads.getText(), "All Leads");
        System.out.println("All Lead page is Interact");

        //Leads setup page
        WebElement leadSetup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Setup']")));
        leadSetup.click();
        Assert.assertTrue(leadSetup.isDisplayed(),"Leads setup page is not Interact");
        Assert.assertEquals(leadSetup.getText(), "Lead Setup");
        System.out.println("Lead setup page is Interact");

        //Lead Distribution page
        WebElement leadDistribution = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Lead Distribution']")));
        leadDistribution.click();
        Assert.assertTrue(leadDistribution.isDisplayed(),"Leads Distribution page is not Interact");
        Assert.assertEquals(leadDistribution.getText(), "Lead Distribution");
        System.out.println("Lead Distribution page is Interact");

        //Lead Distribution page
        WebElement reports = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Reports']")));
        reports.click();
        Assert.assertTrue(reports.isDisplayed(),"Leads Distribution page is not Interact");
        Assert.assertEquals(reports.getText(), "Reports");
        System.out.println("Reports page is Interact");

        //Export History page
        WebElement exportHistory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Export history']")));
        exportHistory.click();
        Assert.assertTrue(exportHistory.isDisplayed(),"Export History page is not Interact");
        Assert.assertEquals(exportHistory.getText(), "Export history");
        System.out.println("Export History page is Interact");
    }  

    //Deal Management Dropdown
    @Test (priority=2)
    public void dealManagement(){

        WebElement dealManagementDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Deal Management']")));
        dealManagementDropdown.click();
        System.out.println("Lead Management Dropdown open.");

        //All Deals page
        WebElement allDeals = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='All Deals']")));
        allDeals.click();
        Assert.assertTrue(allDeals.isDisplayed(),"All Deals page is not Interact");
        Assert.assertEquals(allDeals.getText(),"All Deals");
        System.out.println("All Deals page is Interact");

        //Won Deals page
        WebElement wonDeals = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Won Deals']")));
        wonDeals.click();
        Assert.assertTrue(wonDeals.isDisplayed(),"Export History page is not Interact");
        Assert.assertEquals(wonDeals.getText(), "Won Deals");
        System.out.println("Won Deals page is Interact");
    }

     //Opportunities section
     @Test (priority=3)
     public void opportunities(){
 
         WebElement opportunities= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Opportunities']")));
         opportunities.click();
         System.out.println("Opportunities Page open: Successfull");
     }
     
    //Automation section
    @Test (priority=4)
    public void automation(){
 
        WebElement autoamtion= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Automation']")));
        autoamtion.click();
        System.out.println("Automation Page open: Successfull");
    }
    
     //Integration section
    @Test (priority=5)
    public void integrations(){
 
        WebElement integration= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Integrations']")));
        integration.click();
        System.out.println("Integrations Page open: Successfull");
    }

     //Staff Management dropdown
     @Test (priority=6)
     public void staffManagement(){
        
        JavascriptExecutor js= (JavascriptExecutor)driver;
        

        WebElement staffMangement= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Staff Management']")));
        js.executeScript("arguments[0].scrollIntoView(true);", staffMangement);
        staffMangement.click();
        
        //staff page
        WebElement staff= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Staff']")));
        js.executeScript("arguments[0].scrollIntoView(true);", staff);
        staff.click();
        Assert.assertTrue(staff.isDisplayed());
        Assert.assertEquals(staff.getText(), "Staff");
        System.out.println("Staff page open: Successfull");

        //Attendance page
        WebElement attendance= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Attendance']")));
        js.executeScript("arguments[0].scrollIntoView(true);", attendance);
        attendance.click();
        Assert.assertTrue(attendance.isDisplayed());
        Assert.assertEquals(attendance.getText(), "Attendance");
        System.out.println("Attendance page open: Successfull");

        //Role and permission page
        WebElement roleAndPermission= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Roles & Permissions']")));
        js.executeScript("arguments[0].scrollIntoView(true);", roleAndPermission);
        roleAndPermission.click();
        Assert.assertTrue(roleAndPermission.isDisplayed());
        Assert.assertEquals(roleAndPermission.getText(), "Roles & Permissions");
        System.out.println("Roles & Permissions page open: Successfull");
        
        //Login History page
        WebElement loginHistory= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Login History']")));
        js.executeScript("arguments[0].scrollIntoView(true);", loginHistory);
        loginHistory.click();
        Assert.assertTrue(loginHistory.isDisplayed());
        Assert.assertEquals(loginHistory.getText(), "Login History");
        System.out.println("Login History page open: Successfull");
    }

     //Setting dropdown
     @Test (priority=7)
     public void setting(){
        
        JavascriptExecutor js= (JavascriptExecutor)driver;
        

        WebElement setting= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Settings']")));
        js.executeScript("arguments[0].scrollIntoView(true);", setting);
        setting.click();
        
        //staff page
        WebElement customize= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Customize']")));
        js.executeScript("arguments[0].scrollIntoView(true);", customize);
        customize.click();
        Assert.assertTrue(customize.isDisplayed());
        Assert.assertEquals(customize.getText(), "Customize");
        System.out.println("Customize page open: Successfull");

        //Attendance page
        WebElement template= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Template']")));
        js.executeScript("arguments[0].scrollIntoView(true);", template);
        template.click();
        Assert.assertTrue(template.isDisplayed());
        Assert.assertEquals(template.getText(), "Template");
        System.out.println("Template page open: Successfull");
    }
}
