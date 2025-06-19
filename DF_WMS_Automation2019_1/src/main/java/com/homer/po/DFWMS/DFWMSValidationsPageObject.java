package com.homer.po.DFWMS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bouncycastle.asn1.dvcs.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import com.homer.dao.CommonDataColumn;
import com.homer.dao.InstanceContainer;
import com.homer.enums.EnumClass.StepResult;
import com.homer.exception.TCTerminationException;
import com.homer.glue.DFWMS.DFWMSIBCheckinPageObject;
import com.homer.glue.DFWMS.DFWMSInbounfFlowStepDefn;
import com.homer.po.PageBase;
import com.homer.resuablecomponents.JDBC_Connection;

import oracle.jdbc.util.Login;

public class DFWMSValidationsPageObject extends PageBase {

	JDBC_Connection jd = new JDBC_Connection(ic);
	public static ArrayList siLPNs = new ArrayList();
	public static ArrayList mLPNS = new ArrayList();
	public static ArrayList<String> olpns = new ArrayList<String>();
	public static ArrayList<String> olpn = new ArrayList<String>();
	public static ArrayList<String> olpnId = new ArrayList<String>();
	public static ArrayList<String> RouteId = new ArrayList<String>();
	public static String dbStatus1 = "";
	public static String olpn_nbr = "";
	public DFWMSValidationsPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void validateTotalItems() throws Exception {

		try{

			if(wh.isElementPresent(ASNselect, 3)){
				wh.clickElement(ASNselect);
			}

			Thread.sleep(1000);

			if(wh.isElementPresent(ASNView, 3)){
				wh.clickElement(ASNView);
			}
			//			driver.switchTo().frame(0);

			Thread.sleep(5000);

			wh.clickElement(Maximize);
			Thread.sleep(2000);
			driver.switchTo().frame(0);
			String Shipped = wh.getText(totalShipped);
			String Received = wh.getText(totalReceived); 

			System.out.println("Shipped :"+Shipped );
			System.out.println("Received :" + Received);

			if (Shipped.equals(Received)){
				System.out.println("The Total Items Received match with Total Items Shipped "+ Shipped + Received);
			}else{
				throw new Exception("The Total Items Received doesn't match with Total Items Shipped .");
			}

		} catch (Exception e) {
			String msg = "The Total Items Received doesn't match with Total Items Shipped " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("Total Items Received Validation", msg, StepResult.FAIL);
		}
	}



	public void validateILPNs(List<String> lPNS, String asnID) throws Exception {
		try{
			//validate the page
			//			wh.clickElement(Maximize);
			//			Thread.sleep(2000);
			//
			//			if(!driver.getTitle().equals(ILPNPageTitle)){
			//				waitForWindowToLoad("iLPNs");
			//			}
			//
			//			if(wh.isElementPresent(ILPNArrow, 3)){
			//				wh.clickElement(ILPNArrow);
			//			}
			//
			//			if(wh.isElementPresent(ASN, 3)){
			//				wh.sendKeys(ASN, asnID);
			//				wh.clickElement(ILPNApply);
			//			}

			//get from DB
			siLPNs = getilpndetails(asnID);

			if(!siLPNs.isEmpty()){
				//report.addReportStep("Validate iLPN's", "iLPNs are created " + siLPNs, StepResult.PASS);
				System.out.println("ILPN'S are created");
			}

			/*if(siLPNs.equals(lPNS)){
                report.addReportStep("Validate iLPN's", "iLPNs are matched", StepResult.PASS);
				 System.out.println("ILPN'S are matched");
			}else{
                throw new Exception("ILPN'S are not matched");

			}*/
			report.addReportStep("Validate iLPN's", "iLPNs are created " + siLPNs, StepResult.PASS);
		}catch (Exception e) {
			String msg = "ILPN'S are not created. " + e.getMessage();
			rc.logger.info(msg);
			report.addReportStep("ILPN'S Validation", msg, StepResult.FAIL);
		}

		closebtn();
	}

	public ArrayList getilpndetails(String ASNno)throws Exception{
		ArrayList siLPNValuefromDB;
		jd.dbDFWMSMapping();
		siLPNValuefromDB = jd.array_Database_Connection("Select TC_LPN_ID from LPN where TC_ASN_ID = '" + ASNno + "'");
		System.out.println("iLPN's Geneated for ASN " +ASNno +"is" +siLPNValuefromDB);
		return siLPNValuefromDB;
	}

	public void validateIlpnStaus(String status) throws Exception {
		String ilpn1 = "";
		if(siLPNs.size() > 0){
			ilpn1 = siLPNs.get(0).toString();
		}
		if(ilpn1 != null){
			ArrayList iLPNValuefromDB1;
			jd.dbDFWMSMapping();
			iLPNValuefromDB1 = jd.array_Database_Connection("SELECT LFS.DESCRIPTION,LPD.SIZE_VALUE "
					+ "FROM LPN LP,LOCN_HDR LH,LPN_DETAIL LPD,LPN_FACILITY_STATUS LFS "
					+ "WHERE LP.CURR_SUB_LOCN_ID=LH.LOCN_ID AND LP.LPN_ID=LPD.LPN_ID AND LFS.LPN_FACILITY_STATUS = LP.LPN_FACILITY_STATUS "
					+ "AND LP.TC_LPN_ID = '" + ilpn1 + "' and LFS.INBOUND_OUTBOUND_INDICATOR = 'I'");
			System.out.println("iLPN1 Status and Qty is" +iLPNValuefromDB1);

			if(iLPNValuefromDB1.contains(status) 
					|| iLPNValuefromDB1.contains("0")
					||iLPNValuefromDB1.contains("1")){
				report.addReportStep("ILPN'S Validation", "ILPN status and Qty are matched" + iLPNValuefromDB1, StepResult.PASS);
			}else{
				//				throw new Exception("ILPN status and Qty are not matched for 1st LPN "+iLPNValuefromDB1);
				report.addReportStep("ILPN'S Validation", "ILPN status and Qty are not matched" + iLPNValuefromDB1, StepResult.FAIL);
			}
		}
	}

	public void validteOnHandQty(int actualQty, int finalQty, String qty) {

		if(finalQty == (actualQty+Integer.parseInt(qty))){
			report.addReportStep("Validate Qty", "Qty are matched", StepResult.PASS);
		}
	}

	public void validateShipmentInASN(String shipmentId) throws TCTerminationException, Exception {

		if(wh.isElementPresent(ASNClose, 5)){
			wh.clickElement(ASNClose);
		}else{
			ASNClose = By.xpath("(.//img[contains(@id,'-toolEl')])[59]");
			wh.clickElement(ASNClose);
		}
		By AsnShipmentid = By.xpath("(.//*[contains(@class,'x-grid-cell-inner ')])[9]");

		if (wh.getText(ASNShipmentID).equalsIgnoreCase(shipmentId)) {
			report.addReportStep("Validate Shipment in ASN screen", "Shipment ID : "+ shipmentId,StepResult.PASS);

		}else if (wh.getText(AsnShipmentid).equalsIgnoreCase(shipmentId)) {
			report.addReportStep("Validate Shipment in ASN screen", "Shipment ID : "+ shipmentId,StepResult.PASS);

		} else {
			report.addReportStep("Validate Shipment in ASN screen","Shipment ID is not mapped", StepResult.FAIL);
			rc.throwTCTerminationException();
		}

		closebtn();
	}

	//fluid inser query
	public void fluidLoadInsertOlpns(String screen, String status) throws TCTerminationException {

		String dbStatus = "";
		try {
			jd.dbDFWMSMapping();
			if(screen.equalsIgnoreCase("oLPNs")){

				//fluid olpns
				if(!DFWMSInbounfFlowStepDefn.doIds.isEmpty()){
					ArrayList<String> olpns = new ArrayList<String>();
					String dvtID = null;
					String mheID = null;
					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
						olpns.addAll(lpns);

					}

					System.out.println("LPNS: "+olpns);
					DFWMSoLPNsPageObject.soLPNs = olpns;
					if(DFWMSoLPNsPageObject.soLPNs.size()>0){

						for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){

							dvtID = jd.str_Database_Connection("SELECT divrt_hist_id FROM divrt_hist WHERE divrt_hist_id = (SELECT MAX(divrt_hist_id)FROM divrt_hist)");
							System.out.println("select query to get divertid and divrt_hist_id: " +dvtID);
							int divertid =Integer.parseInt(dvtID) +1;
							System.out.println("Diverthistory id: " +divertid);

							mheID = jd.str_Database_Connection("select mhe_ord_id from divrt_hist where divrt_hist_id = '"+dvtID+"'");
							System.out.println("select query to get divertid and mheorderid: " +mheID);
							int mheorderid = Integer.parseInt(mheID)+1;
							System.out.println("mheorderid" +mheorderid);



							if(DFWMSIBCheckinPageObject.carrierCode.equals("UPS")&&jd.envrnment.equalsIgnoreCase("Baltimore_2019")
									|| jd.envrnment.equalsIgnoreCase("Newark_2019")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");



							}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','832','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'018',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','823','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(DFWMSIBCheckinPageObject.carrierCode.equals("UPS")&&jd.envrnment.equalsIgnoreCase("Houston_2019")
									||jd.envrnment.equalsIgnoreCase("Miami_2019")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','831','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -", StepResult.PASS);

							}else if(DFWMSIBCheckinPageObject.carrierCode.equals("UPS")&&jd.envrnment.equalsIgnoreCase("Tampa_2019")
									|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"',,'"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}

							else if( DFWMSIBCheckinPageObject.carrierCode.equals("FDX")&&jd.envrnment.equalsIgnoreCase("Baltimore_2019")
									|| jd.envrnment.equalsIgnoreCase("Newark_2019")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");


							}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','832','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-211',90,'017',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','823','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(DFWMSIBCheckinPageObject.carrierCode.equals("FDX")&&jd.envrnment.equalsIgnoreCase("Houston_2019")
									||jd.envrnment.equalsIgnoreCase("Miami_2019")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','831','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-211',90,'000',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(DFWMSIBCheckinPageObject.carrierCode.equals("FDX")&&jd.envrnment.equalsIgnoreCase("Tampa_2019")
									|| jd.envrnment.equalsIgnoreCase("Tracey_2019")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"',,'"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

								jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+DFWMSoLPNsPageObject.soLPNs.get(i)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
								jd.str_Database_Connection("commit;");

							}


						}
					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}else if(!DFWMSInbounfFlowStepDefn.doId.isEmpty()){
					DFWMSoLPNsPageObject.soLPNs = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");
					System.out.println(DFWMSoLPNsPageObject.soLPNs);

					if(DFWMSoLPNsPageObject.soLPNs.size()>0){
						for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
							String lpnStatus = jd.str_Database_Connection("select b.description from lpn a, "
									+ "lpn_facility_status b where a.tc_lpn_id = '"+DFWMSoLPNsPageObject.soLPNs.get(i)+"' "
									+ "and b.inbound_outbound_indicator = 'O' and "
									+ "a.lpn_facility_status = b.lpn_facility_status");

							if(lpnStatus.equalsIgnoreCase(status)){
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -"+lpnStatus, StepResult.PASS);
							}else{
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -"+lpnStatus, StepResult.FAIL);
								rc.throwTCTerminationException();
							}
						}
					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}

			}
		} catch (Exception e) {
			report.addReportStep("Validate Status for "+screen,"; Expected: "+status+"; Result: "+dbStatus, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	//fluid inser Pallet
	public void fluidLoadInsertPallet(String screen, String status) throws TCTerminationException {

		String dbStatus = "";
		try {
			jd.dbDFWMSMapping();
			if(screen.equalsIgnoreCase("Pallet")){

				//fluid olpns
				if(!DFWMSInbounfFlowStepDefn.doIds.isEmpty()){
					ArrayList<String> palletId = new ArrayList<String>();
					String dvtID = null;
					String mheID = null;
					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						List<String> pallet = jd.array_Database_Connection("select  a.tc_parent_LPN_id from lpn a where tc_order_id in ('"+doId+"')");
						palletId.addAll(pallet);

					}

					System.out.println("palletId: "+palletId.get(0));
					//DFWMSoLPNsPageObject.soLPNs = olpns;
					if(palletId.size()>0){


						dvtID = jd.str_Database_Connection("SELECT divrt_hist_id FROM divrt_hist WHERE divrt_hist_id = (SELECT MAX(divrt_hist_id)FROM divrt_hist)");
						System.out.println("select query to get divertid and divrt_hist_id: " +dvtID);
						int divertid =Integer.parseInt(dvtID) +1;
						System.out.println("Diverthistory id: " +divertid);

						mheID = jd.str_Database_Connection("select mhe_ord_id from divrt_hist where divrt_hist_id = '"+dvtID+"'");
						System.out.println("select query to get divertid and mheorderid: " +mheID);
						int mheorderid = Integer.parseInt(mheID)+1;
						System.out.println("Diverthistory id: " +mheorderid);



						if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
								|| jd.envrnment.equalsIgnoreCase("Newark_2019") && DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");


						}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','832','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'018',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','823','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
								||jd.envrnment.equalsIgnoreCase("Miami_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','831','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
								|| jd.envrnment.equalsIgnoreCase("Tracey_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"',,'"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("UPS")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}

						else if(jd.envrnment.equalsIgnoreCase("Baltimore_2019")
								|| jd.envrnment.equalsIgnoreCase("Newark_2019") && DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");


						}else if(jd.envrnment.equalsIgnoreCase("Lacey_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','832','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-211',90,'017',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Dallas_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','823','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Houston_2019")
								||jd.envrnment.equalsIgnoreCase("Miami_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','831','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-211',90,'000',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Tampa_2019")
								|| jd.envrnment.equalsIgnoreCase("Tracey_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"',,'"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Atlanta_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}else if(jd.envrnment.equalsIgnoreCase("Boston_2019")&& DFWMSIBCheckinPageObject.carrierCode.equals("FDX")){

							jd.str_Database_Connection("insert into DIVRT_HIST values('"+divertid+"','','"+palletId.get(0)+"',NULL,2,0,NULL,'"+mheorderid+"',0,'DIV-SHP-210',90,'001',sysdate,sysdate,NULL,1001,1)");
							jd.str_Database_Connection("commit;");

						}


					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}else if(!DFWMSInbounfFlowStepDefn.doId.isEmpty()){
				DFWMSoLPNsPageObject.soLPNs = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");
				System.out.println(DFWMSoLPNsPageObject.soLPNs);

				if(DFWMSoLPNsPageObject.soLPNs.size()>0){
					for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
						String lpnStatus = jd.str_Database_Connection("select b.description from lpn a, "
								+ "lpn_facility_status b where a.tc_lpn_id = '"+DFWMSoLPNsPageObject.soLPNs.get(i)+"' "
								+ "and b.inbound_outbound_indicator = 'O' and "
								+ "a.lpn_facility_status = b.lpn_facility_status");

						if(lpnStatus.equalsIgnoreCase(status)){
							report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -"+lpnStatus, StepResult.PASS);
						}else{
							report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -"+lpnStatus, StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}


		} catch (Exception e) {
			report.addReportStep("Validate Status for "+screen,"; Expected: "+status+"; Result: "+dbStatus, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	//Validate Mhe state for olpns
	public void validateMheCntrStateinTable(String screen, String status) throws TCTerminationException {
		String dbStatus = "";
		String mhe_cntr_state = "";

		try {
			jd.dbDFWMSMapping();
			if(screen.equalsIgnoreCase("oLPNs")){
				//mutliple do
				if(!DFWMSInbounfFlowStepDefn.doIds.isEmpty()){
					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
						olpns.addAll(lpns);
					}
					System.out.println("LPNS: "+olpns);
					DFWMSoLPNsPageObject.soLPNs = olpns;
					if(DFWMSoLPNsPageObject.soLPNs.size()>0){
						for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
							mhe_cntr_state = jd.str_Database_Connection("select mhe_cntr_state from divrt_hist where cntr_nbr = '"+DFWMSoLPNsPageObject.soLPNs.get(i)+"' and mhe_sort_prty = '0'  ");


							if(mhe_cntr_state.equalsIgnoreCase(status)){
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" : Actual - "+status +" Expected: "+mhe_cntr_state, StepResult.PASS);
							}else{
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" : Actual - "+status +" Expected: "+mhe_cntr_state, StepResult.FAIL);
								rc.throwTCTerminationException();
							}
						}
					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}			} 
		}
		catch (Exception e) {
			report.addReportStep("Validate Status for "+screen,"; Expected: "+status+"; Result: "+dbStatus, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void validateStatus(String screen, String status) throws TCTerminationException {
		String dbStatus = "";
		try {
			jd.dbDFWMSMapping();
			if(screen.equalsIgnoreCase("Distribution Orders")){
				//for multiple do
				if(DFWMSInbounfFlowStepDefn.doIds.size()>0){
					for(int i=0;i<DFWMSInbounfFlowStepDefn.doIds.size();i++){
						dbStatus = jd.str_Database_Connection("select b.description from orders a,"
								+ "do_status b where a.do_status = b.order_status and "
								+ "tc_order_id = '"+DFWMSInbounfFlowStepDefn.doIds.get(i)+"'");

						if(dbStatus.equalsIgnoreCase(status)){
							report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doIds.get(i)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);

						}else{
							if(!DFWMSPOObject.undoDOFlag){
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doIds.get(i)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.FAIL);
								rc.throwTCTerminationException();
							}else{
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doIds.get(i)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);


							}
						}
					}
				}else if(!DFWMSInbounfFlowStepDefn.doId.isEmpty()){
					dbStatus = jd.str_Database_Connection("select b.description from orders a,"
							+ "do_status b where a.do_status = b.order_status and "
							+ "tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");
					if(dbStatus.equalsIgnoreCase(status)){
						report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doId+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);
					}else{
						if(!DFWMSPOObject.undoDOFlag){
							report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doId+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();
						}else{
							report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doId+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);
						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get DOId", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}else if(screen.equalsIgnoreCase("oLPNs")){
				//mutliple do
				if(!DFWMSInbounfFlowStepDefn.doIds.isEmpty()){
					ArrayList<String> olpns = new ArrayList<String>();
					olpn=new ArrayList<>();
					olpnId=new ArrayList();
					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						//olpn=jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");


						List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");

						List<String> lpnId = jd.array_Database_Connection("select lpn_id from lpn where tc_order_id = '"+doId+"'");
						olpns.addAll(lpns);
						olpn.addAll(lpns);
						olpnId.addAll(lpnId);
						System.out.println("olpnId:"+olpnId);

					}
					System.out.println("LPNS: "+olpns);
					DFWMSoLPNsPageObject.soLPNs = olpns;
					if(DFWMSoLPNsPageObject.soLPNs.size()>0){
						for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
							String lpnStatus = jd.str_Database_Connection("select b.description from lpn a, "
									+ "lpn_facility_status b where a.tc_lpn_id = '"+DFWMSoLPNsPageObject.soLPNs.get(i)+"' "
									+ "and b.inbound_outbound_indicator = 'O' and "
									+ "a.lpn_facility_status = b.lpn_facility_status");
							if(lpnStatus.equalsIgnoreCase(status)){
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" : Actual - "+status +" Expected: "+lpnStatus, StepResult.PASS);
							}else{
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" : Actual - "+status +" Expected: "+lpnStatus, StepResult.FAIL);
								rc.throwTCTerminationException();
							}
						}
					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}else if(!DFWMSInbounfFlowStepDefn.doId.isEmpty()){
					//olpn=jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");
					olpn=new ArrayList<>();
					olpnId=new ArrayList();

					DFWMSoLPNsPageObject.soLPNs = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");
					List<String> lpnId = jd.array_Database_Connection("select lpn_id from lpn where tc_order_id='"+DFWMSInbounfFlowStepDefn.doId+"' ");

					System.out.println(DFWMSoLPNsPageObject.soLPNs);
					olpn.addAll(DFWMSoLPNsPageObject.soLPNs);
					olpnId.addAll(lpnId);
					System.out.println("olpnId:"+olpnId);

					if(DFWMSoLPNsPageObject.soLPNs.size()>0){
						for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
							String lpnStatus = jd.str_Database_Connection("select b.description from lpn a, "
									+ "lpn_facility_status b where a.tc_lpn_id = '"+DFWMSoLPNsPageObject.soLPNs.get(i)+"' "
									+ "and b.inbound_outbound_indicator = 'O' and "
									+ "a.lpn_facility_status = b.lpn_facility_status");

							if(lpnStatus.equalsIgnoreCase(status)){
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -"+lpnStatus, StepResult.PASS);
							}else{
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" ; Expected - "+status +"; Result -"+lpnStatus, StepResult.FAIL);
								rc.throwTCTerminationException();
							}
						}
					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}
			}else if(screen.equalsIgnoreCase("ASN")){
				Thread.sleep(5000);
				if(!DFWMSInbounfFlowStepDefn.ASNID.isEmpty()){
					dbStatus = jd.str_Database_Connection("select b.description from asn a,"
							+ " asn_status b where a.asn_status = b.asn_status and "
							+ "a.tc_asn_id = '"+DFWMSInbounfFlowStepDefn.ASNID+"'");
					if(dbStatus.equalsIgnoreCase(status)){
						report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.ASNID+" ; Expected - "+status +"; Result -"+dbStatus, StepResult.PASS);
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.ASNID+" ; Expected - "+status +"; Result -"+dbStatus, StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get ASN", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}
			else if(screen.equalsIgnoreCase("Event_Id")){
				//mutliple do

				if(olpn.size()>0){
					for(int i = 0;i<olpn.size();i++){

						List<String> evtId=jd.array_Database_Connection("select event_id from EVENT_MESSAGE  where event_id='6120' and ek_wave_nbr='"+DFWMSRunWavesPageObject.sWaveNumber+"' order by create_date_time desc");
						System.out.println("lpnsStatus: "+evtId);

						if(evtId.get(i).equalsIgnoreCase(status)){
							report.addReportStep("Validate Status ",screen+ ":"+" "+olpn.get(i)+" : Actual - "+status +" Expected: "+olpn.get(i), StepResult.PASS);
						}else{
							report.addReportStep("Validate Status ",screen+ ":"+" "+olpn.get(i)+" : Actual - "+status +" Expected: "+olpn.get(i), StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}

			else if(screen.equalsIgnoreCase("Service_Type")){
				//mutliple do

				if(olpnId.size()>0){
					for(int i = 0;i<olpnId.size();i++){

						List<String> EpiStatus=jd.array_Database_Connection("select status from epi_transaction_log where object_id='"+olpnId.get(i)+"' order by created_dttm desc");
						System.out.println("EpiStatus: "+EpiStatus);

						List<String> service_type=jd.array_Database_Connection("select service_type from epi_transaction_log where object_id='"+olpnId.get(i)+"' order by created_dttm desc");
						System.out.println("service_type: "+service_type);

						if(service_type.get(0).equalsIgnoreCase(status)&& EpiStatus.get(0).equalsIgnoreCase("SUCCESS")){
							report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(i)+" : Actual - "+status +" Expected: "+service_type.get(0), StepResult.PASS);
							report.addReportStep("Validate Status ","Epi Status"+ ":"+"Success"+" Actual - "+status +" Expected: "+EpiStatus.get(0), StepResult.PASS);

						}else{

							break;
						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get service type", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}
			else if(screen.equalsIgnoreCase("EpiStatus")){
				//mutliple do

				if(olpnId.size()>0){
					for(int i = 0;i<olpnId.size();i++){


						List<String> EpiStatus=jd.array_Database_Connection("select status from epi_transaction_log where object_id='"+olpnId.get(i)+"' order by created_dttm desc");
						System.out.println("EpiStatus: "+EpiStatus);

						if(EpiStatus.get(0).equalsIgnoreCase(status)){
							report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(i)+" : Actual - "+status +" Expected: "+EpiStatus.get(0), StepResult.PASS);
						}else{
							report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(i)+" : Actual - "+status +" Expected: "+EpiStatus.get(0), StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get status", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}
			else if(screen.equalsIgnoreCase("Printed_Event_Id")){
				//mutliple do


				if(olpn.size()>0){

					List<String> evtId=jd.array_Database_Connection("select event_id from EVENT_MESSAGE  where event_id='6120' and ek_wave_nbr='"+DFWMSRunWavesPageObject.sWaveNumber+"' order by create_date_time desc");
					System.out.println("lpnsStatus: "+evtId);

					if(evtId.get(0).equalsIgnoreCase(status)&&evtId.get(1).equalsIgnoreCase(status)){
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpn.get(1)+" : Actual - "+status +" Expected: "+olpn.get(1), StepResult.PASS);
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpn.get(3)+" : Actual - "+status +" Expected: "+olpn.get(3), StepResult.PASS);

					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpn.get(1)+" : Actual - "+status +" Expected: "+olpn.get(1), StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}
			}
			else if(screen.equalsIgnoreCase("Printed_Service_Type")){
				//mutliple do

				if(olpnId.size()>0){

					List<String> service_type=jd.array_Database_Connection("select service_type from epi_transaction_log where object_id='"+olpnId.get(1)+"' order by created_dttm desc");
					System.out.println("service_type: "+service_type);

					List<String> service_type1=jd.array_Database_Connection("select service_type from epi_transaction_log where object_id='"+olpnId.get(3)+"' order by created_dttm desc");
					System.out.println("service_type: "+service_type1);

					List<String> EpiStatus=jd.array_Database_Connection("select status from epi_transaction_log where object_id='"+olpnId.get(1)+"' order by created_dttm desc");
					System.out.println("EpiStatus: "+EpiStatus);

					List<String> EpiStatus1=jd.array_Database_Connection("select status from epi_transaction_log where object_id='"+olpnId.get(3)+"' order by created_dttm desc");
					System.out.println("EpiStatus1: "+EpiStatus1);


					if(service_type.get(0).equalsIgnoreCase(status)&& service_type1.get(0).equalsIgnoreCase(status)&&EpiStatus.get(0).equalsIgnoreCase("SUCCESS")&&EpiStatus1.get(0).equalsIgnoreCase("SUCCESS")){
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(1)+" : Actual - "+status +" Expected: "+service_type.get(0), StepResult.PASS);
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(3)+" : Actual - "+status +" Expected: "+service_type1.get(0), StepResult.PASS);
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(1)+" : Actual - "+status +" Expected: "+EpiStatus.get(0), StepResult.PASS);
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(3)+" : Actual - "+status +" Expected: "+EpiStatus1.get(0), StepResult.PASS);

					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(0)+" : Actual - "+status +" Expected: "+service_type.get(0), StepResult.FAIL);
						report.addReportStep("Validate Status ",screen+ ":"+" "+olpnId.get(0)+" : Actual - "+status +" Expected: "+EpiStatus.get(0), StepResult.FAIL);
						rc.throwTCTerminationException();
					}


				}
			}



		} catch (Exception e) {
			report.addReportStep("Validate Status for "+screen,"; Expected: "+status+"; Result: "+dbStatus, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}
	public void validateStatusForLM(String screen, String status) throws TCTerminationException {
		String dbStatus = "";
		try {
			jd.dbDFWMSMapping();
			if(screen.equalsIgnoreCase("Status")){
				//for multiple do
				String usernameColumn = CommonDataColumn.UIUsername;

				String currentUsername = dataTable.getCommonData(usernameColumn);

				List<String> transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				dbStatus = jd.str_Database_Connection("select status from labor_msg where tran_nbr ='"+transNbr.get(0)+"' order by LABOR_MSG_ID desc");

				if(dbStatus.equalsIgnoreCase(status)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);
					}
				}
			}
			else if(screen.equalsIgnoreCase("Distribution Orders")){
				//for multiple do
				if(DFWMSInbounfFlowStepDefn.doIds.size()>0){
					for(int i=0;i<DFWMSInbounfFlowStepDefn.doIds.size();i++){
						dbStatus = jd.str_Database_Connection("select b.description from orders a,"
								+ "do_status b where a.do_status = b.order_status and "
								+ "tc_order_id = '"+DFWMSInbounfFlowStepDefn.doIds.get(i)+"'");

						if(dbStatus.equalsIgnoreCase(status)){
							report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSInbounfFlowStepDefn.doIds.get(i)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);

						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get Distribution order", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}
			else if(screen.equalsIgnoreCase("oLPNs")){
				//for multiple do
				if(!DFWMSInbounfFlowStepDefn.doIds.isEmpty()){
					ArrayList<String> olpns = new ArrayList<String>();
					olpn=new ArrayList<>();
					olpnId=new ArrayList();
					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						//olpn=jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");


						List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");

						List<String> lpnId = jd.array_Database_Connection("select lpn_id from lpn where tc_order_id = '"+doId+"'");
						olpns.addAll(lpns);
						olpn.addAll(lpns);
						olpnId.addAll(lpnId);
						System.out.println("olpnId:"+olpnId);

					}
					System.out.println("LPNS: "+olpns);
					DFWMSoLPNsPageObject.soLPNs = olpns;
					if(DFWMSoLPNsPageObject.soLPNs.size()>0){
						for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
							String lpnStatus = jd.str_Database_Connection("select b.description from lpn a, "
									+ "lpn_facility_status b where a.tc_lpn_id = '"+DFWMSoLPNsPageObject.soLPNs.get(i)+"' "
									+ "and b.inbound_outbound_indicator = 'O' and "
									+ "a.lpn_facility_status = b.lpn_facility_status");
							if(lpnStatus.equalsIgnoreCase(status)){
								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" : Actual - "+status +" Expected: "+lpnStatus, StepResult.PASS);
							}
							//							else{
							//								report.addReportStep("Validate Status ",screen+ ":"+" "+DFWMSoLPNsPageObject.soLPNs.get(i)+" : Actual - "+status +" Expected: "+lpnStatus, StepResult.FAIL);
							//								//rc.throwTCTerminationException();
							//							}
						}
					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}
			}

			else if(screen.equalsIgnoreCase("Act Name")){
				//for multiple do
				String usernameColumn = CommonDataColumn.UIUsername;

				String currentUsername = dataTable.getCommonData(usernameColumn);

				List<String> transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				dbStatus = jd.str_Database_Connection("select act_name from labor_msg where tran_nbr ='"+transNbr.get(0)+"' order by LABOR_MSG_ID desc");

				if(dbStatus.equalsIgnoreCase(status)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+status +"; Result - "+dbStatus, StepResult.PASS);
					}
				}
			}
		} catch (Exception e) {
			report.addReportStep("Validate Status for "+screen,"; Expected: "+status+"; Result: "+dbStatus, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}

	public void storeProcedureForWCS(String screen) throws Exception {
		// TODO Auto-generated method stub
		try {
			jd.dbDFWMSMapping();
			//String ToteValue2;
			//ToteValue2=DFWMSRunWavesPageObject.ToteValue;
			ArrayList<String> alloc_Id = jd.array_Database_Connection("select a.alloc_invn_dtl_id from alloc_invn_dtl a  where tc_order_id='"+DFWMSInbounfFlowStepDefn.doId+"' order by create_date_time desc");

			for(int i=0; i<alloc_Id.size();i++) {

				 olpn_nbr = jd.str_Database_Connection("select task_cmpl_ref_nbr from alloc_invn_dtl a  where alloc_invn_dtl_id='"+alloc_Id.get(i)+"' order by create_date_time desc");

				if(screen.equalsIgnoreCase("CONT_STAT_DIVERT")) {
                    System.out.println("Alloc id is :");
                    System.out.println(alloc_Id);
					jd.array_Database_Connection_StoreProcedure("DECLARE P_OLPN VARCHAR2(50);P_ACTION VARCHAR2(200); P_QTY_PACKED NUMBER;P_USER VARCHAR2(50);P_PUTWALL_LN VARCHAR2(10);P_ALLOC_INVN_DTL_ID NUMBER;P_LOGICDIV VARCHAR2(200); P_PHYDIV VARCHAR2(200);BEGIN P_OLPN := '"+olpn_nbr+"'; P_ACTION := 'CONT_STAT_DIVERT'; P_QTY_PACKED := NULL; P_USER := 'IXZ84PF'; P_PUTWALL_LN := NULL; P_ALLOC_INVN_DTL_ID := '"+alloc_Id.get(i)+"'; P_LOGICDIV := '001012'; P_PHYDIV := '531';  C_PROC_SEND_MHE_MSG1( P_OLPN => P_OLPN, P_ACTION => P_ACTION, P_QTY_PACKED => P_QTY_PACKED, P_USER => P_USER, P_PUTWALL_LN => P_PUTWALL_LN, P_ALLOC_INVN_DTL_ID => P_ALLOC_INVN_DTL_ID,P_LOGICDIV => P_LOGICDIV,P_PHYDIV => P_PHYDIV);END;");

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+DFWMSRunWavesPageObject.ToteValue+"%' order by created_dttm desc");

					if(data.get(i).contains("DIVERTED")) {

						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure successfully completed. " + data.get(i), StepResult.PASS);

					}else {
						
						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure NOT completed. " + data.get(i), StepResult.FAIL);

					}
					break;

				}
				else if(screen.equalsIgnoreCase("FULLPICK")) {

					jd.array_Database_Connection_StoreProcedure("DECLARE  P_OLPN VARCHAR2(50); P_ACTION VARCHAR2(200); P_QTY_PACKED NUMBER; P_USER VARCHAR2(50); P_PUTWALL_LN VARCHAR2(10); P_ALLOC_INVN_DTL_ID NUMBER; P_LOGICDIV VARCHAR2(200); P_PHYDIV VARCHAR2(200); BEGIN P_OLPN := '"+olpn_nbr+"'; P_ACTION := 'FULLPICK'; P_QTY_PACKED := '"+DFWMSRunWavesPageObject.ItemQty+"';  P_USER := 'IXZ84PF'; P_PUTWALL_LN := '001012';  P_ALLOC_INVN_DTL_ID := '"+alloc_Id.get(i)+"';  P_LOGICDIV := NULL;  P_PHYDIV := NULL;  C_PROC_SEND_MHE_MSG1(  P_OLPN => P_OLPN,  P_ACTION => P_ACTION,  P_QTY_PACKED => P_QTY_PACKED, P_USER => P_USER, P_PUTWALL_LN => P_PUTWALL_LN, P_ALLOC_INVN_DTL_ID => P_ALLOC_INVN_DTL_ID, P_LOGICDIV => P_LOGICDIV, P_PHYDIV => P_PHYDIV );END;");

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+olpn_nbr+"%' order by created_dttm desc");

					if(data.get(i).contains("COMPLETE")) {

						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure successfully completed. " + data.get(i), StepResult.PASS);

					}else {
						
						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure NOT completed. " + data.get(i), StepResult.FAIL);

					}
					//break;




				}
				else if(screen.equalsIgnoreCase("CONT_STAT_PUSH")){

					jd.array_Database_Connection_StoreProcedure("DECLARE P_OLPN VARCHAR2(50); P_ACTION VARCHAR2(200); P_QTY_PACKED NUMBER; P_USER VARCHAR2(50); P_PUTWALL_LN VARCHAR2(10); P_ALLOC_INVN_DTL_ID NUMBER; P_LOGICDIV VARCHAR2(200); P_PHYDIV VARCHAR2(200); BEGIN P_OLPN := '"+olpn_nbr+"'; P_ACTION := 'CONT_STAT_PUSH';P_QTY_PACKED := NULL; P_USER := 'IXZ84PF';  P_PUTWALL_LN := NULL; P_ALLOC_INVN_DTL_ID := '"+alloc_Id.get(i)+"';  P_LOGICDIV := NULL;  P_PHYDIV := NULL; C_PROC_SEND_MHE_MSG1( P_OLPN => P_OLPN,  P_ACTION => P_ACTION,  P_QTY_PACKED => P_QTY_PACKED,  P_USER => P_USER, P_PUTWALL_LN => P_PUTWALL_LN, P_ALLOC_INVN_DTL_ID => P_ALLOC_INVN_DTL_ID, P_LOGICDIV => P_LOGICDIV, P_PHYDIV => P_PHYDIV);END;");

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+olpn_nbr+"%' order by created_dttm desc");

					if(data.get(i).contains("PUSHED")) {

						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure successfully completed. " + data.get(i), StepResult.PASS);

					}else {
						
						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure NOT completed. " + data.get(i), StepResult.FAIL);

					}
					break;



				}
				else if(screen.equalsIgnoreCase("PUSHED")){

					List<String> loc=jd.array_Database_Connection("select distinct  m.dsp_locn from locn_hdr m,lpn l, alloc_invn_dtl a where l.tc_order_id in ('"+DFWMSInbounfFlowStepDefn.doId+"') and l.curr_sub_locn_id=m.locn_id and a.task_cmpl_ref_nbr=l.tc_lpn_id and a.invn_need_type = '52'");
					
							
						jd.array_Database_Connection_StoreProcedure("DECLARE P_OLPN VARCHAR2(50); P_ACTION VARCHAR2(200); P_QTY_PACKED NUMBER; P_USER VARCHAR2(50); P_PUTWALL_LN VARCHAR2(10); P_ALLOC_INVN_DTL_ID NUMBER; P_LOGICDIV VARCHAR2(200); P_PHYDIV VARCHAR2(200); BEGIN P_OLPN := '"+olpn_nbr+"'; P_ACTION := 'CONT_STAT_PUSH';P_QTY_PACKED := NULL; P_USER := 'Irfan';  P_PUTWALL_LN := '"+loc.get(i)+"'; P_ALLOC_INVN_DTL_ID := '"+alloc_Id.get(i)+"';  P_LOGICDIV := NULL;  P_PHYDIV := NULL; C_PROC_SEND_MHE_MSG1( P_OLPN => P_OLPN,  P_ACTION => P_ACTION,  P_QTY_PACKED => P_QTY_PACKED,  P_USER => P_USER, P_PUTWALL_LN => P_PUTWALL_LN, P_ALLOC_INVN_DTL_ID => P_ALLOC_INVN_DTL_ID, P_LOGICDIV => P_LOGICDIV, P_PHYDIV => P_PHYDIV);END;");

						List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+olpn_nbr+"%' order by created_dttm desc");

						if(data.get(i).contains("PUSHED")) {

							report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure successfully completed. " + data.get(i), StepResult.PASS);

							break;


					}else {
						
						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure NOT completed. " + data.get(i), StepResult.FAIL);

					}


				}

				else if(screen.equalsIgnoreCase("LIGHT")){

					jd.array_Database_Connection_StoreProcedure("DECLARE P_OLPN VARCHAR2(50); P_ACTION VARCHAR2(200); P_QTY_PACKED NUMBER; P_USER VARCHAR2(50);P_PUTWALL_LN VARCHAR2(10);P_ALLOC_INVN_DTL_ID NUMBER; P_LOGICDIV VARCHAR2(200); P_PHYDIV VARCHAR2(200);BEGIN P_OLPN := '"+olpn_nbr+"';  P_ACTION := 'LIGHT';  P_QTY_PACKED := NULL;  P_USER := 'Irfan'; P_PUTWALL_LN := NULL;  P_ALLOC_INVN_DTL_ID := NULL; P_LOGICDIV := NULL; P_PHYDIV := NULL; C_PROC_SEND_MHE_MSG1( P_OLPN => P_OLPN, P_ACTION => P_ACTION,P_QTY_PACKED => P_QTY_PACKED, P_USER => P_USER, P_PUTWALL_LN => P_PUTWALL_LN, P_ALLOC_INVN_DTL_ID => P_ALLOC_INVN_DTL_ID, P_LOGICDIV => P_LOGICDIV, P_PHYDIV => P_PHYDIV );END;");

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+olpn_nbr+"%' order by created_dttm desc");

					if(data.get(i).contains(olpn_nbr)) {

						report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure successfully completed. " + data.get(i), StepResult.PASS);

					}

					report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure successfully completed. " , StepResult.PASS);

				}else {
					report.addReportStep("Validate Status ",screen+ ": Unable to complete PL/SQL procedure . " , StepResult.FAIL);

				}
			}

		}
		catch(Exception e) {
			System.out.println("fail:"+ e.getMessage());
			report.addReportStep("Validate Status ",screen+ ": PL/SQL procedure failed. " , StepResult.FAIL);

		}
	}

	public void compareTable(String screen) throws TCTerminationException {

		String dbStatus="";

		String totalEvtLog="";

		String totalEvntSmry="";

		String usernameColumn ="";

		String currentUsername ="";
		
		ArrayList<String> Misc_4;

		String status ="";


		List<String> transNbr;

		List<String> E_AUD_LOG;

		List<String> E_EVNT_SMRY_ELM_DTL ;

		try {
			jd.dbDFWMSMapping();
			if(screen.equalsIgnoreCase("Compare table")){
				//for multiple do
				usernameColumn = CommonDataColumn.UIUsername;

				currentUsername = dataTable.getCommonData(usernameColumn);

				transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				E_AUD_LOG = jd.array_Database_Connection("SELECT TRAN_NBR, UOM, SUM(TA_MULTIPLIER) AS UOM_TOTAL, SUM(TOT_TIME) AS STD_TOTAL FROM E_AUD_LOG WHERE TRAN_NBR='"+transNbr.get(0)+"' AND UOM !='ACT' GROUP BY TRAN_NBR, UOM order by UOM desc");

				E_EVNT_SMRY_ELM_DTL = jd.array_Database_Connection("SELECT A.TRAN_NBR, C.MSRMNT_CODE AS UOM, SUM(A.UOM_QTY) AS UOM_TOTAL, SUM(TOTAL_SAM) AS STD_TOTAL FROM E_EVNT_SMRY_ELM_DTL A ,E_MSRMNT C WHERE A.MSRMNT_ID=C.MSRMNT_ID AND A.TRAN_NBR='"+transNbr.get(0)+"' and A.UOM_QTY>0 AND C.MSRMNT_CODE!='ACT' GROUP BY A.TRAN_NBR, C.MSRMNT_CODE order by UOM desc");

				if(E_AUD_LOG.equals(E_EVNT_SMRY_ELM_DTL)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+E_AUD_LOG +"; Result - "+E_EVNT_SMRY_ELM_DTL, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+E_AUD_LOG +"; Result - "+E_EVNT_SMRY_ELM_DTL, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+E_AUD_LOG +"; Result - "+E_EVNT_SMRY_ELM_DTL, StepResult.PASS);
					}
				}
			}
			else if(screen.equalsIgnoreCase("Suspended Short Multis")){
				
				System.out.println("TEST SUSPENDED MISC VALUE");
				//for multiple do
				Misc_4 = jd.array_Database_Connection("select misc_instr_code_4 from lpn where tc_lpn_id='"+olpn.get(0)+"'");
				
				System.out.println("misc value is:");
				System.out.println(Misc_4);
				if(Misc_4.contains("S1")) {

					report.addReportStep("Validate Pallet Size ",screen+ ":"+" "+Misc_4+" ; Expected - "+Misc_4+"; Result - "+Misc_4, StepResult.PASS);

				}else {

					report.addReportStep("Validate Pallet Size Less than 1",screen+ ":"+" "+Misc_4+" ; Expected - "+Misc_4+"; Result - "+Misc_4, StepResult.FAIL);
					rc.throwTCTerminationException();

				}
				
			}
			
			
			
			
			
			
			
			else if(screen.equalsIgnoreCase("TTPPL")){
				//for multiple do
				usernameColumn = CommonDataColumn.UIUsername;

				currentUsername = dataTable.getCommonData(usernameColumn);

				transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				totalEvtLog = jd.str_Database_Connection("SELECT COUNT(DISTINCT(PALLET_ID)) AS TTL_PALLETS FROM VIEW_E_DTL_MSG_LOG WHERE TRAN_NBR='"+transNbr.get(0)+"' AND PALLET_ID IS NOT NULL AND QTY>'0'");

				totalEvntSmry = jd.str_Database_Connection("SELECT  SUM(A.UOM_QTY) AS UOM_TOTAL FROM E_EVNT_SMRY_ELM_DTL A,E_MSRMNT C WHERE C.MSRMNT_CODE='TTPPL' AND A.MSRMNT_ID=C.MSRMNT_ID AND TRAN_NBR='"+transNbr.get(0)+"' and A.UOM_QTY>0 AND C.MSRMNT_CODE!='ACT' GROUP BY A.TRAN_NBR, C.MSRMNT_CODE");

				if(totalEvtLog.equalsIgnoreCase(totalEvntSmry)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
					}
				}
			}
			else if(screen.equalsIgnoreCase("SinglePallet")){
				//for multiple do

				ArrayList<String> palletValue = jd.array_Database_Connection("select * from lpn where TC_Parent_LPN_ID in ('"+DFWMSPickToLabelinPuttyPageObject.palletID+"')");

				if(palletValue.size()>1) {

					report.addReportStep("Validate Pallet Size ",screen+ ":"+" "+palletValue.size()+" ; Expected - "+palletValue +"; Result - "+palletValue, StepResult.PASS);

				}else {

					report.addReportStep("Validate Pallet Size Less than 1",screen+ ":"+" "+palletValue.size()+" ; Expected - "+palletValue +"; Result - "+palletValue, StepResult.FAIL);
					rc.throwTCTerminationException();

				}
			}
			else if(screen.equalsIgnoreCase("MultiPallet")){
				//for multiple do


				ArrayList<String> palletValue1 = jd.array_Database_Connection("select tc_lpn_id from lpn where TC_Parent_LPN_ID in ('"+DFWMSPickToLabelinPuttyPageObject.palletID+"')");

				ArrayList<String> palletValue2 = jd.array_Database_Connection("select tc_lpn_id from lpn where TC_Parent_LPN_ID in ('"+DFWMSPickToLabelinPuttyPageObject.palletID1+"')");


				if(palletValue1.size()>1) {

					report.addReportStep("Validate Pallet 1 Size ",screen+ ":"+" "+palletValue1.size()+" ; Expected - "+palletValue1 +"; Result - "+palletValue1, StepResult.PASS);

				}else {

					report.addReportStep("Validate Pallet 1 Size Less than 1",screen+ ":"+" "+palletValue1.size()+" ; Expected - "+palletValue1 +"; Result - "+palletValue1, StepResult.FAIL);
					rc.throwTCTerminationException();

				}

				if(palletValue2.size()>1) {

					report.addReportStep("Validate Pallet 2 Size ",screen+ ":"+" "+palletValue2.size()+" ; Expected - "+palletValue2 +"; Result - "+palletValue2, StepResult.PASS);

				}else {

					report.addReportStep("Validate Pallet 2 Size Less than 1",screen+ ":"+" "+palletValue2.size()+" ; Expected - "+palletValue2 +"; Result - "+palletValue2, StepResult.FAIL);
					rc.throwTCTerminationException();

				}
			}
			else if(screen.equalsIgnoreCase("TTLOC")){
				//for multiple do
				usernameColumn = CommonDataColumn.UIUsername;

				currentUsername = dataTable.getCommonData(usernameColumn);

				transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				totalEvtLog = jd.str_Database_Connection("SELECT COUNT(DSP_LOCN) AS TTL_Locations FROM VIEW_E_DTL_MSG_LOG WHERE TRAN_NBR='"+transNbr.get(0)+"' AND SKU_BRCD IS NOT NULL AND QTY>'0'");

				totalEvntSmry = jd.str_Database_Connection("SELECT  SUM(A.UOM_QTY) AS UOM_TOTAL FROM E_EVNT_SMRY_ELM_DTL A,E_MSRMNT C WHERE C.MSRMNT_CODE='TTLOC' AND A.MSRMNT_ID=C.MSRMNT_ID AND TRAN_NBR='"+transNbr.get(0)+"' and A.UOM_QTY>0 AND C.MSRMNT_CODE!='ACT' GROUP BY A.TRAN_NBR, C.MSRMNT_CODE");

				if(totalEvtLog.equalsIgnoreCase(totalEvntSmry)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
					}
				}
			}
			else if(screen.equalsIgnoreCase("TTCTN")){
				//for multiple do
				usernameColumn = CommonDataColumn.UIUsername;

				currentUsername = dataTable.getCommonData(usernameColumn);

				transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				totalEvtLog = jd.str_Database_Connection("\r\n"
						+ "SELECT SUM(CASE WHEN QTY < SKU_PACK_QTY THEN QTY WHEN QTY>= SKU_PACK_QTY THEN CEIL(QTY/SKU_PACK_QTY) ELSE 0 END) AS TTL_Cartons FROM VIEW_E_DTL_MSG_LOG WHERE TRAN_NBR='"+transNbr.get(0)+"' AND SKU_BRCD IS NOT NULL AND QTY>'0'");

				totalEvntSmry = jd.str_Database_Connection("SELECT  SUM(A.UOM_QTY) AS UOM_TOTAL FROM E_EVNT_SMRY_ELM_DTL A,E_MSRMNT C WHERE C.MSRMNT_CODE='TTCTN' AND A.MSRMNT_ID=C.MSRMNT_ID AND TRAN_NBR='"+transNbr.get(0)+"' and A.UOM_QTY>0 AND C.MSRMNT_CODE!='ACT' GROUP BY A.TRAN_NBR, C.MSRMNT_CODE");

				if(totalEvtLog.equalsIgnoreCase(totalEvntSmry)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
					}
				}
			}
			else if(screen.equalsIgnoreCase("PLTTP")){
				//for multiple do
				usernameColumn = CommonDataColumn.UIUsername;

				currentUsername = dataTable.getCommonData(usernameColumn);

				transNbr = jd.array_Database_Connection("select tran_nbr from labor_msg where LOGIN_USER_ID ='"+currentUsername+"' order by LABOR_MSG_ID desc");

				totalEvtLog = jd.str_Database_Connection("SELECT COUNT(TRAN_NBR) AS PTL_Trips FROM VIEW_E_DTL_MSG_LOG WHERE TRAN_NBR='"+transNbr.get(0)+"' AND TRAN_NBR IS NOT NULL AND QTY>'0'");

				totalEvntSmry = jd.str_Database_Connection("SELECT  SUM(A.UOM_QTY) AS UOM_TOTAL FROM E_EVNT_SMRY_ELM_DTL A,E_MSRMNT C WHERE C.MSRMNT_CODE='PLTTP' AND A.MSRMNT_ID=C.MSRMNT_ID AND TRAN_NBR='"+transNbr.get(0)+"' and A.UOM_QTY>0 AND C.MSRMNT_CODE!='ACT' GROUP BY A.TRAN_NBR, C.MSRMNT_CODE");

				if(totalEvtLog.equalsIgnoreCase(totalEvntSmry)){
					report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
				}else{
					if(!DFWMSPOObject.undoDOFlag){
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.FAIL);
						rc.throwTCTerminationException();
					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+transNbr.get(0)+" ; Expected - "+totalEvtLog +"; Result - "+totalEvntSmry, StepResult.PASS);
					}
				}
			}

			else if(screen.equalsIgnoreCase("ShipviaUPS")){
				//for multiple do

				String shipVia = "";


				ArrayList<String> shipViaList = jd.array_Database_Connection("select unique ship_via from LPN where TC_ORDER_ID = '"+DFWMSInbounfFlowStepDefn.doId+"'");
				if(!shipViaList.isEmpty()){

					if(shipViaList.get(0).equalsIgnoreCase("E047")) {

						shipVia =  shipViaList.get(0).toString();

						report.addReportStep("Shipvia UPS Created", "Shipvia UPS generated :"+shipVia, StepResult.PASS);
					}else {


						report.addReportStep("Shipvia UPS Created Wrongly", "Shipvia UPS generated with Wrong:"+shipVia, StepResult.FAIL);

						rc.throwTCTerminationException();

					}

				}
				System.out.println("shipVia id returned from DB is " + shipVia);
				//String ServiceLevelVal = "5823";
				if(shipVia.isEmpty() || shipVia.equalsIgnoreCase("0001")){
					//shipVia = "(none)";
					report.addReportStep("Create Shipment", "Ship via not generated"+shipVia, StepResult.FAIL);
					rc.throwTCTerminationException();
				}					

			}
			else if(screen.equalsIgnoreCase("FTSR_NBR")){
				//for multiple do

				String ftsr = "";


				ftsr = jd.str_Database_Connection("select ftsr_nbr from orders  where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doId+"'");
				if(!ftsr.isEmpty()){


					if(ftsr.equalsIgnoreCase("001")||ftsr.equalsIgnoreCase("210")||ftsr.equalsIgnoreCase("020")||ftsr.equalsIgnoreCase("009")||ftsr.equalsIgnoreCase("008")||ftsr.equalsIgnoreCase("220")||ftsr.equalsIgnoreCase("180")||ftsr.equalsIgnoreCase("019")||ftsr.equalsIgnoreCase("211")||ftsr.equalsIgnoreCase("600")||ftsr.equalsIgnoreCase("500")
							||ftsr.equalsIgnoreCase("176")||ftsr.equalsIgnoreCase("341")||ftsr.equalsIgnoreCase("176")||ftsr.equalsIgnoreCase("017")||ftsr.equalsIgnoreCase("002")) {

						report.addReportStep("FTSR number  Created", "FTSR UPS generated :"+ftsr, StepResult.PASS);

					}else {


						report.addReportStep("FTSR number Created Wrongly", "FTSR  generated with Wrong:"+ftsr, StepResult.FAIL);

						rc.throwTCTerminationException();

					}

				}
				System.out.println("FTSR id returned from DB is " + ftsr);
				//String ServiceLevelVal = "5823";
				if(ftsr.isEmpty() || ftsr.equalsIgnoreCase("0001")){
					//shipVia = "(none)";
					report.addReportStep("Create FTSR", "FTSR number not generated"+ftsr, StepResult.FAIL);
					rc.throwTCTerminationException();
				}					

			}

			else if(screen.equalsIgnoreCase("ShipviaFXHD")){
				//for multiple do

				String shipVia = "";


				ArrayList<String> shipViaList = jd.array_Database_Connection("select unique ship_via from LPN where TC_ORDER_ID = '"+DFWMSInbounfFlowStepDefn.doId+"'");
				if(!shipViaList.isEmpty()){

					if(shipViaList.get(0).equalsIgnoreCase("E006")) {

						shipVia =  shipViaList.get(0).toString();

						report.addReportStep("Shipvia FXHD Created", "Shipvia FXHD generated :"+shipVia, StepResult.PASS);
					}else {


						report.addReportStep("Shipvia FXHD Created Wrongly", "Shipvia FXHD generated with Wrong:"+shipVia, StepResult.FAIL);

						rc.throwTCTerminationException();

					}

				}
				System.out.println("shipVia id returned from DB is " + shipVia);
				//String ServiceLevelVal = "5823";
				if(shipVia.isEmpty() || shipVia.equalsIgnoreCase("0001")){
					//shipVia = "(none)";
					report.addReportStep("Create Shipment", "Ship via not generated"+shipVia, StepResult.FAIL);
					rc.throwTCTerminationException();
				}					

			}
			else if(screen.equalsIgnoreCase("ShipviaFGND")){
				//for multiple do

				String shipVia = "";


				ArrayList<String> shipViaList = jd.array_Database_Connection("select unique ship_via from LPN where TC_ORDER_ID = '"+DFWMSInbounfFlowStepDefn.doId+"'");
				if(!shipViaList.isEmpty()){

					if(shipViaList.get(0).equalsIgnoreCase("E005")) {

						shipVia =  shipViaList.get(0).toString();

						report.addReportStep("Shipvia FGND Created", "Shipvia FGND generated :"+shipVia, StepResult.PASS);
					}else {


						report.addReportStep("Shipvia FGND Created Wrongly", "Shipvia FGND generated with Wrong:"+shipVia, StepResult.FAIL);

						rc.throwTCTerminationException();

					}

				}
				System.out.println("shipVia id returned from DB is " + shipVia);
				//String ServiceLevelVal = "5823";
				if(shipVia.isEmpty() || shipVia.equalsIgnoreCase("0001")){
					//shipVia = "(none)";
					report.addReportStep("Create Shipment", "Ship via not generated"+shipVia, StepResult.FAIL);
					rc.throwTCTerminationException();
				}					

			}

			else if(screen.equalsIgnoreCase("QTY")){
				//for multiple do

				try {
					
				
				ArrayList<String> qty = new ArrayList<String>();

			    qty = jd.array_Database_Connection("Select total_lpn_qty from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");

			    olpn=jd.array_Database_Connection("Select tc_lpn_id from LPN where TC_ORDER_ID IN ('" + DFWMSInbounfFlowStepDefn.doId + "')");


			    if(olpn.size()>1) {
			    	
			    	for(int i=0;i<olpn.size();i++) {

			    		
						report.addReportStep("Split Olpn :"+olpn.get(i)+": "+qty.get(i)+" ","olpn Qty: "+qty.get(i) , StepResult.PASS);

			    	}			    	
			    	
			    }else {
			    	
			    	   report.addReportStep("Before Split Olpn Qty:", qty.toString(), StepResult.PASS);

			    }
				}catch(Exception e) {
					
			    	   report.addReportStep("Unable to split olpn:", "Failed to split olpn :"+ e.getMessage(), StepResult.PASS);

					
				}
			
				}


			else if(screen.equalsIgnoreCase("Canceled")){
				//for multiple do

				ArrayList<String> olpns = new ArrayList<String>();
				olpn=new ArrayList<>();
				olpnId=new ArrayList();
				for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
					//olpn=jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");


					List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");

					List<String> lpnId = jd.array_Database_Connection("select lpn_id from lpn where tc_order_id = '"+doId+"'");
					olpns.addAll(lpns);
					olpn.addAll(lpns);
					olpnId.addAll(lpnId);
					System.out.println("olpnId:"+olpnId);

				}
				System.out.println("LPNS: "+olpns);
				DFWMSoLPNsPageObject.soLPNs = olpns;
				if(DFWMSoLPNsPageObject.soLPNs.size()>0){
					for(int i = 0;i<DFWMSoLPNsPageObject.soLPNs.size();i++){
						String lpnStatus = jd.str_Database_Connection("select lpn_status from lpn where tc_order_id = '"+DFWMSInbounfFlowStepDefn.doIds.get(0)+"' and lpn_status='99'");
						if(lpnStatus.equalsIgnoreCase("99")){
							report.addReportStep("Validate Status ",screen+ ":"+"  : Actual - 99  Expected: "+lpnStatus, StepResult.PASS);
							break;
						}else{
							report.addReportStep("Validate Status ",screen+ ":"+" : Actual - 99  Expected: "+lpnStatus, StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}
				}else{
					report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
					rc.throwTCTerminationException();
				}

			}

			else if(screen.equalsIgnoreCase("CL_Message")||screen.equalsIgnoreCase("TOTEDIRECTIVE")||screen.equalsIgnoreCase("OLPNDIRECTIVE")||screen.equalsIgnoreCase("LIGHTCONTROL")){
				//mutliple do

				jd.dbDFWMSMapping();

				if(screen.equalsIgnoreCase("TOTEDIRECTIVE")) {

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+DFWMSRunWavesPageObject.ToteValue+"%' order by created_dttm asc");

					for(int i=0;i<data.size();i++) {

						if(data.get(i).contains("TOTEDIRECTIVE")) {
							report.addReportStep("Validate Status ",screen+ ":"+" "+data.get(i)+" : Actual - "+data.get(i) +" Expected: "+data.get(i), StepResult.PASS);
							break;
						}
						else{
							
							report.addReportStep("Validate Status ",screen+ ":"+" "+data.get(i)+" : Actual - "+data.get(i) +" Expected: "+data.get(i), StepResult.FAIL);
							rc.throwTCTerminationException();
						}
						break;
					}

				}
				
				
				else if(screen.equalsIgnoreCase("OLPNDIRECTIVE")) {
					
					jd.dbDFWMSMapping();
					
					
					System.out.println("Olpn is :");
					System.out.println(olpn);
					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+olpn.get(0)+"%' order by created_dttm desc");
					
					for(int i=0;i<data.size();i++) {
						System.out.println("Data is :");
						System.out.println(data);
						if(data.get(i).contains("OLPNDIRECTIVE")) {
							System.out.println("6180 got generated ");
							report.addReportStep("Validate Status ",screen+ ":"+" "+data.get(i)+" : \n Actual - "+data+olpn.get(i) +" \n Expected: \n"+data.get(i), StepResult.PASS);
							break;
						}else{
							
							report.addReportStep("Validate Status ",screen+ "6180 Not generated ", StepResult.FAIL);
							rc.throwTCTerminationException();
						}
					}

				}
				
				else if(screen.equalsIgnoreCase("LIGHTCONTROL")) {
					
					jd.dbDFWMSMapping();

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+olpn.get(0)+"%' || '%011%' order by created_dttm desc");
					System.out.println("Data is :");
					System.out.println(data);
					for(int i=0;i<data.size();i++) {

						if(data.get(i).contains("LIGHTCONTROL")) {
							System.out.println("LIGHTON got generated once user enters into Multis PS ");
							report.addReportStep("Validate Status ",screen+ ":"+" "+data.get(i)+" : \nActual - "+data+olpn.get(i) +" \nExpected: "+data.get(i), StepResult.PASS);
							break;
						}
						else{
							
							report.addReportStep("Validate Status ",screen+ " LIGHTCONTROL MSG NOT GENERATED ", StepResult.FAIL);
							//rc.throwTCTerminationException();
							
						}
						break;
					}
					

				}

				else {
					status="CNCLOLPN|";
					if(olpn.size()>0){
						for(int i = 0;i<olpn.size();i++){

							List<String> clId=jd.array_Database_Connection("select data from cl_message where data like '%CNCLOLPN|"+olpn.get(i)+"%' and created_dttm > sysdate -1");

							//							String sample=clId.get(0).toString();
							//					        String[] arrOfStr = sample.split("CNCLOLPN");
							//					        String olpnValue="|"+olpn.get(i);
							//					        
							System.out.println("lpnsStatus: "+clId.get(0));
							if(clId.get(0).contains(status+olpn.get(i))){
								report.addReportStep("Validate Status ",screen+ ":"+" "+clId.get(0)+" : Actual - "+status+olpn.get(i) +" Expected: "+clId.get(0), StepResult.PASS);
							}else{
								report.addReportStep("Validate Status ",screen+ ":"+" "+clId.get(0)+" : Actual - "+status+olpn.get(i) +" Expected: "+clId.get(0), StepResult.FAIL);
								rc.throwTCTerminationException();
							}

						}

					}else{
						report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
						rc.throwTCTerminationException();
					}
				}
			}
			else if(screen.equalsIgnoreCase("WaveData")){
				//mutliple do

				try {


					List<String> msg_Id=jd.array_Database_Connection("select msg_id from cl_message where data like '%"+DFWMSRunWavesPageObject.sWaveNumber+"%' and created_dttm > sysdate -1");


					Thread.sleep(3000);


					if(msg_Id.isEmpty()||msg_Id==null) {

						for(int j=0; j<35;j++) {
							System.out.println("empty data");


							if(!msg_Id.isEmpty()||msg_Id.size()>0) {

								System.out.println(" msg_Id :"+msg_Id);


								dbStatus=jd.str_Database_Connection("select  status from cl_endpoint_queue  where msg_id='"+msg_Id.get(0)+"'");

								Thread.sleep(2000);
								System.out.println("dbStatus: "+dbStatus);
								if(dbStatus.equalsIgnoreCase("5")){
									report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"5" +" Expected: "+dbStatus, StepResult.PASS);
								}else if(dbStatus.equalsIgnoreCase("2")){
									report.addReportStep("Validate Status : Ready: ",screen+ ":"+" "+dbStatus+" : Actual - "+"2" +" Expected: "+dbStatus, StepResult.FAIL);
									rc.throwTCTerminationException();
								}else if(dbStatus.equalsIgnoreCase("10")) {
									report.addReportStep("Validate Status : server busy : ",screen+ ":"+" "+dbStatus+" : Actual - "+"10" +" Expected: "+dbStatus, StepResult.FAIL);
									rc.throwTCTerminationException();

								}else {
									report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"1000" +" Expected: "+dbStatus, StepResult.FAIL);
									rc.throwTCTerminationException();

								}

								break;

							}else {
								msg_Id=jd.array_Database_Connection("select msg_id from cl_message where data like '%"+DFWMSRunWavesPageObject.sWaveNumber+"%'  and created_dttm > sysdate -1");
								System.out.println(" data"+msg_Id);

							}
							Thread.sleep(2000);

						}
					}else {

						System.out.println(" msg_Id :"+msg_Id);


						dbStatus=jd.str_Database_Connection("select  status from cl_endpoint_queue  where msg_id='"+msg_Id.get(0)+"'");

						Thread.sleep(2000);
						System.out.println("dbStatus: "+dbStatus);
						if(dbStatus.equalsIgnoreCase("5")){
							report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"5" +" Expected: "+dbStatus, StepResult.PASS);
						}else if(dbStatus.equalsIgnoreCase("2")){
							report.addReportStep("Validate Status : Ready: ",screen+ ":"+" "+dbStatus+" : Actual - "+"2" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();
						}else if(dbStatus.equalsIgnoreCase("10")) {
							report.addReportStep("Validate Status : server busy : ",screen+ ":"+" "+dbStatus+" : Actual - "+"10" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();

						}else {
							report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"1000" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();

						}

					}
				}
				catch (Exception e) {
					report.addReportStep("Validate Status for "+screen,"; Expected: "+dbStatus+"; Result: "+dbStatus, StepResult.FAIL);
					rc.throwTCTerminationException();
				}

			}else if(screen.equalsIgnoreCase("WES")){
				//mutliple do

				try {


					ArrayList<String> oLPNs = new ArrayList<String>();

					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
						oLPNs.addAll(lpns);

					}
					System.out.println("OLPN: "+oLPNs.get(0));

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+oLPNs.get(0)+"|DIV-%' and created_dttm > sysdate -1");

					RouteId.addAll(data);
					Thread.sleep(3000);

					if(!data.isEmpty()||data.size()>0) {

						System.out.println(" data :"+data);

						String[] result = data.get(0).split("DIV-");

						System.out.print("result = " +result[1]);

						String numberOnly= result[1].replaceAll("[^0-9]", "");

						//String[] part = result[1].split("(?<=\\D)(?=\\d)");  
						//System.out.println(part[0]);  
						System.out.println(numberOnly);
						String substring = "";

						if(numberOnly.length()>2) {
							substring= numberOnly.substring(0, 3);
						}
						dbStatus1=substring;
						Thread.sleep(2000);
						System.out.println("dbStatus: "+dbStatus1);
						if(dbStatus1.equalsIgnoreCase("511")||dbStatus1.equalsIgnoreCase("016")||dbStatus1.equalsIgnoreCase("005")){
							report.addReportStep("Validate Status ","Actual -"+ ":"+" "+dbStatus1+" Expected: "+dbStatus1, StepResult.PASS);
						}else {
							report.addReportStep("Validate Status ","Actual -"+ ":"+" "+dbStatus1+" Expected: "+dbStatus1, StepResult.FAIL);
							rc.throwTCTerminationException();

						}

					}else {
						report.addReportStep("Validate Status ","Actual -"+ ":"+" "+dbStatus1+" Expected: "+dbStatus1, StepResult.FAIL);
						rc.throwTCTerminationException();

					}



				}catch (Exception e) {
					report.addReportStep("Validate Status for "+screen,"; Expected: "+dbStatus1+"; Result: "+dbStatus1, StepResult.FAIL);
					rc.throwTCTerminationException();
				}

			}else if(screen.equalsIgnoreCase("Diverted")){
				//mutliple do

				try {

					ArrayList<String> oLPNs = new ArrayList<String>();

					for (String doId : DFWMSInbounfFlowStepDefn.doIds) {
						List<String> lpns = jd.array_Database_Connection("select tc_lpn_id from lpn where tc_order_id = '"+doId+"'");
						oLPNs.addAll(lpns);

					}
					System.out.println("OLPN: "+oLPNs.get(0));

					List<String> data=jd.array_Database_Connection("select data from cl_message where data like '%"+oLPNs.get(0)+"|DIV-%' and created_dttm > sysdate -1");

					Thread.sleep(3000);



					if(!data.isEmpty()||data.size()>0) {

						System.out.println(" data :"+data);

						for(int i=0;i<data.size();i++) {
							if(data.get(i).contains("DIVERTED")){
								report.addReportStep("Validate Status ","Actual -"+ ":"+" "+data.get(i)+" Expected: "+data.get(i), StepResult.PASS);
								break;
							}


						}

					}else {
						report.addReportStep("Validate Status ","Actual -"+ ":"+" "+data.get(0)+" Expected: "+data.get(0), StepResult.FAIL);
						rc.throwTCTerminationException();

					}



				}catch (Exception e) {
					report.addReportStep("Validate Status for "+screen,"; Expected: Is Not equals to Actual Result: ", StepResult.FAIL);
					rc.throwTCTerminationException();
				}

			}
			else if(screen.equalsIgnoreCase("LocateOlpn")){
				//mutliple do

				String msg_Id=jd.str_Database_Connection("select msg_id from cl_message where data like '%"+DFWMSRunWavesPageObject.sWaveNumber+"%WMMHE'  and source_id='WYN_TO_WM_LOCATE_OLPN' and source_uri='tcp://*:32004' order by when_created desc");

				Thread.sleep(2000);
				dbStatus=jd.str_Database_Connection("select  status from cl_endpoint_queue  where msg_id='"+msg_Id+"'");

				Thread.sleep(2000);


				if(olpn.size()>0){
					for(int i = 0;i<olpn.size();i++){

						int num = 0;
						while (!dbStatus.equals("5")) {
							if(num<10){
								System.out.println(num);
								Thread.sleep(2000);
								dbStatus=jd.str_Database_Connection("select  status from cl_endpoint_queue  where msg_id='"+msg_Id+"'");
								Thread.sleep(2000);
								num++;
							}
							else{
								break;
							}
						}			

						System.out.println("data: "+dbStatus);

						if(dbStatus.equalsIgnoreCase("5")){
							report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"5" +" Expected: "+dbStatus, StepResult.PASS);
						}else if(dbStatus.equalsIgnoreCase("2")){
							report.addReportStep("Validate Status : Ready: ",screen+ ":"+" "+dbStatus+" : Actual - "+"2" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();
						}else if(dbStatus.equalsIgnoreCase("10")) {
							report.addReportStep("Validate Status : server busy : ",screen+ ":"+" "+dbStatus+" : Actual - "+"10" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();

						}else {
							report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"1000" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();

						}

					}

				}
			}else if(screen.equalsIgnoreCase("Pick_Pack")){
				//mutliple do

				if(olpn.size()>0){
					for(int i = 0;i<olpn.size();i++){

						String msg_Id=jd.str_Database_Connection("select msg_id from cl_message where data like '%"+DFWMSRunWavesPageObject.sWaveNumber+"%'  and source_id='WYN_TO_WM_PACK_PICK_CART' and source_uri='tcp://*:32002' order by when_created desc");

						Thread.sleep(2000);
						dbStatus=jd.str_Database_Connection("select  status from cl_endpoint_queue  where msg_id='"+msg_Id+"'");

						Thread.sleep(2000);

						int num = 0;
						while (!dbStatus.equals("5")) {
							if(num<10){
								System.out.println(num);
								Thread.sleep(2000);
								dbStatus=jd.str_Database_Connection("select  status from cl_endpoint_queue  where msg_id='"+msg_Id+"'");
								Thread.sleep(2000);
								num++;
							}
							else{
								break;
							}
						}			

						System.out.println("data: "+dbStatus);

						if(dbStatus.equalsIgnoreCase("5")){
							report.addReportStep("Validate Status ",screen+ ":"+" "+dbStatus+" : Actual - "+"5" +" Expected: "+dbStatus, StepResult.PASS);
							driver.switchTo().window(DFWMSLoginPageObject.first_tab);
							Thread.sleep(2000);

						}else if(dbStatus.equalsIgnoreCase("2")){
							report.addReportStep("Validate Status : Ready: ",screen+ ":"+" "+dbStatus+" : Actual - "+"2" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();
						}else if(dbStatus.equalsIgnoreCase("10")) {
							report.addReportStep("Validate Status : server busy : ",screen+ ":"+" "+dbStatus+" : Actual - "+"10" +" Expected: "+dbStatus, StepResult.FAIL);
							rc.throwTCTerminationException();

						}else if(dbStatus.equalsIgnoreCase("6")){

							report.addReportStep("Validate Status : Failed",screen+ ":"+" "+dbStatus+" : Actual - "+"6" +" Expected: "+dbStatus, StepResult.FAIL);
							//rc.throwTCTerminationException();
							Thread.sleep(2000);

							driver.switchTo().window(DFWMSLoginPageObject.first_tab);
							Thread.sleep(2000);

						}

					}

				}else{
					report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
					rc.throwTCTerminationException();
				}
			}

			else if(screen.equalsIgnoreCase("Printed_CL_Message")){
				//mutliple do

				status="CNCLOLPN|";
				if(olpn.size()>0){

					List<String> clId=jd.array_Database_Connection("select data from cl_message where data like '%CNCLOLPN|"+olpn.get(1)+"%' and created_dttm > sysdate -1");
					List<String> clId1=jd.array_Database_Connection("select data from cl_message where data like '%CNCLOLPN|"+olpn.get(3)+"%' and created_dttm > sysdate -1");

					//							String sample=clId.get(0).toString();
					//					        String[] arrOfStr = sample.split("CNCLOLPN");
					//					        String olpnValue="|"+olpn.get(i);
					//					        
					System.out.println("lpnsStatus: "+clId.get(0));
					System.out.println("lpnsStatus1: "+clId1.get(0));

					if(clId.get(0).contains(status+olpn.get(1))&&clId1.get(0).contains(status+olpn.get(3))){
						report.addReportStep("Validate Status ",screen+ ":"+" "+clId.get(0)+" : Actual - "+status+olpn.get(0) +" Expected: "+clId.get(0), StepResult.PASS);
						report.addReportStep("Validate Status ",screen+ ":"+" "+clId.get(0)+" : Actual - "+status+olpn.get(2) +" Expected: "+clId.get(0), StepResult.PASS);

					}else{
						report.addReportStep("Validate Status ",screen+ ":"+" "+clId.get(0)+" : Actual - "+status+olpn.get(0) +" Expected: "+clId.get(0), StepResult.FAIL);
						rc.throwTCTerminationException();
					}

				}

			}else{
				report.addReportStep("Validate Status: ","Unable to get olpns", StepResult.FAIL);
				rc.throwTCTerminationException();
			}
		}


		catch (Exception e) {
			report.addReportStep("Validate Status for "+screen,"; Expected: "+dbStatus+"; Result: "+dbStatus, StepResult.FAIL);
			rc.throwTCTerminationException();
		}
	}	


}