package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage {
	public FooterPage(WebDriver wd) {
		PageFactory.initElements(wd, this);
	}
	@FindBy(xpath = "//ul[@class='social']/li") private List<WebElement> NetWorkLinks;
	@FindBy(xpath = "//div[@class='footer_copy']") private WebElement FooterCopy;
	
	public List<WebElement> presenceOfLinksVerification() {
		return NetWorkLinks;
	}
	public String footerCopyText() {
		return FooterCopy.getText();
	}
}