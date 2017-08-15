package com.jersey.bean;

/**     
 * Description: TODO  
 *
 * ClassName: ManagerStationInfoReq 
 * date: 2016年3月15日 上午10:38:06    
 *   
 * @author lihao.qiu
 * @version    
 * @since JDK 1.8 
 * Copyright (c) 2016, o2o-lottery All Rights Reserved.     
 */
public class GetDrawInfoReq extends BaseRequest {
	/**
	 * 彩种
	 */
	private String gameId;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

}
