package com.homer.resuablecomponents;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.DOMException;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.helper.DataTable;
import com.homer.helper.HelperClass;
import com.homer.po.DFWMS.TPELoginPageObject;
import com.homer.reports.Report;
import com.homer.uistore.CommonElements;
import com.homer.uistore.HomePageUI;
import com.homer.uistore.PLPUI;
import com.homer.util.DateUtil;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.json.XML;

public class ReusableComponents {

	Report report;
	WebDriver driver;
	WebDriverHelper wh;
	DataTable dataHelper;
	public boolean terminateTestOnElementNotPresent = true;
	public HashMap<String, Object> dataStore = new HashMap<String, Object>();
	
	public DataClass data;
	
	public ReusableComponents(WebDriver driver, Report report,
			WebDriverHelper wh, DataTable dataHelper, DataClass data) {

		this.report = report;
		this.driver = driver;
		this.dataHelper = dataHelper;
		this.wh = wh;
		this.data = data;
	}

	public ReusableComponents(WebDriver driver, Report report,
			WebDriverHelper wh, DataTable dataHelper) {

		this.report = report;
		this.driver = driver;
		this.dataHelper = dataHelper;
		this.wh = wh;
	}
	
	/**
	 * Method to open HomePage
	 * @return
	 * @throws Exception
	 */
	public void openHomeDepotAppln() throws Exception {
		
		String envUrl = dataHelper
				.getCommonData(CommonDataColumn.EnvironmentUrl);

		try {

			driver.manage().deleteAllCookies();
			driver.get(envUrl);
			driver.manage().window().maximize();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			wh.handleAlert();
		}
		
		if (isConsumerHomePage()) {
			report.addReportStep("Open browser and enter HomeDepot URL",
					"Home page displayed successfully", StepResult.PASS);
		} else {
			report.addReportStep("Open browser and enter HomeDepot URL",
					"Home page was not displayed successfully", StepResult.FAIL);
		}
	}
	
	/**
	 * Method to verify Consumer Home Page
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isConsumerHomePage() throws Exception {

		if (wh.isElementPresent(HomePageUI.verifyHomepage, 3) || 
				wh.isElementPresent(HomePageUI.verifyHomepage1, 3)) {

			return true;
		} else {

			return false;
		}
	}

	/**
	 * Method to search keyword
	 * @throws Exception
	 */
	public void searchKeyword() throws Exception {
		
		String keyword = dataHelper.getData(DataColumn.Keyword);

		wh.sendKeys(CommonElements.searchTxtBox, keyword);
		wh.clickElement(CommonElements.searchBtn);

		if (wh.isElementPresent(PLPUI.VerifyPLPPage, 4)
				&& wh.getText(CommonElements.breadCumb).contains(
						keyword)) {

			report.addReportStep("Type '" + keyword
					+ "' in search text box and click on search button",
					"User navigated to search plp page.", StepResult.PASS);
		} else {

			report.addReportStep("Type '" + keyword
					+ "' in search text box and click on search button",
					"User is not navigated to search plp page.",
					StepResult.FAIL);

			terminateTestCase("search plp page");
		}
		
	}
	
		/**
	 * Method to throw custom exception to terminate test case
	 * 
	 * @throws Exception
	 */
	public void throwCustomException() throws Exception {

		throw new Exception("Custom Error");
	}

	/**
	 * Method to terminate test case.
	 * 
	 * @param pageName
	 * @throws Exception
	 */
	public void terminateTestCase(String pageName) throws Exception {

		if (terminateTestOnElementNotPresent) {

			report.addCustomErrorStep("Terminating test case",
					"Terminating test case as " + pageName
							+ " is not displayed");

			throwCustomException();
		}
	}
	
	/**
	 * Method to get analytic value
	 * @param analyticsProperty
	 * @return
	 */
	public String getAnalyticsValue(String analyticsProperty) {          
		   	
	     String analyticsObject= "_hddata";
	     Object propertyValue;
	     String jsProperty = analyticsObject + "."
	              + analyticsProperty + ";";
	
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     propertyValue = js.executeScript("return " + jsProperty);
	
	    // System.out.println("Property returned is : " + propertyValue);
	     if (propertyValue != null)
	        return propertyValue.toString().trim();
	     else
	        return "null";
	}
	
	/**
	 * Method to check if environment is Prod
	 * 
	 * @return true if Prod Environment else false
	 */
	public boolean isProdEnvironment() {

		if (driver.getCurrentUrl().contains("www.homedepot.com")
				|| dataHelper.getCommonData(CommonDataColumn.EnvironmentUrl)
						.contains("www.homedepot.com")
				|| HelperClass.baseModel.runEnvironment.equals("Prod")) {

			return true;

		} else {

			return false;
		}
	}
	
	public String getXMLResponse(String getEndPointURL){
		
		String strResponse = null;
		try{
			
		    URL obj = new URL(getEndPointURL);
		    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		    
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            System.out.println("RESPONSE CODE : " + responseCode);
            BufferedReader in;
            if (responseCode != 200)
                   in = new BufferedReader(new InputStreamReader(
                                 con.getErrorStream()));
            else
                   in = new BufferedReader(new InputStreamReader(
                                 con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                   response.append(inputLine);
            }
            in.close();
            System.out.println("RESPONSE: "+response);
            strResponse = response.toString();
            System.out.println("Json Response:" +strResponse);
            
            String xml = null;
            try {
            	JSONObject json = new JSONObject(strResponse);
				xml= XML.toString(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

            System.out.println("XML Response: "+xml);
			
		}catch (FileNotFoundException e) {
		    System.out.println(e.getCause());
		    System.out.println("EXCEPTION EXPLANATION:");
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		} catch (MalformedURLException e) {
		    System.out.println(e.getCause());
		    System.out.println("EXCEPTION EXPLANATION:");
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		} catch (ProtocolException e) {
		    System.out.println(e.getCause());
		    System.out.println("EXCEPTION EXPLANATION:");
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		}  catch (DOMException e) {
		    System.out.println(e.getCause());
		    System.out.println("EXCEPTION EXPLANATION:");
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		} catch (IOException e) {
		    System.out.println(e.getCause());
		    System.out.println("EXCEPTION EXPLANATION:");
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		} 
		return strResponse;
	}
	
	public void addReportStep(String description, String actualResult,StepResult stepResult) {
		
		boolean captureScreenShot = false;
		String fileName = "";
		
		if(stepResult == StepResult.PASS && HelperClass.baseModel.runPassScreenshots) {
			
			captureScreenShot= true;			
			
		} else if(HelperClass.baseModel.runFailScreenshots && (stepResult==StepResult.FAIL ||
				stepResult==StepResult.FAIL_ENV || stepResult==StepResult.FAIL_FEATURE ||
				stepResult==StepResult.FAIL_TESTDATA)) {
			
			captureScreenShot= true;
		}
		
		if(captureScreenShot) {
			
			fileName = takeScreenShot();
			report.addReportLinkScreenshot(description, actualResult, stepResult, fileName);
			
		} else {
			
			report.addReportStepNoScreenshots(description, actualResult, stepResult);
		}
	}

	private String takeScreenShot() {
		
		String formattedDate = DateUtil.getFormattedDateTime();
		String screenShotFilePath = "";
		String testCaseName = data.testCaseName.replace(" ", "_");

		String stepDateForScreenShot;
		int maxTCNameLength;
		int maxScreenShotNameLength = 50;

		stepDateForScreenShot = "_Step-No_" +data.stepNo +"_"+ formattedDate + ".png";

		maxTCNameLength = maxScreenShotNameLength
				- stepDateForScreenShot.length();

		if (testCaseName.length() > maxTCNameLength) {

			testCaseName = testCaseName.substring(0, maxTCNameLength);
		}

		screenShotFilePath = "./ScreenShots/" + testCaseName
				+ stepDateForScreenShot;

		String screnShotSavePath = "";

		screnShotSavePath = data.screenShotFolder + "/" + testCaseName
				+ stepDateForScreenShot;

		saveScreenShot(screnShotSavePath);

		return screenShotFilePath;
	}

	private void saveScreenShot(String screnShotSavePath) {
		
        try{
        	
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", new File(screnShotSavePath));
        }
        catch(Exception e){
           System.out.println("Unable to capture Screenshot--"+e); 
        }
	}
	
	/**
	 * Method to Inject AJAX Script in  WebDriver.
	 * 
	 * @param endPointURL
	 * @param JSONRequest
	 * @param requestType (POST/GET,etc.,)
	 * @param destinationPageURL
	 * @return status
	 */
	public String executeAJAXScript(String endPointURL,String JSONRequest,String requestType,String destinationPageURL){
		
		Object status = null;
		
		//Setting java script executor time out
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		//Building AJAX Script based on endPointURL,JSONRequest and RequestType - can be customized based on needs
		String script ="var callback = arguments[arguments.length - 1];" +
				"$.ajax({"+
				"url:\'"+endPointURL+"\',"+
				"type: \'"+requestType+"\',"+
				"dataType:'json',"+
				"contentType:'application/json; charset=UTF-8',"+
				"data:"+JSONRequest+","+
				"xhrFields: {"+
					"withCredentials: true"+
				"},"+
				"success: function(data){"+
					"callback(data);"+
				    "},"+
				"error: function(data) {"+
					"callback(data);"+
				    "},"+
				"dataType: 'json'"+
				"});";

		try{
			//Execute asynchronous JavaScript 
			status = ((JavascriptExecutor)driver).executeAsyncScript(script);
			
			Thread.sleep(3000);
			
			//Get the Destination Page URL
			driver.get(destinationPageURL);
			
			wh.waitForPageLoaded();
		}
		catch(Exception ex){
			report.addReportStep("Execute Ajax script", "Exeception occured while executing script", StepResult.FAIL);
		}
		
		//Return the Response from the API. null will be returned if any exceptions.
		return status.toString();

	}
	
	public void terminateTestCase(String pageName,StepResult stepResult) throws Exception, TCTerminationException {

		
		if (terminateTestOnElementNotPresent) {

			report.addCustomErrorStep("Terminating test case",
					"Terminating test case as " + pageName
							+ " is not displayed",stepResult);
			
			throwTCTerminationException();
			
		}
	}
	/**
	 * Method to throw custom exception to terminate test case
	 * 
	 * @throws Exception
	 * @throws TCTerminationException 
	 */
	public void throwTCTerminationException() throws TCTerminationException {

		throw new TCTerminationException("TC Termination Exception");
	}
	
	
	/**
	 * Method to create log file under current test case report folder
	 * 	
	 *//*
	public void logWrite() throws Exception{
		//BufferedWriter writer = null;
		try {
			//create a temporary file
			String timeLog = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			File logFile = new File(timeLog);

			String logPath=this.dataHelper.currentTestCase.reportFolder;
			logPath=logPath+logFile;
			// This will output the full path where the file will be written to...
			System.out.println(logFile.getCanonicalPath());

			//writer = new BufferedWriter(new FileWriter(logPath));
			//writer = logWriter(logtext, logPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}	
	}

	public void logWriter(String logtext) throws Exception {
		BufferedWriter writer = null;
		try{
		
		String logPath=this.dataHelper.currentTestCase.reportFolder;
		writer = new BufferedWriter(new FileWriter(logPath,true));		
		writer.write(logtext);
		//return writer;
		}catch(Exception es){
			es.printStackTrace();
		}finally{
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
			
		}
		
	}*/
	
	/**
	 * log4j component
	 */
	public final static Logger logger = Logger.getLogger(ReusableComponents.class);
	
	
}




