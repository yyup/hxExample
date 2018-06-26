package com.xmhx.cnlife.core.model;

import java.io.Serializable;

import com.xmhx.cnlife.buzz.utils.DateUtils;
import com.xmhx.cnlife.core.annotation.Column;
import com.xmhx.cnlife.core.annotation.Entity;

/**
 * 公有字段类
 * @author wujin
 * @createtime 2014-06-03
 */
@Entity
public class BaseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * UUID
	 */
	private String hxuuid;
	/**
	 * 是否有效 Y-有效 N-无效
	 */
	protected String data_state;
	/**
	 * 创建人
	 */
	protected String created_by;
	/**
	 * 创建时间
	 */
	protected String date_created;
	/**
	 * 更新人
	 */
	protected String updated_by;
	/**
	 * 更新时间
	 */
	protected String date_updated;
	
	public BaseDTO() {
		this.data_state = "Y";
		this.date_created = DateUtils.getDatetimeNow();
		this.date_updated = DateUtils.getDatetimeNow();
	}
	
	@Column(columnName="hxuuid")
	public String getHxuuid() {
		return hxuuid;
	}

	public void setHxuuid(String hxuuid) {
		this.hxuuid = hxuuid;
	}
	
	@Column(columnName="data_state")
	public String getData_state() {
		return data_state;
	}

	public void setData_state(String data_state) {
		this.data_state = data_state;
	}

	@Column(columnName="created_by")
	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	@Column(columnName="date_created")
	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	@Column(columnName="updated_by")
	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	@Column(columnName="date_updated")
	public String getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}

}
