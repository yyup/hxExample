package com.xmhx.cnlife.core.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.xmhx.cnlife.core.annotation.Entity;

@Entity
public class PushMsg implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean status;		// 状态
	private Object info;		// 信息	
	private int arg1;			// 附加值
	private Map<String, Object> attr = new HashMap<String, Object>();

	public PushMsg() {}

	public PushMsg(Boolean status, String info) {
		this.info = info;
		this.status = status;
	}
	
	public PushMsg(Boolean status, String info, int arg1) {
		this.arg1 = arg1;
		this.info = info;
		this.status = status;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public int getArg1() {
		return arg1;
	}

	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}
	
	/** 
	 * 加入反馈属性
	 * @return
	 */
	public Map<String, Object> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

	/**
	 * 得到消息对象
	 * @param info
	 * @param status
	 * @return
	 */
	public static PushMsg getPushMsg(String info, Boolean status) {
		PushMsg msg = new PushMsg();
		msg.setInfo(info);
		msg.setStatus(status);
		return msg;
	}

}
