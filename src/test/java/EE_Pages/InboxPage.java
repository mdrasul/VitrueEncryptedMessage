package EE_Pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SupportSeleniumUIActions.WebUI;

public class InboxPage {

	
	WebDriver driver;
	WebUI selenium;
	String sLoginPageURL = "http://gmail.com";
	

	//###### Locators :-   *****
	@FindBy(xpath="//table[@class='F cf zt']")
	public WebElement inboxtable;

	@FindBy(xpath="//a[contains(@href,'secure.virtru.com/start')]")
	public WebElement VirtruMsgLink;
	
	//@FindBy(xpath="//span[contains(text(),'Next')]/../..")
	//public WebElement VirtruMsgLink;
	
	
	
	
	/****** Constructor ******************/ 
	public InboxPage(WebDriver driver)
	{
		//==>> Loading All Web-Element Object Using Page Factory
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		//==>> Initialize SELENIUM wrapper that will help to perform all Web-UI Related Action  [ Click , Set ETC] 
		selenium = new WebUI(driver);
	}


	/****** Page Actions Functions ******************/ 
	
	public boolean OpenEmailWithSubjectContain(String subjectLine) {
		try
		{
			selenium.ClickTableCellText(inboxtable, subjectLine);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public void UnlockVirtruMsg() {
		selenium.ClickButton(VirtruMsgLink);
	}


	 String oldTab;
	 ArrayList<String> newTab;
	 
	public void SwitchToVirtruSecureReaderTab() 
	{
        //==>> When This Function Get Called - Assume Virtru Tab is Open we Need a Swith from Gmail Tab to This Tab 
        oldTab = driver.getWindowHandle();
		newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);				   				  
		driver.switchTo().window(newTab.get(0)); //=>change focus to new tab

	}
	public void SwitchBacktoGmailInbox()
	{
		//==>> Switching Back To Old Tab 
		driver.close();  
		driver.switchTo().window(oldTab);
	}


	public void DecryptVirtruEmailMsgfor(EmailAccount acct) 
	{
		WebElement selectYourEmailButton = driver.findElement(By.xpath("//span[contains(text(),'"+acct.getId()+"')]/.."));
		selenium.ClickButton(selectYourEmailButton);
		
		WebElement loginWithOuthProbiderButton = driver.findElement(By.xpath("//a[contains(text(),'Login With')]"));
		selenium.ClickButton(loginWithOuthProbiderButton);
		
	}


	public String ReadVirtruDecryptedEmail() {
		
		
		WebElement VirtruDecryptedMsgBody = driver.findElement(By.xpath("//div[@class='row row-body']"));
		return VirtruDecryptedMsgBody.getText();
		
	}
	
	
}
