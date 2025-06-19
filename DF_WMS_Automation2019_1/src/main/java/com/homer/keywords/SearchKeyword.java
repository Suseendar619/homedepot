package com.homer.keywords;
import com.homer.dao.DataClass;
import com.homer.dao.DataColumn;

public class SearchKeyword extends BaseKeywords {
	
	public SearchKeyword(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	public void searchKeyword1() throws Throwable {

		System.out.println("Iteration number "+data.iterationNo);
		System.out.println("Step number "+data.stepNo);
		
	     plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	}

	public void searchKeyword2() throws Throwable {

		System.out.println("Iteration number "+data.iterationNo);
		System.out.println("Step number "+data.stepNo);
		
	     plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	     rc.terminateTestCase("Search page");
	}

	public void searchKeyword3() throws Throwable {

	     plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	}

	public void searchKeyword4() throws Throwable {

	     plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	}

	public void searchKeyword5() throws Throwable {

	     plpPage = homePage.searchKeyword(dataTable.getData(DataColumn.Keyword));
	}
}

