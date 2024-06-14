package composeEmail.GmailTesting;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class StepDefinition {
	
	public static WebDriver driver=null;
	final ComposePage composePage = new ComposePage();
	
	@Given("^I am logged into Gmail with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void i_am_logged_into_Gmail_with_username_and_password(String userEmail, String userPassword) throws Exception{
		composePage.login(userEmail, userPassword);
	}

	@When("^I open the compose window$")
	public void i_open_the_compose_window() throws Exception {
		composePage.openComposeWindow();
	}

	@When("^I enter \"([^\"]*)\" in the subject field$")
	public void i_enter_in_the_subject_field(String emailSubject) throws Exception {
	    composePage.enterSubject(emailSubject);
	}

	@When("^I enter \"([^\"]*)\" in the body field$")
	public void i_enter_in_the_body_field(String emailBody) throws Exception {
	    composePage.enterBody(emailBody);
	}

	@When("^I enter the recipient email \"([^\"]*)\"$")
	public void i_enter_the_recipient_email(String recipientEmail) throws Exception {
		composePage.enterRecipient(recipientEmail);
	}

	@When("^I click the send button$")
	public void i_click_the_send_button() throws Exception {
		composePage.clickSend();
	}

	@Then("^the email should be sent successfully$")
	public void the_email_should_be_sent_successfully() throws Exception {
		composePage.verifyEmailSent();
	}

	@Then("^the email should appear in Sent items with subject \"([^\"]*)\"$")
	public void the_email_should_appear_in_Sent_items_with_subject(String emailSubject) throws Exception {
		composePage.verifyEmailInSentItems(emailSubject);
	}

	
}