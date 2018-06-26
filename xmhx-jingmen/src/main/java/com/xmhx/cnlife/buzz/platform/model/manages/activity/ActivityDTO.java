package com.xmhx.cnlife.buzz.platform.model.manages.activity;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

/**
 * 活动表
 * @author yyp
 *
 */
@Entity
public class ActivityDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	// PK(主键,自增)
	private int id;
	// 类型（0 其它 1聚会 2 沙龙 3 培训 4 路演 ）
	private String tp;
	// 标题
	private String title;
	// 内容
	private String content;
	// 活动开始时间
	private String stime;
	// 活动结束时间
	private String etime;
	// 报名截止时间
	private String overtime;
	// 地点
	private String address;
	// 联系人
	private String contact;
	// 联系电话
	private String tel;
	// 地点经度
	private String longitude;
	// 地点维度
	private String latitude;
	// 报名人数
	private int enternum;
	// 点赞数
	private int praisenum;
	// 审核状态(0待审核 1审核通过 2审核不通过)
	private String verifystatus;
	// 审核人FK
	private String verifytorid;
	// 审核人名称
	private String verifytor;
	// 审核时间
	private String dateverify;
	// 审核意见
	private String verifydesc;
	// 活动发布人FK
	private String activiissueid;
	
	//扩展字段
	// 活动第一张图片
	private String firstpic;
	// 活动状态
	private String acstate;
	// 开始时间
	private String start_time;
	// 结束时间
	private String end_time;
	// 报名电话
	private String entertel;
	
	//扩展字段
	// 活动图片
	private List<AttachDTO> picList;
	// 评论
	private List<ActivityCommentDTO> commentList;  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public int getEnternum() {
		return enternum;
	}
	public void setEnternum(int enternum) {
		this.enternum = enternum;
	}
	public int getPraisenum() {
		return praisenum;
	}
	public void setPraisenum(int praisenum) {
		this.praisenum = praisenum;
	}
	public String getVerifystatus() {
		return verifystatus;
	}
	public void setVerifystatus(String verifystatus) {
		this.verifystatus = verifystatus;
	}
	public String getVerifytorid() {
		return verifytorid;
	}
	public void setVerifytorid(String verifytorid) {
		this.verifytorid = verifytorid;
	}
	public String getVerifytor() {
		return verifytor;
	}
	public void setVerifytor(String verifytor) {
		this.verifytor = verifytor;
	}
	public String getDateverify() {
		return dateverify;
	}
	public void setDateverify(String dateverify) {
		this.dateverify = dateverify;
	}
	public String getVerifydesc() {
		return verifydesc;
	}
	public void setVerifydesc(String verifydesc) {
		this.verifydesc = verifydesc;
	}
	public String getActiviissueid() {
		return activiissueid;
	}
	public void setActiviissueid(String activiissueid) {
		this.activiissueid = activiissueid;
	}
	public String getFirstpic() {
		return firstpic;
	}
	public void setFirstpic(String firstpic) {
		this.firstpic = firstpic;
	}
	public String getAcstate() {
		return acstate;
	}
	public void setAcstate(String acstate) {
		this.acstate = acstate;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public List<AttachDTO> getPicList() {
		return picList;
	}
	public void setPicList(List<AttachDTO> picList) {
		this.picList = picList;
	}
	public List<ActivityCommentDTO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<ActivityCommentDTO> commentList) {
		this.commentList = commentList;
	}
	public String getEntertel() {
		return entertel;
	}
	public void setEntertel(String entertel) {
		this.entertel = entertel;
	}
	
	
	
}
