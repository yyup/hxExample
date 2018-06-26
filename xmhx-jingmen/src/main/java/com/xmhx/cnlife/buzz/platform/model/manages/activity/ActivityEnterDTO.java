package com.xmhx.cnlife.buzz.platform.model.manages.activity;

import com.xmhx.cnlife.core.annotation.Entity;
import com.xmhx.cnlife.core.model.BaseDTO;

/**
 * 园区活动报名表
 * 
 * @author 吴进 by 20160405
 * 
 */
@Entity
public class ActivityEnterDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// PK
	private int id;
	// 活动id
	private String activityid;
	// 电话
	private String entertel;
	// 姓名
	private String enternm;
	// 公司
	private String entercompany;
	// 备注
	private String enterremark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}

	public String getEntertel() {
		return entertel;
	}

	public void setEntertel(String entertel) {
		this.entertel = entertel;
	}

	public String getEnternm() {
		return enternm;
	}

	public void setEnternm(String enternm) {
		this.enternm = enternm;
	}

	public String getEntercompany() {
		return entercompany;
	}

	public void setEntercompany(String entercompany) {
		this.entercompany = entercompany;
	}

	public String getEnterremark() {
		return enterremark;
	}

	public void setEnterremark(String enterremark) {
		this.enterremark = enterremark;
	}

}
