package com.xmhx.cnlife.core.model;

import java.io.Serializable;
import java.util.List;

import com.xmhx.cnlife.core.annotation.Entity;

/**
 * 树
 * @author wujin
 *
 * @param <T>
 */
@Entity
public class TreeVO<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private String iconCls;
	private String state = "open";
	private boolean checkbox;
	private boolean checked;
	private List<TreeVO<T>> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<TreeVO<T>> getChildren() {
		return children;
	}
	public void setChildren(List<TreeVO<T>> children) {
		this.children = children;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
}
