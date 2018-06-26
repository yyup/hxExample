package com.xmhx.cnlife.buzz.platform.model.manages.activity;

import java.util.List;

import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

/**
 * 活动评论表
 * @author yyp
 *
 */
@Entity
public class ActivityCommentDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	// 自增id
	private int id;
	// 活动id
	private String activityid;
	// 评论内容
	private String content;
	
	//扩展字段
	private List<CommentAttachDTO> commentPicList;// 图片
	private String member_name;// 姓名
	private String member_head;// 头像
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<CommentAttachDTO> getCommentPicList() {
		return commentPicList;
	}
	public void setCommentPicList(List<CommentAttachDTO> commentPicList) {
		this.commentPicList = commentPicList;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_head() {
		return member_head;
	}
	public void setMember_head(String member_head) {
		this.member_head = member_head;
	}
	
	
	   
}
