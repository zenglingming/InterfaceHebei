package com.palm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.Test;

public class StringUtil {
	
	private static String[] arr= null;
	
	/**
	 * 获取目标字符串中子字符串对应的值，值必须用单引号或者双引号括起来
	 * @param str 输入的字符串
	 * @param subStr 求str字符串中subStr对应的值
	 */
	public static String getSubStrValue(String str,String subStr) {
		String subStrValue = "";
		int subStrLength = subStr.length();
		str = str.substring(str.indexOf(subStr));
		try {
			subStrValue = str.substring(str.indexOf(subStr)+subStrLength+2, str.indexOf("' "));
		} catch (IndexOutOfBoundsException e) {
			subStrValue = str.substring(str.indexOf(subStr)+subStrLength+2, str.indexOf("\" "));
		}
		return subStrValue;
	}
	
	/**
	 * 获取目标字符串中子字符串对应的值
	 * @param str 输入的字符串
	 * @param subStr 求str字符串中subStr对应的值
	 * @param edge 值的边界符号
	 */
	public static String getSubStrValue(String str,String subStr,String edge) {
		int subStrLength = subStr.length();
		str = str.substring(str.indexOf(subStr));
		String subStrValue = str.substring(str.indexOf(subStr)+subStrLength+2, str.indexOf(edge+" "));
		return subStrValue;
	}
	
	/**
	 * 将需要改变的字段changedStr变为changedValue
	 * @param str 输入的字符串
	 * @param changedStr 需要改变的字段
	 * @param changedValue 需要改变的字段的值	
	 */
	public static String getChangedStr(String str,String changedStr,String changedValue) {
		String frontStr = str.substring(0,str.indexOf(changedStr));
		String behindStr = str.substring(str.indexOf(changedStr));
		String changeBehindStr;
		if(changedStr == "userId") {
			changeBehindStr = behindStr.replace(behindStr.substring(0, behindStr.indexOf("\"}")), 
					changedStr+"\": \""+changedValue);
		} else {
			changeBehindStr = behindStr.replace(behindStr.substring(0, behindStr.indexOf("\",")), 
					changedStr+"\": \""+changedValue);
		}
		String ChangedStr = frontStr+changeBehindStr;
		return ChangedStr;
	}
	
	/**
	 * 获取字符串中包含子字符串的个数
	 * @param str 需要查找的字符串
	 * @param splitStr 字符串
	 */
	public static int getStringNumbers(String str,String splitStr){
		int counter = 0;
		arr = null;
		if(str.indexOf(splitStr) != -1)  {  
	        arr = str.split(splitStr);  
	        counter = arr.length-1;
	    } 
        return counter;       
    }
		
	/**
	 * 获取字符串中包含子字符串的所有的值
	 * @param str 需要查找的字符串
	 * @param splitStr 字符串
	 * @param edge 值的边界符号
	 */
	public static String[] getStringValues(String str,String splitStr,String edge){
		String[] arr1 = new String[getStringNumbers(str,splitStr)];
		for(int i=1;i<arr.length;i++){
			arr1[i-1] = arr[i].substring(arr[i].indexOf("="+edge)+2, arr[i].indexOf(edge+" ")).trim();
		}
        return arr1;     
    }
	
	public static String readFromFile(File src) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while((content = bufferedReader.readLine() )!=null){
                stringBuilder.append(content);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
	
	public static void main(String[] args) {
//		String str  = "<head transcode='1101' partnerId='16' version='3.1' partnerId='16' time='20151028110216'/>";
//		String str1 = "";
		//		System.out.println(getChangedStr(str,"partnerId"));
//		System.out.println(getStringValues(str,"partnerId","'").length);
//		System.out.println(str1.trim().length()==0);
		System.out.println(readFromFile(new File("D:/testlog/failLog.xml")));				
	}
	
	@Test
	public void test() {
		String token = "1234";
		String sendMsg =readFromFile(new File("D:/testlog/cache/sendMsg_1.xml"));
		sendMsg = StringUtil.getChangedStr(sendMsg, "userId", token);
		System.out.println(sendMsg);
	}
}
