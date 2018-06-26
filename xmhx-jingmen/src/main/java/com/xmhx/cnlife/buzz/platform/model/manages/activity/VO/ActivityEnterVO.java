package com.xmhx.cnlife.buzz.platform.model.manages.activity.VO;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityEnterDTO;
import com.xmhx.cnlife.core.annotation.Column;
import com.xmhx.cnlife.core.annotation.Entity;

/**
 * 园区活动报名扩展类
 * @author 杨瑜萍 by 20160622
 *
 */
@Entity
public class ActivityEnterVO extends ActivityEnterDTO {
	
	private static final long serialVersionUID = 1L;
	
	// 成员名称
	private String membernm;
	//成员头像
	private String memberhead;
	
	public String getMembernm() {
		return membernm;
	}
	public void setMembernm(String membernm) {
		this.membernm = membernm;
	}
	
	public String getMemberhead() {
		return memberhead;
	}
	public void setMemberhead(String memberhead) {
		this.memberhead = memberhead;
	}
	
}
