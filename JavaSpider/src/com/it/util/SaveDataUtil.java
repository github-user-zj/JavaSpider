package com.it.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class SaveDataUtil {
	
	/**
	 * linux操作系统的路径 		/data/spiderNews
	 */
	public static  final String LIUNXPATH = "/data"+File.separator +"spiderNews";
	
	/**
	 * 判断系统
	 */
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") != -1) {
			return true;
		}
		return false;
	}
	
	/**
	 *  保存的爬虫数据根路径
	 * @author：zhangjun
	 *  @date：2016年11月14日
	 *	下午1:53:08
	 *
	 */
	public static String getPath() {
		String str = null;
		if (isWindows()) {
			str = new File("").getAbsolutePath();
			String string[] = str.split("\\\\");
			if (string.length >= 2) {
				str = str.replace(string[string.length - 1], "webapps"+ File.separator + "QuaninveCrawler"+ File.separator+"public"+File.separator + "spiderNews");
			}

		} else {
			str = LIUNXPATH;
			File file = new File(str);

			// 依次创建多个父目录
			// if (!file.getParentFile().exists()) {
			// file.getParentFile().mkdirs();
			// }
			if (!file.isDirectory()) {
				file.mkdirs();
			}
		}
		// 如果不存在新建文件夹
		File folder = new File(str);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}

		return str;
	}
	
	/**
	 * @author：zhangjun
	 *  @date：2016年11月14日
	 *	下午1:53:48
	 *  用html格式保存政策信息
	 *  
	 */
	
	public static void savePoliData(String urlString,String policyContent){
		String date = CrawlerTimeUtil.getData02();
		String poliPath = getPath() + File.separator + "Policy"+ File.separator + date;
		
		// 每天新建一个文件夹单独存放
		File folder = new File(poliPath);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		String pathstring = urlString.replaceAll("//", "。")
				.replaceAll("/", "\\$").replaceAll(":", "#")
				.replaceAll("\\?", "@");
		// 存放网页内容
		File file = new File(folder + File.separator + pathstring + ".html");
		try (FileOutputStream fop = new FileOutputStream(file)) {

			// 如果不存在怎创建文件将内容写入，如果存在则不写入
			if (!file.exists()) {
				file.createNewFile();
			}
			fop.write(policyContent.getBytes());
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
//		savePoliData();
		System.out.println(getPath());
	}
	
	/**
	 * 用cvs格式保存信息昨天的股票数据
	 * 
	 */

	public static String getCVSPath() {
		String str = getPath() + File.separator +"CVS";
		// 如果不存在则创建
		File fileName = new File(str);
		if (!fileName.isDirectory()) {
			fileName.mkdirs();
		}
		return str;
	}
	
	

	/**
	 * 将抓取实时新闻NullPointerException的url 写入文件
	 */
	static int exc = 0;
	public static void writeExceptionUrl(String webSiteName,String url) {
		String filepathexcep = getPath()+ File.separator +"ExceptionFile" + File.separator+"NullPointerUrl"+ File.separator;
		try {
			File fileName = new File(filepathexcep+CrawlerTimeUtil.getData02());
			if (!fileName.isDirectory()) {
				fileName.mkdirs();
			}
			File file = new File(fileName+ File.separator +"nullPointerUrl.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write((++exc)+": "+webSiteName+" : "+url + "\r\n\r\n");
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据传入的名字创建文件
	 */
	public static String getPaths(String fileString) {
		String str = getPath() + File.separator +fileString;
		// 如果不存在则创建
		File fileName = new File(str);
		if (!fileName.isDirectory()) {
			fileName.mkdirs();
		}
		return str;
	}
	
	/**
	 * 将出现的未知错误写入文件
	 */
	
	static int excInt = 0;
	public static void writeExceptionFile(String webSiteName,String url) {
		//linux路径     /data/spiderNews/ExceptionFile/ExceptionUrl/exceptionUrl
		String filepathexcep =  getPath()+ File.separator +"ExceptionFile" + File.separator+"ExceptionUrl"+ File.separator;
		try {
			File fileName = new File(filepathexcep+CrawlerTimeUtil.getData02());
			if (!fileName.isDirectory()) {
				fileName.mkdirs();
			}
			File file = new File(fileName+ File.separator +"exceptionUrl.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write((++excInt)+": "+webSiteName+" : "+url + "\r\n\r\n");
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  记录抓取成功的数据的url
	 */
	static int rightCount = 0;
	public static void writeRightFile(String webSiteName,String url){
		//linux路径     /data/spiderNews/RightFile/RightUrl
		String rightfilepath =  getPath()+ File.separator +"RightFile" + File.separator+"RightUrl"+ File.separator;
		try {
			File fileName = new File(rightfilepath+CrawlerTimeUtil.getData02());
			if (!fileName.isDirectory()) {
				fileName.mkdirs();
			}
			File file = new File(fileName+ File.separator +"RightUrl.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write((++rightCount)+": "+webSiteName+" : "+url + "\r\n\r\n");
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  记录抓取成功总数
	 */
	static int rightLongCount = 0;
	public static void writeRightCountFile(String webSiteName,int count){
		//linux路径     /data/spiderNews/RightFile/RightUrl
		String rightfilepath =  getPath()+ File.separator +"RightFile" + File.separator+"RightUrl"+ File.separator;
		String time = CrawlerTimeUtil.getTime();
		try {
			File fileName = new File(rightfilepath+CrawlerTimeUtil.getData02());
			if (!fileName.isDirectory()) {
				fileName.mkdirs();
			}
			File file = new File(fileName+ File.separator +"RightCount.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fileWritter = new FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write("--"+time+"--\r"+(++rightLongCount)+": "+webSiteName+" : "+count + "条记录\r\n\r\n");
			bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
