package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	public  CartPage(WebDriver wd) {
		PageFactory.initElements(wd, this);

	}
	@FindBy(xpath = "//a[@class='shopping_cart_link']") private WebElement CartImage;
	@FindBy(xpath = "//span[@class='title']") private WebElement CartPageText;
	@FindBy(xpath = "//div[@id='cart_contents_container']/div/div") private List<WebElement> CartProducts;
	@FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item']/div[2]/div[2]/button[1]") private List <WebElement> RemoveButton;
	@FindBy(id="continue-shopping") private WebElement ContinueShopping;
	@FindBy(id="checkout") private WebElement CheckOut;
	
	public void clickOnCartIcon() {
		CartImage.click();
	}
	public String pageText() {
		return CartPageText.getText();
	}
	public WebElement removeButtonPresence() {
		Random rand = new Random();
		return RemoveButton.get(rand.nextInt(RemoveButton.size()));
	}
	public WebElement continueBtnVerification() {
		return ContinueShopping;
	}
	public void clickOnContinueBtn() {
		ContinueShopping.click();
	}
	public WebElement chekoutBtnVerification() {
		return CheckOut;
	}
	public void clickOnCheckout() {
		CheckOut.click();
	}
}

