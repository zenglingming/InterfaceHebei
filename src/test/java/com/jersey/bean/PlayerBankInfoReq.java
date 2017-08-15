package com.jersey.bean;   


/**     
 * Description: TODO 
 *
 * ClassName:PlayerBindBankReq 
 * Date:     2016年5月4日 下午2:57:29    
 * @author   jianjun.zhang   
 * @version     
 * @since    JDK 1.8   
 * Copyright (c) 2016, o2o-lottery All Rights Reserved.         
 */
public class PlayerBankInfoReq extends BaseRequest{
	
	/**
	 * 银行卡号
	 */
	private String bankCard;
	
	/**
	 * 银行信息
	 */
	private String bank;
	
	/**
	 * 开户行地址
	 */
	private String bankAddress;
	
	/**
	 * 支行名称
	 */
	private String bankBranch;
	

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
}
   
