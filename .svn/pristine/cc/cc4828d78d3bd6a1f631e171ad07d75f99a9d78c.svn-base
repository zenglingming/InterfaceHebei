package com.palm.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;


public class CsvUtil {	
	
	public static String getTestData(String filePath,String columnName,String lineName) throws IOException{
		String value = null;
		CSVReader reader = null;
		List<String[]> approvalList = new ArrayList<String[]>();
		String nextLine[];		
		try {
			reader = new CSVReader(new FileReader(System.getProperty("user.dir")+"/"+filePath));
			try {
				while ((nextLine = reader.readNext()) != null) {
					approvalList.add(nextLine);					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
			int count = 0;
			int index = 0;			
			for(String j:approvalList.get(0)){
				count++;
				if(j.equals(columnName)){
					index = count-1;
				}
			}			
			for(String[] i : approvalList){
				if(i[0].equals(lineName))
					   value = i[index].toString();
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reader.close();
		return value;
	}
	
	public static int getLine(String filePath) throws IOException{
		int line = 0;
		CSVReader reader = null;
		List<String[]> approvalList = new ArrayList<String[]>();
		String nextLine[];
		try {
			reader = new CSVReader(new FileReader(System.getProperty("user.dir")+"/"+filePath));
			try {
				nextLine = reader.readNext();
				while (nextLine != null && nextLine[0].length()>0) {
					approvalList.add(nextLine);
					nextLine = reader.readNext();
				}
				line = approvalList.size()-1;
			} catch (IOException e) {
				e.printStackTrace();
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reader.close();
		return line;
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println(System.getProperty("user.dir"));
		System.out.println(CsvUtil.getLine("xmlDir.csv"));
		String str = CsvUtil.getTestData("xmlDir.csv", "xmlDir", "2");
		System.out.println(str.substring(0, str.indexOf("=")));
	}	
}
	

