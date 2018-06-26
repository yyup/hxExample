package com.xmhx.core.model;

import java.util.List;

import com.xmhx.core.annotation.Entity;

@Entity
public class PageVO<T> {
	// 总数
	private int total;
	// 数据
	private List<T> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
