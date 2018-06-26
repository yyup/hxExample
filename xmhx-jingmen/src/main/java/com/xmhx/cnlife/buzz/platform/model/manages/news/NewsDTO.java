package com.xmhx.cnlife.buzz.platform.model.manages.news;

import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

@Entity
public class NewsDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int readCount;	//阅读量
	private String newsTitle;	//标题
	private String newsSubtitle;//副标题
	private String newsType;	//新闻类型 （预留）
	private String newsContent;	//内容
	private String verifyStatus;//审核状态(0待审核 1审核通过 2审核不通过)
	private String verifytorId;	//审核人ID
	private String verifytor;	//审核人姓名
	private String verifyDate;	//审核时间
	private String verifyDesc;	//审核意见
	
	//虚拟字段
	private String attachFullpath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttachFullpath() {
		return attachFullpath;
	}
	public void setAttachFullpath(String attachFullpath) {
		this.attachFullpath = attachFullpath;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsSubtitle() {
		return newsSubtitle;
	}
	public void setNewsSubtitle(String newsSubtitle) {
		this.newsSubtitle = newsSubtitle;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getVerifytorId() {
		return verifytorId;
	}
	public void setVerifytorId(String verifytorId) {
		this.verifytorId = verifytorId;
	}
	public String getVerifytor() {
		return verifytor;
	}
	public void setVerifytor(String verifytor) {
		this.verifytor = verifytor;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getVerifyDesc() {
		return verifyDesc;
	}
	public void setVerifyDesc(String verifyDesc) {
		this.verifyDesc = verifyDesc;
	}


}
