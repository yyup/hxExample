package com.xmhx.cnlife.buzz.platform.model.manages.activity.VO;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityCommentDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.CommentAttachDTO;
import com.xmhx.cnlife.core.annotation.Column;
import com.xmhx.cnlife.core.annotation.Entity;

/**
 * 评论扩展类
 * 
 * @author 杨瑜萍 创建时间：2016-4-6
 */
@Entity
public class ActivityCommentVO extends ActivityCommentDTO {

	private static final long serialVersionUID = 1L;
	
	private List<CommentAttachDTO> commentPicList;// 图片
	private String member_nm;// 姓名
	private String member_head;// 头像

	// 虚拟字段
	private Integer idxid; // PK的别名，因为iOS系统对于id认为是关键字而不能使用
	private String in_date;// 评论时间

	public List<CommentAttachDTO> getCommentPicList() {
		return commentPicList;
	}

	public void setCommentPicList(List<CommentAttachDTO> commentPicList) {
		this.commentPicList = commentPicList;
	}

	public String getMember_nm() {
		return member_nm;
	}

	public void setMember_nm(String member_nm) {
		this.member_nm = member_nm;
	}

	public String getMember_head() {
		return member_head;
	}

	public void setMember_head(String member_head) {
		this.member_head = member_head;
	}

	public Integer getIdxid() {
		return idxid;
	}

	public void setIdxid(Integer idxid) {
		this.idxid = idxid;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

}
