package com.homer.po.DFWMS;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.sikuli.basics.Settings;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.glue.DFWMS.DFWMSLTLOutboundFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;
import com.ibm.disthub2.impl.matching.DisthubValueAccessor.Action;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DFWMSRunWavesPageObject extends PageBase {

	protected DFWMSHomePageObject2019 dfwmsHomePageObject2019;

	public static String decisionPoint;
	public DFWMSRunWavesPageObject(InstanceContainer ic) {
		super(ic);
		// TODO Auto-generated constructor stub
	}

	public static String ToteValue="";
	
	//public static String DOQty="";
	public static String OrderIdVAR="";
	
	public static String ItemQty="";

	public static String itemValue="";

	public Session session;

	JDBC_Connection jd = new JDBC_Connection(ic);
	String randomNumber;
	static final By RunWaveDesc = By
			.xpath(".//input[contains(@alt, 'Find Description')]");
	static final By RunWaveApply = By
			.xpath(".//input[contains(@title, 'Apply') and contains(@value,'Apply') and contains(@name, 'filterIdapply')]");
	static final By RunWaveBtn = By
			.xpath(".//INPUT[contains(@id,'rmButton_1RunWave1')]");
	static final By RunWaveSubmitBtn = By
			.xpath(".//INPUT[contains(@id,'rmButton_1SubmitWave1')]");
	static final By transactionNmae = By
			.xpath("//input[@alt='Find Transaction Name']");
	static final By LoadTrailerOption = By
			.xpath("//tr[@class='advtbl_row -dg_tr']//td[@class='tbl_checkBox advtbl_col advtbl_body_col']//input[@value='0']");
	static final By ParameterTab = By
			.xpath("//div//span//a[@name='parameterTab']");
	static final By MaxLengthTxt = By
			.xpath("(//div//span[text()='Max LPN Scan Limit'])[3]");
	static final By RuleCheckbox = By
			.xpath(".//input[contains(@id,'ruleHdrUseStatusCheckbox')]");
	static final By DOParamInput = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:0:ruleSelDtlRuleCmparValue']");
	static final By DOParamInput7 = By
			.xpath("//input[@id='dataForm:ruleSelDtlDataTable:newRow_1:ruleSelDtlRuleCmparValue']");
	static final By DOAddBtn = By
			.xpath("//input[@id='dataForm:ruleSelAddButton']");
	static final By DOParamInput1 = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:5:ruleSelDtlRuleCmparValue']");
	static final By DOParamInput2 = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:2:ruleSelDtlRuleCmparValue']");
	static final By DOParamInput3 = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:7:ruleSelDtlRuleCmparValue']");
	static final By DOParamInput4 = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:4:ruleSelDtlRuleCmparValue']");
	static final By DOParamInput5 = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:6:ruleSelDtlRuleCmparValue']");
	static final By DOParamInput6 = By
			.xpath(".//INPUT[@id='dataForm:ruleSelDtlDataTable:3:ruleSelDtlRuleCmparValue']");

	static final By CopyWaveDesc = By.xpath(".//input[contains(@type, 'text') and contains(@id,'listView:filterId')]");
	static final By CopyWave = By.xpath(".//input[contains(@type, 'button') and contains(@value,'Copy')]");
	static final By Desc = By.xpath(".//input[contains(@type, 'text') and contains(@tabindex,'135')]");
	static final By CopyWaveSave = By.xpath(".//input[contains(@type, 'button') and contains(@value,'Save') and contains(@tabindex,'143')]");


	static final By WaveNumber = By.xpath(".//a[@id='dataForm:AwvNbrRun']");
	static final By OlpnTxt = By.xpath("//input[@alt='Find oLPN']");
	static final By CancelBtn = By.xpath("//input[@name='dataForm:LPNListInboundMain_commandbutton_CanceloLPN']");
	static final By OlpnCheckBox = By.xpath("//input[@name='dataForm:listView:dataTable_checkAll']");
	static final By WaveNumberInput = By.xpath(".//INPUT[@alt='Find Ship Wave number']");
	static final By WaveNumberApply = By.xpath(".//INPUT[@id='dataForm:listView:filterId:filterIdapply']");
	static final By WaveStatus = By.xpath(".//span[@id='dataForm:listView:dataTable:0:c0012']");
	static final By WaveSelect = By.xpath(".//input[@id='checkAll_c0_dataForm:listView:dataTable']");
	static final By WaveView = By.xpath(".//input[contains(@id,'rmButton_2View1')]");
	static final By WaveMore = By.xpath(".//input[contains(@id,'rmbuttons_1moreButton')]");
	static final By UndoWave = By.xpath(".//input[contains(@id,'rmButton_1UndoWave1')]");
	final By remoteuserName = By.id("user_pass_form_username_field");
	final By remotePassword = By.id("user_pass_form_password_field");
	final By GO = By.xpath("//span[text()='Continue']");
	final By AuthMethodBtn = By.xpath("(//div[@class='AuthMethodButton AuthMethodBtnNormal']//div//img)[3]");
	final By remoteSignIn = By.xpath("//span[text()='Sign In']");
	final By secureConnectBtn = By.xpath("//button[text()='Secure Connect']");
	final By address = By.xpath("//input[@id='txtAddress']");
	static final By RemoteMachineName= By.xpath("//input[@placeholder='Enter or select a remote machine']");
	final By pimUserName = By.xpath("//input[@id='txtUser']");
	final By pimPassword = By.xpath("//input[@id='txtPass']");
	final By connect = By.xpath("//button[text()='Connect']");
	static final By connectRemote= By.xpath("//button//div//div[text()=' Connect ']");
	static final By SearchAccountText=By.xpath("//input[@aria-label='Search for accounts']");
	//WynSoft
	static final By olpnManagement = By.xpath("//div[text()='oLPN Management']");
	static final By searchBtn = By.xpath("(//div[@class='flyout-button'])[1]");
	protected static By orderTxt = By.xpath("//div//a//span[text()='Orders']");
	protected static By task = By.xpath("//div//a//span[text()='Tasks']");
	protected static By container = By.xpath("//div//a//span[text()='Containers']");
	protected static By Routing = By.xpath("//div//a//span[text()='Routing']");
	protected static By Destination = By.xpath("//div//span[text()='Destinations']");
	protected static By statusTxt = By.xpath("//td//div//span[text()='Ready To Release']");
	protected static By containerTxt = By.xpath("//td//div//span[text()='CREATED']");
	protected static By shippedTxt = By.xpath("//td//div//span[text()='SHIPPED']");
	protected static By closeBtnWES=By.xpath("//i[@class='h-icon global close i-clear-search']");
	protected static By Tasktext = By.xpath("//td//div//span[text()='Planned']");
	protected static By TaskCompleted = By.xpath("//td//div//span[text()='Completed']");
	protected static By TaskRelease= By.xpath("//td//div//span[text()='Released']");
	protected static By orderGettext = By.xpath("//td//div//span[text()='Ready To Release']");
	protected static By enterOrderNumber = By.xpath("//input[@placeholder='Search For']");
	protected static By searchOrderBtn = By.xpath("//div//i[@class='sc-bdVaJa isyuQd h-icon common search-icon']");
	protected static By applyWESBtn = By.xpath("//td[@class='p-selection-column']//div//span[@class='p-checkbox-icon p-clickable']");
	protected static By releaseWESBtn = By.xpath("//div[text()='Release']");
	protected static By releaseCheckBox = By.xpath("//div//label[text()='I understand that this cannot be undone.']");
	protected static By releaseBtnAlert = By.xpath("//button//div[text()='Release']");
	static final By enterWavenumber = By.xpath("//input[@id='waveNumber']");
	static final By applyBtn = By.xpath("//input[@value='Apply']");
	static final By applyBtnCheckBox = By.xpath("/html/body/div/section/div[2]/div[2]/div/div[1]/div/div/div[1]/div/section[2]/div/div/div[2]/div[2]/div[1]/table/tbody/tr/td[1]/div/div[2]");
	static final By applyBtnCheckBox1 = By.xpath("//*[@id='i-display-panel-content']/div/div/div[1]/div/section[2]/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/div/div[2]");
	static final By applyBtnCheckBox2 = By.xpath("//*[@id='i-display-panel-content']/div/div/div[1]/div/section[2]/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/div/div[2]");
	static final By workAssignment = By.xpath("//table[@id='oLPN-0']//tbody//tr[@data-expandablerowcontrollername='LPNManagement']//td[15]//div");
	static final By waveNumbertxt = By.xpath("//table[@id='oLPN-0']//tbody//tr[@data-expandablerowcontrollername='LPNManagement']//td[17]//div");
	static final By cartStart = By.xpath("//div[text()='Cart Start']");
	static final By enterWorkAssignment = By.xpath("//input[@id='workAssignment']");
	static final By printShippingLabel = By.xpath("//input[@value='Print Shipping Label'][@alt='Print Shipping Label']");
	static final By homBtn = By.xpath("//div//a[@class='home-button']");
	static final By selectWorkAssignmentNumber = By.xpath("//tr[@class='WorkAssignment']//td[1]//span");
	static final By releaseBtn = By.xpath("//input[@id='release-workassignments']");
	static final By releaseBtnWES = By.xpath("//div[text()='Release']");	
	static final By okBtn = By.xpath("//div[@class='jconfirm-buttons']//button[text()='OK']");
	static final By ContinueBtn = By.xpath("//span[text()='Connect']");
	static final By yesBtn = By.xpath("//div[@class='jconfirm-buttons']//button[text()='YES']");
	static final By noBtn = By.xpath("//div[@class='jconfirm-buttons']//button[text()='NO']");
	static final By failedToPrint = By.xpath("//div[@class='jconfirm-content-pane']//div");
	static final By searchIcon = By.xpath("//button[@href='#/search']");
	static final By searchBtnPackSize = By.xpath("//button[@id='searchButton']");
	static final By releaseText = By.xpath("//div//ul[@class='overlayerrorList']//li[contains(text(),'Selected Tasks were successfully released')]");
	static final By i_Icon=By.xpath("/html/body/table/tbody/tr/td[2]/input[@id='tb_567']");

	static final By WaveMoreTasks = By.xpath(".//*[contains(@id,'rmButton_2Tasks1')]");
	static final By WaveMoreLpns = By.xpath(".//*[contains(@id,'rmButton_2LPNs1')]");
	static final By WaveViewCancel = By.xpath(".//input[contains(@id,'rmButton_2Cancel1')]");

	static final By DOClose = By.xpath(".//img[contains(@id,'tool') and contains(@class,'collapse-left')]");
	static final By DOSelectAll = By.xpath("(.//div//span[contains(@data-ref,'textEl') and contains(@class,'x-column-header-text')])[1]");	
	static final By DOSelect = By.xpath(".//div[contains(@role,'presentation') and contains(@class,'x-grid-row-checker')]");
	static final By DOSelect1 = By.xpath("(.//div[contains(@role,'presentation') and contains(@class,'x-grid-row-checker')])[1]");
	static final By DOMore = By.xpath(".//span[contains(text(),'More') and contains(@id,'button')]");
	static final By DOUndoWave = By.xpath(".//span[contains(text(),'Undo Wave') and contains(@id,'menuitem')]");
	static final By DOWave = By.xpath("(.//span[contains(text(),'Wave') and contains(@id,'menuitem')])[1]");	
	static final By DOAlert = By.xpath(".//span[contains(text(),'Yes') and contains(@id,'button')]");
	static final By OrderLinesSelected = By.xpath(".//span[@id='dataForm:OrderLinesSelected']");
	static final By UnitsRequired = By.xpath(".//span[@id='dataForm:UnitsRequired']");
	static final By UnitsAllocated = By.xpath(".//span[@id='dataForm:UnitsAllocated']");
	static final By UnitsShorted = By.xpath(".//span[@id='dataForm:UnitsShorted']");
	static final By Tasks = By.xpath(".//span[@id='dataForm:Tasks']");
	static final By OLPNsSelected = By.xpath(".//span[@id='dataForm:OLPNsSelected']");	
	static final By UndoWaeInWaveUI = By.xpath("//div[@id='dataForm:listView:dataTable_bodyDiv']//table//tbody//tr//td[1]//input[@value='0' and contains(@id,'checkAll_c0_dataForm:listView:dataTable')]");	
	static final By UndoWaeBtn = By.xpath("//input[@value='Undo Wave']");	
	//static final By MoveBtn= By.xpath("//input[@value='More â–¼']");
	static final By MoveBtn= By.xpath("//input[@value='More ▼']");
	static final By TaskReleaseBtn= By.xpath("//a[@title='Tasks']//span[text()='Tasks']");
	static final By releaseTaskCheckBox= By.xpath("//input[@name='dataForm:lview:dataTable_checkAll']");
	static final By releaseTaskBtn =By.xpath("//input[@value='Release Task']");
	static final By TaskId= By.xpath("//td//span[@id='dataForm:lview:dataTable:0:taskIdVal']");
	public static String sWaveNumber = "" ;
	public static int sTasksVal = 30; //Standard Max Task Number
	public static List workAsmt = new ArrayList<>();
	public static String TaskIdText;
	public static String routingText;
	/**
	 * Method to validate WMS DO
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchWaveCheckBox(String WaveDesc) throws Exception {
		By WaveDescElement;
		if(WaveDesc.equalsIgnoreCase("BK1  - Non Ship Ready")){
			WaveDescElement = By.xpath("(.//SPAN[contains(text(),'" + WaveDesc
					+ "')])[2]");
		}else{
			WaveDescElement = By.xpath("(.//SPAN[contains(text(),'" + WaveDesc
					+ "')])[1]");
		}

		By table = By.xpath("(.//div[contains(@id,'dataForm:listView:dataTable_scrollDiv')])[1]");
		scrollPage(table);
		// System.out.println(wh.getAttribute(WaveDescElement, "id"));
		String RunWaveCheckBoxInput = wh.getAttribute(WaveDescElement, "id");
		RunWaveCheckBoxInput = RunWaveCheckBoxInput.replace(
				"dataForm:listView:dataTable:", "");
		RunWaveCheckBoxInput = RunWaveCheckBoxInput.replace(":wvdesc", "");
		return (RunWaveCheckBoxInput);

	}

	public void unDoWaveInWaveUI() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		driver.findElement(UndoWaeInWaveUI).click();
		driver.findElement(UndoWaeBtn).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		closebtn();
		report.addReportStep("Undo Wave ",
				"Successfully Undo Wave  "+sWaveNumber+" completed",
				StepResult.PASS);


	}

	public void unDoWaveInWaveUIPacked() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		driver.findElement(UndoWaeInWaveUI).click();
		driver.findElement(UndoWaeBtn).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		closebtn();
		report.addReportStep("Undo Wave ",
				"Successfully Undo Wave  "+sWaveNumber+" completed",
				StepResult.PASS);


	}


	public void voicePicking(String screen) throws ClassNotFoundException, InterruptedException, IOException {
		// TODO Auto-generated method stub


		if(screen.equalsIgnoreCase("VoicePicking")) {

			String str = "";
			String str1 = "";
			Map item = new LinkedHashMap(); 
			Map item1 = new LinkedHashMap(); 


			try {



				String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());//TMMDDYYHHmm

				String query=" SELECT TGE.TASK_GRP FROM TASK_HDR TH JOIN TASK_GRP_ELGBLTY TGE ON TGE.TASK_TYPE= TH.TASK_TYPE WHERE TGE.WORK_AREA= TH.START_CURR_WORK_AREA AND TH.TASK_GENRTN_REF_NBR LIKE '%"+sWaveNumber+"%' AND TH.STAT_CODE= '10'";

				System.out.println(query);

				String taskGrp = jd.str_Database_Connection(query);

				System.out.println("Task Group : "+taskGrp);

				Map item_root = new LinkedHashMap();
				Map item_root1 = new LinkedHashMap();

				if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")){
					item_root.put("env","LG");
					item_root1.put("env","LG");

				}



				item_root.put("key","voice_picking");
				item_root.put("testcase_name","voice_picking");

				item_root1.put("key","voice_picking_step5");
				item_root1.put("testcase_name","voice_picking_step5");

				Thread.sleep(3000);
				item.put("binpath", "cd /opt/hd/dc/apps/scope/wmos/wms/cbs/bin");

				Thread.sleep(3000);
				item.put("step1", "PkVocollectC -m \"prTaskLUTCoreSignOn ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"', '"+DFWMSLoginPageObject.currentPassword+"')\"");

				Thread.sleep(3000);
				item.put("step2", "PkVocollectC -m \"prTaskLUTCoreValidFunctions ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"', '1')\"");
				item.put("step3", "PkVocollectC -m \"prTaskLUTRegionPermissionsForWorkType ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"', '11')\"");
				item.put("step4", "PkVocollectC -m \"prTaskLUTPickingRegion ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"','"+taskGrp+"', '11')\"");
				item.put("step5", "PkVocollectC -m \"prTaskLUTGetAssignment ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"','6', '0')\"");
				item.put("step6", "PkVocollectC -m \"prTaskLUTCoreSignOff ('11-22-23 08:00:00', '"+sWaveNumber+"', 'DFCAUTO2')\"");


				item_root.put("input_params",item);



				JSONObject json = new JSONObject(item_root);

				str = json.toJSONString();
				System.out.println(str);

				//StringBuilder sc = new StringBuilder();
				// URL url = new URL("http://wn3c3a:4000/executeTestcase");
				//String port = "";
				jd.dbDFWMSMapping();

//				URL url = new URL("http://wn3c3a:"+DFWMSLoginPageObject.port+"/executeTestcase");
//				URLConnection conn = url.openConnection();
//				conn.setDoOutput(true);
//				conn.setReadTimeout(15000);
//				conn.setConnectTimeout(15000);
//				conn.setRequestProperty("Content-Type", "application/json");
//				// stores server response.
//				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//				writer.write(str);
//				Thread.sleep(10000);
//				writer.flush();
//				String line2;
//				// reads line by line
//				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//				Thread.sleep(2000);
//				while ((line2 = reader.readLine()) != null) {
//					sc.append(line2);
//					Thread.sleep(3000);
//					Thread.sleep(5000);
//				}
//				
				puttyCall(str, DFWMSLoginPageObject.port);
				
//				Thread.sleep(3000);
//
//				item1.put("binpath", "cd /opt/hd/dc/apps/scope/wmos/wms/cbs/bin");
//
//				
//				Thread.sleep(3000);
//
//				item1.put("step1", "PkVocollectC -m \"prTaskLUTCoreSignOn ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"', '"+DFWMSLoginPageObject.currentPassword+"')\"");
//
//				item1.put("step4", "PkVocollectC -m \"prTaskLUTPickingRegion ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"','"+taskGrp+"', '11')\"");
//
//				item1.put("step5", "PkVocollectC -m \"prTaskLUTGetAssignment ('11-22-23 08:00:00', '"+sWaveNumber+"', '"+DFWMSLoginPageObject.currentUsername+"','6', '0')\"");
//
//
//				item_root1.put("input_params",item1);
//
//
//
//				JSONObject json1 = new JSONObject(item_root1);
//
//				str1 = json1.toJSONString();
//				System.out.println(str1);
//
//				Thread.sleep(5000);
//				
//				puttyCall(str1, DFWMSLoginPageObject.port);


				System.out.println("Putty Operations performed Successfully for Inbound Receive By pallet :"+str);
				report.addReportStep("Putty Operations", "Putty Operations performed Successfully:"+str, StepResult.PASS);
			}catch (Exception e) {
				String msg = "Message not posted. " + e.getMessage();
				report.addReportStep("Putty Operations failed", str, StepResult.PASS);
				System.out.println("Result : "+msg);
				//rc.logger.info(msg);
				//report.addReportStep("Post Message", msg, StepResult.FAIL);
				//throw new Exception("Putty Operations are not performed for Inbound Receive");
			}

		}			
	}


	public void taskPickingRF(String screen) throws InterruptedException, TCTerminationException {
		// TODO Auto-generated method stub
		try {


			if(screen.equalsIgnoreCase("WCS_2019")||screen.equalsIgnoreCase("VoicePicking")) {

				itemValue=DFWMSLTLOutboundFlowStepDefn.itemName;
				
				OrderIdVAR = DFWMSPOObject.DOOrderId;
				//orderIdValue=DFWMSLTLOutboundFlowStepDefn.orderId;
				
				

				wh.clickElement(Maximize);
				//

				driver.switchTo().frame(0);

				Thread.sleep(2000);


				int rand_int2 = ThreadLocalRandom.current().nextInt();


				String tote="T0"+String.valueOf(rand_int2);

				ToteValue = tote.replaceAll("[-+.^:,]","");
				
				if(ToteValue.length()>7) {

					ToteValue = ToteValue.substring(0, 7);

				}


				System.out.println("Tote Id generated is " +ToteValue);


				Thread.sleep(5000);

				driver.findElement(i_Icon).click();

				Thread.sleep(2000);


				driver.findElement(By.xpath("//span[@value='Chg Task Grp']")).click();

				Thread.sleep(2000);
				driver.findElement(By.id("dataForm:taskGrp")).sendKeys("ALL");

				Thread.sleep(2000);
				driver.findElement(By.id("location_Input")).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				driver.findElement(By.id("dataForm:INT")).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				driver.findElement(By.id("tb_567")).click();

				Thread.sleep(2000);

				//String itemConvey = jd.str_Database_Connection("SELECT DISTINCT IFW.MISC_SHORT_ALPHA_1  FROM PICK_LOCN_HDR PLH,PICK_LOCN_DTL PLD,ITEM_CBO IC, ITEM_WMS IW,LOCN_HDR LH, ITEM_FACILITY_MAPPING_WMS IFW, WM_INVENTORY WI WHERE PLH.LOCN_ID = PLD.LOCN_ID AND PLD.LOCN_ID = LH.LOCN_ID AND PLD.ITEM_ID = IW.ITEM_ID AND IW.ITEM_ID = IC.ITEM_ID AND LH.LOCN_ID  = WI.LOCATION_ID AND IC.ITEM_NAME ='"+itemValue+"'  and ifw.misc_short_alpha_1='PS'");
				String itemConvey1 =jd.str_Database_Connection("select item_id from ITEM_MASTER where SKU_BRCD='"+itemValue+"'");
				String itemConvey =jd.str_Database_Connection("select misc_short_alpha_1 from ITEM_FACILITY_MAPPING_WMS where ITEM_ID ='"+itemConvey1+"'");
				
				
				
				System.out.println("OrderId is");
				System.out.println(OrderIdVAR);
				ItemQty = jd.str_Database_Connection("select total_nbr_of_units from orders where tc_order_id ='"+OrderIdVAR+"'");
				System.out.println(ItemQty);
				
				
				if(itemConvey.equalsIgnoreCase("TOT")) {

					driver.findElement(By.xpath("//span[@value='Enter Task']")).click();
					//TaskIdText

					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='dataForm:input1']")).sendKeys(TaskIdText);

					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(Keys.ENTER);
					
					String	 locn1 = driver.findElement(By.xpath("//span[@id='dataForm:delLocn']")).getText();
					
					String query="select locn_brcd from locn_hdr where dsp_locn in ('"+locn1+"')";

					String barcode = jd.str_Database_Connection(query);
					
					Thread.sleep(5000);
					driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(barcode,Keys.ENTER);
					
					Thread.sleep(2000);
					driver.findElement(By.id("itemId1Brcd")).sendKeys(DFWMSLTLOutboundFlowStepDefn.itemName,Keys.ENTER);
					
					
					
					Thread.sleep(2000);
					
					//DOOrderId
					//driver.findElement(By.xpath("//input[@id='input1qty221']")).clear();
					driver.findElement(By.xpath("//input[@id='input1qty221']")).sendKeys(ItemQty,Keys.ENTER);

					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(ToteValue,Keys.ENTER);


					//					if(wh.isElementPresent(By.xpath("//span[@id='dataForm:delLocn']"))) {
					//						String	 locn1 = driver.findElement(By.xpath("//span[@id='dataForm:delLocn']")).getText();
					//
					//
					//						String query="select locn_brcd from locn_hdr where dsp_locn in ('"+locn1+"')";
					//
					//						String barcode = jd.str_Database_Connection(query);
					//
					//						Thread.sleep(5000);
					//						driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(barcode,Keys.ENTER);
					//
					//	
					//					}

					

					

					//Thread.sleep(2000);
					//driver.findElement(By.xpath("//input[@id='input1qty221']")).sendKeys("2",Keys.ENTER);

					
					closebtn();
					report.addReportStep("Task picking completed",
							"Task picking completed Successfully :   "+TaskIdText+"",
							StepResult.PASS);


					//					String	 locn2 = driver.findElement(By.xpath("//span[@id='dataForm:delLocn']")).getText();
					//
					//
					//					String query1="select locn_brcd from locn_hdr where dsp_locn in ('"+locn2+"')";
					//
					//					String barcode1 = jd.str_Database_Connection(query1);
					//
					//					Thread.sleep(5000);
					//					driver.findElement(By.xpath("//input[@id='barcode4']")).sendKeys(barcode1,Keys.ENTER);


				}else {



					int rand_int3 = ThreadLocalRandom.current().nextInt();


					String slap="S000"+String.valueOf(rand_int3);

					String SlapperValue = slap.replaceAll("[-+.^:,]","");

					if(SlapperValue.length()>11) {

						SlapperValue = SlapperValue.substring(0, 11);

					}

					System.out.println("SlapperValue generated is " +SlapperValue);


					driver.findElement(By.xpath("//span[@value='Enter Task']")).click();
					//TaskIdText

					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='dataForm:input1']")).sendKeys(TaskIdText);

					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(Keys.ENTER);


					String	 locn1 = driver.findElement(By.xpath("//span[@id='dataForm:delLocn']")).getText();
					String query="select locn_brcd from locn_hdr where dsp_locn in ('"+locn1+"')";
					String barcode = jd.str_Database_Connection(query);

					Thread.sleep(5000);
					driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(barcode,Keys.ENTER);

					Thread.sleep(2000);
					driver.findElement(By.id("itemId1Brcd")).sendKeys(DFWMSLTLOutboundFlowStepDefn.itemName,Keys.ENTER);


					if(screen.equalsIgnoreCase("WCS_2019")) {

						/*Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@id='input1qty221']")).clear();

						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@id='input1qty221']")).sendKeys("2",Keys.ENTER);*/

						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(SlapperValue,Keys.ENTER);

					}else {

						driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(SlapperValue,Keys.ENTER);
					}





					//				String	 locn2 = driver.findElement(By.xpath("//span[@id='dataForm:delLocn']")).getText();
					//
					//
					//				String query1="select locn_brcd from locn_hdr where dsp_locn in ('"+locn2+"')";
					//
					//				String barcode1 = jd.str_Database_Connection(query1);
					//
					//				Thread.sleep(5000);
					//				driver.findElement(By.xpath("//input[@id='barcode4']")).sendKeys(barcode1,Keys.ENTER);
					//

					report.addReportStep("Task picking completed",
							"Task picking completed Successfully :   "+TaskIdText+"",
							StepResult.PASS);
					closebtn();
				}
			}else {

				Thread.sleep(2000);


				String currentDate = new SimpleDateFormat("MMddyymmss").format(Calendar.getInstance().getTime());
				int Tote = Integer.parseInt(currentDate);
				ToteValue = "T0" + Tote;
				if(ToteValue.length()>10){
					ToteValue = ToteValue.substring(0,11);
				}


				//			String currentDate = new SimpleDateFormat("MMddyymms").format(Calendar.getInstance().getTime());//TMMDDYYHHmm
				//			int LPN = Integer.parseInt(currentDate);
				System.out.println("Tote Id generated is " +ToteValue);

				DFWMSLoginPageObject.enterUserNameAndPassword();

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value='Enter']")).click();

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value='Chg Task Grp']")).click();

				Thread.sleep(2000);
				driver.findElement(By.id("dataForm:taskGrp")).sendKeys("ALL");

				Thread.sleep(2000);
				driver.findElement(By.id("location_Input")).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				driver.findElement(By.id("dataForm:INT")).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@value='Enter Task']")).click();
				//TaskIdText
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='dataForm:input1']")).sendKeys(TaskIdText);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='barcode']")).sendKeys(ToteValue,Keys.ENTER);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='input1qty221']")).clear();

				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='input1qty221']")).sendKeys("2",Keys.ENTER);

				report.addReportStep("Task picking completed",
						"Task picking completed Successfully :   "+TaskIdText+"",
						StepResult.PASS);

			}



		}catch(Exception e) {

			report.addReportStep(" Tasks cannot be released ",
					"Task cannot be not released :   "+e.getMessage()+"",
					StepResult.FAIL);
			rc.throwTCTerminationException();

		}


	}

	public void releaseTaskFromWave(String Screen) throws Exception {
		// TODO Auto-generated method stub

		if(Screen.equalsIgnoreCase("LG_2012")) {
			Thread.sleep(2000);
			driver.findElement(MoveBtn).click();
			Thread.sleep(2000);
			//WebElement findElement = driver.findElement(TaskReleaseBtn);
			//		Actions action = new Actions(driver);
			//
			//		action.moveToElement(findElement).click().perform();
			String searchText="Tasks"; 
			WebElement dropdown = driver.findElement(By.id("rmbuttons_1foActions"));

			List<WebElement> options = dropdown.findElements(By.tagName("li"));
			for (WebElement option : options)
			{
				if (option.getText().equals(searchText))
				{
					option.click(); // click the desired option
					break;
				}
			}		
			wh.waitForPageLoaded();

			Thread.sleep(5000);

			TaskIdText =driver.findElement(TaskId).getText();

			System.out.println("Tsk ID :"+TaskIdText);

			driver.findElement(releaseTaskCheckBox).click();

			Thread.sleep(2000);

			driver.findElement(releaseTaskBtn).click();;

			Thread.sleep(2000);
			driver.switchTo().alert().accept();

			Thread.sleep(2000);
			//String releaseText = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li[contains(text(),'Selected Tasks were successfully released')]")).getText();
			String releaseText1 = null;

			int num = 0;
			while (wh.isElementNotPresent(releaseText)) {
				try {
					if(num<25){
						System.out.println(num);

						if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close']//img)[1]"))) {

							driver.findElement(By.xpath("(//td//div[@class='pop_close']//img)[1]")).click();

						}

						driver.findElement(releaseTaskCheckBox).click();

						Thread.sleep(2000);

						driver.findElement(releaseTaskBtn).click();;

						Thread.sleep(2000);
						driver.switchTo().alert().accept();

						releaseText1 = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li")).getText();

						if(wh.isElementPresent(releaseText)) {
							if(releaseText1.equalsIgnoreCase("Selected Tasks were successfully released")) {

								if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close']//img)[1]"))) {

									driver.findElement(By.xpath("(//td//div[@class='pop_close']//img)[1]")).click();
								}
								report.addReportStep("Pick Tasks released",
										"Wave Download Response Successfully received from WCS for Wave:   "+sWaveNumber+"",
										StepResult.PASS);
								break;
							}
						}
						if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close']//img)[1]"))) {

							driver.findElement(By.xpath("(//td//div[@class='pop_close']//img)[1]")).click();

						}


						Thread.sleep(10000);
						num++;
					}
					else{

						releaseText1 = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li")).getText();

						if(releaseText1.equalsIgnoreCase("Selected Tasks were successfully released")) {

							if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close']//img)[1]"))) {

								driver.findElement(By.xpath("(//td//div[@class='pop_close']//img)[1]")).click();

							}
							report.addReportStep("Pick Tasks released",
									"Wave Download Response Successfully received from WCS for Wave:   "+sWaveNumber+"",
									StepResult.PASS);

							break;

						}else {

							report.addReportStep("Pick Tasks cannot be released ",
									"Wave Download Response pending from WCS for Wave:   "+sWaveNumber+"",
									StepResult.FAIL);
							rc.throwTCTerminationException();
						}

						break;
					}
				}catch(Exception e) {

					report.addReportStep("Pick Tasks cannot be released ",
							"Wave Download Response pending from WCS for Wave:   "+sWaveNumber+"",
							StepResult.FAIL);
					rc.throwTCTerminationException();

				}





			}


			if(wh.isElementPresent(releaseText)) {

				releaseText1 = driver.findElement(releaseText).getText();
				if(releaseText1.equalsIgnoreCase("Selected Tasks were successfully released")) {
					if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close']//img)[1]"))) {

						driver.findElement(By.xpath("(//td//div[@class='pop_close']//img)[1]")).click();

					}
					report.addReportStep("Pick Tasks released",
							"Wave Download Response Successfully received from WCS for Wave:   "+sWaveNumber+"",
							StepResult.PASS);


				}else {

					report.addReportStep("Pick Tasks cannot be released ",
							"Wave Download Response pending from WCS for Wave:   "+sWaveNumber+"",
							StepResult.FAIL);
					rc.throwTCTerminationException();
				}

			}
		}
		else {

			Thread.sleep(2000);

			String releaseText1 = null;

			driver.findElement(WaveSelect).click();
			Thread.sleep(2000);
			driver.findElement(MoveBtn).click();
			Thread.sleep(2000);
			//WebElement findElement = driver.findElement(TaskReleaseBtn);
			//		Actions action = new Actions(driver);
			//
			//		action.moveToElement(findElement).click().perform();
			String searchText="Tasks"; 
			WebElement dropdown = driver.findElement(By.id("rmbuttons_1foActions"));

			List<WebElement> options = dropdown.findElements(By.tagName("li"));
			for (WebElement option : options)
			{
				if (option.getText().equals(searchText))
				{
					option.click(); // click the desired option
					break;
				}
			}		
			wh.waitForPageLoaded();

			Thread.sleep(5000);

			TaskIdText =driver.findElement(TaskId).getText();

			System.out.println("Tsk ID :"+TaskIdText);

			driver.findElement(releaseTaskCheckBox).click();

			Thread.sleep(2000);

			driver.findElement(releaseTaskBtn).click();;

			Thread.sleep(2000);
			driver.switchTo().alert().accept();

			releaseText1 = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li")).getText();

			if(wh.isElementPresent(releaseText)) {
				if(releaseText1.contains("Selected Tasks were successfully released")) {

					if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close'])[1]"))) {

						driver.findElement(By.xpath("(//td//div[@class='pop_close'])[1]")).click();
						System.out.println("1");

						closebtn();

						report.addReportStep("Pick Tasks released",
								"Wave Download Response Successfully received from WCS for Wave:   "+sWaveNumber+"",
								StepResult.PASS);

					}
				}
			}
			else {
				Thread.sleep(2000);
				//String releaseText = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li[contains(text(),'Selected Tasks were successfully released')]")).getText();

				int num = 0;
				while (wh.isElementNotPresent(releaseText)) {
					try {
						if(num<25){
							System.out.println(num);

							if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close'])[1]"))) {

								driver.findElement(By.xpath("(//td//div[@class='pop_close'])[1]")).click();

							}

							driver.findElement(releaseTaskCheckBox).click();

							Thread.sleep(2000);

							driver.findElement(releaseTaskBtn).click();;

							Thread.sleep(2000);
							driver.switchTo().alert().accept();

							releaseText1 = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li")).getText();

							Thread.sleep(2000);
							if(wh.isElementPresent(releaseText)) {
								if(releaseText1.contains("Selected Tasks were successfully released")) {
									Thread.sleep(2000);

									if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close'])[1]"))) {

										driver.findElement(By.xpath("(//td//div[@class='pop_close'])[1]")).click();
										System.out.println("1");
									}
									Thread.sleep(2000);

									report.addReportStep("Pick Tasks released",
											"Wave Download Response Successfully received from WCS for Wave:   "+sWaveNumber+"",
											StepResult.PASS);

									if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close'])[1]"))) {

										driver.findElement(By.xpath("(//td//div[@class='pop_close'])[1]")).click();
										System.out.println("2");
									}

									Thread.sleep(2000);

									closebtn();

									break;
								}
							}

							if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close'])[1]"))) {

								driver.findElement(By.xpath("(//td//div[@class='pop_close'])[1]")).click();
								System.out.println("3");
							}


							Thread.sleep(10000);
							num++;
						}
						else{

							releaseText1 = driver.findElement(By.xpath("//div//ul[@class='overlayerrorList']//li")).getText();
							Thread.sleep(2000);

							if(releaseText1.contains("Selected Tasks were successfully released")) {
								Thread.sleep(2000);

								if(wh.isElementPresent(By.xpath("(//td//div[@class='pop_close'])[1]"))) {

									driver.findElement(By.xpath("(//td//div[@class='pop_close'])[1]")).click();
									System.out.println("4");
								}
								Thread.sleep(2000);

								report.addReportStep("Pick Tasks released",
										"Wave Download Response Successfully received from WCS for Wave:   "+sWaveNumber+"",
										StepResult.PASS);
								Thread.sleep(2000);

								closebtn();
								break;


							}else {

								report.addReportStep("Pick Tasks cannot be released ",
										"Wave Download Response pending from WCS for Wave:   "+sWaveNumber+"",
										StepResult.FAIL);
								rc.throwTCTerminationException();
								System.out.println("5");
							}

							break;
						}

					}catch(Exception e) {

						report.addReportStep("Pick Tasks cannot be released ",
								"Wave Download Response pending from WCS for Wave:   "+sWaveNumber+"",
								StepResult.FAIL);
						rc.throwTCTerminationException();
						System.out.println("6");
					}
				}


			}

		}

	}

	public void selectSubmitBtn() {
		// TODO Auto-generated method stub
		try {
			if (wh.isElementPresent(RunWaveSubmitBtn, 5)) {
				wh.clickElement(RunWaveSubmitBtn);


				report.addReportStep("Click Run Wave Submit Button",
						"Successfully clicked run wave submit button",
						StepResult.PASS);

			} else {
				throw new Exception("Run Wave Submit button is not populated."
						+ "XPath used is: " + RunWaveSubmitBtn.toString());
			}
		} catch (Exception e) {
			report.addReportStep(
					"Click Run Wave Submit Button",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
		}

		try {
			if (wh.isElementPresent(WaveNumber, 5)) {
				Thread.sleep(2000);
				sWaveNumber = wh.getText(WaveNumber);


				report.addReportStep("Wave Number",
						"Successfully started the wave run. Wave number is "
								+ sWaveNumber, StepResult.PASS);
			} else {
				throw new Exception("Wave Number not found."
						+ "XPath used is: " + WaveNumber.toString());
			}
			closebtn();
		} catch (Exception e) {
			report.addReportStep(
					"Wave Number",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
		}


	}

	public void selectLoadTrailer() {
		// TODO Auto-generated method stub

		try {

			wh.waitForPageLoaded();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			wh.clickElement(Maximize);
			driver.switchTo().frame(0);
			JavascriptExecutor js = (JavascriptExecutor) driver;

			if (wh.isElementPresent(transactionNmae, 5)) {

				wh.sendKeys(transactionNmae, "WM18");
				driver.findElement(By.xpath("//input[@alt='Find Transaction Name']")).sendKeys(Keys.ENTER);
			}


			if (wh.isElementPresent(LoadTrailerOption, 5)) {

				wh.clickElement(LoadTrailerOption);
			}

			if(wh.isElementPresent(ParameterTab, 5)) {
				wh.clickElement(ParameterTab);
			}

			if(wh.isElementPresent(MaxLengthTxt, 5)) {

				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")));

				Thread.sleep(2000);
				if(driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")).getText().equalsIgnoreCase("")||(driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")).getText().equalsIgnoreCase(null))){
					report.addReportStep("Max LPN Scan Limit",
							" Max LPN Scan Limit Is "
									+ driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")).getText(), StepResult.PASS);

				}
			}else {

				driver.findElement(By.xpath("(//input[@alt='Next'])[2]")).click();

				Thread.sleep(2000);

				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")));

				if(driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")).getText().equalsIgnoreCase("")||(driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")).getText().equalsIgnoreCase(null))){
					report.addReportStep("Max LPN Scan Limit",
							" Max LPN Scan Limit Is "
									+ driver.findElement(By.xpath("//div[@id='dataForm:tranParmDataTable_body_tr1_td3_view']//span")).getText(), StepResult.PASS);

				}

			}

		}catch (Exception e) {
			report.addReportStep(
					"Wave Number",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
		}
	}

	public String selectWaveCheckBox(String WaveDesc) throws Exception {


		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		Thread.sleep(1000);
		String validation = "";
		if(WaveDesc.equalsIgnoreCase("Value Added Services") 
				|| WaveDesc.equalsIgnoreCase("Select Rack - Non Ship Ready")
				|| WaveDesc.equalsIgnoreCase("HAZMAT - Boom Room/Aerosols")
				|| WaveDesc.equalsIgnoreCase("Wave Replenishment")
				|| WaveDesc.equalsIgnoreCase("Conveyable Cart Pick (Wyn/OBX/SR)")
				){ 
			if (wh.isElementPresent(RunWaveDesc, 5)) {
				wh.sendKeys(RunWaveDesc, WaveDesc);
			}

			if (wh.isElementPresent(RunWaveApply, 5)) {
				wh.clickElement(RunWaveApply);
			}
		}
		String RunWaveCheckBoxInput = searchWaveCheckBox(WaveDesc);
		By WaveCheckBoxInput = By.xpath(".//INPUT[@id='checkAll_c"
				+ RunWaveCheckBoxInput + "_dataForm:listView:dataTable']");

		try {
			if (wh.isElementPresent(WaveCheckBoxInput, 5)) {

				wh.clickElement(WaveCheckBoxInput);
				report.addReportStep("Select Wave CheckBox",
						"Successfully checked " + WaveDesc + " wave checkbox",
						StepResult.PASS);
			} else {
				validation = "NoWave";
				/*throw new Exception("Wave checkbox is not populated."
						+ "XPath used is: " + WaveCheckBoxInput.toString());*/
			}
		} catch (Exception e) {
			report.addReportStep(
					"Select Wave CheckBox",
					"Unable to select, locate " + WaveDesc + " checkbox. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		try {
			if (wh.isElementPresent(RunWaveBtn, 5)) {
				wh.clickElement(RunWaveBtn);
				//waitForWindowToLoad("Run Wave");
				report.addReportStep(
						"Click Run Wave Button",
						"Successfully clicked run wave button and Run wave page is opened",
						StepResult.PASS);
			} else {
				throw new Exception("Run Wave button is not populated."
						+ "XPath used is: " + RunWaveBtn.toString());
			}
		} catch (Exception e) {
			report.addReportStep("Click Run Wave Button",
					"Unable to locate Run Wave button. " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		return validation;
	}

	public String searchRuleCheckBox(String WaveDesc) throws Exception {

		By RuleDescElement = By.xpath(".//SPAN[contains(text(),'" + WaveDesc
				+ "')][1]");
		String RunRuleCheckBoxInput = wh.getAttribute(RuleDescElement, "id");
		return (RunRuleCheckBoxInput);

	}

	public void selectRuleCheckBox(String RuleDesc, String DONBR)
			throws Exception {

		String RunRuleCheckBoxInput = searchRuleCheckBox(RuleDesc);
		By RuleCheckBoxInput = By.xpath(".//span[@id='" + RunRuleCheckBoxInput
				+ "']");
		Thread.sleep(10000);
		try {
			if (wh.isElementPresent(RuleCheckBoxInput, 5)) {
				wh.focusElement(RuleCheckBoxInput);
				wh.clickElement(RuleCheckBoxInput);
				Thread.sleep(5000);
				wh.sendKeys(DOParamInput, DONBR);
				Thread.sleep(2000);
				report.addReportStep("Select Wave Rule and Enter DO",
						"Successfully selected " + RuleDesc
						+ " rule and entered DO", StepResult.PASS);
			} else {
				throw new Exception("Wave rule not found. " + "XPath used is: "
						+ RuleCheckBoxInput.toString());
			}
		} catch (Exception e) {
			report.addReportStep(
					"Select Wave Rule and Enter DO",
					"Unable to select, locate " + RuleDesc + " rule. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		try {
			if (wh.isElementPresent(RunWaveSubmitBtn, 5)) {
				wh.clickElement(RunWaveSubmitBtn);
				report.addReportStep("Click Run Wave Submit Button",
						"Successfully clicked run wave submit button",
						StepResult.PASS);
			} else {
				throw new Exception("Run Wave Submit button is not populated."
						+ "XPath used is: " + RunWaveSubmitBtn.toString());
			}
		} catch (Exception e) {
			report.addReportStep(
					"Click Run Wave Submit Button",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		try {
			if (wh.isElementPresent(WaveNumber, 5)) {
				Thread.sleep(2000);
				sWaveNumber = wh.getText(WaveNumber);
				report.addReportStep("Wave Number",
						"Successfully started the wave run. Wave number is "
								+ sWaveNumber, StepResult.PASS);
			} else {
				throw new Exception("Wave Number not found."
						+ "XPath used is: " + WaveNumber.toString());
			}
		} catch (Exception e) {
			report.addReportStep(
					"Wave Number",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}


	public String selectWaveCheckBox2012(String WaveDesc) throws Exception {


		Thread.sleep(1000);
		String validation = "";
		String RunWaveCheckBoxInput = searchWaveCheckBox(WaveDesc);
		By WaveCheckBoxInput = By.xpath(".//INPUT[@id='checkAll_c"
				+ RunWaveCheckBoxInput + "_dataForm:listView:dataTable']");

		try {
			if (wh.isElementPresent(WaveCheckBoxInput, 5)) {
				wh.clickElement(WaveCheckBoxInput);
				report.addReportStep("Select Wave CheckBox",
						"Successfully checked " + WaveDesc + " wave checkbox",
						StepResult.PASS);
			} else {
				validation = "NoWave";
				/*throw new Exception("Wave checkbox is not populated."
						+ "XPath used is: " + WaveCheckBoxInput.toString());*/
			}
		} catch (Exception e) {
			report.addReportStep(
					"Select Wave CheckBox",
					"Unable to select, locate " + WaveDesc + " checkbox. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		try {
			if (wh.isElementPresent(RunWaveBtn, 5)) {
				wh.clickElement(RunWaveBtn);
				//waitForWindowToLoad("Run Wave");
				report.addReportStep(
						"Click Run Wave Button",
						"Successfully clicked run wave button and Run wave page is opened",
						StepResult.PASS);
			} else {
				throw new Exception("Run Wave button is not populated."
						+ "XPath used is: " + RunWaveBtn.toString());
			}
		} catch (Exception e) {
			report.addReportStep("Click Run Wave Button",
					"Unable to locate Run Wave button. " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		return validation;
	}

	public void changeOLPNCarrier(String carrier) throws Exception {
		// TODO Auto-generated method stub

		try {
			
		
		Thread.sleep(3000);
		
		wh.clickElement(Maximize);
		
		String olpn = jd.str_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");

		driver.switchTo().frame(0);

		if (wh.isElementPresent(By.xpath("//input[@id='dataForm:EnterLPNNumber']"), 5)) {
			
				
				wh.sendKeys(By.xpath("//input[@id='dataForm:EnterLPNNumber']"),olpn);
				
				driver.findElement(By.xpath("//input[@id='dataForm:EnterLPNNumber']")).sendKeys(Keys.ENTER);

				
				Thread.sleep(2000);

			}

		String Carrier="";
		
		if(carrier.equalsIgnoreCase("UPS")) {
			
			Carrier="UPSÂ® Ground - E047";
			
			if(wh.isElementPresent(By.xpath("//select[@id='dataForm:shipviaList']"))) {
				
				Thread.sleep(2000);
				
				wh.selectText(By.xpath("//select[@id='dataForm:shipviaList']"),Carrier );
			}
			
			if(wh.isElementPresent(By.xpath("//input[@id='dataForm:nextButton1']"))) {
				
				wh.clickElement(By.xpath("//input[@id='dataForm:nextButton1']"));
				
			}
			
			report.addReportStep("Change carrier: "+Carrier,
					"Successfully changed carrier value",
					StepResult.PASS);
			
			
		}else if(carrier.equalsIgnoreCase("FXHD")){

			 Carrier="FedEx Home DeliveryÂ® - E006";
			 
			 if(wh.isElementPresent(By.xpath("//select[@id='dataForm:shipviaList']"))) {
					
					Thread.sleep(2000);
					
					wh.selectText(By.xpath("//select[@id='dataForm:shipviaList']"),Carrier );
				}
				
				if(wh.isElementPresent(By.xpath("//input[@id='dataForm:nextButton1']"))) {
					
					wh.clickElement(By.xpath("//input[@id='dataForm:nextButton1']"));
					
				}
				report.addReportStep("Change carrier: "+Carrier,
						"Successfully changed carrier value",
						StepResult.PASS);
				
				
			
		}else if(carrier.equalsIgnoreCase("FGND")) {
			

			 Carrier="FedEx GroundÂ® - E005";
			 
			 if(wh.isElementPresent(By.xpath("//select[@id='dataForm:shipviaList']"))) {
					
					Thread.sleep(2000);
					
					wh.selectText(By.xpath("//select[@id='dataForm:shipviaList']"),Carrier );
				}
				
				if(wh.isElementPresent(By.xpath("//input[@id='dataForm:nextButton1']"))) {
					
					wh.clickElement(By.xpath("//input[@id='dataForm:nextButton1']"));
					
				}
				report.addReportStep("Change carrier: "+Carrier,
						"Successfully changed carrier value",
						StepResult.PASS);
				
				
			
		} else if(carrier.equalsIgnoreCase("Update_OlpnWeight")) {
		
			if(wh.isElementPresent(By.xpath("//input[@id='dataForm:actWeight']"))) {
				
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//input[@id='dataForm:actWeight']")).clear();
				
				driver.findElement(By.xpath("//input[@id='dataForm:actWeight']")).sendKeys("75.12");
				
				Thread.sleep(2000);

			}
			
			if(wh.isElementPresent(By.xpath("//input[@id='dataForm:nextButton1']"))) {
				
				wh.clickElement(By.xpath("//input[@id='dataForm:nextButton1']"));
				
				
			}
			
			String weight = jd.str_Database_Connection("Select weight from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");

			
			report.addReportStep("Updated OLPN Weight ",
					"Successfully changed OLPN Weight: "+weight,
					StepResult.PASS);
			
		}
		
				
		
		}

		catch(Exception e) {
			
			System.out.println("unable to change carrier :"+e);
			report.addReportStep("unable to change carrier :",
					"unable to change carrier in drop down",
					StepResult.FAIL);
		}

	}
	
	public void enterWaveandApply() throws Exception {

		try {


			wh.clickElement(Maximize);
			Thread.sleep(1000);
			driver.switchTo().frame(0);

			if (wh.isElementPresent(WaveNumberInput, 5)) {
				wh.sendKeys(WaveNumberInput, sWaveNumber.trim());
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(WaveNumberApply, 5)) {
				wh.clickElement(WaveNumberApply);
				Thread.sleep(1000);
				if (wh.isElementPresent(WaveStatus, 5)) {

					report.addReportStep("Input Wave Number and Click Appply",
							"Successfully entered the Wave Number "+sWaveNumber+" and Clicked Apply",
							StepResult.PASS);
				} else {
					throw new Exception("Wave is not populated."
							+ "XPath used is: " + WaveStatus.toString());
				}
			}

		} catch (Exception e) {
			report.addReportStep("Input Wave Number and Appply",
					"Unable to Input and Apply Wave - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void cancelOlpnFromUI(String screen) throws Exception {

		try {


			wh.clickElement(Maximize);
			Thread.sleep(1000);
			driver.switchTo().frame(0);

			String olpn = jd.str_Database_Connection("Select tc_lpn_id from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");
			
			
			
			if (wh.isElementPresent(OlpnTxt, 5)) {
				wh.sendKeys(OlpnTxt, olpn);
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(WaveNumberApply, 5)) {
				wh.clickElement(WaveNumberApply);
				Thread.sleep(2000);
				
			}
			
			if (wh.isElementPresent(OlpnCheckBox, 5)) {
				wh.clickElement(OlpnCheckBox);
				Thread.sleep(2000);
				
			}
			
			if (wh.isElementPresent(CancelBtn, 5)) {
				wh.clickElement(CancelBtn);
				Thread.sleep(2000);
				
			}
			
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			
			report.addReportStep("Olpn Cancelled",
					"Successfully Olpn cancelled :"+olpn+" ",
					StepResult.PASS);

		} catch (Exception e) {
			report.addReportStep("Unable to cancel olpn",
					"Failed to cancel olpn - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void selectOlpnManagement() throws Exception {

		try {


			if (wh.isElementPresent(olpnManagement, 5)) {
				wh.clickElement(olpnManagement);
				Thread.sleep(2000);
			}


			if (wh.isElementPresent(searchBtn, 5)) {
				wh.clickElement(searchBtn);
				Thread.sleep(1000);

			}

			if (wh.isElementPresent(enterWavenumber, 5)) {

				wh.sendKeys(enterWavenumber, sWaveNumber);

				Thread.sleep(3000);
				report.addReportStep("Input Wave Number and Click Appply",
						"Successfully entered the Wave Number "+sWaveNumber+" and Clicked Apply",
						StepResult.PASS);
			} 

			if (wh.isElementPresent(applyBtn, 5)) {

				wh.clickElement(applyBtn);

				Thread.sleep(2000);


				JavascriptExecutor js = (JavascriptExecutor) driver;

				int num = 0;
				while (wh.isElementNotPresent(By.xpath("//td[@class=' isselected ']//span[@class='checkbox']"))) {
					if(num<25){
						System.out.println(num);
						wh.clickElement(searchBtn);
						wh.clickElement(applyBtn);
						Thread.sleep(10000);
						num++;
					}
					else{
						break;
					}
				}			


				List<WebElement> rws = driver.findElements(workAssignment);
				int rws_cnt = rws.size();
				//iterate rows of table
				for (int j = 0;j < rws_cnt; j++) {
					// count columns with size() method
					WebElement ele1= driver.findElement(By.xpath("(//a[text()='Work Assign'])[1]"));
					Thread.sleep(1000);
					js.executeScript("arguments[0].scrollIntoView();", ele1);	
					Thread.sleep(1000);

					String c = rws.get(j).getText();

					if(c!="" && c!=null) {

						workAsmt.add(c);

						System.out.println("The cell value is: " + c);
						System.out.println("The workAsmt value is: " + workAsmt);


					}
				}


				WebElement ele3= driver.findElement(homBtn);
				js.executeScript("arguments[0].scrollIntoView();", ele3);	

			}


			if(wh.isElementPresent(homBtn, 5)) {

				wh.clickElement(homBtn);

				Thread.sleep(2000);

			}

			else {
				throw new Exception("Wave is not populated."
						+ "XPath used is: " + WaveStatus.toString());
			}


		}catch (Exception e) {
			report.addReportStep("Input Wave Number and Appply",
					"Unable to Input and Apply Wave - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}


	public void searchOLPNInContainerAndTask(String screen) throws TCTerminationException {
		// TODO Auto-generated method stub

		try {

			driver.switchTo().window(DFWMSLoginPageObject.next_tab);

			ArrayList<String> oLPNs = new ArrayList<String>();

			for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
				List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
				oLPNs.addAll(lpns);

			}
			System.out.println("OLPN: "+oLPNs.get(0));

			if(screen.equalsIgnoreCase("Routing")) {
				System.out.println("select Routing");
				wh.waitForPageLoaded();

				if (wh.isElementPresent(Routing, 5)) {

					wh.clickElement(Routing);

					Thread.sleep(2000);

				}

				if (wh.isElementPresent(Destination, 5)) {

					wh.clickElement(Destination);

					Thread.sleep(2000);

				}

				if (wh.isElementPresent(enterOrderNumber, 5)) {

					wh.sendKeys(enterOrderNumber,routingText);

					Thread.sleep(2000);

					wh.clickElement(searchOrderBtn);

					Thread.sleep(3000);

					report.addReportStep("Input destination Number and Click Appply",

							"Successfully entered the destination Number "+routingText+" and Clicked Apply",

							StepResult.PASS);

					Thread.sleep(3000);


				}

				if (wh.isElementPresent(applyBtnCheckBox, 5)) {

					wh.clickElement(applyBtnCheckBox);

				}else if (wh.isElementPresent(applyBtnCheckBox1, 5)) {

					wh.clickElement(applyBtnCheckBox1);

				}else if (wh.isElementPresent(applyBtnCheckBox2, 5)) {

					wh.clickElement(applyBtnCheckBox2);

				}


				String text = driver.findElement(By.xpath("(//div//span[@class='sidepanel-details-middle-column wide'])[2]")).getText();
				String[] split = text.split("-");
				decisionPoint=split[0];
				System.out.println("Routing Name:"+decisionPoint);
			}
			else {


				System.out.println("select Container");
				wh.waitForPageLoaded();
				if (wh.isElementPresent(container, 5)) {

					wh.clickElement(container);

					Thread.sleep(2000);

				}

				if (wh.isElementPresent(enterOrderNumber, 5)) {

					wh.sendKeys(enterOrderNumber,oLPNs.get(0));

					Thread.sleep(2000);

					wh.clickElement(searchOrderBtn);

					Thread.sleep(3000);

					report.addReportStep("Input OLPN Number and Click Appply",

							"Successfully entered the OLPN Number "+oLPNs.get(0)+" and Clicked Apply",

							StepResult.PASS);

					Thread.sleep(3000);


					int num = 0;

					while (wh.isElementNotPresent(By.xpath("//td//div//span[text()='"+oLPNs.get(0)+"']"))) {

						if(num<10){

							System.out.println(num);


							driver.findElement(By.xpath("//i[@class='h-icon global close i-clear-search']")).click();

							wh.sendKeys(enterOrderNumber, oLPNs.get(0));

							wh.clickElement(searchOrderBtn);


							Thread.sleep(10000);

							num++;

						}

						else{
							break;
						}

					}

					if (wh.isElementPresent(applyBtnCheckBox, 5)) {

						wh.clickElement(applyBtnCheckBox);

					}else if (wh.isElementPresent(applyBtnCheckBox1, 5)) {

						wh.clickElement(applyBtnCheckBox1);

					}
					if (wh.isElementPresent(containerTxt, 5)) {
						if(screen.equalsIgnoreCase("Routes_Container")) {

							Thread.sleep(2000);
							driver.findElement(By.xpath("//div[@class='i-details-toggle']//i[@class='h-icon global caret-right scuf-medium']")).click();
							String text = driver.findElement(By.xpath("//div[@class='content']")).getText();
							System.out.println("Route ID:"+text);
							if(DFWMSValidationsPageObject.RouteId.get(0).contains(text)) {

								System.out.println(DFWMSValidationsPageObject.RouteId.get(0));
								report.addReportStep("Route ID status is: ",
										"Successfully get route ID "+text+" ",
										StepResult.PASS);

							}
							else {

								report.addReportStep(" Container status not equal CREATED",

										"Container status not equal CREATED: "+driver.findElement(containerTxt).getText()+"",

										StepResult.FAIL);

								rc.throwTCTerminationException();

							}


						}else if(driver.findElement(containerTxt).getText().equalsIgnoreCase(screen))
						{

							System.out.println(driver.findElement(containerTxt).getText());
							report.addReportStep("Search OLPN Number and status is created",
									"Successfully Released the OLPN Number "+oLPNs.get(0)+" and status is created",
									StepResult.PASS);


						}


					}else if(wh.isElementPresent(shippedTxt, 5)) {

						if(screen.equalsIgnoreCase("Routes_Container")) {

							Thread.sleep(2000);
							driver.findElement(By.xpath("//div[@class='i-details-toggle']//i[@class='h-icon global caret-right scuf-medium']")).click();
							String text = driver.findElement(By.xpath("//div[@class='content']")).getText();
							System.out.println("Route ID:"+text);
							if(DFWMSValidationsPageObject.RouteId.get(0).contains(text)) {

								System.out.println(DFWMSValidationsPageObject.RouteId.get(0));
								report.addReportStep("Route ID status is: ",
										"Successfully get route ID "+text+" ",
										StepResult.PASS);

							}
							else {

								report.addReportStep(" Container status not equal SHIPPED",

										"Container status not equal CREATED: "+driver.findElement(containerTxt).getText()+"",

										StepResult.FAIL);

								rc.throwTCTerminationException();

							}


						}else if(driver.findElement(shippedTxt).getText().equalsIgnoreCase(screen))
						{

							System.out.println(driver.findElement(shippedTxt).getText());
							report.addReportStep("Search OLPN Number and status is :"+driver.findElement(shippedTxt).getText(),
									"Successfully Released the OLPN Number "+oLPNs.get(0)+" and status is "+driver.findElement(shippedTxt).getText(),
									StepResult.PASS);


						}

					}

					else {

						report.addReportStep(" Container status not equal shipped",

								"Container status not equal SHIPPED: "+driver.findElement(containerTxt).getText()+"",

								StepResult.FAIL);

						rc.throwTCTerminationException();

					}


				}


				System.out.println("select Task");
				wh.waitForPageLoaded();
				if (wh.isElementPresent(task, 5)) {

					wh.clickElement(task);

					Thread.sleep(2000);

				}

				if (wh.isElementPresent(enterOrderNumber, 5)) {

					//wh.clearElement(enterOrderNumber);
					//				if(wh.isElementPresent(closeBtnWES,5)) {
					//
					//
					//					wh.clickElement(closeBtnWES);
					//					Thread.sleep(3000);
					//
					//				}

					wh.sendKeys(enterOrderNumber,oLPNs.get(0));

					Thread.sleep(2000);

					wh.clickElement(searchOrderBtn);

					Thread.sleep(3000);

					report.addReportStep("Input OLPN Number and Click Appply",

							"Successfully entered the OLPN Number "+oLPNs.get(0)+" and Clicked Apply",

							StepResult.PASS);

					Thread.sleep(3000);


					int num = 0;

					while (wh.isElementNotPresent(By.xpath("//td//div//span[text()='"+oLPNs.get(0)+"']"))) {

						if(num<10){

							System.out.println(num);


							driver.findElement(By.xpath("//i[@class='h-icon global close i-clear-search']")).click();

							wh.sendKeys(enterOrderNumber, oLPNs.get(0));

							wh.clickElement(searchOrderBtn);


							Thread.sleep(10000);

							num++;

						}

						else{
							break;
						}

					}

					if (wh.isElementPresent(applyBtnCheckBox, 5)) {

						wh.clickElement(applyBtnCheckBox);

					}

					if (wh.isElementPresent(Tasktext, 5)) {

						if(screen.equalsIgnoreCase("Routes_Container")) {


							if(driver.findElement(TaskRelease).getText().equalsIgnoreCase("Released")) {
								Thread.sleep(2000);	
								System.out.println(DFWMSValidationsPageObject.RouteId.get(0));
								routingText = driver.findElement(By.xpath("(//td[5]//div[@class='cell-data-wrap'])[1]")).getText();
								report.addReportStep("Route ID status is: ",
										"Successfully get route ID "+driver.findElement(TaskRelease).getText()+" ",
										StepResult.PASS);

							}
							else {

								report.addReportStep(" Container status not equal CREATED",

										"Container status not equal CREATED: "+driver.findElement(containerTxt).getText()+"",

										StepResult.FAIL);

								rc.throwTCTerminationException();

							}
						}
						else if(driver.findElement(Tasktext).getText().equalsIgnoreCase("Planned")) {

							System.out.println(driver.findElement(Tasktext).getText());
							report.addReportStep("Search OLPN Number and status is Planned",
									"Successfully Released the OLPN Number "+oLPNs.get(0)+" and status is Planned",
									StepResult.PASS);


						}else if(driver.findElement(TaskCompleted).getText().equalsIgnoreCase("Completed")) {

							System.out.println(driver.findElement(TaskCompleted).getText());
							report.addReportStep("Search OLPN Number and status is Completed",
									"Successfully Released the OLPN Number "+oLPNs.get(0)+" and status is Completed",
									StepResult.PASS);


						}



						else {

							report.addReportStep(" Container status not equal CREATED",

									"Container status not equal CREATED: "+driver.findElement(Tasktext).getText()+"",

									StepResult.FAIL);

							rc.throwTCTerminationException();

						}

					}else {

						report.addReportStep(" Container text not available",

								"Container text not available: "+driver.findElement(Tasktext).getText()+"",

								StepResult.FAIL);

						rc.throwTCTerminationException();

					}



				}
			}

			driver.switchTo().window(DFWMSLoginPageObject.first_tab);

		}catch (Exception e) {

			report.addReportStep("Input OLPN Number and Appply",

					"Unable to Input and Apply OLPN - " + e.getMessage(),

					StepResult.FAIL);

			rc.throwTCTerminationException();
		}

	}

	public static String middle(String str)
	{
		int position;
		int length;
		if (str.length() % 2 == 0)
		{
			position = str.length() / 2 - 1;
			length = 2;
		}
		else
		{
			position = str.length() / 2;
			length = 1;
		}
		return str.substring(position, position + length);
	}
	
	public void ShortatMultis(String screen) throws InterruptedException, ClassNotFoundException, IOException, TCTerminationException 
	{
		if(screen.equalsIgnoreCase("WCS_2019")) {

			try {

				
				Thread.sleep(10000);
				driver.findElement(By.cssSelector("div[id^='combobox-'][id$='trigger-picker']")).click();

				

				List<WebElement> findElements = driver.findElements(By.xpath("/html/body/div[15]/div/ul/li"));
				
				Thread.sleep(3000);

				String cur_loc = jd.str_Database_Connection("select distinct m.dsp_locn from locn_hdr m,lpn l where l.tc_order_id in ('" + DFWMSInbounfFlowStepDefn.doId + "') and l.curr_sub_locn_id=m.locn_id");

				System.out.println(cur_loc);


				String[] split = cur_loc.split(" ");

				String lastTwoDigits = split[0].substring(1); 

				System.out.println(lastTwoDigits);

				String mid=middle(split[1]);
				System.out.println("mid of value:"+ mid);
				//			String substring = split[3].substring(0, split[3].length() - 1);
				String loc="Putwall "+lastTwoDigits+" Pack Station "+lastTwoDigits+""+mid+"";
				System.out.println("loc:"+ loc);
				for(int i=0;i<findElements.size();i++) {
					System.out.println("Multis loop");
					Thread.sleep(5000);


					if(findElements.get(i).getText().equalsIgnoreCase(loc)) {
						findElements.get(i).click();
						break;
					}
				}

				
				
				Thread.sleep(3000);

				driver.findElement(By.xpath("//div[@class='x-toolbar  x-box-item x-toolbar-wmtouch-mpsactionbar-light x-box-layout-ct']//span[text()='Start Packing']")).click();
				Thread.sleep(5000);

				//ArrayList<String> olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");

				//for(int i =0;i<olpn.size();i++) {

					driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[3]")).sendKeys(itemValue,Keys.ENTER);

					Thread.sleep(5000);
					driver.findElement(By.xpath("//*[@id=\"button-2740-btnIconEl\"]")).click();

//					driver.findElement(By.xpath("//a//span[text()='OK']")).click();
//					Thread.sleep(5000);


				//}
				Thread.sleep(3000);
				driver.findElement(By.xpath("/html/body/div[14]/div[2]/div/div/div[2]/div/div/a[3]")).click();
				//driver.findElement(By.xpath("//*[@id=\"button-2774-btnIconEl\"]")).click();
				Thread.sleep(3000);
				closebtn();


				report.addReportStep("Multis transaction completed",

						"Multis transaction completed Successfully",

						StepResult.PASS);

			}catch(Exception e) {

				report.addReportStep("Multis tracking failed",

						"Unable to perform Multis transaction - " + e.getMessage(),

						StepResult.FAIL);

				rc.throwTCTerminationException();

			}

		}
		else {

			try {

				Thread.sleep(2000);
				driver.findElement(By.id("dataForm:PutPack_INP_PackStatnNbr_input")).sendKeys("011",Keys.ENTER);
				Thread.sleep(2000);

				driver.findElement(By.id("dataForm:submitButton")).click();
				Thread.sleep(2000);



				List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");

				for(int i=0;i<lpns.size();i++) {

					driver.findElement(By.id("dataForm:PutPack_INP_oLPNNbr1")).sendKeys(lpns.get(i));
					driver.findElement(By.id("PackNextOLPNButton")).click();

					Thread.sleep(2000);
					if(wh.isElementPresent(By.id("dataForm:PutWallPackStation_VerifyAll_button"))) {

						driver.findElement(By.id("dataForm:PutWallPackStation_VerifyAll_button")).click();
					}

					if(wh.isElementPresent(By.id("dataForm:PutWallPackStation_PrintShippingLabel_button"))) {

						driver.findElement(By.id("dataForm:PutWallPackStation_PrintShippingLabel_button")).click();
					}

					Thread.sleep(3000);
					System.out.println("OLPN: "+lpns.get(i));
				}

				report.addReportStep("Multis transaction completed",

						"Multis transaction completed Successfully"+lpns.get(0)+"",

						StepResult.PASS);

			}catch(Exception e) {

				report.addReportStep("Multis tracking failed",

						"Unable to perform Multis transaction - " + e.getMessage(),

						StepResult.FAIL);

				rc.throwTCTerminationException();

			}

		}
		
		
		
		
	}
	
	
	
	public void SinglesPacking(String screen) throws InterruptedException, ClassNotFoundException, IOException, TCTerminationException 
	{
		//driver.manage().window().maximize();
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("//*[@id=\"tool-1099-toolEl\"]")).click();
		Thread.sleep(3000);
		
		WebElement testDrop=driver.findElement(By.cssSelector("div[id^='combobox-'][id$='trigger-picker']"));
		testDrop.click();
		Thread.sleep(4000);
		
		
		
		//WebElement var1;
		driver.findElement(By.xpath("/html/body/div[15]/div/ul/li[1]")).click();
		
		//var1.click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@class='x-toolbar  x-box-item x-toolbar-wmtouch-mpsactionbar-light x-box-layout-ct']//span[text()='Start Packing']")).click();
		System.out.println(ToteValue);
		System.out.println(itemValue);
		
		Thread.sleep(5000);
		
		
		
		driver.findElement(By.id("textfield-2750-inputEl")).sendKeys(ToteValue,Keys.ENTER);
		
		
		
		//driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[1]")).click();
		//driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[1]")).sendKeys(ToteValue,Keys.ENTER);
		Thread.sleep(8000);
		
		driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[3]")).sendKeys(itemValue,Keys.ENTER);
		
		Thread.sleep(3000);
		
		report.addReportStep("Singles transaction completed",

				"Packing at Singles completed Successfully",

				StepResult.PASS);
		
	}
	
	public void multisPackingInRF(String screen) throws InterruptedException, ClassNotFoundException, IOException, TCTerminationException {
		// TODO Auto-generated method stub

		if(screen.equalsIgnoreCase("Diff_Loc")) {

			try {


				//wh.waitForPageLoaded();
				////div[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-wmtouch-boundlist-light x-border-box']//div//li[text()='']
				Thread.sleep(10000);

				List<String> loc=jd.array_Database_Connection("select distinct  m.dsp_locn from locn_hdr m,lpn l, alloc_invn_dtl a where l.tc_order_id in ('"+DFWMSInbounfFlowStepDefn.doId+"') and l.curr_sub_locn_id=m.locn_id and a.task_cmpl_ref_nbr=l.tc_lpn_id and a.invn_need_type = '52'");

				if(loc.size()>1) {

					for(int i=0;i<loc.size();i++) {

						driver.findElement(By.cssSelector("div[id^='combobox-'][id$='trigger-picker']")).click();

						Thread.sleep(3000);

						List<WebElement> findElements = driver.findElements(By.xpath("/html/body/div[15]/div/ul/li"));


						String[] split = loc.get(i).split(" ");

						String lastTwoDigits = split[0].substring(1); 

						System.out.println(lastTwoDigits);

						String mid=middle(split[1]);
						if(Integer.parseInt(mid)==0) {

							mid="1";
						}
						System.out.println("mid of value:"+ mid);
						//						String substring = split[3].substring(0, split[3].length() - 1);
						String loc1="Putwall "+lastTwoDigits+" Pack Station "+lastTwoDigits+""+mid+"";
						System.out.println("loc:"+ loc1);
						for(int j=0;j<findElements.size();j++) {
							Thread.sleep(2000);


							if(findElements.get(j).getText().equalsIgnoreCase(loc1)) {
								findElements.get(j).click();
								break;
							}
						}

						Thread.sleep(3000);

						driver.findElement(By.xpath("//div[@class='x-toolbar  x-box-item x-toolbar-wmtouch-mpsactionbar-light x-box-layout-ct']//span[text()='Start Packing']")).click();
						Thread.sleep(5000);

						//ArrayList<String> olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");


						driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[3]")).sendKeys(itemValue,Keys.ENTER);

						Thread.sleep(5000);
						
						if(ItemQty != null) {
							
							for(int j=0;j<ItemQty.length();j++) {

								driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div/a[1]/span/span/span[2]")).click();
							}
						}
//						if(wh.isElementPresent(By.xpath("//a//span[text()='OK']"))) {
//
//							driver.findElement(By.xpath("//a//span[text()='OK']")).click();
//							Thread.sleep(5000);
//
//						}
//
//						if(wh.isElementPresent(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-wmtouch-centerconfirmbutton-light-small  ']"))) {
//
//							driver.findElement(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-wmtouch-centerconfirmbutton-light-small  ']")).click();
//
//							Thread.sleep(3000);
//
//						}
//
//						if(wh.isElementPresent(By.xpath("//a//span[text()='OK']"))) {
//
//							driver.findElement(By.xpath("//a//span[text()='OK']")).click();
//							Thread.sleep(5000);
//
//						}

							driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div[3]/div/div/div/a/span/span")).click();

						report.addReportStep("Multis transaction completed",

								"Multis transaction completed Successfully",

								StepResult.PASS);

						closebtn();

						dfwmsHomePageObject2019.WMSmenu();
						dfwmsHomePageObject2019.searchInput("Pack Station Multis", "Distribution");

					}
				}else {

					//driver.findElement(By.cssSelector("div[id^='combobox-'][id$='trigger-picker']")).click();

					Thread.sleep(3000);

					//div[class='x-boundlist x-boundlist-floating x-layer x-boundlist-wmtouch-boundlist-light x-border-box x-boundlist-above'] div[id^='boundlist-'][id$='listWrap'] ul  li
					//List<WebElement> findElements = driver.findElements(By.cssSelector("div[class='x-boundlist x-boundlist-floating x-layer x-boundlist-wmtouch-boundlist-light x-border-box x-boundlist-above'] div[id^='boundlist-'][id$='listWrap'] ul  li"));

					//List<WebElement> findElements = driver.findElements(By.xpath("/html/body/div[15]/div/ul/li"));


					String cur_loc = jd.str_Database_Connection("select distinct m.dsp_locn from locn_hdr m,lpn l where l.tc_order_id in ('" + DFWMSInbounfFlowStepDefn.doId + "') and l.curr_sub_locn_id=m.locn_id");

					System.out.println(cur_loc);


					String[] split = cur_loc.split(" ");

					String lastTwoDigits = split[0].substring(1); 

					System.out.println(lastTwoDigits);

					String mid=middle(split[1]);
					if(Integer.parseInt(mid)==0) {
						mid="1 ";
					}
					System.out.println("mid of value:"+ mid);
					//				String substring = split[3].substring(0, split[3].length() - 1);
					String location="Putwall "+lastTwoDigits+" Pack Station "+lastTwoDigits+""+mid+"";
					System.out.println("loc:"+ location);

					Thread.sleep(2000);
					//driver.findElement(By.cssSelector("input[id^='combobox-'][id$='inputEl']")).sendKeys(location,Keys.ENTER);

					driver.findElement(By.cssSelector("input[id^='combobox-'][id$='inputEl']")).sendKeys(location);

					Thread.sleep(3000);
					driver.findElement(By.cssSelector("input[id^='combobox-'][id$='inputEl']")).sendKeys(Keys.ENTER);

					Thread.sleep(3000);

					driver.findElement(By.xpath("//div[@class='x-toolbar  x-box-item x-toolbar-wmtouch-mpsactionbar-light x-box-layout-ct']//span[text()='Start Packing']")).click();
					Thread.sleep(5000);

					ArrayList<String> olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");
					
					
                   
					//for(int i =0;i<2;i++) {

						driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[3]")).sendKeys(itemValue,Keys.ENTER);

						Thread.sleep(5000);
						
                        if(ItemQty != null) {
							
							for(int j=0;j<ItemQty.length();j++) {
                                 
								driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div/a[1]/span/span/span[2]")).click();
							}
						}
                        
                        driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div[3]/div/div/div/a/span/span")).click();
//						if(wh.isElementPresent(By.xpath("//a//span[text()='OK']"))) {
//
//							driver.findElement(By.xpath("//a//span[text()='OK']")).click();
//							Thread.sleep(5000);
//
//						}
//
//						if(wh.isElementPresent(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-wmtouch-centerconfirmbutton-light-small  ']"))) {
//
//							driver.findElement(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-wmtouch-centerconfirmbutton-light-small  ']")).click();
//
//							Thread.sleep(3000);
//
//						}
//
//						if(wh.isElementPresent(By.xpath("//a//span[text()='OK']"))) {
//
//							driver.findElement(By.xpath("//a//span[text()='OK']")).click();
//							Thread.sleep(5000);
//
//						}
//
//
//					}
//
					closebtn();


					report.addReportStep("Multis transaction completed",

							"Multis transaction completed Successfully",

							StepResult.PASS);

				}
				}catch(Exception e) {

				report.addReportStep("Multis tracking failed",

						"Unable to perform Multis transaction - " + e.getMessage(),

						StepResult.FAIL);

				rc.throwTCTerminationException();

			}

		}
		else if(screen.equalsIgnoreCase("WCS_2019")) {

			try {

				//wh.waitForPageLoaded();
				////div[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-wmtouch-boundlist-light x-border-box']//div//li[text()='']
				Thread.sleep(10000);


				driver.findElement(By.cssSelector("div[id^='combobox-'][id$='trigger-picker']")).click();

				

				List<WebElement> findElements = driver.findElements(By.xpath("/html/body/div[15]/div/ul/li"));
				
				Thread.sleep(3000);

				String cur_loc = jd.str_Database_Connection("select distinct m.dsp_locn from locn_hdr m,lpn l where l.tc_order_id in ('" + DFWMSInbounfFlowStepDefn.doId + "') and l.curr_sub_locn_id=m.locn_id");

				System.out.println(cur_loc);


				String[] split = cur_loc.split(" ");

				String lastTwoDigits = split[0].substring(1); 

				System.out.println(lastTwoDigits);

				String mid=middle(split[1]);
				System.out.println("mid of value:"+ mid);
				//			String substring = split[3].substring(0, split[3].length() - 1);
				String loc="Putwall "+lastTwoDigits+" Pack Station "+lastTwoDigits+""+mid+"";
				System.out.println("loc:"+ loc);
				for(int i=0;i<findElements.size();i++) {
					System.out.println("Multis loop");
					Thread.sleep(5000);


					if(findElements.get(i).getText().equalsIgnoreCase(loc)) {
						findElements.get(i).click();
						break;
					}
				}

				
				
				Thread.sleep(3000);

				driver.findElement(By.xpath("//div[@class='x-toolbar  x-box-item x-toolbar-wmtouch-mpsactionbar-light x-box-layout-ct']//span[text()='Start Packing']")).click();
				Thread.sleep(5000);

				ArrayList<String> olpn = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");

				for(int i =0;i<olpn.size();i++) {

					driver.findElement(By.xpath("(//input[starts-with(@id, 'textfield')])[3]")).sendKeys(itemValue,Keys.ENTER);

					Thread.sleep(5000);
					driver.findElement(By.xpath("//*[@id=\"button-2768-btnInnerEl\"]")).click();

//					driver.findElement(By.xpath("//a//span[text()='OK']")).click();
//					Thread.sleep(5000);


				}
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"button-2774-btnIconEl\"]")).click();

				closebtn();


				report.addReportStep("Multis transaction completed",

						"Multis transaction completed Successfully",

						StepResult.PASS);

			}catch(Exception e) {

				report.addReportStep("Multis tracking failed",

						"Unable to perform Multis transaction - " + e.getMessage(),

						StepResult.FAIL);

				rc.throwTCTerminationException();

			}

		}
		else {

			try {

				Thread.sleep(2000);
				driver.findElement(By.id("dataForm:PutPack_INP_PackStatnNbr_input")).sendKeys("011",Keys.ENTER);
				Thread.sleep(2000);

				driver.findElement(By.id("dataForm:submitButton")).click();
				Thread.sleep(2000);



				List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");

				for(int i=0;i<lpns.size();i++) {

					driver.findElement(By.id("dataForm:PutPack_INP_oLPNNbr1")).sendKeys(lpns.get(i));
					driver.findElement(By.id("PackNextOLPNButton")).click();

					Thread.sleep(2000);
					if(wh.isElementPresent(By.id("dataForm:PutWallPackStation_VerifyAll_button"))) {

						driver.findElement(By.id("dataForm:PutWallPackStation_VerifyAll_button")).click();
					}

					if(wh.isElementPresent(By.id("dataForm:PutWallPackStation_PrintShippingLabel_button"))) {

						driver.findElement(By.id("dataForm:PutWallPackStation_PrintShippingLabel_button")).click();
					}

					Thread.sleep(3000);
					System.out.println("OLPN: "+lpns.get(i));
				}

				report.addReportStep("Multis transaction completed",

						"Multis transaction completed Successfully"+lpns.get(0)+"",

						StepResult.PASS);

			}catch(Exception e) {

				report.addReportStep("Multis tracking failed",

						"Unable to perform Multis transaction - " + e.getMessage(),

						StepResult.FAIL);

				rc.throwTCTerminationException();

			}

		}
	}
	
	
	
	
	public void PickingWithWM(String itemname) throws Exception {
		// TODO Auto-generated method stub

		try {

			wh.clickElement(Maximize);
			Thread.sleep(2000);

			driver.switchTo().frame(0);
			new Actions(driver).keyDown(Keys.CONTROL).sendKeys("f").perform();


			Thread.sleep(2000);

			DFWMSInboundReceiveInPuttyPageObject.sendKeys(RFTransSearch, "Pick to Label Con (THD)");


			//driver.findElement(By.id("dataForm:it_1")).sendKeys("Pick to Label Con (THD)",Keys.ENTER);

			Thread.sleep(2000);
			DFWMSInboundReceiveInPuttyPageObject.sendKeys(RFTransSearch, "Pick to Label Con (THD)");

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			DFWMSInboundReceiveInPuttyPageObject.sendKeys(RFTransSearch, "1");
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			//driver.findElement(By.id("dataForm:it_1")).sendKeys("1",Keys.ENTER);

			ArrayList<String> oLPNs = new ArrayList<String>();

			for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
				List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
				oLPNs.addAll(lpns);

			}
			System.out.println("OLPN: "+oLPNs.get(0));

			driver.findElement(By.id("barcode")).sendKeys(oLPNs.get(0),Keys.ENTER);

			Thread.sleep(2000);

			System.out.println("ItemName: "+itemname);
			driver.findElement(By.id("itemIdBrcd")).sendKeys(itemname,Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("input1qty2")).sendKeys("1",Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@value='Accpt/Proceed']")).click();

			report.addReportStep("RF transaction completed",

					"RF transaction completed Successfully"+oLPNs.get(0)+"",

					StepResult.PASS);
			closebtn();

		}catch(Exception e) {

			report.addReportStep("RF picking failed",

					"Unable to perform RF transaction - " + e.getMessage(),

					StepResult.FAIL);

			rc.throwTCTerminationException();

		}

	}
	public void searchOrderNumber() throws Exception {

		try {

			System.out.println("select order");
			wh.waitForPageLoaded();
			if (wh.isElementPresent(orderTxt, 5)) {

				wh.clickElement(orderTxt);

				Thread.sleep(2000);

			}

			if (wh.isElementPresent(enterOrderNumber, 5)) {

				wh.sendKeys(enterOrderNumber, DFWMSInbounfFlowStepDefn.doId);

				wh.clickElement(searchOrderBtn);

				Thread.sleep(3000);

				report.addReportStep("Input Order Number and Click Appply",

						"Successfully entered the Order Number "+DFWMSInbounfFlowStepDefn.doId+" and Clicked Apply",

						StepResult.PASS);

			}
			Thread.sleep(3000);


			int num = 0;

			while (wh.isElementNotPresent(By.xpath("//td//div//span[text()='"+DFWMSInbounfFlowStepDefn.doId+"']"))) {

				if(num<10){

					System.out.println(num);


					driver.findElement(By.xpath("//i[@class='h-icon global close i-clear-search']")).click();

					wh.sendKeys(enterOrderNumber, DFWMSInbounfFlowStepDefn.doId);

					wh.clickElement(searchOrderBtn);


					Thread.sleep(10000);

					num++;

				}

				else{
					break;
				}

			}

			if (wh.isElementPresent(applyBtnCheckBox, 5)) {

				wh.clickElement(applyBtnCheckBox);

			}

			if (wh.isElementPresent(statusTxt, 5)) {
				if(driver.findElement(statusTxt).getText().equalsIgnoreCase("Ready To Release")) {

					System.out.println(driver.findElement(statusTxt).getText());
					if (wh.isElementPresent(releaseBtnWES, 5)) {

						wh.clickElement(releaseBtnWES);

						Thread.sleep(2000);

						//driver.switchTo().alert();

						wh.clickElement(releaseCheckBox);

						wh.clickElement(releaseBtnAlert);

						report.addReportStep("Search Order Number and Click Appply",

								"Successfully Released the Order Number "+DFWMSInbounfFlowStepDefn.doId+" and Clicked Apply",

								StepResult.PASS);


					}

				}

			}
			else if (wh.isElementPresent(statusTxt, 5)) {
				if(driver.findElement(statusTxt).getText().equalsIgnoreCase("Release")) {


					report.addReportStep("Search Order Number Release status",

							"Order Number Released Status "+DFWMSInbounfFlowStepDefn.doId+" and Clicked Apply",

							StepResult.PASS);


				}

			}

			else {

				report.addReportStep(" Order Number not equal Ready to release state",

						"Unable to release Order Number "+DFWMSInbounfFlowStepDefn.doId+" and Clicked Apply",

						StepResult.FAIL);

				rc.throwTCTerminationException();

			}


			//			else {
			//
			//				throw new Exception("Order number is not populated."
			//
			//                               + "XPath used is: " + WaveStatus.toString());
			//
			//			}

		}catch (Exception e) {

			report.addReportStep("Input Order Number and Appply",

					"Unable to Input and Apply Order - " + e.getMessage(),

					StepResult.FAIL);

			rc.throwTCTerminationException();
		}
	}

	public void selectCartandRelease() throws Exception {

		try {


			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement ele2= driver.findElement(cartStart);
			Thread.sleep(1000);
			js.executeScript("arguments[0].scrollIntoView();", ele2);	

			if (wh.isElementPresent(cartStart, 5)) {
				wh.clickElement(cartStart);
				Thread.sleep(2000);
			}


			if (wh.isElementPresent(searchBtn, 5)) {
				wh.clickElement(searchBtn);
				Thread.sleep(1000);

			}

			if (wh.isElementPresent(enterWorkAssignment, 5)) {

				String workNumber = workAsmt.get(0).toString();

				wh.sendKeys(enterWorkAssignment, workNumber);

				Thread.sleep(3000);

				WebElement ele= driver.findElement(applyBtn);
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", ele);	

				Thread.sleep(4000);

				ele.click();

				Thread.sleep(4000);

				driver.findElement(selectWorkAssignmentNumber).click();


				WebElement findElement = driver.findElement(releaseBtn);
				js.executeScript("arguments[0].scrollIntoView();", findElement);	

				Thread.sleep(2000);

				findElement.click();


				Thread.sleep(2000);

				String text = driver.findElement(failedToPrint).getText();

				if(text.trim().contains("Warning the following Lpns are Missing Labels:")) {

					wh.clickElement(noBtn);

					report.addReportStep("LPN are Missing",
							"Unable to release - "+text ,
							StepResult.FAIL);
					rc.throwTCTerminationException();


				}else if(text.trim().contains("Work Assignments Successfully Released")){


					if (wh.isElementPresent(okBtn, 5)) {
						wh.clickElement(okBtn);
						Thread.sleep(2000);
					}else if (wh.isElementPresent(yesBtn, 5)){

						wh.clickElement(yesBtn);

					}


					report.addReportStep("Release task",
							"Successfully released the work asignment Number "+workNumber+" "+text+" ",
							StepResult.PASS);

				}
				Thread.sleep(3000);

				//driver.close();

			}


			else {
				throw new Exception("work asignment is not populated."
						+ "XPath used is: " + WaveStatus.toString());
			}


		} catch (Exception e) {
			report.addReportStep("Input work asignment Number and Appply",
					"Unable to Input and Apply Wave - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void searchWaveNumberInPacksize() throws Exception {

		try {



			if (wh.isElementPresent(searchIcon)) {
				wh.clickElement(searchIcon);
				Thread.sleep(2000);
			}


			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
			LocalDate localDate = LocalDate.now();

			String format = dtf.format(localDate);
			int cureent= Integer.parseInt(format);
			System.out.println("current date" +cureent);

			int previous=cureent-1;

			if(previous==0) {
				previous=29;
			}
			System.out.println("previous date" +previous);



			driver.findElement(By.xpath("//div[@id='selectedDateRange']")).click();


			driver.findElement(By.xpath("(//table[@class='month1']//td//div[text()='"+previous+"'])[1]")).click();

			driver.findElement(By.xpath("(//table[@class='month2']//td//div[text()='"+cureent+"'])[1]")).click();


			WebElement saveSearch = driver.findElement(By.xpath("//button[@id='saveDatePicker']"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", saveSearch);	

			saveSearch.click();

			if (wh.isElementPresent(searchBtnPackSize)) {
				wh.clickElement(searchBtnPackSize);
				Thread.sleep(2000);
			}

			List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");

			for(int i=0; i<lpns.size();i++) {

				List<WebElement> lpnUI = driver.findElements(By.xpath("//div[@class='ui-grid-cell-contents ng-scope']//a[contains(text(),'"+lpns.get(i)+"')]"));
				List<WebElement> status = driver.findElements(By.xpath("(//div[@class='ui-grid-cell-contents ng-binding ng-scope'])[5]"));
				if(lpns.get(i).equalsIgnoreCase(lpnUI.get(i).getText())&& status.get(i).getText().equalsIgnoreCase("Closed")) {

					report.addReportStep("Search Waves is completed in packsize","OLPN Status : "+status.get(i).getText()+" ",StepResult.PASS);

				}else {

					report.addReportStep("Search Waves is  completed in packsize","OLPN Status : "+status.get(i).getText()+" ",StepResult.FAIL);

				}
			}


		} catch (Exception e) {
			report.addReportStep("OLPN number not displayed",
					"OLPN number not displayed in Packsize - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}


	public void enterValueandScanOlpn() throws Exception {

		try {


			driver.findElement(By.xpath("//input[@id='dataForm:PutPack_INP_PackStatnNbr_input']")).sendKeys("011",Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='dataForm:submitButton']")).click();


			List<String> lpn = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");


			for(int i=0; i<lpn.size();i++) {

				String Status = jd.str_Database_Connection("select lpn_facility_status from lpn where tc_lpn_id='"+lpn.get(i)+"'");
				if(Status.equalsIgnoreCase("20")) {
					report.addReportStep("Beofre Multis packing Status Code","Successfully Multis packing  completed : "+Status+" "+lpn.get(i)+"",StepResult.PASS);
				}else {
					report.addReportStep("Before multis packing Status Code"," Multis packing not completed : "+Status+" "+lpn.get(i)+"",StepResult.FAIL);
					rc.throwTCTerminationException();
				}
				driver.findElement(By.xpath("//input[@id='dataForm:PutPack_INP_oLPNNbr1']")).sendKeys(lpn.get(i),Keys.ENTER);
				driver.findElement(By.xpath("//input[@id='dataForm:PutWallPackStation_VerifyAll_button']")).click();
				Thread.sleep(4000);

				if (wh.isElementPresent(printShippingLabel, 5)) {

					wh.clickElement(printShippingLabel);
					Thread.sleep(2000);

				}



				//				String maxNum = jd.str_Database_Connection("select CURR_NBR from nxt_up_cnt where REC_TYPE_ID='136'");

				//				int max=Integer.parseInt(maxNum)+i;
				//
				//				System.out.println(max);

				//System.out.println("execute C_DIVERT_CONFIRM('"+lpn.get(i)+"','009','69','"+max+"')");

				//jd.str_Store_Proc_Connection("execute C_DIVERT_CONFIRM('"+lpn.get(i)+"','009','69','"+max+"')");

				String afterStaus = jd.str_Database_Connection("select lpn_facility_status from lpn where tc_lpn_id='"+lpn.get(i)+"'");
				if(afterStaus.equalsIgnoreCase("30")) {
					report.addReportStep("After Multis Packing Status Code","Successfully Multis packing  completed : "+afterStaus+" "+lpn.get(i)+"",StepResult.PASS);
				}else {
					report.addReportStep("After Multis Packing Status Code"," Multis packing not completed : "+afterStaus+" "+lpn.get(i)+"",StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}




		} catch (Exception e) {
			report.addReportStep("Input work asignment Number and Appply",
					"Unable to Input and Apply Wave - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}


	public void RouterIntelligrated(String Screen) throws TCTerminationException {
		// TODO Auto-generated method stub

		try {
			WiniumDriverService service;
			WiniumDriver desktop;

			DesktopOptions option = new DesktopOptions();

			option.setApplicationPath("C:\\Users\\BXS8PPN\\Downloads\\WES\\WES\\RouteBuilder.exe");

			//Path for Winium Desktop Driver
			File driverPath = new File(System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"Winium.Desktop.Driver.exe");

			//Port is 9999, you can change it
			service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
			//Thread.sleep(5000);

			service.start();
			desktop = new WiniumDriver(service,option);

			Thread.sleep(2000);

			if(Screen.equalsIgnoreCase("Pick_Pack")) {

				//desktop.findElementByName("Maximize").click();
				Thread.sleep(2000);
				Robot robot = new Robot();  // Robot class throws AWT Exception	
				Thread.sleep(2000); // Thread.sleep throws InterruptedException	
				robot.keyPress(KeyEvent.VK_ALT);  // press arrow down key of keyboard to navigate and select Save radio button	
				robot.keyRelease(KeyEvent.VK_ALT);

				Thread.sleep(2000);  // sleep has only been used to showcase each event separately	
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
				Thread.sleep(2000);	
				robot.keyPress(KeyEvent.VK_C);	
				robot.keyRelease(KeyEvent.VK_C);

				Thread.sleep(2000);	
				robot.keyPress(KeyEvent.VK_W);	
				robot.keyRelease(KeyEvent.VK_W);

				Thread.sleep(2000);	
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);

				Thread.sleep(2000);


				//Screen s=new Screen();

				//				String windowHandle = desktop.getWindowHandle();
				//				desktop.switchTo().window(windowHandle);
				//				// Pattern p= new Pattern("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\DecisionPoint-2.png");
				Thread.sleep(5000);
				//
				Screen s= new Screen();


				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\DecisionPoint.png",DFWMSRunWavesPageObject.decisionPoint);

				s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\DivertInformation.png");

				Thread.sleep(15000);

				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\BoxId.png","05");

				Thread.sleep(5000);

				ArrayList<String> oLPNs = new ArrayList<String>();

				for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
					List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
					oLPNs.addAll(lpns);

				}

				System.out.println("OLPN: "+oLPNs.get(0));


				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\RawBarcode1.png",oLPNs.get(0));

				Thread.sleep(5000);

				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\DestinationCCSID1.png",DFWMSValidationsPageObject.dbStatus1);

				Thread.sleep(5000);

				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\ActualDivertCCSID.png",DFWMSValidationsPageObject.dbStatus1);

				Thread.sleep(5000);

				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\DestinationCCSID2.png","0");

				Thread.sleep(5000);


				s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\SuccessfulDivert.png");

				Thread.sleep(5000);
				s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\ReasonCode.png","Success");

				Thread.sleep(5000);
				s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\WES_Sikuli.sikuli\\SendBtn.png");

				Thread.sleep(5000);



				report.addReportStep("RouteIntelligrated Olpn request",
						"Successfully send Locate Olpn request Intelligrated",
						StepResult.PASS);

			}
			else {


				report.addReportStep("PacketSender request Failed","Failed to send request PacketSender connection Fail",StepResult.FAIL);
				desktop.close();
				rc.throwTCTerminationException();


			}



		} catch (Exception e) {
			report.addReportStep("Pack sender not opened",
					"Unable to open Pack sender " ,
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}
	public void packetSenderRequest(String Screen) throws Exception {

		try {


			WiniumDriverService service;
			WiniumDriver desktop;

			DesktopOptions option = new DesktopOptions();

			option.setApplicationPath("C:\\Program Files\\PacketSender\\packetsender.exe");

			//Path for Winium Desktop Driver
			File driverPath = new File(System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"Winium.Desktop.Driver.exe");

			//Port is 9999, you can change it
			service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
			//Thread.sleep(5000);

			service.start();
			desktop = new WiniumDriver(service,option);

			Thread.sleep(2000);
			//			
			//			String parent = driver.getWindowHandle().toString();
			//	        System.out.println("Parent window handle: " + parent);
			//	        driver.findElement(By.name("Maximize")).click();
			//	        Thread.sleep(3000);
			//	        driver.switchTo().window(parent);

			List array=new ArrayList();
			String text1=jd.str_Database_Connection("select data from cl_message where data like '%ITEMDWN|ADD|"+DFWMSRunWavesPageObject.sWaveNumber+"%' and created_dttm > sysdate -1");

			String[] split = text1.split("PICK");

			String substring = split[0].substring(179, 189);
			System.out.println(substring);

			array.add(substring);
			System.out.println(array.get(0));

			int min = 30000000; // Minimum value of range
			int max = 100000; // Maximum value of range
			// Print the min and max  
			System.out.println("Random value in int from "+ min + " to " + max + ":");
			// Generate random int value from min to max
			int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

			//String s1 = String.valueOf(random_int);
			// Printing the generated random numbers
			System.out.println(random_int);

			int min1 = 1000; // Minimum value of range
			int max1 = 10000; // Maximum value of range
			// Print the min and max  
			System.out.println("Random value in int from "+ min1 + " to " + max1 + ":");
			// Generate random int value from min to max
			int random_int1 = (int)Math.floor(Math.random() * (max1 - min1 + 1) + min1);
			// Printing the generated random numbers
			System.out.println(random_int1);
			if(Screen.equalsIgnoreCase("Locate_Olpn")) {

				randomNumber=String.valueOf(random_int1);
				String Locate="\\02WYNRIGHT|WCSHOST|MANHATTAN|MAHOST|CONTAINERSTATUS|"+random_int+"|"+sWaveNumber+"|"+DFWMSInbounfFlowStepDefn.doId+"|"+DFWMSoLPNsPageObject.soLPNs.get(0)+"|LOCATE|||||CRT"+randomNumber+"|WMMHE\\03";

				System.out.println("Locate: "+Locate);
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetNameEdit").sendKeys("WynHoustonCartPick");
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetASCIIEdit").sendKeys(Locate);
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetIPEdit").sendKeys("lnc105e7.homedepot.com");
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetPortEdit").sendKeys("32004");
				desktop.findElementByName("Send").click();
				report.addReportStep("PacketSender Locate Olpn request",
						"Successfully send Locate Olpn request PacketSender",
						StepResult.PASS);

			}else if(Screen.equalsIgnoreCase("Pick_Pack")) {

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY MM dd HH mm ss");
				LocalDateTime now = LocalDateTime.now();

				String Locate="\\02WYNRIGHT|WCSHOST|MANHATTAN|MAHOST|ITEMSTATUS|"+random_int+"|"+sWaveNumber+"|"+DFWMSInbounfFlowStepDefn.doId+"|"+array.get(0)+"|COMPLETED|CRT"+randomNumber+"|"+DFWMSoLPNsPageObject.soLPNs.get(0)+"|1|"+dtf.format(now)+"|DFCAUTO2|||5831|\\03";

				System.out.println("Pick_Pack: "+Locate);
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetNameEdit").sendKeys("WynHoustonCartPick");
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetASCIIEdit").sendKeys(Locate);
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetIPEdit").sendKeys("lnc105e7.homedepot.com");
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetPortEdit").sendKeys("32002");
				desktop.findElementByName("Send").click();

				report.addReportStep("PacketSender Pick Pack request",
						"Successfully send Pick Pack request PacketSender",
						StepResult.PASS);

			}

			if(desktop.findElementById("MainWindow.centralWidget.splitter.layoutWidget1.trafficLogTable").isDisplayed()){

				report.addReportStep("PacketSender request",
						"Successfully send request PacketSender",
						StepResult.PASS);


				desktop.close();


			}else {


				report.addReportStep("PacketSender request Failed","Failed to send request PacketSender connection Fail",StepResult.FAIL);
				desktop.close();
				rc.throwTCTerminationException();


			}



		} catch (Exception e) {
			report.addReportStep("Pack sender not opened",
					"Unable to open Pack sender " ,
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void OpenWCSInRemote(String Screen) throws Exception {

		try {


			WiniumDriverService service;
			WiniumDriver desktop;

			DesktopOptions option = new DesktopOptions();

			//option.setApplicationPath("C:\\Program Files\\PacketSender\\packetsender.exe");

			//Path for Winium Desktop Driver
			File driverPath = new File(System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"Winium.Desktop.Driver.exe");

			//Port is 9999, you can change it
			service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
			//Thread.sleep(5000);

			//service.start();
			desktop = new WiniumDriver(service,option);

			Thread.sleep(2000);
			//			
			//			String parent = driver.getWindowHandle().toString();
			//	        System.out.println("Parent window handle: " + parent);
			//	        driver.findElement(By.name("Maximize")).click();
			//	        Thread.sleep(3000);
			//	        driver.switchTo().window(parent);

			List array=new ArrayList();

			if(Screen.equalsIgnoreCase("WCS")) {

				System.out.println("Locate: ");
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetNameEdit").sendKeys("WynHoustonCartPick");
				//desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetASCIIEdit").sendKeys(Locate);
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetIPEdit").sendKeys("lnc105e7.homedepot.com");
				desktop.findElementById("MainWindow.centralWidget.packetSetupGroup.packetPortEdit").sendKeys("32004");
				desktop.findElementByName("Send").click();
				report.addReportStep("PacketSender Locate Olpn request",
						"Successfully send Locate Olpn request PacketSender",
						StepResult.PASS);

			}
			if(desktop.findElementById("MainWindow.centralWidget.splitter.layoutWidget1.trafficLogTable").isDisplayed()){

				report.addReportStep("PacketSender request",
						"Successfully send request PacketSender",
						StepResult.PASS);


				desktop.close();


			}else {


				report.addReportStep("PacketSender request Failed","Failed to send request PacketSender connection Fail",StepResult.FAIL);
				desktop.close();
				rc.throwTCTerminationException();


			}



		} catch (Exception e) {
			report.addReportStep("Pack sender not opened",
					"Unable to open Pack sender " ,
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void openRemoteServer(String Screen) throws Exception {

		try {

			//driver.get("https://pim-qa.homedepot.com/PasswordVault/logon.aspx?ReturnUrl=%2fPasswordVault%2fSecureConnect.aspx");

			if(Screen.equalsIgnoreCase("Voice_Picking")) {

				//				jd.dbDFWMSMapping();
				//
				//				String next_tab;
				//
				//				String first_tab = driver.getWindowHandle();
				//
				//				((JavascriptExecutor) driver).executeScript("window.open()");
				//
				//				//Switch to new tab 
				//				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				//				driver.switchTo().window(tabs.get(1));
				//				driver.get("https://pim-qa.homedepot.com/PasswordVault/logon.aspx?ReturnUrl=%2fPasswordVault%2fSecureConnect.aspx");
				//				Thread.sleep(2000);
				//
				//				//driver.manage().window().maximize();
				//
				//				if (wh.isElementPresent(GO, 3)) {
				//					wh.clickElement(GO);
				//
				//				}
				//
				//				if (wh.isElementPresent(AuthMethodBtn, 3)) {
				//					wh.clickElement(AuthMethodBtn);
				//
				//				}
				//
				//
				//
				//				String usernameColumn = CommonDataColumn.RemoteUserName;
				//				String passwordColumn = CommonDataColumn.RemotePassword;
				//
				//				String currentUsername = dataTable.getCommonData(usernameColumn);
				//				String currentPassword = dataTable.getCommonData(passwordColumn);
				//
				//				if (wh.isElementPresent(remoteuserName, 3)) {
				//					wh.clickElement(remoteuserName);
				//					wh.sendKeys(remoteuserName, currentUsername);
				//				} else {
				//					throw new Exception("Unable to locate username input field");
				//				}
				//
				//				if (wh.isElementPresent(remotePassword, 3)) {
				//					wh.clickElement(remotePassword);
				//					wh.sendKeys(remotePassword, currentPassword);
				//				} else {
				//					throw new Exception("Unable to locate password input field");
				//				}
				//
				//				if (wh.isElementPresent(remoteSignIn, 3)) {
				//					wh.clickElement(remoteSignIn);
				//
				//					Thread.sleep(2000);
				//
				//				} else {
				//					throw new Exception("Unable to locate AuthMethodBtn input field");
				//				}
				//
				//				//			report.addReportStep("PIM Login Successful ",
				//				//					"Successfully Logged in Portal",
				//				//						StepResult.PASS);
				//				//				
				//				//			if (wh.isElementPresent(secureConnectBtn, 3)) {
				//				//				wh.clickElement(secureConnectBtn);
				//				//
				//				//			}
				//
				//				wh.waitForPageLoaded();
				//
				//				if(wh.isElementNotPresent(By.xpath("//button[@aria-label='search']"))) {
				//					wh.waitForPageLoaded();
				//
				//					Thread.sleep(30000);
				//				}else {
				//					Thread.sleep(10000);
				//
				//				}
				//
				//				Thread.sleep(25000);
				//
				//
				//				driver.switchTo().frame(0);
				//
				//				if (wh.isElementPresent(SearchAccountText, 3)) {
				//
				//					driver.findElement(SearchAccountText).sendKeys("BXS8PPN");
				//
				//					Thread.sleep(5000);
				//
				//					driver.findElement(By.xpath("//button[@aria-label='search']")).click();
				//
				//					Thread.sleep(10000);
				//
				//				}
				//
				//				if (wh.isElementPresent(connectRemote, 3)) {
				//					wh.clickElement(connectRemote);
				//					Thread.sleep(2000);
				//
				//				}
				//
				//				if (wh.isElementPresent(RemoteMachineName, 3)) {
				//					wh.clickElement(RemoteMachineName);
				//					wh.sendKeys(RemoteMachineName, "wncc923");
				//				}
				//
				//				if (wh.isElementPresent(ContinueBtn, 3)) {
				//					wh.clickElement(ContinueBtn);
				//					Thread.sleep(2000);
				//
				//				}
				//
				//
				//				Thread.sleep(72000);		
				//
				//				Robot robot = new Robot();
				//				robot.keyPress(KeyEvent.VK_WINDOWS);
				//
				//				robot.keyRelease(KeyEvent.VK_WINDOWS);
				//				Thread.sleep(200);
				//				robot.keyPress(KeyEvent.VK_R);
				//				robot.keyRelease(KeyEvent.VK_R);
				//				Thread.sleep(200);
				//				robot.keyPress(KeyEvent.VK_C);
				//				robot.keyRelease(KeyEvent.VK_C);
				//				Thread.sleep(200);
				//				robot.keyPress(KeyEvent.VK_M);
				//				robot.keyRelease(KeyEvent.VK_M);
				//				Thread.sleep(200);
				//				robot.keyPress(KeyEvent.VK_D);
				//				robot.keyRelease(KeyEvent.VK_D);
				//				Thread.sleep(200);
				//				robot.keyPress(KeyEvent.VK_ENTER);
				//				robot.keyRelease(KeyEvent.VK_ENTER);
				//				Thread.sleep(2000);
				//
				//
				////				String text1 = "H:";
				////				StringSelection stringSelection1 = new StringSelection(text1);
				////				Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
				////				clipboard1.setContents(stringSelection1, stringSelection1);
				////				robot.keyPress(KeyEvent.VK_CONTROL);
				////				robot.keyPress(KeyEvent.VK_V);
				////				robot.keyRelease(KeyEvent.VK_CONTROL);
				////				robot.keyRelease(KeyEvent.VK_V);
				////				robot.keyPress(KeyEvent.VK_ENTER);
				////				robot.keyRelease(KeyEvent.VK_ENTER);
				////
				////				Thread.sleep(8000);
				////
				////				String text2 = "cd H:\\Wynright Cart Picking 022 190724";
				////				StringSelection stringSelection2 = new StringSelection(text2);
				////				Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
				////				clipboard2.setContents(stringSelection2, stringSelection2);
				////				robot.keyPress(KeyEvent.VK_CONTROL);
				////				robot.keyPress(KeyEvent.VK_V);
				////				robot.keyRelease(KeyEvent.VK_CONTROL);
				////				robot.keyRelease(KeyEvent.VK_V);
				////				robot.keyPress(KeyEvent.VK_ENTER);
				////				robot.keyRelease(KeyEvent.VK_ENTER);
				//
				//				Thread.sleep(8000);
				//
				//				String text = "echo (timeout /t 10 && echo ready && timeout /t 10 && echo 5871 && timeout /t 10 && echo yes && timeout /t 10 && echo 3 && timeout /t 10 && echo yes && timeout /t 10 && echo 12 && timeout /t 10 && echo yes && timeout /t 10 && echo #CRT01907 && timeout /t 10 && echo yes && timeout /t 10 ) | python run_vad.py\r\n"
				//						+ "echo program ended\r\n"
				//						+ "pause";
				//				StringSelection stringSelection = new StringSelection(text);
				//				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				//				clipboard.setContents(stringSelection, stringSelection);
				//
				//				robot.keyPress(KeyEvent.VK_CONTROL);
				//				robot.keyPress(KeyEvent.VK_V);
				//				robot.keyRelease(KeyEvent.VK_CONTROL);
				//				robot.keyRelease(KeyEvent.VK_V);


			}
			else {

				//wh.clickElement(Maximize);

				String next_tab;

				String first_tab = driver.getWindowHandle();

				((JavascriptExecutor) driver).executeScript("window.open()");

				//Switch to new tab 
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.get("https://pim-qa.homedepot.com/PasswordVault/logon.aspx?ReturnUrl=%2fPasswordVault%2fSecureConnect.aspx");
				Thread.sleep(2000);

				driver.manage().window().maximize();

				if (wh.isElementPresent(GO, 3)) {
					wh.clickElement(GO);

				}

				if (wh.isElementPresent(AuthMethodBtn, 3)) {
					wh.clickElement(AuthMethodBtn);

				}



				String usernameColumn = CommonDataColumn.RemoteUserName;
				String passwordColumn = CommonDataColumn.RemotePassword;

				String currentUsername = dataTable.getCommonData(usernameColumn);
				String currentPassword = dataTable.getCommonData(passwordColumn);

				if (wh.isElementPresent(remoteuserName, 3)) {
					wh.clickElement(remoteuserName);
					wh.sendKeys(remoteuserName, currentUsername);
				} else {
					throw new Exception("Unable to locate username input field");
				}

				if (wh.isElementPresent(remotePassword, 3)) {
					wh.clickElement(remotePassword);
					wh.sendKeys(remotePassword, currentPassword);
				} else {
					throw new Exception("Unable to locate password input field");
				}

				if (wh.isElementPresent(remoteSignIn, 3)) {
					wh.clickElement(remoteSignIn);

					Thread.sleep(2000);

				} else {
					throw new Exception("Unable to locate AuthMethodBtn input field");
				}

				//			report.addReportStep("PIM Login Successful ",
				//					"Successfully Logged in Portal",
				//						StepResult.PASS);
				//				
				//			if (wh.isElementPresent(secureConnectBtn, 3)) {
				//				wh.clickElement(secureConnectBtn);
				//
				//			}

				wh.waitForPageLoaded();

				if(wh.isElementNotPresent(By.xpath("//button[@aria-label='search']"))) {
					wh.waitForPageLoaded();

					Thread.sleep(10000);
				}else {
					Thread.sleep(10000);

				}

				Thread.sleep(5000);


				driver.switchTo().frame(0);

				if (wh.isElementPresent(SearchAccountText, 3)) {

					driver.findElement(SearchAccountText).sendKeys("BXS8PPN");

					Thread.sleep(5000);

					driver.findElement(By.xpath("//button[@aria-label='search']")).click();

					Thread.sleep(20000);

				}

				if (wh.isElementPresent(connectRemote, 3)) {
					wh.clickElement(connectRemote);
					Thread.sleep(2000);

				}

				jd.dbDFWMSMapping();

				if(jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")) {


					if (wh.isElementPresent(RemoteMachineName, 3)) {
						wh.clickElement(RemoteMachineName);
						wh.sendKeys(RemoteMachineName, "wnc1b836");
					}

					if (wh.isElementPresent(ContinueBtn, 3)) {
						wh.clickElement(ContinueBtn);
						Thread.sleep(2000);

					}


					//Robot r= new Robot();
					//driver.switchTo().defaultContent();



				}else {
					if (wh.isElementPresent(RemoteMachineName, 3)) {
						wh.clickElement(RemoteMachineName);
						wh.sendKeys(RemoteMachineName, "waqaap131");
					}

					if (wh.isElementPresent(ContinueBtn, 3)) {
						wh.clickElement(ContinueBtn);
						Thread.sleep(2000);

					}

					driver.switchTo().defaultContent();
					//closebtn();
				}
				Thread.sleep(15000);



				Thread.sleep(30000);
				//			

				//driver.switchTo().defaultContent();

				report.addReportStep("PIM Login Successful ",
						"Successfully Logged in Portal",
						StepResult.PASS);


				Screen s=new Screen();
				Thread.sleep(15000);

				jd.dbDFWMSMapping();

				if(jd.envrnment.equalsIgnoreCase("LG_Main_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")) {

					try {


						Thread.sleep(30000);



						//						
						//						r1.keyPress(KeyEvent.VK_CONTROL);
						//						r1.keyPress(KeyEvent.VK_TAB);		
						//
						//						r1.keyRelease(KeyEvent.VK_CONTROL);
						//						r1.keyRelease(KeyEvent.VK_TAB);		

						//						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\OKBtn-2.png", 10) != null) {
						//
						//							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\OKBtn-2.png");
						//
						//							Thread.sleep(5000);
						//
						//						}


						if(s.exists("C:\\\\Users\\\\BXS8PPN\\\\git\\\\DF_WMS_Automation2019\\\\Troy.sikuli\\\\OM_Display-3.png", 10) != null) {

							Pattern p= new Pattern("C:\\\\Users\\\\BXS8PPN\\\\git\\\\DF_WMS_Automation2019\\\\Troy.sikuli\\\\OM_Display-3.png");

							Thread.sleep(5000);
							s.hover(p);
							s.doubleClick(p);

						}

						Thread.sleep(18000);
						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\UserName-2.png", 10) != null) {

							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\UserName-2.png", "SXJ8188");
							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Password-2.png", 10) != null) {

							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Password-2.png", "1234");
							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\OM_OKBtn-2.png", 10) != null) {

							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\OM_OKBtn-2.png");

							Thread.sleep(15000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\ToolsBtn-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\ToolsBtn-2.png");
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\ToolsBtn-2.png");

							Thread.sleep(10000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\WaveInfoBtn-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\WaveInfoBtn-2.png");
							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\WaveInfoBtn-2.png");

							Thread.sleep(10000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\pickWave-2.png", 10) != null) {

							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\pickWave-2.png");

							Thread.sleep(10000);

							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\pickWave-2.png", DFWMSRunWavesPageObject.sWaveNumber);

							Thread.sleep(15000);

						}


						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\RetrieveWave-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\RetrieveWave-2.png");
							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\RetrieveWave-2.png");

							Thread.sleep(10000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Status-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Status-2.png");
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Status-2.png");

							Thread.sleep(8000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Minimise_OM-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Minimise_OM-2.png");
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Minimise_OM-2.png");

							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Launch_Sim_3.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Launch_Sim_3.png");
							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Launch_Sim_3.png");

							Thread.sleep(25000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\5th_sim-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\5th_sim-2.png");

							Thread.sleep(10000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Sim_3-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Sim_3-2.png");

							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Sim_3-2.png");

							Thread.sleep(10000);


						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Induction_Source-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Induction_Source-2.png");

							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Induction_Source-2.png");


							Thread.sleep(15000);


						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Totenumber_text-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Totenumber_text-2.png");

							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Totenumber_text-2.png");


							Thread.sleep(5000);


						}


						Robot r= new Robot();

						r.keyPress(KeyEvent.VK_BACK_SPACE);
						r.keyRelease(KeyEvent.VK_BACK_SPACE);		

						Thread.sleep(5000);

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Tote_nbr-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Tote_nbr-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Tote_nbr-2.png");


						}


						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Tote_nbr-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Tote_nbr-2.png");

							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Container_Tote_nbr-2.png", ToteValue);
							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Scan_Btn-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Scan_Btn-2.png");

							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Scan_Btn-2.png");
							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Putwall_Icon-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Putwall_Icon-2.png");

							s.delayClick(100);
							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Putwall_Icon-2.png");	
							Thread.sleep(8000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\UserId.png", 10) != null) {

							Thread.sleep(5000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\UserId.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\UserId.png");
							Thread.sleep(5000);
							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png", "SXJ8188");
							Thread.sleep(5000);

						}


						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");
							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\toteNbr_txt-2.png", 10) != null) {

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\toteNbr_txt-2.png");

							s.delayClick(100);
							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\toteNbr_txt-2.png");
							Thread.sleep(5000);
							r.keyPress(KeyEvent.VK_BACK_SPACE);
							r.keyRelease(KeyEvent.VK_BACK_SPACE);		

						}


						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png");

							s.delayClick(100);
							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png", ToteValue);						
							Thread.sleep(5000);

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png", 10) != null) {

							Thread.sleep(5000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

						}

						Thread.sleep(8000);

						//						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Tote1-2.png", 10) != null) {
						//
						//							Thread.sleep(5000);
						//
						//							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Tote1-2.png");
						//
						//							s.delayClick(100);
						//							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Tote1-2.png");
						//
						//							r.keyPress(KeyEvent.VK_BACK_SPACE);
						//							r.keyRelease(KeyEvent.VK_BACK_SPACE);		
						//
						//						}



						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png");

							s.delayClick(100);
							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png",itemValue );

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png", 10) != null) {

							Thread.sleep(5000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\P1-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\P1-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\P1-2.png");

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PushBtn-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PushBtn-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PushBtn-2.png");

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PullBtn.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PullBtn.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PullBtn.png");

						}

						//second scan

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Item_Delete.png", 10) != null) {

							Thread.sleep(5000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Item_Delete.png");

							s.delayClick(100);
							s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Item_Delete.png");

							r.keyPress(KeyEvent.VK_BACK_SPACE);
							r.keyRelease(KeyEvent.VK_BACK_SPACE);		

						}



						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png");

							s.delayClick(100);
							s.type("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Hand_Scanner-2.png",itemValue );

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png", 10) != null) {

							Thread.sleep(5000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\SendBtn_putwall-2.png");

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\P1-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\P1-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\P1-2.png");

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PushBtn-2.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PushBtn-2.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PushBtn-2.png");

						}

						if(s.exists("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PullBtn.png", 10) != null) {

							Thread.sleep(7000);

							s.hover("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PullBtn.png");

							s.delayClick(100);
							s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\PullBtn.png");

						}

						Thread.sleep(5000);

						Robot r1= new Robot();


						r1.keyPress(KeyEvent.VK_CONTROL);
						r1.keyPress(KeyEvent.VK_ALT);
						r1.keyPress(KeyEvent.VK_HOME);

						r1.keyRelease(KeyEvent.VK_CONTROL);		
						r1.keyRelease(KeyEvent.VK_ALT);
						r1.keyRelease(KeyEvent.VK_HOME);

						Thread.sleep(3000);

						r1.keyPress(KeyEvent.VK_ALT);
						r1.keyPress(KeyEvent.VK_TAB);

						r1.keyRelease(KeyEvent.VK_ALT);
						r1.keyRelease(KeyEvent.VK_TAB);

						Thread.sleep(3000);

						driver.switchTo().window(first_tab);


						Thread.sleep(3000);

						report.addReportStep("WCS Launch server executed ",
								"Successfully Launch WCS server",
								StepResult.PASS);


					}catch(Exception e) {

						//ScreenImage capture = s.capture(s.getBounds());

						report.addReportStep("Failed in env :"+jd.envrnment+" server ",
								"Please check the image or flow: "+e+"",
								StepResult.FAIL);

					}


				}
				else if(jd.envrnment.equalsIgnoreCase("LG_2012")) {

					s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sample.sikuli\\OKBtn.png");
					// s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\OKBtn-2.png");
					//s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\Winkey-2.png");
					//s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\RemoteDesktop-2.png");
					//s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\OKBtn.JPG");

					Thread.sleep(15000);
					//s.wait(9000);
					//s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\okBtn-2.png");
					//Pattern p = new Pattern().similar(0.7f);



					Pattern p= new Pattern("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\OMDisplay.png");

					s.doubleClick(p);


					Thread.sleep(5000);

					s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\UserName.png", "config");
					Thread.sleep(5000);
					s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\PasswordText.png", "config");
					Thread.sleep(5000);
					s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\OM_OKbtn.png");

					Thread.sleep(5000);
					s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\Tools.png");

					Thread.sleep(5000);
					s.doubleClick("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\WaveInfo.png");

					Thread.sleep(5000);
					s.click("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\PickWave.png");

					Thread.sleep(5000);

					s.type("C:\\Users\\BXS8PPN\\DFCWMS_Updated\\DF_WMS_Automation2019-DFWMSAutomation2019_WIP\\Sikulix\\PickWave.png", DFWMSRunWavesPageObject.sWaveNumber);

					Thread.sleep(5000);

					s.click("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\RetrieveWaveBtn.png");

					Thread.sleep(5000);

					s.doubleClick("C:\\Users\\BXS8PPN\\git\\DF_WMS_Automation2019\\Troy.sikuli\\Launch_WCS_SIM.png");

				}

			}
		} catch (Exception e) {
			report.addReportStep("Pim server unble to Open",
					"Unable to open PIM Server " ,
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}


	public void ValidateWaveAttributes()
			throws Exception {

		try {
			// Type input into search bar
			if (wh.isElementPresent(WaveStatus, 5)) {

			} else {
				throw new Exception("Wave Status is not populated."
						+ "XPath used is: " + WaveStatus.toString());
			}

			int num = 0;
			while (!wh.getText(WaveStatus).equalsIgnoreCase("Ship wave completed") && !wh.getText(WaveStatus).equalsIgnoreCase("Ship wave completed - Agile Complete")) {
				if(num<25){
					System.out.println(num+wh.getText(WaveStatus));
					wh.clickElement(WaveNumberApply);
					Thread.sleep(10000);
					num++;
				}
				else{
					break;
				}
			}			

			// Validate that DO
			if (wh.getText(WaveStatus).equalsIgnoreCase("Ship wave completed") || wh.getText(WaveStatus).equalsIgnoreCase("Ship wave completed - Agile Complete")) {
				report.addReportStep("Validate Wave status", "Wave "
						+ sWaveNumber + " status is " + wh.getText(WaveStatus),
						StepResult.PASS);
			} else {
				report.addReportStep(
						"Validate Wave Status",
						"Wave Status is not in Ship wave completed status. Wave # "
								+ sWaveNumber + " is in "
								+ wh.getText(WaveStatus), StepResult.FAIL);
				rc.throwTCTerminationException();
			}

		} catch (Exception e) {
			report.addReportStep(
					"Validate Wave Status ",
					"Unable to validate wave " + sWaveNumber + " status "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		if(jd.envrnment.equalsIgnoreCase("LG_2019")||jd.envrnment.equalsIgnoreCase("LG_Q3_2019")) {
			
			closebtn();
			System.out.println("Please dont close wave screen until task release");
		}else {
			
		closebtn();
		}
	}

	public void ValidateWaveResults(String sOrderVal,String sUnitsVal,String sAllocatedVal,String sShortedVal,String sTaskVal,String soLPNsVal) throws Exception {

		try {

			if (wh.isElementPresent(WaveSelect, 5)) {
				wh.clickElement(WaveSelect);
				Thread.sleep(1000);
			}
			if (wh.isElementPresent(WaveView, 5)) {
				wh.clickElement(WaveView);
				waitForWindowToLoad("Wave Details");
				String sTempText = "";
				sTempText = ValidateWaveResultsAttribute(OrderLinesSelected,sOrderVal,"OrderLinesSelected");
				sTempText = sTempText+ValidateWaveResultsAttribute(UnitsRequired,sUnitsVal,"UnitsRequired");
				sTempText = sTempText+ValidateWaveResultsAttribute(UnitsAllocated,sAllocatedVal,"UnitsAllocated");
				sTempText = sTempText+ValidateWaveResultsAttribute(UnitsShorted,sShortedVal,"UnitsShorted");
				sTempText = sTempText+ValidateWaveResultsAttribute(Tasks,sTaskVal,"Tasks");
				sTempText = sTempText+ValidateWaveResultsAttribute(OLPNsSelected,soLPNsVal,"OLPNsSelected");
				if(sTempText.contains("failed")){
					throw new Exception(sTempText);					
				}
				else {
					report.addReportStep("Validate Wave Result Values",
							"Successfully validate the values: "+'\n'+sTempText,
							StepResult.PASS);
				}	
			} 
			else {
				throw new Exception("Wave view button not populated. "
						+ "XPath used is: " + WaveView.toString());
			}


		} catch (Exception e) {
			report.addReportStep("Validate Wave Result Values",
					"Validation Failed. " + e.getMessage(),
					StepResult.WARNING);
			rc.throwTCTerminationException();
		}
		wh.clickElement(WaveViewCancel);
		Thread.sleep(1000);
		waitForWindowToLoad("Waves");	

	}

	public void OpenWaveTasks() throws Exception {

		try {
			if (wh.isElementPresent(WaveSelect, 5)) {
				wh.clickElement(WaveSelect);
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(WaveMore, 5)) {
				wh.clickElement(WaveMore);
				Thread.sleep(1000);

				if (wh.isElementPresent(WaveMoreTasks, 5)) {
					wh.clickElement(WaveMoreTasks);
					//waitForWindowToLoad("Tasks");
					report.addReportStep("Navigate to Tasks screen",
							"Successfully navigated to the Wave Tasks Screen",
							StepResult.PASS);
				} else {
					throw new Exception("Tasks menu not found. "
							+ "XPath used is: " + WaveMoreTasks.toString());
				}
			}
			closebtn();
		} catch (Exception e) {
			report.addReportStep("Navigate to Tasks screen",
					"Unable to navigate to Tasks screen - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

	}

	public String ValidateWaveResultsAttribute(By sActualVal,String sExpVal,String sPrintVal) throws Exception {

		String sResultText = "";

		try {
			if (wh.getText(sActualVal).equalsIgnoreCase(sExpVal)) {
				sResultText = sPrintVal+" value passed. "+System.lineSeparator();

			} else {
				sResultText = sPrintVal+" value failed. "+System.lineSeparator();
			}

		} catch (Exception e) {
			report.addReportStep("Validate Wave Result Values",
					"Validation Failed. " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		return(sResultText);

	}


	public void selectRuleCheckBox(String RuleDesc, String DONBR, String screen)
			throws Exception {

		try {
			String CheckBoxOrderId = null;
			CheckBoxOrderId = searchRuleCheckBox(RuleDesc);
			Thread.sleep(1000);
			if (wh.isElementPresent(MomentumWithDORadioBtn, 5)) {
				wh.clickElement(MomentumWithDORadioBtn);
			}
			Thread.sleep(2000);
			By RuleCheckBoxOrderId = By.id(CheckBoxOrderId);
			Thread.sleep(1000);
			if (wh.isElementPresent(RuleCheckBoxOrderId, 5)) {
				wh.clickElement(RuleCheckBoxOrderId);
			}
			Thread.sleep(1000);
			if (wh.isElementPresent(RuleCheckBoxInput, 5)) {
				wh.focusElement(RuleCheckBoxInput);
				wh.clickElement(RuleCheckBoxInput);
				Thread.sleep(2000);
				if(screen.equalsIgnoreCase("OutboundConvey") || screen.equalsIgnoreCase("LTLMultiStop") 
						|| screen.equalsIgnoreCase("LTLSplitStop") || screen.equalsIgnoreCase("NonConPackBypass")
						||screen.equalsIgnoreCase("ConPackBypass")){
					if (wh.isElementPresent(RuleOperatorInput, 5)) {
						wh.selectValue(RuleOperatorInput, "equals");
						wh.sendKeys(DOParamInput, DONBR.substring(0, Math.min(DONBR.length(), 6)));
					}
				}else if(screen.equalsIgnoreCase("BVR_Mutli_Stop")
						||screen.equalsIgnoreCase("BVR_Split_Shipment")
						||screen.equalsIgnoreCase("BVR_UndoDO")){


					if (wh.isElementPresent(RuleOperatorInput, 5)) {

						wh.selectValue(RuleOperatorInput, "equals");

						wh.sendKeys(DOParamInput, DONBR.substring(0, Math.min(DONBR.length(), 9)));
					}
				}else if(screen.equalsIgnoreCase("ConveyFlagYes")){


					if (wh.isElementPresent(DOParamInput, 5)) {


						wh.sendKeys(DOParamInput, DONBR.substring(0, Math.min(DONBR.length(), 9)));


						wh.clickElement(DOAddBtn);

						wh.selectValue(RuleOperatorAndOr, "And");

						wh.selectValue(RuleOperatorItemConveyFlag, "ITEM WMS Conveyable Flag");

						wh.selectValue(RuleOperatorInput2, "equals");

						wh.sendKeys(DOParamInput7, "Y");


					}

				}
				else if(screen.equalsIgnoreCase("LG_New")||screen.equalsIgnoreCase("BVR_LG")){


					if (wh.isElementPresent(DOParamInput, 5)) {


						wh.sendKeys(DOParamInput, DONBR.substring(0, Math.min(DONBR.length(), 9)));


						wh.clickElement(DOAddBtn);

						wh.selectValue(RuleOperatorAndOr, "And");

						wh.selectValue(RuleOperatorItemConveyFlag, "ITEM WMS Conveyable Flag");

						wh.selectValue(RuleOperatorInput2, "equals");

						wh.sendKeys(DOParamInput7, "Y");


					}

				}

				else if(screen.equalsIgnoreCase("ConveyFlagNo")){


					if (wh.isElementPresent(DOParamInput, 5)) {


						wh.sendKeys(DOParamInput, DONBR.substring(0, Math.min(DONBR.length(), 9)));


						wh.clickElement(DOAddBtn);

						wh.selectValue(RuleOperatorAndOr, "And");

						wh.selectValue(RuleOperatorItemConveyFlag, "ITEM WMS Conveyable Flag");

						wh.selectValue(RuleOperatorInput2, "equals");

						wh.sendKeys(DOParamInput7, "N");


					}

				}

				else if(screen.equalsIgnoreCase("EnvelopMultiOrder_Dallas") 
						|| screen.equalsIgnoreCase("EnvelopSingleTote_Dallas")
						|| screen.equalsIgnoreCase("LTL_Multistop")){
					if (wh.isElementPresent(RuleColumnInput, 5)) {
						wh.selectValue(RuleColumnInput, "Orders Ref 4 (Wave Marker)");
						wh.sendKeys(DOParamInput,DFWMSLTLOutboundFlowStepDefn.refFiled4);
					}
				}/*else if(screen.equalsIgnoreCase("LTL HDU Dallas")){
					if (wh.isElementPresent(DOParamInput1, 5)) {
						wh.sendKeys(DOParamInput1, DONBR);
					}
				}else if(//screen.equalsIgnoreCase("BVR_Dallas_Yard")
						 screen.equalsIgnoreCase("BK3NonShipReady")
					//	|| screen.equalsIgnoreCase("BK1PalletJack")
						//|| screen.equalsIgnoreCase("HazmatFlow")
						){
					if (wh.isElementPresent(DOParamInput2, 5)) {
						wh.sendKeys(DOParamInput2, DONBR);
					}
				}*/else if(screen.equalsIgnoreCase("DallasVAS")
						//|| screen.equalsIgnoreCase("HDUTL_Dallas")
						//|| screen.equalsIgnoreCase("LTLOutboundDallas")
						//||screen.equalsIgnoreCase("SplitShipmentDallas")
						){
					if (wh.isElementPresent(DOParamInput4, 5)) {
						wh.sendKeys(DOParamInput4, DONBR);
					}
				}else if(screen.equalsIgnoreCase("WaveReplenishment"))
					//|| screen.equalsIgnoreCase("UPS_Dallas")
					//|| screen.equalsIgnoreCase("UPS_Dallas_MISP"))
					//|| screen.equalsIgnoreCase("BVR_Dallas_MISP")
					//|| screen.equalsIgnoreCase("BVR_Dallas_MIMP"))
				{
					if (wh.isElementPresent(DOParamInput, 5)) {
						wh.sendKeys(DOParamInput, DONBR);
					}
				}else if( screen.equalsIgnoreCase("WynSoft")
						//|| screen.equalsIgnoreCase("BVR_ShotGun_Dallas")
						){


					if (wh.isElementPresent(DOParamInput5, 5)) {

						Thread.sleep(2000);

						WebElement ele= driver.findElement(DOParamInput5);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView();", ele);	

						Thread.sleep(4000);

						wh.sendKeys(DOParamInput5, DONBR);
					}
				}else{
					wh.sendKeys(DOParamInput, DONBR);
				}

				Thread.sleep(1000);
				report.addReportStepWithScreenshots("Select Wave Rule and Enter DO",
						"Successfully selected " + RuleDesc
						+ " rule and entered DO", StepResult.PASS);
			} else {
				throw new Exception("Wave rule not found. " + "XPath used is: "
						+ RuleCheckBoxInput.toString());
			}
		} catch (Exception e) {
			report.addReportStepWithScreenshots("Select Wave Rule and Enter DO","Unable to select, locate " + RuleDesc + " rule. "+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}
		try {
			if (wh.isElementPresent(RunWaveSubmitBtn, 5)) {
				wh.clickElement(RunWaveSubmitBtn);
				report.addReportStepWithScreenshots("Click Run Wave Submit Button","Successfully clicked run wave submit button",StepResult.PASS);
			} else {
				throw new Exception("Run Wave Submit button is not populated."
						+ "XPath used is: " + RunWaveSubmitBtn.toString());
			}
		} catch (Exception e) {
			report.addReportStepWithScreenshots(
					"Click Run Wave Submit Button",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		try {
			if (wh.isElementPresent(WaveNumber, 5)) {
				Thread.sleep(2000);
				sWaveNumber = wh.getText(WaveNumber);
				report.addReportStep("Wave Number","Successfully started the wave run. Wave number is "+ sWaveNumber, StepResult.PASS);
				System.out.println("Wave number generated is : "+ sWaveNumber);

			} else {
				throw new Exception("Wave Number not found."
						+ "XPath used is: " + WaveNumber.toString());
			}
		} catch (Exception e) {
			report.addReportStepWithScreenshots(
					"Wave Number",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		closebtn();
	}

	public void selectRuleCheckBox2012(String RuleDesc, String DONBR, String screen)
			throws Exception {

		try {

			//			driver.findElement(By.xpath("(//div//span//a[text()='Parameters'])[1]")).click();
			//
			//			Thread.sleep(2000);
			//
			//			driver.findElement(By.xpath("/html/body/form/div[5]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/div/div[5]/div[5]/div/div/table/tbody/tr/td/span[4]/div[3]/div/table/tbody/tr[6]/td[5]/div/input")).click();
			//
			//			Thread.sleep(2000);

			driver.findElement(By.xpath("//div//span//a[text()='Rules']")).click();

			String CheckBoxOrderId = null;
			CheckBoxOrderId = searchRuleCheckBox(RuleDesc);
			Thread.sleep(1000);
			if (wh.isElementPresent(MomentumWithDORadioBtn2012, 5)) {
				wh.clickElement(MomentumWithDORadioBtn2012);
			}
			Thread.sleep(2000);
			//			By RuleCheckBoxOrderId = By.id(CheckBoxOrderId);
			//			Thread.sleep(1000);
			//			if (wh.isElementPresent(RuleCheckBoxOrderId, 5)) {
			//				wh.clickElement(RuleCheckBoxOrderId);
			//			}
			//			Thread.sleep(3000);
			//			
			wh.sendKeys(DOParamInput, DONBR);


			Thread.sleep(3000);
			report.addReportStepWithScreenshots("Select Wave Rule and Enter DO",
					"Successfully selected " + RuleDesc
					+ " rule and entered DO", StepResult.PASS);
		}catch (Exception e) {
			report.addReportStepWithScreenshots(
					"Click Run Wave Submit Button",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}


		try {



			if (wh.isElementPresent(RunWaveSubmitBtn, 5)) {
				wh.clickElement(RunWaveSubmitBtn);
				report.addReportStepWithScreenshots("Click Run Wave Submit Button","Successfully clicked run wave submit button",StepResult.PASS);
			} else {
				throw new Exception("Run Wave Submit button is not populated."
						+ "XPath used is: " + RunWaveSubmitBtn.toString());
			}
		} catch (Exception e) {
			report.addReportStepWithScreenshots(
					"Click Run Wave Submit Button",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		try {
			if (wh.isElementPresent(WaveNumber, 5)) {
				Thread.sleep(2000);
				sWaveNumber = wh.getText(WaveNumber);
				report.addReportStep("Wave Number","Successfully started the wave run. Wave number is "+ sWaveNumber, StepResult.PASS);
				System.out.println("Wave number generated is : "+ sWaveNumber);

			} else {
				throw new Exception("Wave Number not found."
						+ "XPath used is: " + WaveNumber.toString());
			}
		} catch (Exception e) {
			report.addReportStepWithScreenshots(
					"Wave Number",
					"Unable to locate Run Wave Submit button. "
							+ e.getMessage(), StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		closebtn();


	}

	public void copyWave() throws Exception {
		wh.clickElement(Maximize);
		Thread.sleep(1000);
		driver.switchTo().frame(0);

		if (wh.isElementPresent(CopyWaveDesc, 5)) {
			wh.sendKeys(CopyWaveDesc, "BK1 - Non Ship Ready");
		}

		if (wh.isElementPresent(RunWaveApply, 5)) {
			wh.clickElement(RunWaveApply);
		}

		String RunWaveCheckBoxInput = searchWaveCheckBox("BK1 - Non Ship Ready");
		By WaveCheckBoxInput = By.xpath(".//INPUT[@id='checkAll_c"
				+ RunWaveCheckBoxInput + "_dataForm:listView:dataTable']");

		if (wh.isElementPresent(WaveCheckBoxInput, 5)) {
			wh.clickElement(WaveCheckBoxInput);
		}

		if (wh.isElementPresent(CopyWave, 5)) {
			wh.clickElement(CopyWave);
		}

		if (wh.isElementPresent(CopyWave, 5)) {
			wh.clearElement(CopyWave);
			wh.sendKeys(CopyWave, "Automation - BK1 - Non Ship Ready");
		}

		if (wh.isElementPresent(CopyWaveSave, 5)) {
			wh.clickElement(CopyWaveSave);
		}

		//run wave
	}

	public void OpenWaveLpns() throws TCTerminationException {

		try {
			if (wh.isElementPresent(WaveSelect, 5)) {
				wh.clickElement(WaveSelect);
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(WaveMore, 5)) {
				wh.clickElement(WaveMore);
				Thread.sleep(1000);

				if (wh.isElementPresent(WaveMoreLpns, 5)) {
					wh.clickElement(WaveMoreLpns);
					//waitForWindowToLoad("Tasks");
					report.addReportStep("Navigate to Lpns screen",
							"Successfully navigated to the Wave Lpns Screen",
							StepResult.PASS);
				} else {
					throw new Exception("Tasks menu not found. "
							+ "XPath used is: " + WaveMoreLpns.toString());
				}
			}
			closebtn();
		} catch (Exception e) {
			report.addReportStep("Navigate to Lpns screen",
					"Unable to navigate to Lpns screen - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void runWaves(String screen) {
		// TODO Auto-generated method stub

		try{
			if (wh.isElementPresent(DOClose, 5)) {
				wh.clickElement(DOClose);
				Thread.sleep(1000);
			}
			if(screen.equalsIgnoreCase("BK1NSRDallas_UndoDO_IP")
					|| screen.equalsIgnoreCase("BK1NSRBaltimore_UndoDO_IP")){
				if (wh.isElementPresent(DOSelect1, 5)) {
					wh.clickElement(DOSelect1);
					Thread.sleep(1000);
				}
			}else{
				if (wh.isElementPresent(DOSelectAll, 5)) {
					wh.clickElement(DOSelectAll);
					Thread.sleep(1000);
				}

			}
			if (wh.isElementPresent(DOMore, 5)) {
				wh.clickElement(DOMore);
				Thread.sleep(1000);
			}
			if (wh.isElementPresent(DOWave, 5)) {
				wh.clickElement(DOWave);
				Thread.sleep(3000);

			}

			closebtn();
			//			Set<String> handles = driver.getWindowHandles();
			//			String current = driver.getWindowHandle();
			//			handles.remove(current);
			//			String newTab = handles.iterator().next();
			//			driver.switchTo().window(newTab);
			//			
			report.addReportStep("Run Wave ",
					"Successfully Run Wave  "+sWaveNumber+" completed",
					StepResult.PASS);


		} catch (Exception e) {
			report.addReportStep("Run Wave Multi DO",
					"Unable to Undo Wave - " + e.getMessage(),
					StepResult.FAIL);
		}

	}
	public void undoWaveDO(String screen) {
		try{
			if (wh.isElementPresent(DOClose, 5)) {
				wh.clickElement(DOClose);
				Thread.sleep(1000);
			}
			if(screen.equalsIgnoreCase("BK1NSRDallas_UndoDO_IP")
					|| screen.equalsIgnoreCase("BK1NSRBaltimore_UndoDO_IP")){
				if (wh.isElementPresent(DOSelect1, 5)) {
					wh.clickElement(DOSelect1);
					Thread.sleep(1000);
				}
			}else{
				if (wh.isElementPresent(DOSelectAll, 5)) {
					wh.clickElement(DOSelectAll);
					Thread.sleep(1000);
				}
			}
			if (wh.isElementPresent(DOMore, 5)) {
				wh.clickElement(DOMore);
				Thread.sleep(1000);
			}
			if (wh.isElementPresent(DOUndoWave, 5)) {
				wh.clickElement(DOUndoWave);
				Thread.sleep(1000);
			}
			if (wh.isElementPresent(DOAlert, 5)) {
				wh.clickElement(DOAlert);
				Thread.sleep(1000);
			}
			report.addReportStep("Undo Wave Multi DO",
					"Undo Wave Success" ,
					StepResult.PASS);
			closebtn();
		} catch (Exception e) {
			report.addReportStep("Undo Wave Multi DO",
					"Unable to Undo Wave - " + e.getMessage(),
					StepResult.FAIL);
		}
	}

	public void undoWave() throws TCTerminationException {

		try {
			if (wh.isElementPresent(WaveSelect, 5)) {
				wh.clickElement(WaveSelect);
				Thread.sleep(1000);
			}

			if (wh.isElementPresent(UndoWave, 5)) {
				wh.clickElement(UndoWave);
				Thread.sleep(2000);

				driver.switchTo().alert().accept();
				Thread.sleep(1000);
				driver.switchTo().alert().accept();
				Thread.sleep(1000);
			}
			int num = 0;
			while (!wh.getText(WaveStatus).equalsIgnoreCase("Ship wave cancelled")) {
				if(num<10){
					System.out.println(num+wh.getText(WaveStatus));
					wh.clickElement(WaveNumberApply);
					Thread.sleep(10000);
					num++;
				}
				else{
					break;
				}
			}	

			if (wh.getText(WaveStatus).equalsIgnoreCase("Ship wave cancelled")){

				report.addReportStep("Wave Undo is Successful",
						"Ship Wave Is Cancelled Successfully in  Waves Screen",
						StepResult.PASS);
			}
			else {
				throw new Exception("Wave not found. Cannot Undo Wave "
						+ "XPath used is: " + UndoWave.toString());
			}

			closebtn();
		} catch (Exception e) {
			report.addReportStep("Navigate to Waves screen",
					"Unable to navigate to Waves screen - " + e.getMessage(),
					StepResult.FAIL);
			rc.throwTCTerminationException();
		}



	}
}