package pageObjects;


import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//div[text()='Swag Labs']") private WebElement LoginText;
	@FindBy(id = "user-name") private WebElement UserName;
	@FindBy(id = "password") private WebElement PassWord;
	@FindBy(id="login-button") private WebElement LoginButton;
	@FindBy(xpath = "//div[@class='error-message-container error']") private WebElement errorMsg;

	public String verifyUrl() {
		return driver.getCurrentUrl();
	}
	public String text() {
		return LoginText.getText();
	}
	public WebElement userNameFieldPresence() {
		return UserName;
	}
	public WebElement passwordFieldPresence() {
		return PassWord;
	}
	public WebElement loginBtn() {
		return LoginButton;
	}
	public void enterCredentials(String user, String pwd) {
		UserName.sendKeys(user);
		PassWord.sendKeys(pwd);
	}
	public void enterInvalidCredentials(String username, String password) {
		UserName.sendKeys( username);
		PassWord.sendKeys(password);
	}
	public void clickOnLogin() {
		 LoginButton.click();
	}
	public WebElement errorMessage() {
		return errorMsg;
	}
	
	public void alertHandling() {
		Alert a = driver.switchTo().alert();
		System.out.println(a.getText());
		a.accept();
	}
	public void handleHandles() {
		Set<String> Handles = driver.getWindowHandles();
		for(String handle: Handles) {
			driver.switchTo().window(handle);
		}		
	}
}
