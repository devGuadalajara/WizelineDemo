package com.wizeline.academy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class saucedemoTest
{
	
	private WebDriver driver;
	
	By userLocator = By.id("user-name");
	By passLocator = By.id("password");
	By signInBtnLocator = By.id("login-button");

	By productsPageLocator = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
	By errorLoginMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

	By hamburgerButtonProducts = By.id("react-burger-menu-btn");
	By logoutButton = By.id("logout_sidebar_link");

	By btnSauceLabsOnesie = By.id("add-to-cart-sauce-labs-onesie");
	By btnSauceLabsBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
	By btnSauceLabsFleeceJacket = By.id("add-to-cart-sauce-labs-fleece-jacket");

	By sauceLabsOnesie = By.id("item_2_title_link");
	By sauceLabsBikeLight = By.id("item_0_title_link");
	By sauceLabsFleeceJacket = By.id("item_5_title_link");

	By btnShoppingCart = By.id("shopping_cart_container");
	By btnPurchase = By.id("checkout");

	By inputFirstName = By.id("first-name");
	By inputLastName = By.id("last-name");
	By inputPostalCode = By.id("postal-code");
	By btnContinue = By.id("continue");
	By btnFinish = By.id("finish");

	By lblOrderCompleted = By.xpath("//*[@id=\"checkout_complete_container\"]/h2");


	@Before
	public void setUp() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/ ");
	}

	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
	
	@Test
	public void loginValidUser() throws InterruptedException
	{
		String validUser = "standard_user";
		String password = "secret_sauce";
		String expectedResult = "PRODUCTS";

		if(driver.findElement(userLocator).isDisplayed())
		{
			driver.findElement(userLocator).sendKeys(validUser);
			driver.findElement(passLocator).sendKeys(password);
			driver.findElement(signInBtnLocator).click();
			Thread.sleep(2000);
			String result = driver.findElement(productsPageLocator).getText();

			assertEquals(result, expectedResult);
		}
		
	}

	@Test
	public void loginInvalidUser() throws InterruptedException
	{
		String invalidUser = "invalidUser";
		String password = "secret_sauce";
		String expectedResult = "Epic sadface: Username and password do not match any user in this service";

		if(driver.findElement(userLocator).isDisplayed())
		{
			driver.findElement(userLocator).sendKeys(invalidUser);
			driver.findElement(passLocator).sendKeys(password);
			driver.findElement(signInBtnLocator).click();
			Thread.sleep(2000);
			String result = driver.findElement(errorLoginMessage).getText();
			assertEquals(result, expectedResult);
		}
	}

	@Test
	public void logout() throws InterruptedException
	{
		loginValidUser();

		driver.findElement(hamburgerButtonProducts).click();
		Thread.sleep(2000);
		driver.findElement(logoutButton).click();
		Thread.sleep(2000);

		assertTrue(driver.findElement(signInBtnLocator).isDisplayed());
	}

	@Test
	public void sortProductsByPrice() throws InterruptedException
	{
		String expectedResult = "PRICE (LOW TO HIGH)";
		String xpathSortBy = "//*[@id='header_container']/div[2]/div[2]/span/span";
		String classSortBy = "product_sort_container";

		loginValidUser();
		Thread.sleep(1000);

		Select ddSortBy = new Select ( driver.findElement(By.className( classSortBy )) );
		ddSortBy.selectByIndex(2);
		Thread.sleep(1000);

		WebElement spanSortBy = driver.findElement(By.xpath(xpathSortBy));
		String result = spanSortBy.getText();

		assertEquals(result, expectedResult);
		Thread.sleep(1000);
	}

	@Test
	public void addItemsToCart() throws InterruptedException
	{
		sortProductsByPrice();

		driver.findElement(btnSauceLabsOnesie).click();
		driver.findElement(btnSauceLabsBikeLight).click();
		driver.findElement(btnSauceLabsFleeceJacket).click();
		Thread.sleep(1000);

		driver.findElement(btnShoppingCart).click();
		Thread.sleep(1000);

		assertTrue(
				driver.findElement(sauceLabsOnesie).isDisplayed() &&
						driver.findElement(sauceLabsBikeLight).isDisplayed() &&
						driver.findElement(sauceLabsFleeceJacket).isDisplayed()
		);
		Thread.sleep(1000);
	}

	@Test
	public void addSauceLabsOnesie() throws InterruptedException
	{
		sortProductsByPrice();

		driver.findElement(btnSauceLabsOnesie).click();
		Thread.sleep(1000);

		driver.findElement(btnShoppingCart).click();
		Thread.sleep(1000);

		assertTrue(
				driver.findElement(sauceLabsOnesie).isDisplayed()
		);
		Thread.sleep(1000);
	}

	@Test
	public void completePurchase() throws InterruptedException
	{
		String firstName, lastName, zipCode;
		firstName = "Jose";
		lastName = "Gomez Camacho";
		zipCode = "45608";
		String expectedResult = "THANK YOU FOR YOUR ORDER";

		addItemsToCart();

		driver.findElement(btnPurchase).click();
		Thread.sleep(1000);

		fillAddress(firstName, lastName, zipCode);

		driver.findElement(btnFinish).click();
		Thread.sleep(1000);

		String result = driver.findElement(lblOrderCompleted).getText();

		assertEquals(result, expectedResult);

	}

	public void fillAddress(String firstName, String lastName, String zipCode) throws InterruptedException
	{
		driver.findElement(inputFirstName).sendKeys(firstName);
		driver.findElement(inputLastName).sendKeys(lastName);
		driver.findElement(inputPostalCode).sendKeys(zipCode);
		driver.findElement(btnContinue).click();
		Thread.sleep(1000);
	}

}
