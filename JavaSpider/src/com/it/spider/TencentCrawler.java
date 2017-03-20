package com.it.spider;
import org.apache.log4j.Logger;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import com.it.util.CrawlerTimeUtil;
import com.it.util.SaveCrawler;
import com.it.util.SaveDataUtil;

/**
 *	腾讯财经网络爬虫
 *
 */

public class TencentCrawler extends BreadthCrawler {
	
	Logger logger = Logger.getLogger(TencentCrawler.class);  
	
	String date = CrawlerTimeUtil.getData02();
	String webSiteName = "Tencent";
	public TencentCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		/* start page */
		this.addSeed("http://finance.qq.com/");
		this.addSeed("http://stock.qq.com/");
		
		String http   = new String("http://.*.qq.com/a/"+date+"/[0-9]*.*");

		this.addRegex(http);
		// this.addRegex("-.*\\.(jpg|png|gif).*");
		this.addRegex("-.*#.*");

	}
   
    @Override
	public void visit(Page page, CrawlDatums next) {
		String url = page.getUrl();
		
		if (page.matchUrl("http://.*.qq.com/a/"+date+"/[0-9]*.*")) {
			try {
					SaveCrawler
							.saveAllHTML(url, page.getContent(), webSiteName);
					System.out.println("url:"+url);
					SaveDataUtil.writeRightFile(webSiteName, url);
			} catch (NullPointerException e) {
				logger.info("url 失效"+url);
				SaveDataUtil.writeExceptionUrl(webSiteName,url);
				e.printStackTrace();
			}catch (Exception e) {
				SaveDataUtil.writeExceptionFile(webSiteName,url);
				logger.info("--Exception--"); 
			}
			
		}
	}

	public static void main(String[] args) throws Exception {
		TencentCrawler crawler = new TencentCrawler("crawl", true);
		
		/**线程数*/
		crawler.setThreads(50);
		
		/**设置每次迭代中爬取数量的上限*/
		crawler.setTopN(500);
		/**
		 * 设置是否为断点爬取，如果设置为false，任务启动前会清空历史数据。
		 * 如果设置为true，会在已有crawlPath(构造函数的第一个参数)的基
		 * 础上继续爬取。
		 */
		crawler.setResumable(false);
		/**
		 * 开始深度为4的爬取，这里深度和网站的拓扑结构没有任何关系 可以将深度理解为迭代次数，往往迭代次数越多，爬取的数据越多
		 */
		/**
		 * 请求间隔
		 */
		//crawler.setExecuteInterval(1006);
		
		crawler.start(2);
	}

}