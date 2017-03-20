package com.it.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CrawlerUtil {

//	private static final String FILEPATH = SaveDataUtil.getPath()+"/";   
	/*
	 * 处理title，将/去除，将英文冒号转换未中文的
	 * 否则无法创建新的网页文件
	 */
	public static String dealTitle(String title) {
		String newTitle = title.replaceAll("/", " ").replaceAll(":", "：")
				.replaceAll("\\*", " ").replaceAll("「", " ")
				.replaceAll("」", " ").replaceAll("\\?", " ").replaceAll("\"", " ").replaceAll("\\|", " ");
		return newTitle;
	}

	
	//判断为空
	public static boolean strIsNull(String str){
		str = str.replaceAll("\\s*", "");
		if( null != str &&!"".equals(str)){
			return true;
		}
		return false;
	}
	
	// 提取<a>标签中的url 链接
	public static String dealUrl(String html) {
		String announcementUrl = null;
		if(strIsNull(html)){
			String regex = "(?<=[\\s+]?href[\\s+]?=[\\s+]?('|\")?)[^(\"|')>]+?(?=\"|')";
			Matcher matcher = Pattern.compile(regex).matcher(html);
			if (matcher.find()) {
				announcementUrl = matcher.group(0);
			}
		}
		return announcementUrl;
	}
	
	//字符串转时间
	public static Date dealDate(SimpleDateFormat sdf, String string){
		Date date = null;
		string = string.replace("/", "-");
		try {
			if(string!=null&&!"".equals(string)){
				date = sdf.parse(string);
			}else{
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
