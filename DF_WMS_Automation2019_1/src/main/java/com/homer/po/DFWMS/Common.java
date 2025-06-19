package com.homer.po.DFWMS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.po.PageBase;


public class Common extends PageBase<Common> {
	
	public Common(InstanceContainer ic) {
		super(ic);
		
		
	}
	
	public final String FRAME = "//iframe[contains(@src,'" + PLACEHOLDER + "')]";

	/**
	 * This function compares 2 strings and reports whether or not the match passed or
	 * failed
	 * @param expected The value we expect to see
	 * @param actual The value we observe
	 * @param type The type of value. Used for reporting
	 * @param terminateOnFailure Test will terminate on mismatch if this is set to true
	 * @throws TCTerminationException 
	 */
	public void compareStrings(String expected, String actual, String type, Boolean terminateOnFailure) throws TCTerminationException{
		
		String description = "Validate " + type;
		
		try{
			
			if (expected.equals(actual)){
				rc.addReportStep( description, type + " is correct. "
						+ "Expected: [" + expected + "], Actual: [" + actual + "]", 
						StepResult.PASS);
			}else{
				throw new Exception(type + " doesn't match expected " + type + ". "
					+ "Expected: [" + expected + "], Actual: [" + actual + "]");
			}
		}catch(Exception e){
			rc.addReportStep(description, type + " validation fails. " + e.getMessage(), 
					StepResult.FAIL);
			if(terminateOnFailure){
				rc.throwTCTerminationException();
			}
		}
	}
	
	/**
	 * This function compares 2 Integers and reports whether or not the match passed or
	 * failed
	 * @param expected The value we expect to see
	 * @param actual The value we observe
	 * @param type The type of value. Used for reporting
	 * @param terminateOnFailure Test will terminate on mismatch if this is set to true
	 * @throws TCTerminationException 
	 */
	public void compareIntegers(Integer expected, Integer actual, String type, Boolean terminateOnFailure) throws TCTerminationException{
		
		String description = "Validate " + type;
		
		try{
			
			if (expected.equals(actual)){
				rc.addReportStep( description, type + " is correct. "
						+ "Expected: [" + expected + "], Actual: [" + actual + "]", 
						StepResult.PASS);
			}else{
				throw new Exception(type + " doesn't match expected " + type + ". "
					+ "Expected: [" + expected + "], Actual: [" + actual + "]");
			}
		}catch(Exception e){
			rc.addReportStep(description, type + " validation fails. " + e.getMessage(), 
					StepResult.FAIL);
			if(terminateOnFailure){
				rc.throwTCTerminationException();
			}
		}
	}

	/**
	 * This function clicks an element and handles exceptions.
	 * @param location Locator of the element
	 * @param description Name of the element. Used for reporting
	 * @param waitTime Length of time to wait for element
	 * @throws TCTerminationException
	 */
	public void click(By location, String description, Integer waitTime, Boolean terminateOnFailure) throws TCTerminationException{
		String stepName = "Click " + description;
		try{
			String msg = "Clicked " + description;
			waitForElement(location, description, waitTime, true);
			wh.clickElement(location);
			rc.addReportStep(stepName, msg, StepResult.PASS);
			rc.logger.info(msg);
		}catch(Exception e){
			String msg = "Unable to click " + description + ". " + e.getMessage();
			rc.logger.info(msg);
			rc.addReportStep(stepName, msg, StepResult.FAIL);	
			if (terminateOnFailure){			
				rc.throwTCTerminationException();
			}
			
		}
	}
	
	/**
	 * This function clicks an element and handles exceptions.
	 * @param location Locator of the element
	 * @param description Name of the element. Used for reporting
	 * @param waitTime Length of time to wait for element
	 * @throws TCTerminationException
	 */
	public void doubleClick(By location, String description, Integer waitTime, Boolean terminateOnFailure) throws TCTerminationException{
		String stepName = "Double Click " + description;
		try{
			String msg = "Double clicked " + description;
			waitForElement(location, description, waitTime, true);
			wh.doubleClickUsingAction(location);
			rc.addReportStep(stepName, msg, StepResult.PASS);
			rc.logger.info(msg);
		}catch(Exception e){
			String msg = "Unable to double click " + description + ". " + e.getMessage();
			rc.logger.info(msg);
			rc.addReportStep(stepName, msg, StepResult.FAIL);	
			if (terminateOnFailure){			
				rc.throwTCTerminationException();
			}
			
		}
	}
	public void sendKeys(By locator, String value) throws TCTerminationException{
		try{
			wh.sendKeys(locator, value);
		}catch(Exception e){
			report.addReportStep("Enter value [" + value + "] in text field", 
					"Unable to enter value [" + value + "] in text field. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		
	}
	/**
	 * This function waits for an element to appear
	 * @param location Location of element
	 * @param description Description/Name of element
	 * @param waitTime Amount of time to wait for element
	 * @param terminateOnFailure Tells whether or not to terminate if we can't find element
	 * @throws TCTerminationException
	 */
	public void waitForElement(By location, String description, Integer waitTime, Boolean terminateOnFailure) throws TCTerminationException{
		String stepName = "Wait for " + description;
		String failMsg = "Unable to find " + description + " in " + waitTime + " seconds.";
		try{
			
			if(!wh.isElementPresent(location, waitTime)){
				
				if (terminateOnFailure){
					throw new Exception(failMsg);
				}
				else{
					report.addReportStep(stepName, 
							failMsg,
							StepResult.WARNING);
				}
			}
		}catch(Exception e){
			report.addReportStep(stepName, 
					 e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	/**
	 * This function waits for an element to be visible or not (specific to EXT-JS). It waits for style values 
	 * in the element to be observed
	 * @param location Element location
	 * @param description Element name. Used for reporting
	 * @param waitTime Length of time to wait for element
	 * @param shouldBeVisible Tells us if element should be visible or not
	 * @throws TCTerminationException
	 */
	private void waitUntilElementVisible(final By location, String description, Integer waitTime, final Boolean shouldBeVisible, Boolean terminateOnFailure) throws TCTerminationException{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);

		String appearedOrDisappeared = "";
		String appearOrDisappear = "";

		if(shouldBeVisible){
			appearedOrDisappeared = "appeared";
			appearOrDisappear = "appear";
		}else{
			appearedOrDisappeared = "disappeared";
			appearOrDisappear = "disappear";
		}

		String stepName = "Wait for " + description +  " to " + appearOrDisappear +".";
		try{

//			wait.until(new ExpectedCondition<Boolean>(){
//				public Boolean apply(WebDriver driver){
//					WebElement el = driver.findElement(location);
//					String style = el.getAttribute("style");
//					Boolean visible = !(style.contains("display: none") || style.equals(""));
//					if (visible.equals(shouldBeVisible)){
//						return true;
//					}else{
//						return false;
//					}
//				}
//			});

			rc.addReportStep(stepName,
					description + " " + appearedOrDisappeared + " within " + waitTime + " seconds." ,
					StepResult.PASS);

		}catch(TimeoutException e){
			report.addReportStep(stepName,
					description + " did not " + appearOrDisappear + " within " + waitTime + " seconds. " + e.getMessage(),
					StepResult.FAIL);
		}catch(Exception e){
			report.addReportStep(stepName,
					"Encountered an issue with waiting for element: " + description + ". " + e.getMessage(),
					StepResult.FAIL);
			if (terminateOnFailure){
				rc.throwTCTerminationException();
			}
		}
	}

	/**
	 * This function waits for an element to be not visible (specific to EXT-JS). It waits for style values 
	 * in the element to be observed
	 * @param location Element location
	 * @param description Element name. Used for reporting
	 * @param waitTime Length of time to wait for element
	 * @param terminateOnFailure
	 * @throws TCTerminationException
	 */
	public void waitUntilElementNotVisible(final By location, String description, Integer waitTime, Boolean terminateOnFailure) throws TCTerminationException{
		waitUntilElementVisible(location, description, waitTime, false, terminateOnFailure);

	}
	/**
	 * This function waits for an element to be visible (specific to EXT-JS). It waits for style values 
	 * in the element to be observed
	 * @param location Element location
	 * @param description Element name. Used for reporting
	 * @param waitTime Length of time to wait for element
	 * @throws TCTerminationException
	 */
	public void waitUntilElementVisible(final By location, String description, Integer waitTime, Boolean terminateOnFailure) throws TCTerminationException{
		waitUntilElementVisible(location, description, waitTime, true, terminateOnFailure);

	}
	
	/**
	 * This function gets an element with the appropriate error handling
	 * @param locator
	 * @param description
	 * @return
	 * @throws TCTerminationException
	 */
	public WebElement getElement(By locator, String description, Boolean terminateOnFailure) throws TCTerminationException{
		WebElement el = null;
		try{
			el = wh.getElement(locator);
		}catch(Exception e){
			rc.addReportStep("Get Element: " + description, 
					"Unable to get element: " + description + ". " + e.getMessage(), 
					StepResult.FAIL);
			if(terminateOnFailure){
				rc.throwTCTerminationException();
			}
		}
		return el;
	}
	
	/**
	 * This function gets a list of elements with the appropriate error handling
	 * @param locator
	 * @param description
	 * @return
	 * @throws TCTerminationException
	 */
	public List <WebElement> getElements(By locator, String description) throws TCTerminationException{
		List <WebElement> els = null;
		try{
			els = wh.getElements(locator);
		}catch(Exception e){
			rc.addReportStep("Get Elements: " + description, 
					"Unable to get elements: " + description + ". " + e.getMessage(), 
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		return els;
	}
	
	/**
	 * This function switches to a frame
	 * @param src Value that SRC value should contain (i.e. 'Appointments.jsp')
	 * @param frameName Name of Frame. Used for reporting
	 * @throws TCTerminationException 
	 */
	public void switchToFrame(By frameLocation, String frameName) throws TCTerminationException{
		String description = "Bring up " + frameName + " Frame";
		try{
			driver.switchTo().defaultContent();
			
			if(!wh.isElementPresent(frameLocation)){
				throw new Exception("Unable to find '" + frameName + "' Frame.");
			}
			
		
			List <WebElement> frameList = getElements(frameLocation, frameName + " Frame");
			Integer onTopLocation = 0;
			Integer highestZIndex = 0;
			for(int i=0; i< frameList.size(); i++){
				
				WebElement container = frameList.get(i).findElement(By.xpath(".//ancestor::*[contains(@style, 'z-index')]"));
				String style = container.getAttribute("style");
			
				String beginIndexStr = "z-index: ";
				Integer beginIndex = style.indexOf(beginIndexStr) + beginIndexStr.length();
				Integer endIndex = style.indexOf(";", beginIndex);
				
				Integer zIndex = Integer.parseInt(style.substring(beginIndex, endIndex).trim());
				
				if (zIndex > highestZIndex){
					onTopLocation = i;
					highestZIndex = zIndex;
				}
			}
			
			driver.switchTo().frame(frameList.get(onTopLocation));
		}catch(Exception e){
			rc.addReportStep(description, "Unable to bring up '" + frameName + "' frame. " + e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
}
