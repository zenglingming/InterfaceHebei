package com.palm.utils; 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**     
 * Description: httpclient工具类 
 *
 * ClassName:HttpRequestUtils 
 * Date:     2016年3月15日 下午3:54:07    
 * @author   lingming.zeng
 * @version     
 * @since    JDK 1.8   
 * Copyright (c) 2016, o2o-lottery All Rights Reserved.         
 */
public class HttpRequestUtils {
	
	private static Logger logger = Logger.getLogger(HttpRequestUtils.class);
			
	/**
	 * 
	 * function: 发送post请求
	 *   
	 * @param jsonParm	 数据包含3部分，String[0]是封装的json串String[1]是transcode值String[2]是partnerid值
	 * @param domain	
	 * @return   
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	public static String httpPost(String json,String domain) throws Exception { 
		logger.info("Post------"+json);
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(domain);  
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("json", json));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    String v31result = EntityUtils.toString(entity, "UTF-8");  
                    logger.info("Response--"+v31result);
                    return v31result;
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
            	if (httpclient!=null) {
            		httpclient.close();  
				}
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return null;  
    }
	
	/**
	 * 
	 * function: 发送get请求
	 * 
	 * @return
	 * @since JDK 1.8
	 */
	public static String httpGet(String url) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httpget
		HttpGet httpget = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if(entity.getContentLength()!=-1){
						String v31result = EntityUtils.toString(entity, "UTF-8");
						return v31result;
					}
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
   
