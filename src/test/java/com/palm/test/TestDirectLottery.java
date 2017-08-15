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
 * Description: 测试直投
 * 
 * @author lingming.zeng
 * @since JDK 1.8 Copyright (c) 2016, o2o-lottery All Rights Reserved.
 */
public class TestDirectLottery {
	
	private static Logger logger = Logger.getLogger(TestDirectLottery.class);
	private String fileName;
	private String dLotterySite;
	private String currentDrawSite;
	
	@BeforeClass
	@Parameters({"dLotteryFile","url","dLotterySite","currentDrawSite"})
	public void beforeClass(String dLotteryFile,String url,
			String dLotterySite,String currentDrawSite) throws IOException {
		this.fileName = dLotteryFile;
		this.dLotterySite = url+dLotterySite;
		this.currentDrawSite = url+currentDrawSite;
		FileUtil.deleteFile(LogFileRoot.getLogFileRoot()+"cache/");
		BaseUtil.fileReader(dLotteryFile);
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
		testBase.testDemo(count, caseId, dLotterySite,currentDrawSite);
	}
}
