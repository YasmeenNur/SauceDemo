package com.qa.saucedemo.SauceDemoWebsite;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutCompletePage;
import pageObjects.CheckoutStepOnePage;
import pageObjects.CheckoutStepTwoPage;
import pageObjects.FooterPage;
import pageObjects.InventoryPage;
import pageObjects.LoginPage;

public class SauceDemoTestCases {
	WebDriver driver;
	LoginPage lp; InventoryPage ip; CartPage cp; FooterPage fp;
	CheckoutStepOnePage csop; CheckoutStepTwoPage cstp;CheckoutCompletePage ccp;

	@BeforeTest
	public void initialisation() {
		driver = new ChromeDriver();
		lp = new LoginPage(driver);ip = new InventoryPage(driver); fp = new FooterPage(driver); 
		cp = new CartPage(driver); csop = new CheckoutStepOnePage(driver);
		cstp = new CheckoutStepTwoPage(driver); ccp = new CheckoutCompletePage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
	}

	@Test(priority = 1)
	public void loginPageValidation() {
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/");
		Assert.assertEquals(lp.text(), "Swag Labs");
		Assert.assertEquals(lp.userNameFieldPresence().getAttribute("placeholder"), "Username");
		Assert.assertEquals(lp.passwordFieldPresence().getAttribute("placeholder"), "Password");
		Assert.assertEquals(lp.loginBtn().getAttribute("value"), "Login");
	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] validLoginData(){
		Object Data[][] = {{"standard_user","secret_sauce"},{"problem_user","secret_sauce"},
				{"performance_glitch_user","secret_sauce"},
				{"error_user","secret_sauce"},{"visual_user","secret_sauce"}};
		return Data;
	}

	@Test(dataProvider = "ValidLoginData", priority = 2)
	public void loginWithValidCredentials(String username, String password) {
		lp.enterCredentials(username, password);
		lp.clickOnLogin();
		ip.clickOnHamBurger();
		ip.clickOnLogout();
	}

	@Test(priority = 3)
	public void loginWithInvalidCredentials() {
		lp.enterCredentials("dsfjdsnkjf", "asdjb@1234");
		lp.clickOnLogin();
		Assert.assertEquals(lp.errorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");		
	}

	@Test(priority = 4)
	public void loginWithValidEMailAndInvalidPwd() {
		lp.enterCredentials("standard_user", "sadjashkjd");
		lp.clickOnLogin();
		Assert.assertEquals(lp.errorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");
	}

	@Test(priority = 5)
	public void loginWithInvalidEmailAndValidPwd() {
		lp.enterCredentials("mndsfbdskj", "secret_sauce");
		lp.clickOnLogin();
		Assert.assertEquals(lp.errorMessage().getText(), "Epic sadface: Username and password do not match any user in this service");
	}

	@Test(priority = 6)
	public void inventoryPageValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");
		Assert.assertEquals(ip.inventoryPageTitle(), "Swag Labs");
	}

	@Test(priority = 7)
	public void inventoryProductsDetails() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> ProductDetails = ip.listOfInventoryProducts();
		for(int i =0; i<ProductDetails.size();i++) {
			System.out.println(ProductDetails.get(i).getText());
		}
	}

	@Test(priority = 8)
	public void addToCartButtonValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		WebElement AddToCart = ip.addtoCartButtonPresence();		
		Assert.assertTrue(AddToCart.isDisplayed());
		Assert.assertTrue(AddToCart.isEnabled());
		Assert.assertEquals(AddToCart.getText(), "Add to cart");
	}

	@Test(priority = 9)
	public void clickOnAddToCartButtonValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.addtoCartButtonVerification();
	}

	@Test(priority = 10)
	public void clickOnAnyProductValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnAnyProduct();
		Assert.assertTrue(lp.verifyUrl().contains("inventory-item.html?id="));
		WebElement AddToCart = ip.addToCart();
		Assert.assertTrue(AddToCart.isDisplayed());
		Assert.assertTrue(AddToCart.isEnabled());
		Assert.assertEquals(AddToCart.getText(), "Add to cart");
		ip.clickOnAddtoCart();
		System.out.println(ip.cartContent());
	}

	@Test(priority = 11)
	public void backTOProductsValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnAnyProduct();
		ip.clickOnAddtoCart();
		System.out.println("The Cart value is "+ip.cartContent());
		Assert.assertTrue(ip.backToProducts().isDisplayed());
		ip.clickOnBackToProduct();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");
	}

	@Test(priority = 12)
	public void hamburgerValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		Assert.assertTrue(ip.hamBurgerPresence().isDisplayed());
		ip.clickOnHamBurger();
		List<WebElement> HamburgerList = ip.hamBugerMenuList();
		for(int i =0;i<HamburgerList.size();i++) {
			System.out.println(HamburgerList.get(i).getText());
		}
	}

	@Test(priority = 13)
	public void hamburgerAllItemsValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnHamBurger();
		ip.clickOnAllItems();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");
	}

	@Test(priority = 14)
	public void hamburgerAboutValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnHamBurger();
		ip.clickOnAbout();
		Assert.assertEquals(lp.verifyUrl(), "https://saucelabs.com/");
		driver.navigate().back();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");
	}

	@Test(priority = 15)
	public void hamburgerLogoutValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnHamBurger();
		ip.clickOnLogout();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/");
	}

	@Test(priority = 16)
	public void filterDropdownValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> DropDownList = ip.dropDownListVerification();
		for(int i =0; i<DropDownList.size();i++) {
			System.out.println(DropDownList.get(i).getText());
		}
	}

	@Test(priority = 17)
	public void selectProductByAlphabeticalOrderValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> ProductNamesBefore = ip.productNames();
		ip.selectNameAtoZ();
		List<WebElement> ProductNamesAfter = ip.productNames();
		for(int i =0; i<ProductNamesBefore.size();i++) {
			Assert.assertEquals(ProductNamesBefore.get(i).getText(), ProductNamesAfter.get(i).getText());
		}		
	}

	@Test(priority = 18)
	public void selectProductByReverseAlphabeticalOrderValidation() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> ProductNamesBefore = ip.productNames();
		for(int i =0; i<ProductNamesBefore.size();i++) {
			System.out.println(ProductNamesBefore.get(i).getText());
		}
		ip.selectNameZtOA();
		List<WebElement> ProductNamesAfter = ip.productNames();
		for(int i =0; i<ProductNamesBefore.size();i++) {
			System.out.println(ProductNamesAfter.get(i).getText());
		}	
	}

	@Test(priority = 19)
	public void selectProductByHighToLow() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> ProductPriceBefore = ip.productPrices();
		List<String> Price = new ArrayList<String>();		
		for(int i = 0; i< ProductPriceBefore.size();i++) {
			Price.add(ProductPriceBefore.get(i).getText());
		}
		Price.sort(null);
		for(String eachPrice: Price) {
			System.out.println(eachPrice);
		}
		ip.selectPriceHightoLow();
		List<WebElement> ProductPriceAfter = ip.productPrices();
		Assert.assertEquals(ProductPriceBefore.size(), ProductPriceAfter.size());
		for(int i = 0; i<ProductPriceAfter.size();i++) {
			System.out.println(ProductPriceAfter.get(i).getText());
		}
	}

	@Test(priority = 20)
	public void selectProductByLowToHigh() {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> ProductPriceBefore = ip.productPrices();
		List<String> Price = new ArrayList<String>();		
		for(int i = 0; i< ProductPriceBefore.size();i++) {
			Price.add(ProductPriceBefore.get(i).getText());
		}
		Price.sort(null);
		for(String eachPrice: Price) {
			System.out.println(eachPrice);
		}
		ip.selectPriceLowtoHigh();
		List<WebElement> ProductPriceAfter = ip.productPrices();
		Assert.assertEquals(ProductPriceBefore.size(), ProductPriceAfter.size());
		for(int i = 0; i<ProductPriceAfter.size();i++) {
			System.out.println(ProductPriceAfter.get(i).getText());
		}
	}

	@Test(priority = 21)
	public void cartPageValidation() throws InterruptedException {
		//checoutStepOnePageValidation
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		Assert.assertTrue(ip.cartIconPresence().isDisplayed());
		cp.clickOnCartIcon();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/cart.html");	
		Assert.assertEquals(cp.pageText(), "Your Cart");
	}

	@Test(priority = 22)
	public void cartPageRemoveButtonValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		System.out.println("Before clickin on Remove button: "+ip.cartContent());
		WebElement RemoveBtn = cp.removeButtonPresence();
		Assert.assertTrue(RemoveBtn.isDisplayed());
		Assert.assertTrue(RemoveBtn.isEnabled());
		Assert.assertEquals(RemoveBtn.getText(), "Remove");
		RemoveBtn.click();
		System.out.println("After clickin on Remove button: "+ip.cartContent());
	}

	@Test(priority = 23)
	public void cartPageContinueBtnValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		WebElement ContinueShoppingBtn = cp.continueBtnVerification();		
		Assert.assertTrue(ContinueShoppingBtn.isDisplayed());
		Assert.assertTrue(ContinueShoppingBtn.isEnabled());
		Assert.assertEquals(ContinueShoppingBtn.getText(), "Continue Shopping");
		cp.clickOnContinueBtn();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");		
	}

	@Test(priority = 24)
	public void cartPageCheckoutBtnValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		WebElement CheckoutBtn = cp.chekoutBtnVerification();
		Assert.assertTrue(CheckoutBtn.isDisplayed());
		Assert.assertTrue(CheckoutBtn.isEnabled());
		Assert.assertEquals(CheckoutBtn.getText(), "Checkout");
		cp.clickOnCheckout();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/checkout-step-one.html");
	}

	@Test(priority = 25)
	public void checkoutStepOnePageValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		Assert.assertEquals(cp.pageText(), "Checkout: Your Information");
	}

	@Test(priority = 26)
	public void checkoutStepOneFieldsValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		Assert.assertTrue(csop.firstname().isDisplayed());
		Assert.assertEquals(csop.firstname().getAttribute("placeholder"), "First Name");
		Assert.assertTrue(csop.lastName().isDisplayed());
		Assert.assertEquals(csop.lastName().getAttribute("placeholder"), "Last Name");
		Assert.assertTrue(csop.postalCode().isDisplayed());
		Assert.assertEquals(csop.postalCode().getAttribute("placeholder"), "Zip/Postal Code");
		csop.enterChecoutDetails("asfdg", "sddgf", "546155");
	}

	@Test(priority = 27)
	public void chekoutStepOnePageCancelBtnValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();		
		WebElement CancelButton = csop.checkoutCancelBtnVerification();
		Assert.assertTrue(CancelButton.isDisplayed());
		Assert.assertTrue(CancelButton.isEnabled());
		Assert.assertEquals(CancelButton.getText(), "Cancel");	
		csop.clickOnCancelBtn();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/cart.html");
	}

	@Test(priority = 28)
	public void checkoutStepOnePageContinueBtnValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("abc", "ghi", "45111");
		WebElement ContinuButton = csop.checkoutContinueBtnVerification();
		Assert.assertTrue(ContinuButton.isDisplayed());
		Assert.assertTrue(ContinuButton.isEnabled());
		Assert.assertEquals(ContinuButton.getAttribute("value"), "Continue");
		csop.clickOnContinueBtn();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/checkout-step-two.html");		 
	}

	@Test(priority = 29)
	public void checkoutStepOnePageContinueBtnWithoutDataValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		WebElement ContinuButton = csop.checkoutContinueBtnVerification();
		Assert.assertTrue(ContinuButton.isDisplayed());
		Assert.assertTrue(ContinuButton.isEnabled());
		Assert.assertEquals(ContinuButton.getAttribute("value"), "Continue");
		csop.clickOnContinueBtn();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/checkout-step-one.html");		 
	}

	@Test(priority = 30)
	public void checkoutStepOnePageEmptyFieldsValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("", "", "");
		csop.clickOnContinueBtn();
		Assert.assertEquals(lp.errorMessage().getText(), "Error: First Name is required");
	}

	@Test(priority = 31)
	public void checkoutStepOnePageFirstNameEmptyValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("", "", "");
		csop.clickOnContinueBtn();
		Assert.assertEquals(lp.errorMessage().getText(), "Error: First Name is required");
	}

	@Test(priority = 32)
	public void checkoutStepOnePageLastNameEmptyValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("abc", "", "");
		csop.clickOnContinueBtn();
		Assert.assertEquals(lp.errorMessage().getText(), "Error: Last Name is required");
	}

	@Test(priority = 33)
	public void checkoutStepOnePagePostalCodeFieldEmptyValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("abc", "ghi", "");
		csop.clickOnContinueBtn();
		Assert.assertEquals(lp.errorMessage().getText(), "Error: Postal Code is required");
	}

	@Test(priority = 34)
	public void chekoutStepTwoPageValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("asfdg", "sddgf", "546155");
		csop.clickOnContinueBtn();
		Assert.assertEquals(cp.pageText(), "Checkout: Overview");
		Assert.assertEquals(cstp.paymentInformationText(), "Payment Information");
		Assert.assertEquals(cstp.paymentValue(), "SauceCard #31337");
		Assert.assertEquals(cstp.shippingInformation(), "Shipping Information");
		Assert.assertEquals(cstp.shippingValue(), "Free Pony Express Delivery!");
		Assert.assertEquals(cstp.totalPriceText(), "Price Total");				
	}

	@Test(priority = 35)
	public void chekoutStepTwoPageCancelBtnValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("asfdg", "sddgf", "546155");
		csop.clickOnContinueBtn();
		WebElement CancelButton = cstp.cancelBtnVerification();
		Assert.assertTrue(CancelButton.isDisplayed());
		Assert.assertTrue(CancelButton.isEnabled());
		Assert.assertEquals(CancelButton.getText(), "Cancel");
		CancelButton.click();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");		
	}

	@Test(priority = 36)
	public void chekoutStepTwoPageFinishBtnValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("asfdg", "sddgf", "546155");
		csop.clickOnContinueBtn();
		WebElement FinishButton = cstp.finishBtnVerification();
		Assert.assertTrue(FinishButton.isDisplayed());
		Assert.assertTrue(FinishButton.isEnabled());
		Assert.assertEquals(FinishButton.getText(), "Finish");
		FinishButton.click();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/checkout-complete.html");				
	}	

	@Test(priority = 37)
	public void checkoutStepTwoPageSubTotalValidation() throws InterruptedException, ParseException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("asfdg", "sddgf", "546155");
		csop.clickOnContinueBtn();
		List<WebElement> Prices = cstp.priceList();
		float SubTotal = 0;
		for(int i = 0; i<Prices.size();i++) {
			Pattern p = Pattern.compile("[^0-9]*([0-9]+(\\.[0-9]*)?)");
			Matcher m = p.matcher(Prices.get(i).getText());
			m.matches();
			String Price = m.group(1);
			float d_num = Float.valueOf(Price);
			SubTotal = SubTotal + d_num;
		}
		Assert.assertNotEquals(SubTotal, cstp.subTotalgValue());	
	}

	@Test(priority = 38)
	public void chekoutCompletePageValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		ip.clickOnMultipleProduct();
		cp.clickOnCartIcon();
		cp.clickOnCheckout();
		csop.enterChecoutDetails("asfdg", "sddgf", "546155");
		csop.clickOnContinueBtn();
		cstp.clickOnFinishBtn();
		Assert.assertEquals(cp.pageText(), "Checkout: Complete!");
		Assert.assertEquals(ccp.thankYouText(), "Thank you for your order!");
		Assert.assertEquals(ccp.completeText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
		WebElement BackHomeBtn = ccp.BackHomeBtn();
		Assert.assertTrue(BackHomeBtn.isDisplayed());
		Assert.assertTrue(BackHomeBtn.isEnabled());
		Assert.assertEquals(BackHomeBtn.getText(), "Back Home");
		ccp.clickOnBackHomeBtn();
		Assert.assertEquals(lp.verifyUrl(), "https://www.saucedemo.com/inventory.html");
	}

	@Test(priority = 39)
	public void footerLinksValidation() throws InterruptedException {
		lp.enterCredentials("standard_user", "secret_sauce");
		lp.clickOnLogin();
		List<WebElement> SocialNetworkLinks = fp.presenceOfLinksVerification();
		Assert.assertEquals(SocialNetworkLinks.size(), 3);
		for(int i = 0;i<SocialNetworkLinks.size();i++) {
			System.out.println(SocialNetworkLinks.get(i).getText());			
		}		
		Assert.assertTrue(fp.footerCopyText().contains("Terms of Service | Privacy Policy"));
	}

}
