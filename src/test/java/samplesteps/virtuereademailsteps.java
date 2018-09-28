package samplesteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import EE_Pages.EmailAccount;
import EE_Pages.EmailLoginPage;
import EE_Pages.InboxPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverutil.initdriver;

public class virtuereademailsteps {
	
	WebDriver driver;
	EmailAccount eaccount;
	EmailLoginPage loginpage;
	InboxPage inboxpage;
	
	
	@Given("^I Login to my Email Account$")
	public void i_Login_to_my_Email_Account() throws Throwable {

		//driver = initdriver.getRemoteWebDriver("chrome");
		driver = initdriver.getlocaldriver();
		eaccount = new EmailAccount("Transfotechstudent1@gmail.com","Dhaka_18");
		loginpage = new EmailLoginPage(driver);
		
		//==>> Here we need to check the System Status Logged In / Not 
		Assert.assertTrue("Failed to Login - > Trace the Error..", loginpage.doLoginWith(eaccount));
	}

	@And("^I See a Virtue Encrypted Email$")
	public void i_See_a_Virtue_Encrypted_Email() throws Throwable {
		inboxpage = new InboxPage(driver);
		
		//==>> Here we need to check Email Was There and we Opened it 
		Assert.assertTrue("VirtruEmail Is Not Found..", inboxpage.OpenEmailWithSubjectContain("Virtru"));
		inboxpage.UnlockVirtruMsg();
	}

	@When("^I Decrypted the Email$")
	public void i_Decrypted_the_Email() throws Throwable {
		inboxpage.SwitchToVirtruSecureReaderTab();
		inboxpage.DecryptVirtruEmailMsgfor(eaccount);
	}

	@Then("^I Validate I can Read VirtruDecryptedEmail Content$")
	public void i_Validate_I_can_Read_Email_Content() throws Throwable {

		String expected = "Test Note\n" + 
						  "This is a Test Email Send to check the Encryption";
		String actual = inboxpage.ReadVirtruDecryptedEmail();
		
		assertEquals(expected, actual);
	}


	
	

}
