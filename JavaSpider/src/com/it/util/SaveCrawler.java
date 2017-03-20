package com.it.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;


public class SaveCrawler {
//	private static final String FILEPATH = "F:/spiderNews/";  
	private static final String FILEPATH = SaveDataUtil.getPath()+ File.separator+"WebSiteData"+ File.separator;  
	public static void saveFile(String title,String url,byte[] content,String time,
								String webSiteName, String newType) {
		//获取标题
	
		String date = CrawlerTimeUtil.getData02();
		String txtName = "";
		if("ccyjj".equals(newType)){
			txtName = "产业经济";
		}else if("cssgs".equals(newType)){
			txtName = "上市公司";
		}else if("czqyw".equals(newType)){
			txtName = "证券要闻";
		}
		
		// 每天新建一个文件夹单独存放
		File fileName = new File(FILEPATH+webSiteName+"/"+date+"/"+newType+ "/");
		if (!fileName.isDirectory()) {
			fileName.mkdirs();
		}
		
		//存放title 和  url 
		File urlFile = new File(fileName+"/record."+ txtName + ".txt");
		writeUrl(url,urlFile,title,time);
				
		//存放网页内容
		File file = new File(fileName+"/"+ title + ".html");
		try (FileOutputStream fop = new FileOutputStream(file)) {

			//如果不存在怎创建文件将内容写入，如果存在则不写入
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(content);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
//	private static String getPath() {
//		String str= new File("").getAbsolutePath();
//        String string[] = str.split("\\\\");
//        if (string.length>=2) {
//			str = str.replace(string[string.length-1], "webapps/spiderNews/");
//		}
//
//		return str;
//	}


	/**
	 * 综合类保存网页
	 */
	public static void saveAllFile(String title,String url,byte[] content,String time,
			String webSiteName) {
		
		String date = CrawlerTimeUtil.getData02();
		
		
		// 每天新建一个文件夹单独存放
		File fileName = new File(FILEPATH+webSiteName+"/"+date+"/");
		if (!fileName.isDirectory()) {
			fileName.mkdirs();
		}
		
		//存放title 和  url 
		File urlFile = new File(fileName+"/1."+ webSiteName + ".txt");
		writeUrl(url,urlFile,title,time);
		
		//存放网页内容
		File file = new File(fileName+"/"+ title + ".html");
		try (FileOutputStream fop = new FileOutputStream(file)) {
			
			//如果不存在怎创建文件将内容写入，如果存在则不写入
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(content);
			fop.flush();
			fop.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 以url地址为文件名保存网页
	 * 注： 存储的url不能有 \ / : * ? " < >  | 
	 *    需要将这些字符转换为其它字符
	 */
	public static void saveAllHTML(String url,byte[] content,
			String webSiteName) {
		
		String date = CrawlerTimeUtil.getData02();
		String urlString = url.replaceAll("//", "。").replaceAll("/", "\\$").replaceAll(":", "#").replaceAll("\\?", "@");
		
		// 每天新建一个文件夹单独存放
		File fileName = new File(FILEPATH+webSiteName+"/"+date+"/");
		if (!fileName.isDirectory()) {
			fileName.mkdirs();
		}
		//存放title 和  url 
		//File urlFile = new File(fileName+"/1."+ webSiteName + ".txt");
		//writeUrl(url,urlFile,title,time);
		
		//存放网页内容
		File file = new File(fileName+"/"+urlString);
		try (FileOutputStream fop = new FileOutputStream(file)) {
			
			//如果不存在怎创建文件将内容写入，如果存在则不写入
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(content);
			fop.flush();
			fop.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void saveHTML(String url,byte[] content,
			String webSiteName, String newType) {
		//获取标题
		
		String date = CrawlerTimeUtil.getData02();
		/*String txtName = "";
		if("ccyjj".equals(newType)){
			txtName = "产业经济";
		}else if("casks".equals(newType)){
			txtName = "上市公司";
		}else if("czqyw".equals(newType)){
			txtName = "证券要闻";
		}*/
		
		// 每天新建一个文件夹单独存放
		File fileName = new File(FILEPATH+webSiteName+"/"+newType+"/"+date+ "/");
		if (!fileName.isDirectory()) {
			fileName.mkdirs();
		}
		
		/*//存放title 和  url 
		File urlFile = new File(fileName+"/1."+ txtName + ".txt");
		writeUrl(url,urlFile,title,time);*/
		String urlString = url.replaceAll("//", "。").replaceAll("/", "\\$").replaceAll(":", "#").replaceAll("\\?", "@");
		//存放网页内容
		File file = new File(fileName+"/"+ urlString);
		try (FileOutputStream fop = new FileOutputStream(file)) {
			
			//如果不存在怎创建文件将内容写入，如果存在则不写入
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(content);
			fop.flush();
			fop.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	 /*
	 * 将标题和url 写入文件
	 */
	static int i = 0;
	public static void writeUrl(String url,File file,String title, String time) {
		try {
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write((++i)+","+title + ",\r\n" +time + ",\r\n"+url + "\r\n\r\n");
			bufferWritter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void aa(){
		
		String urlString = "http#。company.stcn.com$2016$0819$12844146.shtml";
		String urlString2 = urlString.replaceAll("//", "。").replaceAll("/", "\\$").replaceAll(":", "#").replaceAll("\\?", "@");
		System.out.println(urlString2);
		System.out.println();
		String urlString3 = urlString2.replaceAll("。", "//").replaceAll("\\$", "/").replaceAll("#", ":").replaceAll("@", "?");
		System.out.println(urlString3);
		
	}
	
	
	
}
