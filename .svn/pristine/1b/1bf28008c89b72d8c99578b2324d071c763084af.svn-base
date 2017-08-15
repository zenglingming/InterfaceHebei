package com.palm.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.jersey.bean.LogFileRoot;
import com.palm.utils.FileUtil;
import com.palm.utils.HttpRequestUtils;
import com.palm.utils.StringUtil;
import com.palm.zson.Zson;
import com.palm.zson.ZsonResult;

public class BaseUtil {
	
	private static Logger logger = Logger.getLogger(BaseUtil.class);
	private static FileUtil failLog = Login.getFailLog();
	
	/**     
	 * Description:读取测试用例，将整个测试用例文件分成多个待发送和预期结果文件 
	 * @Date     2016年5月31日
	 * @param	 fileName   文件路径和文件名
	 * @author   lingming.zeng    
	 * @since    JDK 1.8   
	 * Copyright (c) 2016, o2o-lottery All Rights Reserved.         
	 */	
	public static void fileReader(String fileName) throws IOException {
		int testCase = 1;
		String str = null;
		FileUtil sendMsg = new FileUtil();
		FileUtil expectMsg = new FileUtil();
		BufferedReader bre = new BufferedReader(new FileReader(fileName));
		while ((str = bre.readLine()) != null)
		{			
			if(str.trim().startsWith("<?caseId:")) {
				expectMsg.createNewFile(LogFileRoot.getLogFileRoot()+"cache/expectMsg_"+testCase+".xml");
				expectMsg.Write(str.trim());
				str = bre.readLine(); 
				sendMsg.createNewFile(LogFileRoot.getLogFileRoot()+"cache/sendMsg_"+testCase+".xml");
				sendMsg.Write(str.trim());
				while ((str = bre.readLine()) != null) {
					logger.debug(str);
					if(str.trim().endsWith("<end>"))break;
					sendMsg.Write(str.trim()+"\n");
				}
				testCase++;
			}
		}
		bre.close();
	}
	
	/**     
	 * Description:读取测试用例，返回测试用例名称和个数，用于testng的@DataProvider
	 * @Date     2016年5月31日
	 * @param    fileName	测试用例文件路径+文件名
	 * @author   lingming.zeng   
	 * @since    JDK 1.8   
	 * Copyright (c) 2016, o2o-lottery All Rights Reserved.         
	 */	
	public static Object[][] testCounts(String fileName) throws IOException {
		int counter = 0;
		String str = null;
		Object[][] countObj = null;
		BufferedReader breCount = new BufferedReader(new FileReader(fileName));
		while ((str = breCount.readLine()) != null) {
			if(str.trim().startsWith("<?caseId")) {			
				counter++;
			}
		}
		breCount.close();
		countObj = new Object[counter][2];
		for(int i=0;i<counter;i++) {
			BufferedReader breExpectMsg = new BufferedReader(new FileReader
					(LogFileRoot.getLogFileRoot()+"cache/expectMsg_"+(i+1)+".xml"));
			String expectString = breExpectMsg.readLine();
			String caseId = expectString.substring(expectString.indexOf("caseId:")+8,expectString.indexOf
					("\"", expectString.indexOf("caseId:")+8));
			countObj[i][0] = (i+1);
			countObj[i][1] = caseId;
			breExpectMsg.close();
		}
	    return countObj;
	}	
	
	/**     
	 * Description:直投或者追号测试模板
	 * 			   1.替换待发送json串中的token、userId、gameId
	 * 			   2.获取从服务器返回的json串，与预期结果比对
	 * 			   3.若测试失败，向FialLog.xml写入失败用例的request和response信息
	 * @param count		跑的第N个测试用例
	 * @param caseId	测试用例号
	 * @param domain	投注接口地址
	 * @param currentDrawSite	当前期接口地址	
	 * @throws Exception
	 * @Date     2016年5月31日    
	 * @author   lingming.zeng     
	 * @since    JDK 1.8   
	 * Copyright (c) 2016, o2o-lottery All Rights Reserved.         
	 */
	public void testDemo(int count,String caseId, String domain,String currentDrawSite) throws Exception {
		String sendMsg = StringUtil.readFromFile(new File(LogFileRoot.getLogFileRoot()+"/cache/sendMsg_"+count+".xml"));
		if(sendMsg.contains("token")) {
			String token = Login.getToken();
			sendMsg = StringUtil.getChangedStr(sendMsg, "token", token);
		}
		if(sendMsg.contains("userId")) {
			String userId = Login.getUserId();
			sendMsg = StringUtil.getChangedStr(sendMsg, "userId", userId);
		}
		if(sendMsg.contains("gameId")) {
			//解析json中的当前期
			Zson z = new Zson();
			ZsonResult zr = z.parseJson(sendMsg);
			String gameId = zr.getValues("//gameId").get(0).toString();
			String drawNo =CurrentDraw.getCurrentDraw(gameId,currentDrawSite);
			//将当前期替换到要发送的json串中
			sendMsg = StringUtil.getChangedStr(sendMsg, "drawNo", drawNo);
		}
		//获取从服务器返回的json串
		String response = HttpRequestUtils.httpPost(sendMsg,domain);
		//预期结果
		BufferedReader breExpectMsg = new BufferedReader(new FileReader(LogFileRoot.getLogFileRoot()+"cache/expectMsg_"+count+".xml"));
		String expectString = breExpectMsg.readLine();
		String assertData = expectString.substring(expectString.indexOf("expectedResult:")+15
				,expectString.indexOf("?>")).trim().toLowerCase();	
		//测试通过或者失败标志，通过（true）-失败（false）
		boolean flag = true;
		if (response.toLowerCase().contains(assertData)) {
			flag = true;
		} else {
			flag=false;
		}
		if (flag) {
			//测试通过
			logger.info("第"+count+"次测试通过,测试用例："+caseId);
		} else {
			//测试失败，向FialLog.xml写入失败用例的request和response信息
			logger.error("第"+count+"次测试失败,测试用例："+caseId);
			failLog.Write(caseId +" Failed:");failLog.Write(expectString);
			failLog.Write("request:");failLog.Write(sendMsg);
			failLog.Write("response:");failLog.Write(response);
			failLog.Write(StringUtils.repeat("=",60));
			Assert.fail("expected:"+assertData+",详见->"+LogFileRoot.getLogFileRoot()+"failLog.xml");
		}
		breExpectMsg.close();
	}

}
