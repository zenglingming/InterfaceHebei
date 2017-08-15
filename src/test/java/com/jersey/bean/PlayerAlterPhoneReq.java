package com.jersey.bean;


/**
 * Description: 修改手机号
 *
 * ClassName:PlayerAlterPhoneReq Date: 2016年5月3日 下午3:32:37
 * 
 * @author jianjun.zhang
 * @version
 * @since JDK 1.8 Copyright (c) 2016, o2o-lottery All Rights Reserved.
 */
public class PlayerAlterPhoneReq extends BaseRequest {
	//原先的手机号
	private String phone;
	// 登录密码
	private String password;
	// 新手机号
	private String newPhone;
	// 验证码
	private String identifyCode;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}
}
