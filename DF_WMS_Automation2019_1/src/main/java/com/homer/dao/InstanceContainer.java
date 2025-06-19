package com.homer.dao;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.homer.helper.DataTable;
import com.homer.reports.Report;
import com.homer.resuablecomponents.JDBC_Connection;
import com.homer.resuablecomponents.ReusableComponents;
import com.homer.resuablecomponents.WebDriverHelper;

public class InstanceContainer {

	public Report report;
	public WebDriver driver;
	public WebDriverHelper wh;
	public ReusableComponents rc;
	public DataTable dataTable;
	public CommonData commonData;
	public JDBC_Connection jDBC_Connection;
	public WebElement element;
	
	public InstanceContainer(WebDriver driver,Report report, DataTable dataTable,
				WebDriverHelper wh, ReusableComponents rc, CommonData commonData2, JDBC_Connection jDBC_Connection2) {
		
		this.report = report;
		this.driver = driver;
		this.element = element;
		this.wh = wh;
		this.rc =rc;
		this.dataTable = dataTable;
		this.commonData = commonData;
		this.jDBC_Connection=jDBC_Connection;
	}
	
	public InstanceContainer( WebDriver driver,Report report, DataTable dataTable,
			WebDriverHelper wh, ReusableComponents rc) {
	
		this.report = report;
		this.driver = driver;
		this.element=element;
		this.wh = wh;
		this.rc =rc;
		this.dataTable = dataTable;
	}
}
