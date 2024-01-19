package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {
	public CheckoutStepTwoPage(WebDriver wd) {
		PageFactory.initElements(wd, this);	
	}
	@FindBy(xpath = "//div[@class='cart_list']/div[@class='cart_item']/div[2]/div[2]") private List<WebElement> CheckoutPriceList;
	@FindBy(xpath = "//div[text()='Payment Information']") private WebElement PaymentInformationText;
	@FindBy(xpath = "//div[text()='SauceCard #31337']") private WebElement PaymentValue;
	@FindBy(xpath = "//div[text()='Shipping Information']") private WebElement ShippingInformationText;
	@FindBy(xpath = "//div[text()='Free Pony Express Delivery!']") private WebElement ShippingValue;
	@FindBy(xpath = "//div[text()='Price Total']") private WebElement TotalPriceText;
	@FindBy(xpath = "//div[contains(@class,'summary_subtotal_label')]") private WebElement SubTotal;
	@FindBy(xpath = "//div[contains(@class,'summary_tax_label')]") private WebElement Tax;
	@FindBy(xpath = "//div[contains(@class,'summary_info_label summary_total_label')]") private WebElement TotalWithTax;
	@FindBy(id = "cancel") private WebElement CancelButton;
	@FindBy(id = "finish") private WebElement FinishButton;
	
	public List<WebElement> priceList() {
		return CheckoutPriceList;
	}
	public String paymentInformationText() {
		return PaymentInformationText.getText();
	}
	public String paymentValue() {
		return PaymentValue.getText();
	}
	public String shippingInformation() {
		return ShippingInformationText.getText();
	}
	public String shippingValue() {
		return ShippingValue.getText();
	}
	public String totalPriceText() {
		return TotalPriceText.getText();
	}
	public String subTotalgValue() {
		return SubTotal.getText();
	}
	public String taxValue() {
		return Tax.getText();
	}
	public String totalPriceWithTax() {
		return TotalWithTax.getText();
	}
	public WebElement cancelBtnVerification() {
		return CancelButton;
	}
	public WebElement finishBtnVerification() {
		return FinishButton;
	}
	public void clickOnCancelBtn() {
		CancelButton.click();
	}
	public void clickOnFinishBtn() {
		FinishButton.click();
	}
}