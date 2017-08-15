package com.palm.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jersey.bean.LogFileRoot;
import com.palm.utils.FileUtil;

/**
 * Description: 测试追号
 * 
 * @author lingming.zeng
 * @since JDK 1.8 Copyright (c) 2016, o2o-lottery All Rights Reserved.
 */
public class TestAddLottery {
	
	private static Logger logger = Logger.getLogger(TestAddLottery.class);
	private String fileName;
	private String aLotterySite;
	private String currentDrawSite;
	
	@BeforeClass
	@Parameters({"aLotteryFile","url","aLotterySite","currentDrawSite"})
	public void beforeClass(String aLotteryFile,String url,
			String dLotterySite,String currentDrawSite) throws IOException {
		this.fileName = aLotteryFile;
		this.aLotterySite = url+dLotterySite;
		this.currentDrawSite = url+currentDrawSite;
		FileUtil.deleteFile(LogFileRoot.getLogFileRoot()+"cache/");
		BaseUtil.fileReader(aLotteryFile);
	}
	
	@AfterClass
	public void afterClass(){
		FileUtil.deleteFile(LogFileRoot.getLogFileRoot()+"cache/");
	}
	
	@DataProvider
	public Object[][] testCounts() throws IOException {
	    return BaseUtil.testCounts(this.fileName);
	}
	
	
	@Test(dataProvider="testCounts")
	private void testDirectLottery(int count,String caseId) throws Exception {
		logger.info(caseId+" test begin");
		BaseUtil testBase =  new BaseUtil();
		testBase.testDemo(count, caseId, aLotterySite,currentDrawSite);
	}
}
