package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
	public CheckoutCompletePage(WebDriver wd) {
		PageFactory.initElements(wd, this);
	}
	@FindBy(xpath = "//h2[text()='Thank you for your order!']") private WebElement ThankyouText;
	@FindBy(xpath = "//*[contains(text(),'Your order has been dispatched')]") private WebElement CompleteText;
	@FindBy(id="back-to-products") private WebElement BackHome;
	
	public String thankYouText() {
		return ThankyouText.getText();
	}
	public String completeText() {
		return CompleteText.getText();
	}
	public WebElement BackHomeBtn() {
		return BackHome;
	}
	public void clickOnBackHomeBtn() {
		BackHome.click();
	}
}