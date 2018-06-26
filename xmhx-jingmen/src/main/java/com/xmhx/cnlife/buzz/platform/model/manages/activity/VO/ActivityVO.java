package com.xmhx.cnlife.buzz.platform.model.manages.activity.VO;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.core.annotation.Column;
import com.xmhx.cnlife.core.annotation.Entity;

/**
 * 活动扩张类
 * 
 * @author 杨瑜萍 创建时间：2016-4-6
 */
@Entity
public class ActivityVO extends ActivityDTO {
	
	private static final long serialVersionUID = 1L;
	
	// 活动图片
	private List<AttachDTO> picList;
	// 评论
	private List<ActivityCommentVO> commentList;
	// 活动第一张图片
	private String firstpic;
	// 活动分享URL
	private String activishareurl;
	// 活动状态
	private String acstate;
	// 展示时间别名，create_date多表或者分页查询存在只显示当前时间
//	private String in_date;
	// 开始时间
	private String start_time;
	// 结束时间
	private String end_time;
	// 报名电话
	private String entertel;

	public List<AttachDTO> getPicList() {
		return picList;
	}

	public void setPicList(List<AttachDTO> picList) {
		this.picList = picList;
	}
/*
	public List<ActivityCommentVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<ActivityCommentVO> commentList) {
		this.commentList = commentList;
	}*/

	public String getFirstpic() {
		return firstpic;
	}

	public void setFirstpic(String firstpic) {
		this.firstpic = firstpic;
	}

	public String getActivishareurl() {
		return activishareurl;
	}

	public void setActivishareurl(String activishareurl) {
		this.activishareurl = activishareurl;
	}

	public String getAcstate() {
		return acstate;
	}

	public void setAcstate(String acstate) {
		this.acstate = acstate;
	}

//	public String getIn_date() {
//		return in_date;
//	}
//
//	public void setIn_date(String in_date) {
//		this.in_date = in_date;
//	}

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

	public String getEntertel() {
		return entertel;
	}

	public void setEntertel(String entertel) {
		this.entertel = entertel;
	}

}
