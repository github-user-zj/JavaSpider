package com.it.spider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.it.entity.NewsArticle;
import com.it.util.CrawlerTimeUtil;
import com.it.util.CrawlerUtil;
import com.it.util.HttpGetData;
import com.it.util.HttpTreadUtil;
import com.it.util.SaveCrawler;


/**
 *  功能：华尔街见闻数据（使用中）
 * @author zhangj
 * 2016年11月24日  下午4:30:04
 */
public class WallStreetCnSpider {
	
	public static void main(String[] args) {
		List<NewsArticle> newsList = getWallStreetNews(new ArrayList<NewsArticle>());
		System.out.println(newsList.size());
		for (int i = 0; i < newsList.size(); i++) {
			System.out.println(newsList.get(i).getUrl()+":"+newsList.get(i).getTitle());
		}
		
	}

	public static List<NewsArticle> getWallStreetNews(List<NewsArticle> urlAndTitle) {
	
		//网站名称
		String webSiteName = "WallStreetCn";
		
		List<NewsArticle> newsList = new ArrayList<NewsArticle>();
		List<String> urlString = getWallStreetcnUrl();
		//去除网页中的广告信息
		List<String> strList = getAd();
		//从数据库中查询出url和标题 ，去重
		List<String> arUrl = new ArrayList<String>();
		List<String> titles = new ArrayList<String>();
		
		for (int i = 0; i < urlAndTitle.size(); i++) {
			arUrl.add(urlAndTitle.get(i).getUrl());
			titles.add(urlAndTitle.get(i).getTitle().replaceAll("\\s*",""));
		}
		
		String[] strs =  threadGetWallStreet(urlString);
		
		for (int i = 0; i < strs.length; i++) {
			String contentHtml = null;
			try {
				contentHtml = strs[i];
				if (CrawlerUtil.strIsNull(contentHtml)) {
					
					Document doc = Jsoup.parse(contentHtml);
					Elements title = doc.select("#main > div.page-article > div.page-article-title > div.title-text");
					Elements time = doc.select("#main > div.page-article > div.page-article-title > div.title-meta > div.title-meta-time");
					Elements author = doc.select("#article-rightbar > div.rightbar-author.rightbar-item > div.rightbar-author-meta > div:nth-child(1) > a.author-name");
					Elements content = doc.select("#main > div.page-article > div.page-article-content");
//					Elements contentText = doc.select("p");
					
					String contentString = content.text();
//					String contentString = contentText.text();

					
					//去除广告等信息
					for (int j = 0; j < strList.size(); j++) {
						contentString = contentString.replace(strList.get(j), "");
					}
					//分段落
					contentString = getContent(contentString);
					//根据P标签分段落
//					contentString=contentString.replaceAll("<p>", "&nbsp;&nbsp;&nbsp;&nbsp;").replaceAll("</p> ", "<br>");
					NewsArticle newsArticle = null;
					//如果标题为空则不添加
					if (CrawlerUtil.strIsNull(title.text())) {
						boolean flag = compareTime(time.text());
						if (flag) {
							//根据名称和url去重
							if ((!arUrl.contains(urlString.get(i)))
									&& (!titles.contains(title.text()))) {
								newsArticle = new NewsArticle();
								newsArticle.setTitle(title.text());
								if(CrawlerUtil.strIsNull(author.text())){
									newsArticle.setAuthor(author.text());
								}else{
									newsArticle.setAuthor("0");
								}
								
								newsArticle.setUrl(urlString.get(i));
								newsArticle.setArTime(getDate(time.text()));

								newsArticle.setContent(contentString);
								newsArticle.setArFrom("华尔街见闻");
								newsArticle.setWebsiteId(8);
								newsArticle.setAddtime(new Date());
								newsArticle.setAdduser(1L);
								//保存到本地的方法
								SaveCrawler.saveAllHTML(urlString.get(i),contentHtml.getBytes(),webSiteName);
//								System.out.println(""+newsArticle.getUrl()+":"+newsArticle.getTitle());
								
								newsList.add(newsArticle);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newsList;
	}


	// 提取<a>标签中的url 链接
	private static String dealUrl(String html) {
		String announcementUrl = null;
		String regex = "(?<=[\\s+]?href[\\s+]?=[\\s+]?('|\")?)[^(\"|')>]+?(?=\"|')";
		Matcher matcher = Pattern.compile(regex).matcher(html);
		if (matcher.find()) {
			announcementUrl = matcher.group(0);
		}
		return announcementUrl;
	}
	
//  获取首页所有的url	
	public static List<String> getWallStreetcnUrl() {
		List<String> urlString = new ArrayList<String>();
		String contentString = "";
		try {
			contentString = HttpGetData.requestByGetMethod(
					"http://wallstreetcn.com/news", "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (CrawlerUtil.strIsNull(contentString)) {
			Document doc = Jsoup.parse(contentString);
			for (int i = 0; i <= 20; i++) {
				Elements title = doc.select("#news > ul > li:nth-child(" + i
						+ ") > div > a.news-title");
				if (CrawlerUtil.strIsNull(title.text())) {
					urlString.add(dealUrl(title + ""));
				}
			}
		}
		
		return urlString;
	}
	//比较是否为当天的新闻
	public static boolean compareTime(String time){
		String date = CrawlerTimeUtil.getData04();
		String[] str = time.split(" ");
		if(str.length==2){
			if(date.equals(str[0].replaceAll(" ", "").replaceAll("", ""))){
				return true;
			}
		}
		return false;
	}
	
	//去除网页中的广告
	public static List<String> getAd(){
		List<String> strList = new ArrayList<String>();
		
		strList.add("© 2010 - 2016 华尔街见闻-上海阿牛信息科技有限公司 wallstreetcn.com 沪ICP备13019121号");
		strList.add("（更多精彩财经资讯，点击这里下载华尔街见闻App)");
		strList.add("扫描上方二维码，关注见闻美股微信公众号");
		return strList;
	}
	
	//转换成时间, 需要去除空格。
	private static String getDate(String dateString) {
		dateString = dateString.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
		return dateString.replaceAll("", "");
	}

	//对网页内容换行
	private static String getContent(String content) {
		
		String contentString = "";
		String[] str = content.split(" ");
		if (str.length >= 1) {
			for (int i = 0; i < str.length; i++) {
				contentString+="&nbsp;&nbsp;&nbsp;&nbsp;"+str[i]+"<br>";
			}
		}
		return contentString;
	}
	
	//多线程访问网页
	public static String[] threadGetWallStreet(List<String> urlString){
		List<Map<String, Object>> reqList = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < urlString.size(); j++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", urlString.get(j));
			map.put("params", null);
			map.put("encode", "utf-8");
			reqList.add(map);
		}
		String[] strs = HttpTreadUtil.threadGet(reqList);
		System.out.println(strs.length);
		return strs;
		
	}

}