package com.homer.po.DFWMS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Test {
	public static void main(String[] args) throws IOException {
		String currentDate = new SimpleDateFormat("mmss").format(Calendar.getInstance().getTime());
		String shipmentId = "S00000"+currentDate;
		System.out.println(shipmentId);
		/*final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		String DeliveryDate = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime())+ "  " +"00:00:00";
		SimpleDateFormat DeliveryDateAgileOutbound = new SimpleDateFormat("MM/dd/yyyy");
		final Calendar DeliveryDateCalendar = Calendar.getInstance();
		DeliveryDateCalendar.add(Calendar.DAY_OF_YEAR, 3);
		DeliveryDate = DeliveryDateAgileOutbound.format(DeliveryDateCalendar.getTime());
		System.out.println(DeliveryDate);*/
		/*
		String path = System.getProperty("user.dir")+"\\TestData\\Excel\\SKU_Data.xlsx";
		File myFile = new File(path);
		String item = "";
		FileInputStream fis = new FileInputStream(myFile);
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator<Row> rowIterator = mySheet.iterator();
		while (rowIterator.hasNext()) {
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				boolean rowValue = false;
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							String cellValue = cell.getStringCellValue();
		   	            	if(cellValue.equalsIgnoreCase("SR2ReserveDallas")){
		   	            		rowValue = true;
		   	            	}
		   	            	if(rowValue && cell.getColumnIndex() == 1){
		   	            		item = cell.getStringCellValue();
		   	            		item = item.replaceAll("[^a-zA-Z0-9]", " ");  
	                        	System.out.print("Item :"+item);
	                        	cell.setCellValue("1000763621");
	                        	FileOutputStream os = new FileOutputStream(path); 
	        					myWorkBook.write(os); 
	        					System.out.println("Writing on XLSX file Finished ...");
	        					os.close();
	        					fis.close();
	        					myWorkBook = new XSSFWorkbook(new FileInputStream(path));
		   	            	}
		   	            	break;
						case Cell.CELL_TYPE_NUMERIC:
							NumberFormat formatter = new DecimalFormat("#");
	                        if(rowValue && cell.getColumnIndex() == 1){
	                        	item = formatter.format(cell.getNumericCellValue());
	                        	item = item.replaceAll("[^a-zA-Z0-9]", " ");  
	                        	System.out.print("Item :"+item);
	                        	cell.setCellValue("1000763621");
	                        	FileOutputStream os = new FileOutputStream(path); 
	        					myWorkBook.write(os); 
	        					System.out.println("Writing on XLSX file Finished ...");
	        					os.close();
	        					fis.close();
	        					myWorkBook = new XSSFWorkbook(new FileInputStream(path));
	                        }
	                    	break;
					}
				}
			}
		}
	 */}
}