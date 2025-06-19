package com.homer.keywords;

import com.homer.dao.DataClass;

public class LandOnPLPKeyword extends BaseKeywords {
	
	public LandOnPLPKeyword(DataClass data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	public void verifyPLP1() throws Throwable {

	     plpPage.verifyPLPPage();
	}

	public void verifyPLP2() throws Throwable {

		System.out.println("Iteration number "+data.iterationNo);
		System.out.println("Step number "+data.stepNo);
	    plpPage.verifyPLPPage();
	}

	public void verifyPLP3() throws Throwable {

	     plpPage.verifyPLPPage();
	}

	public void verifyPLP4() throws Throwable {

	     plpPage.verifyPLPPage();
	}

	public void verifyPLP5() throws Throwable {

	     plpPage.verifyPLPPage();
	}
}

