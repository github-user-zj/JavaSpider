package com.it.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CrawlerTimeUtil {
	/*
	 * 新浪的时间筛选
	 */
	public static String getData(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	/*
	 * 腾讯的时间筛选
	 */
	public static String getData02() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	/*
	 * 证券财经的时间筛选
	 */
	public static String getData03() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MMdd");
		return df.format(new Date());
	}
	/*
	 * 中国证券网的时间筛选
	 */
	public static String getCnStockData() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		return df.format(new Date());
	}
	
	/*
	 * 华尔街见闻的时间筛选
	 */
	public static String getData04() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.format(new Date());
	}
	/*
	 * 获取前一天的日期
	 */
	public static String getYestodayData(){
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      //  System.out.println(df.format(date));
		
		return df.format(date);
		
	}
	/*
	 * 获取前一天的日期
	 */
	public static String getYestodayData02(){
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.DAY_OF_MONTH, -1);  
		date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		//  System.out.println(df.format(date));
		
		return df.format(date);
		
	}
	
	@Test
	public void aa(){
		System.out.println(getYestodayData());
	}
	
	/*
	 * 获取当前时间戳
	 */
	public static String getTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm:ss");
		String str = sdf.format(date);
		return str;
	}
	
	
	public static String getData05() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		return df.format(new Date());
	}
	
	public static String getData06() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(new Date());
	}
	/*
	 * 获取前几天的日期
	 */
	public static String getBeforeData(int i){
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -i);  
        date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
      //  System.out.println(df.format(date));
		
		return df.format(date);
		
	}
	
	/*
	 * 获取后几天的日期
	 */
	public static String getAfterData(int i){
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.DAY_OF_MONTH, i);  
		date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
		
	}
	
}
