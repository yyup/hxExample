package com.xmhx.cnlife.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xmhx.cnlife.core.annotation.Entity;

/**
 * 这个是兼容 MySQL的分页
 * @author 吴进 by 20150813
 *
 * @param <T>
 */
@Entity
public class PageBean<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int recordCount;		// 记录总数
	private List<T> dataList;		// 数据
	
	private int currentPage;		// 第几页,即当前页
	private int pageSize;			// 每页大小
	private int pageCount;			// 共几页
	private int startPage;			// 开始页码数
	private int endPage;			// 结束页码数
	private int maxShowPageNum = 6;	// 最大显示页码数
	
	private List<Integer> pages ;
	private Object obj;
	
	public PageBean() {}
	
	public PageBean(int pageNumber, int pageSize) {
		this.currentPage = pageNumber;
		this.pageSize = pageSize;
	}
	
	public PageBean(int pageNumber, int pageSize,int maxShowPageNum) {
		this.currentPage = pageNumber;
		this.pageSize = pageSize;
		this.maxShowPageNum = maxShowPageNum;
	}
	
	private void initPages() {
		int startPage = 0;
    	int endPage = 0;
    	int ban_page_Num = (this.maxShowPageNum % 2)==0 ? (this.maxShowPageNum / 2):(this.maxShowPageNum / 2)+1;
    	if ( this.currentPage <= ban_page_Num) {
    		// 如果当前页码小于显示最大页码数的一半
    		startPage = 1;
    		if (pageCount <= this.maxShowPageNum) {
    			endPage = pageCount+1;
    		} else {
    			endPage = this.maxShowPageNum;
    		}
    	} else if (this.currentPage > (pageCount - ban_page_Num)) {
    		// 如果当前页面大于最大页数-显示最大页码数的一半
    		startPage = pageCount - this.maxShowPageNum;
    		endPage = pageCount;
    	} else {
    		// 否则，保持当前页码在输出页码数的中间
    		startPage = this.currentPage - ban_page_Num + 1;
    		endPage = this.currentPage + ban_page_Num;
    	}
    	if (startPage < 1) startPage = 1;
    	if (endPage > pageCount) endPage = pageCount;
    	this.startPage = startPage;
    	this.endPage = endPage;
    	this.pages = new ArrayList<Integer>();
    	for (int i = this.startPage; i <= this.endPage; i++) {
			this.pages.add(i);
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int recordCount) {
		if (recordCount%pageSize==0) {
			this.pageCount = recordCount/pageSize;
		} else {
			this.pageCount = recordCount/pageSize+1;
		}
		initPages();
	}
	
	public int getRecordCount() {
		return recordCount;
	}
	
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public List<T> getDataList() {
		if(null==dataList){
			return new ArrayList<T>(0);
		}
		return dataList;
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getStartPage() {
		return this.startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public List<Integer> getPages() {
    	return pages;
	}

	public int getMaxShowPageNum() {
		return maxShowPageNum;
	}

	public void setMaxShowPageNum(int maxShowPageNum) {
		this.maxShowPageNum = maxShowPageNum;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
