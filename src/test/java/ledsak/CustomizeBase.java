package ledsak;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomizeBase {

    public WebDriver driver;

    public CustomizeBase(WebDriver driver) {
        this.driver = driver;
        if (driver != null) {
            PageFactory.initElements(driver, this);
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "label_calls")
    public WebElement callField;

    @FindBy(name = "label_notes")
    public WebElement notesField;

    @FindBy(name = "label_emails")
    public WebElement emailField;

    @FindBy(name = "label_meetings")
    public WebElement meetingField;

    @FindBy(name = "label_tasks")
    public WebElement taskField;

    @FindBy(name = "label_branch")
    public WebElement branchField;

    @FindBy(name = "label_lead_field")
    public WebElement leadfieldField;

    @FindBy(name = "label_lead_group")
    public WebElement groupField;

    @FindBy(name = "label_lead_source")
    public WebElement sourceField;

    @FindBy(name = "label_lead_stage")
    public WebElement stageField;

    @FindBy(xpath = "//button[text()='Update ']")
    public WebElement updateButton;

    public void callTextBox() throws InterruptedException {
        callField.click();
        Thread.sleep(300);
        callField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        callField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        callField.sendKeys("Call");
        System.out.println("call value: " + callField.getDomAttribute("value"));
    }

    public void emailTextBox() throws InterruptedException {
        emailField.click();
        Thread.sleep(300);
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        emailField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        emailField.sendKeys("Email");
        System.out.println("email value: " + emailField.getDomAttribute("value"));

    }

    public void meetingTextBox() throws InterruptedException {
        meetingField.click();
        Thread.sleep(300);
        meetingField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        meetingField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        meetingField.sendKeys("Meeting");
        System.out.println("meeting value: " + meetingField.getDomAttribute("value"));

    }

    public void taskTextBox() throws InterruptedException {
        taskField.click();
        Thread.sleep(300);
        taskField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        taskField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        taskField.sendKeys("Task");

        System.out.println("task value: " + taskField.getDomAttribute("value"));

    }

    public void branchTextBox() throws InterruptedException {
        branchField.click();
        Thread.sleep(300);
        branchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        branchField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        branchField.sendKeys("Branch");

        System.out.println("Branch label value: " + branchField.getDomAttribute("value"));
    }

    public void leadFieldTextBox() throws InterruptedException {
        leadfieldField.click();
        Thread.sleep(300);
        leadfieldField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        leadfieldField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        leadfieldField.sendKeys("Lead Field");

        System.out.println("Lead Field label value: " + leadfieldField.getDomAttribute("value"));
    }

    public void groupFieldTextBox() throws InterruptedException {
        groupField.click();
        Thread.sleep(300);
        groupField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        groupField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        groupField.sendKeys("Group");

        System.out.println("Group label value: " + groupField.getDomAttribute("value"));
    }

    public void sourceFieldTextBox() throws InterruptedException {
        sourceField.click();
        Thread.sleep(300);
        sourceField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sourceField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        sourceField.sendKeys("Branch");

        System.out.println("Source label value: " + sourceField.getDomAttribute("value"));
    }

    public void StageieldTextBox() throws InterruptedException {
        stageField.click();
        Thread.sleep(300);
        stageField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        stageField.sendKeys(Keys.DELETE);
        Thread.sleep(300);
        stageField.sendKeys("Stage");

        System.out.println("Stage label value: " + stageField.getDomAttribute("value"));
    }

    public void updateButton() {
        updateButton.click();

    }
}
