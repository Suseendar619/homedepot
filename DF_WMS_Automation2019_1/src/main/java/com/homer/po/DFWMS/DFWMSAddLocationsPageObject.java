package com.homer.po.DFWMS;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.homer.dao.InstanceContainer;
import com.homer.po.PageBase;

public class DFWMSAddLocationsPageObject extends PageBase{

	public DFWMSAddLocationsPageObject(InstanceContainer ic) {
		super(ic);
	}

	public void addLoc() throws Exception {

		wh.waitUntilDisappear(LoadingFrame);
		wh.clickElement(Maximize);
		Thread.sleep(2000);
		driver.switchTo().frame(0);		
		
//		wh.waitForPageLoaded();
//		wh.waitUntilDisappear(LoadingFrame);
		
		//read xml data start
		String filePath = System.getProperty("user.dir")+"\\TestData\\Excel";
		String fileName = "Test.xlsx";
		File file = new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet("Houston");
		//int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		//no of rows in sheet completed till 51
		for (int i = 955; i <= 1000; i++) {
			Row row = sheet.getRow(i);

			System.out.println("Current row load :" + i);

			if(wh.isElementPresent(ReserveLocPage, 3)){
				//add loc
				if(wh.isElementPresent(AddLoc, 3)){
					wh.clickElement(AddLoc);
					Thread.sleep(1000);
				}

				//class type
				if(wh.isElementPresent(ClassVal, 3)){
					wh.selectValue(ClassVal, "Reserve");
					Thread.sleep(1000);
				}
				
				for (int j = 7; j < 22; j++) {
				
					if(j == 7 && wh.isElementPresent(ZoneValReserve, 2)){
						//zone
						wh.sendKeys(ZoneValReserve, row.getCell(j).toString().trim());//for string values
					}else if(j == 8 && wh.isElementPresent(AisleValReserve, 2)){
						//aisle
						wh.sendKeys(AisleValReserve, "0"+new DataFormatter().formatCellValue(row.getCell(j)));// for int values
					}else if(j == 9 && wh.isElementPresent(BayValReserve, 2)){
						//Bay
						wh.sendKeys(BayValReserve, "0"+new DataFormatter().formatCellValue(row.getCell(j)));// for int values
					}else if(j == 10 && wh.isElementPresent(LevelValReserve, 2)){
						//level
						wh.sendKeys(LevelValReserve, row.getCell(j).toString().trim());//for string values
					}else if(j == 11 && wh.isElementPresent(PositionValReserve, 2)){
						//position
						wh.sendKeys(PositionValReserve, "0"+new DataFormatter().formatCellValue(row.getCell(j)));// for int values
					}else if(j == 14 && wh.isElementPresent(Length, 2)){
						//len
						wh.sendKeys(Length, new DataFormatter().formatCellValue(row.getCell(j)));// for int values
					}else if(j == 15 && wh.isElementPresent(Width, 2)){
						//depth/width
						wh.sendKeys(Width, new DataFormatter().formatCellValue(row.getCell(j)));// for int values
					}else if(j == 16 && wh.isElementPresent(Height, 2)){
						//height
						wh.sendKeys(Height, new DataFormatter().formatCellValue(row.getCell(j)));// for int values
					}else if(j == 20 && wh.isElementPresent(PickSeq, 2)){
						//pick seq
						wh.sendKeys(PickSeq, row.getCell(j).toString().trim());//for string values
					}else if(j == 21 && wh.isElementPresent(PutAwaySeq, 2)){
						//put seq
						wh.sendKeys(PutAwaySeq, row.getCell(j).toString().trim());//for string values
					}
				}
				//save
				if(wh.isElementPresent(ReserveLocSave, 3)){
					wh.clickElement(ReserveLocSave);
				}
				Thread.sleep(8000);
				
				//alert handling
				if(wh.isElementPresent(AlertBox, 3)){
					wh.clickElement(AlertBox);
					wh.clickElement(ReserveLocCancel);
				}

				if(wh.isAlertPresent()){
					wh.handleAlert();
				}
				Thread.sleep(1000);
			}else{
				System.out.println("Loc is not saved");
				throw new Exception("Loc is not saved");
			}
		} 
			
		/*for(int i = 111; i <= 120; i++){
			System.out.println(i);
			addLocByClass(i, "Reserve");
			addLocByClass(i, "Staging");
		}*/
	}

	private void addLocByClass(int i, String classVal) throws Exception {
		Thread.sleep(1000);
		wh.waitForPageLoaded();
		if(wh.isElementPresent(AddLoc, 3)){
			wh.clickElement(AddLoc);
			Thread.sleep(1000);
		}
		
		if(wh.isElementPresent(ClassVal, 3)){
			wh.selectValue(ClassVal, classVal);
			Thread.sleep(1000);
		}
	
		if(classVal.equals("Reserve") && wh.isElementPresent(ZoneValReserve, 2)){
			wh.sendKeys(ZoneValReserve, "STG");
		}else if(classVal.equals("Staging") && wh.isElementPresent(ZoneValStaging, 2)){
			wh.sendKeys(ZoneValStaging, "STG");
		}
		Thread.sleep(1000);
		
		if(classVal.equals("Reserve") && wh.isElementPresent(AisleValReserve, 2)){
			wh.sendKeys(AisleValReserve, Integer.toString(i));
		}else if(classVal.equals("Staging") && wh.isElementPresent(AisleValStaging, 2)){
			wh.sendKeys(AisleValStaging, Integer.toString(i));
		}
		Thread.sleep(1000);
		
		wh.sendKeys(BarCodeVal, "STG-"+Integer.toString(i));
		Thread.sleep(1000);
		wh.clickElement(ReserveLocSave);
		Thread.sleep(1000);
		if(wh.isAlertPresent()){
			wh.handleAlert();
		}
		Thread.sleep(1000);
	}
}
