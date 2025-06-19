package com.homer.uistore;

import org.openqa.selenium.By;

public class PLPUI {

	public static By VerifyPLPPage = By.id("hd_plp");
	public static By GridNavigationLink = By.cssSelector("div.gridNavigation ul.activeLevel a");
	public static By SearchPLPHeader =By.cssSelector("div.cell_section1 div.content_image a") ; 
	public static By FirstProdImage =By.xpath("//div[@id='products']//span[@class='plus']//ancestor::div[contains(@class,'product pod plp-grid grid_6')]//div[@class='product-image']/a"); 
	public static By FirstProdAddToCart = By.xpath("//div[@id='products']//span[@class='plus']//ancestor::div[contains(@class,'product pod plp-grid grid_6')]//div[@class='cart_options']/a");
	
	public static By ModelNumber = By.xpath("//form[@name='comparisonform']//div[@class='cell_section2']//p[@class='model_container']//span[2]");	
	public static By ModelNumberCount = By.xpath("//form[@name='comparisonform']");
	
	public static By SelectStoreInProd = By.linkText("Select a Store");
	
	public static By CheckStoreInventory = By.cssSelector("a[rel='bopis'] span") ;
	public static By CheckStoreInventoryPop = By.id("fancybox-content") ;
	public static By StoreNameOnChkInvntryPopUP = By.cssSelector("div#localizedStore div[class^='storeInfo-Col last-Col'] div[class^='b storeInfo-ColName']") ;
	
	public static By ItemOutOfStockMsg = By.cssSelector("div.smrtOv-LocErr.smrtError-color.b.storeInfo-Col");
	public static By ShowNearByStoresBtn = By.cssSelector("span.btn-store-show");
	public static By OtherStoreQtyBx = By.xpath("//div[@id='otherStores']//input[@class='input-mini smrtQtyInp']");
}
