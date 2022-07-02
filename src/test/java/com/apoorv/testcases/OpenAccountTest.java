package com.apoorv.testcases;

import org.testng.annotations.Test;
import com.apoorv.base.TestBase;
import com.apoorv.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")
	public void addCustomerTest(String customer, String currency ) {
		
	}


}
