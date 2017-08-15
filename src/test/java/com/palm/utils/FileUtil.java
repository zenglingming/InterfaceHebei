package com.palm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
	
	File f;
	
	public void createNewFile(String destFileName) {
	    f = new File(destFileName);  
        //判断目标文件所在的目录是否存在,不存在则创建  
        if(!f.getParentFile().exists()) {  
            f.getParentFile().mkdirs();
        }
        //创建文件
        try {
        	this.delete();
        	f.createNewFile();
        } catch(IOException e) {
        	 e.printStackTrace();
        }
	}
	
	public void delete() throws IOException {
		f.delete();
	}
	
	/** 
	 * 删除文件、文件夹 
	 */  
	public static void deleteFile(String path) {  
	    File file = new File(path);  
	    if (file.isDirectory()) {  
	        File[] ff = file.listFiles();  
	        for (int i = 0; i < ff.length; i++) {  
	            deleteFile(ff[i].getPath());  
	        }  
	    }  
	    file.delete();  
	}	
//	public static void deleteFile(String path) {
//		File folder = new File(path);
//		File[] files = folder.listFiles();
//		if (files != null) {
//			for(File file:files){
//				if(file.getName().contains("send") || file.getName().contains("expect"))
//				file.delete();
//			}
//		}
//	}
	
	public void Write(String s) throws IOException {
		// 向文档内追加Log
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(f, true), "GBK")); // 指定编码格式，以免读取时中文字符异常
		fw.append(s);
		fw.newLine();
		fw.close();
	}
	
	public static void main(String[] args) throws IOException {
		int count = 3;
		FileUtil fileUtil = new FileUtil();
		fileUtil.createNewFile("D:\\testlog\\FailedLog.txt");
		fileUtil.Write("efd");
		FileUtil.deleteFile("D:/testlog/sendMsg_"+count+".xml");
	}
	
}
