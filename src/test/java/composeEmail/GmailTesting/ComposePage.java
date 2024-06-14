package composeEmail.GmailTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.manager.SeleniumManager;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class ComposePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public ComposePage() {
    	String projectPath = Paths.get("").toAbsolutePath().toString();
        String driverPath = Paths.get(projectPath, "drivers", "geckodriver.exe").toString();
        System.setProperty("webdriver.gecko.driver", driverPath);
        FirefoxOptions options = new FirefoxOptions();

	    driver=new FirefoxDriver(options);

	    driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String userEmail, String userPassword) {
        driver.get("https://mail.google.com/");
        
        // Enter email id
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
        emailField.sendKeys(userEmail);
        WebElement nextButton = driver.findElement(By.id("identifierNext"));
        nextButton.click();
        
        // Enter password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("Passwd")));
        passwordField.sendKeys(userPassword);
        WebElement passwordNextButton = driver.findElement(By.id("passwordNext"));
        passwordNextButton.click();
        
        // Wait until the inbox is loaded
        wait.until(ExpectedConditions.titleContains("Inbox"));
    }

    public void openComposeWindow() {
        WebElement composeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Compose']")));
        composeButton.click();
    }

    public void enterSubject(String emailSubject) {
        WebElement subjectField = wait.until(ExpectedConditions.elementToBeClickable(By.name("subjectbox")));
        subjectField.sendKeys(emailSubject);
    }

    public void enterBody(String body) {
        WebElement bodyField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Message Body']")));
        bodyField.sendKeys(body);
    }

    public void enterRecipient(String recipientEmail) {
    	WebElement toField = wait.until(ExpectedConditions.elementToBeClickable(By.id(":bm")));
        toField.sendKeys(recipientEmail);
    }

    public void clickSend() {
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Send']")));
        sendButton.click();
    }

    public void verifyEmailSent() {
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Message sent')]")));
        if (confirmation.isDisplayed()) {
            System.out.println("Email sent confirmation displayed.");
        } else {
            throw new AssertionError("Email sent confirmation not displayed.");
        }
    }

    public void verifyEmailInSentItems(String subject) {
        WebElement sentItemsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '#sent')]")));
        sentItemsLink.click();
        
        List<WebElement> sentEmails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='bog']/span[text()='" + subject + "']")));
        if (!sentEmails.isEmpty()) {
            System.out.println("Email found in Sent items.");
        } else {
            throw new AssertionError("Email not found in Sent items.");
        }
    }
}