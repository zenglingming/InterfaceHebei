package com.jersey.bean;   

/**     
 * Function: TODO 
 *
 * ClassName:Head 
 * Date:     2015年12月22日 上午9:29:15    
 * @author   mengq   
 * @version     
 * @since    JDK 1.8   
 * Copyright (c) 2015, palm-commerce All Rights Reserved.         
 */
public class Head {

	/**
	 * 令牌
	 */
	private String token;
	
	/**
	 * 版本号 3.1
	 */
	private String version = "3.1";
	
	/**
	 * 格式：20150924130457
	 */
	private String time="20160428142534";
	
	/**
	 * 秘钥
	 */
	private String key;
	
	/**
	 * 用户唯一标识
	 */
	private String userId;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
   
