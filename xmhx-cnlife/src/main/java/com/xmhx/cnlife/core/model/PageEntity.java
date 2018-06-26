package com.xmhx.cnlife.core.model;

import java.io.Serializable;

import com.xmhx.cnlife.core.annotation.Entity;

/**
 * MySQL Mybatis入参
 * @author 吴进 by 20161130
 *
 * @param <T>
 */
@Entity
public class PageEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 第几页
	private int page;
	// 每页显示几行
	private int rows;
	// 排序字段
	private String sort;
	// 正序或倒序
	private String order;
	// 实体
	private T t;
	// 起始行
	private int beginRow;
	// 结束行
	private int endRow;
	
	public PageEntity(int page, int rows, String sort, String order, T t, PageBean<T> pageBean) {
		this.page = page;
		this.rows = rows;
		this.sort = sort;
		this.order = order;
		this.t = t;
		this.beginRow = (pageBean.getCurrentPage() <= 1 ? 0 : pageBean.getCurrentPage()-1)*pageBean.getPageSize();
		this.endRow = beginRow + pageBean.getPageSize();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
}
