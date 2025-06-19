package com.homer.uistore;

import org.openqa.selenium.By;

public class HeaderUI {
	
	public static By MyAccount = By.linkText("Your Account");
	public static By MyAccountList = By.xpath("//div[@id='thdMyAccount']/ul");
	public static By ProjectHowTo = By.linkText("Project: How-To");
	public static By ProjectHowtoList = By.xpath("//div[@id='thdProjectHT']/ul/li");

	public static By DeleteItem = By.id("DeleteItem");
	public static By CartButton = By.id("cart");
	public static By ProductSectionCart = By.xpath("//div[@id='cartMainSection']//div[contains(@class,'prodSection')]");
	public static By TotalCartItems = By.id("miniCartNum");
	
	public static final String linkUserReg="//div[@id='accountInfo']/a[contains(text(),'Register')]";
}
