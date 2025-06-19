package com.homer.po.DFWMS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.homer.dao.InstanceContainer;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;

public class TPETransactionsPageObject extends PageBase {
	
	public TPETransactionsPageObject(InstanceContainer ic) {
		super(ic);
		common = new Common(ic);
	}
	Common common;
	public static final By EXPANDOPTIONSBUTTON = By.xpath("//*[contains(@class, 'fltr_tbl_tdmodeTop')]//input[contains(@class, 'fltrHidden')]");
	public static final By FROMDATEDROPDOWNBUTTON = By.xpath("(//*[contains(@class, 'dtddropdown') "
			+ "and preceding-sibling::*[text()='From' and "
			+ "preceding-sibling::*[child::*[text()='Internal process date/time:']]]])[1]//*[contains(@class, 'asbasdwndiv')]");
	public static final By TODATEDROPDOWNBUTTON = By.xpath("//*[contains(@class, 'dtddropdown') "
			+ "and preceding-sibling::*[text()='To' "
			+ "and preceding-sibling::*[child::*[text()='Internal process date/time:']]]]//*[contains(@class, 'asbasdwndiv')]");
	private static String CALENDAROPTIONSTR = "//*[contains(@class, 'calendarItem') and text()='" + PLACEHOLDER + "']";
	public static final By MESSAGETYPEDROPDOWNBUTTON = By.xpath("//select[preceding-sibling::*[child::*[text()='Message type:']]]");
	private static String MESSAGETYPEOPTIONSTR = "//select[preceding-sibling::*[child::*[text()='Message type:']]]//option[@value='" + PLACEHOLDER + "']";
	public static final By OBJECTIDTEXTFIELD = By.xpath("//input[@type='text' and preceding-sibling::*[child::*[text()='Object ID:']]]");
	public static final By APPLYBUTTON = By.xpath("//*[@id='dataForm:TLlistFil1:TLlistFil1apply']");
	public static final By TRANSACTIONSFRAME = By.xpath("//iframe[contains(@src, 'TranlogList.jsflps')]");
	public static final By ROWS = By.xpath("//*[@id='dataForm:tranlogTable_body']//tr[not(contains(@class, 'trhide'))]");
	public static final By RESPONSEXML=By.xpath("//*[@id='dataForm:responseXMLText']");
	
	/**
	 * This function clicks the expand filter options button
	 * @throws TCTerminationException
	 */
	public void clickExpandOptionsButton() throws TCTerminationException{
		switchToTransactionsFrame();
		common.click(EXPANDOPTIONSBUTTON, "Expand Options button", 5, true);
	}
	
	/**
	 * This function clicks the 'From' date dropdown button
	 * @throws TCTerminationException
	 */
	public void clickFromDateDropDownButton() throws TCTerminationException{
		switchToTransactionsFrame();
		common.click(FROMDATEDROPDOWNBUTTON, "'From' Dropdown button'", 5, true);
	}
	
	/**
	 * This function clicks the 'To' date dropdown button
	 * @throws TCTerminationException
	 */
	public void clickToDateDropDownButton() throws TCTerminationException{
		switchToTransactionsFrame();
		common.click(TODATEDROPDOWNBUTTON, "'To' Dropdown button", 5, true);
	}
	
	/**
	 * This function clicks the individual Drop Down option for the 'From' and 'To' dropdown
	 * lists
	 * @param option
	 * @throws TCTerminationException
	 */
	private void clickDropDownItem(String option) throws TCTerminationException{
		switchToTransactionsFrame();
		String locatorStr = CALENDAROPTIONSTR.replace(PLACEHOLDER, option);
		By locator = By.xpath(locatorStr);
		common.click(locator, "Dropdown option [" + option + "]" , 5, true);
	}
	
	/**
	 * This function clicks the individual Drop Down option for the Message Option
	 * dropdown list
	 * @param option
	 * @throws TCTerminationException
	 */
	private void clickMessageOption(String option) throws TCTerminationException{
		switchToTransactionsFrame();
		String locatorStr = MESSAGETYPEOPTIONSTR.replace(PLACEHOLDER, option);
		By locator = By.xpath(locatorStr);
		common.click(locator, "Dropdown option [" + option + "]" , 5, true);
	}

	/**
	 * This function clicks the 'From' date/time and enters the value
	 * @param option
	 * @throws TCTerminationException
	 */
	public void enterFromDateTime(String option) throws TCTerminationException{
		switchToTransactionsFrame();
		clickFromDateDropDownButton();
		clickDropDownItem(option);
	}
	
	/**
	 * This function clicks the 'To' date/time and enters the value
	 * @param option
	 * @throws TCTerminationException
	 */
	public void enterToDateTime(String option) throws TCTerminationException{
		switchToTransactionsFrame();
		clickToDateDropDownButton();
		clickDropDownItem(option);
	}

	/**
	 * This function clicks the Message 
	 * @param option
	 * @throws TCTerminationException
	 */
	public void selectMessageType(String option) throws TCTerminationException{
		switchToTransactionsFrame();
		clickMessageTypeDropdownButton();
		clickMessageOption(option);
	}
	
	public void clickMessageTypeDropdownButton() throws TCTerminationException{
		switchToTransactionsFrame();
		common.click(MESSAGETYPEDROPDOWNBUTTON, "Message Type Dropdown button", 
				5, 
				true);
	}
	
	public void enterObjectId(String id) throws TCTerminationException{
		switchToTransactionsFrame();
		common.click(OBJECTIDTEXTFIELD, "Object ID text box", 5, true);
		
		common.sendKeys(OBJECTIDTEXTFIELD, id);
	}
	
	public void clickApplyButton() throws TCTerminationException, InterruptedException{
		switchToTransactionsFrame();
		Thread.sleep(8000);
		common.click(APPLYBUTTON, "Apply Button", 5, true);
		Thread.sleep(5000);
		common.waitForElement(ROWS, "Rows in 'Transactions' table", 5, true);
	}
	public void VerifyTransactionData() throws TCTerminationException, InterruptedException{
		switchToTransactionsFrame();
		Thread.sleep(2000);
		common.click(APPLYBUTTON, "Apply Button", 5, true);
		Thread.sleep(1000);
		common.waitForElement(ROWS, "Rows in 'Transactions' table", 5, true);
	}
	public void switchToTransactionsFrame() throws TCTerminationException{
		common.switchToFrame(TRANSACTIONSFRAME, "Transactions");
	}
	
	public List<WebElement> getRows() throws TCTerminationException{
		return common.getElements(ROWS, "Rows in 'Transactions' Table");
	}
	
	public Integer getRowSize() throws TCTerminationException{
		return getRows().size();
	}
	
	public void doubleClickFirstRow() throws TCTerminationException{
		switchToTransactionsFrame();
		By locator = By.xpath("//*[@id='dataForm:tranlogTable:0:tranlogId_Column_Param_Text']");
		common.doubleClick(locator, "First row in 'Transactions' Table", 5, true);
		
		common.waitForElement(RESPONSEXML, "Transaction Detail Screen", 10, true);
	}
	
	public String getResponseXml() throws TCTerminationException{
		switchToTransactionsFrame();
		WebElement el = common.getElement(RESPONSEXML, "Response XML", true);
		String text = el.getText();
		return text;
	}
}
