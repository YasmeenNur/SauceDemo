package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
	WebDriver driver;
	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//div[@class='app_logo']") private WebElement InventoryPageTitle;
	@FindBy(xpath = "//div[@class='inventory_list']/div") List<WebElement> ProductsDetails;
	@FindBy(id = "react-burger-menu-btn") private WebElement HamBurgermenu;
	@FindBy(id = "inventory_sidebar_link") private WebElement AllItem;
	@FindBy(id = "about_sidebar_link") private WebElement About;
	@FindBy(id = "logout_sidebar_link") private WebElement LogOut;
	@FindBy(id = "reset_sidebar_link") private WebElement RestAppState ;
	@FindBy(id = "react-burger-cross-btn") private WebElement CloseIcon;
	@FindBy(xpath = "//select[@class='product_sort_container']") private WebElement DropDown;
	@FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[2]/button[1]") private List<WebElement> AddtocartButtonList;
	@FindBy(xpath = "//div[@class='inventory_list']/div/div[1]/a") private List<WebElement> ProductImages; 
	@FindBy(xpath = "//div[@class='inventory_details_desc_container']/button") private WebElement AddtocartButton;
	@FindBy(xpath = "//a[@class='shopping_cart_link']") private WebElement CartIcon;
	@FindBy(xpath = "//span[@class='shopping_cart_badge']") private WebElement shoppingCartContent;
	@FindBy(xpath = "//nav[@class='bm-item-list']/a") private List<WebElement> HamburgerMenuList;
	@FindBy(xpath = "//div[@id='contents_wrapper']/div/div[2]/div/button") private WebElement BackTOProductBtn;
	@FindBy(xpath = "//select[@class='product_sort_container']/option") private List<WebElement> DropDownList;
	@FindBy(xpath = "//div[@class='inventory_item_name ']") private List<WebElement> AlphabitcalNameAtoz;
	@FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[1]/a[1]/div[1]") private List<WebElement> ProductNames;
	@FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[2]/div[1]") private List<WebElement> ProductPrices;

	public String inventoryPageTitle() {
		return InventoryPageTitle.getText();
	}
	public void clickOnAnyProduct() {
		Random rand = new Random();
		ProductImages.get(rand.nextInt(ProductImages.size())).click();
	}
	public void clickOnMultipleProduct() throws InterruptedException {
		Random rand = new Random();
		for(int i = 0; i<3;i++) {
			AddtocartButtonList.get(rand.nextInt(AddtocartButtonList.size())).click();
			Thread.sleep(500);
		}		
	}
	public void addtoCartButtonVerification() throws InterruptedException {
		Random r= new Random();
		for(int i=1;i<=3;i++) {
			AddtocartButtonList.get(r.nextInt(AddtocartButtonList.size())).click();
			Thread.sleep(500);
			System.out.println("The shopping cart content when clicked on "+i+" products it is: "+shoppingCartContent.getText());
		}
	}
	public List<WebElement> listOfInventoryProducts(){
		return ProductsDetails;
	}
	public WebElement hamBurgerPresence() {
		return HamBurgermenu;
	}
	public void clickOnHamBurger() {
		HamBurgermenu.click();
	}
	public List<WebElement> hamBugerMenuList() {
		return HamburgerMenuList;
	}
	public WebElement allItemPresence() {
		return AllItem;
	}
	public void clickOnAllItems() {
		AllItem.click();
	}
	public WebElement aboutPresence() {
		return About;
	}
	public void clickOnAbout() {
		About.click();
	}
	public WebElement logOutPresence() {
		return LogOut;
	}
	public void clickOnResetApp() {
		RestAppState.click();
	}
	public WebElement closeIconPresence() {
		return CloseIcon;
	}
	public void ClickOnCloseIcon() {
		CloseIcon.click();
	}
	public List<WebElement> dropDownListVerification() {
		return DropDownList;
	}
	public void selectNameAtoZ() {
		Select s = new Select(DropDown);
		s.selectByVisibleText("Name (A to Z)");
	}
	public List<WebElement> productNames() {
		return ProductNames;
	}
	public void selectNameZtOA() {
		Select s = new Select(DropDown);
		DropDown.click();
		s.selectByValue("za");
	}
	public List<WebElement> productPrices(){
		return ProductPrices;
	}
	public void selectPriceLowtoHigh() {
		Select s= new Select(DropDown);
		DropDown.click();
		s.selectByVisibleText("Price (low to high)");
	}
	public void selectPriceHightoLow() {
		Select s= new Select(DropDown);
		DropDown.click();
		s.selectByVisibleText("Price (high to low)");
	}
	public WebElement addtoCartButtonPresence() {
		Random r= new Random();
		return AddtocartButtonList.get(r.nextInt(AddtocartButtonList.size()));
	}
	public WebElement addToCart() {
		return AddtocartButton;
	}
	public void clickOnAddtoCart() {
		AddtocartButton.click();
	}
	public WebElement cartIconPresence() {
		return CartIcon;
	}
	public void clickOnCartIcon() {
		CartIcon.click();
	}
	public String cartContent() {
		return shoppingCartContent.getText();
	}
	public void clickOnLogout() {
		LogOut.click();
	}
	public String cartIconContent() {
		return shoppingCartContent.getText();
	}
	public WebElement backToProducts() {
		return BackTOProductBtn;
	}
	public void clickOnBackToProduct() {
		BackTOProductBtn.click();
	}
}