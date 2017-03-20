package com.it.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpGetData{
	
	Logger log = Logger.getLogger(HttpGetData.class);
	
    public static String requestByGetMethod(String url,String charset) throws IOException {
    	System.out.println("");
    	System.out.print("--Get连接开始：");
        CloseableHttpClient httpClient = null;
        String pdfString = null;
        CloseableHttpResponse httpResponse = null;
        try {
        	
        	HttpGet get = new HttpGet(url);          
        	//设置30秒获取不到就取消连接
        	RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000*30).build();
        	get.setConfig(requestConfig);
			// 执行请求
        	httpClient = HttpClients.createDefault();
        	if(httpClient !=null){
        		httpResponse = httpClient.execute(get);
            	if(httpResponse != null){
            		HttpEntity entity = httpResponse.getEntity();
        			if (null != entity) {
        				pdfString = EntityUtils.toString(entity , charset);
        			}
            	}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally{
        	if(httpResponse!= null){
        		try {
					httpResponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if (httpClient != null){
            	try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        	System.out.println("--Get连接结束。");
        }
        
    	return pdfString;
    }
}
