package com.it.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *   新闻类
 */
public class NewsArticle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ar_title")
	private String title;	//标题
	
	@Column(name = "ar_author")
	private String author;	//作者（0：未获取到作者名）
	
	@Column(name = "ar_url")
	private String url;		//文章url地址
	
	@Column(name = "website_id")
	private Integer websiteId;//文章所属网站
	
	@Column(name = "ar_time")
	private String arTime;	//文章发表时间
	
	@Column(name = "ar_content")
	private String content;	//文章内容
	
	@Column(name = "ar_root_from")
	private String arFrom;  //文章的原创地址
	
	//==============================================
	
	@Column(name = "adduser" )
	private java.lang.Long adduser;
    	    	    	    
	@Column(name = "updateuser" )
	private java.lang.Long updateuser;
    	    	    	    
	@Column(name = "addtime" )
	private java.util.Date addtime;
    	    	    	    
	@Column(name = "updatetime" )
	private java.util.Date updatetime;
	
	@Column(name = "p1")
	private String p1; 
	
	@Column(name = "p2")
	private String p2; 
	
	@Column(name = "p3")
	private String p3; 
	
	public NewsArticle() {
		super();
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getWebsiteId() {
		return websiteId;
	}


	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getArFrom() {
		return arFrom;
	}


	public void setArFrom(String arFrom) {
		this.arFrom = arFrom;
	}

	public String getArTime() {
		return arTime;
	}

	public void setArTime(String arTime) {
		this.arTime = arTime;
	}

	public java.lang.Long getAdduser() {
		return adduser;
	}

	public void setAdduser(java.lang.Long adduser) {
		this.adduser = adduser;
	}

	public java.lang.Long getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(java.lang.Long updateuser) {
		this.updateuser = updateuser;
	}

	public java.util.Date getAddtime() {
		return addtime;
	}

	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	
}
