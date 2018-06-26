package com.xmhx.cnlife.buzz.platform.service.manages.news;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.core.model.PageBean;

public interface NewsService {
	//分页查询新闻
	PageBean<NewsDTO> getNewsByPage(int page, int rows, String sort, String order, NewsDTO newsDTO) throws ResponseException;
	
	//分页查询新闻(包含标题图片路径)
	PageBean<NewsDTO> getNewsAndAttachByPage(int page, int rows, String sort, String order, NewsDTO newsDTO) throws ResponseException;
	
	//添加新闻
	String addNews(NewsDTO newsDTO) throws ResponseException;
	//删除新闻
	int deleteNews(String[] ids) throws ResponseException;
	//修改新闻
	int modifyNews(NewsDTO newsDTO) throws ResponseException;
	//根据ID查询新闻
	NewsDTO queryNewsById(String hxuuid) throws ResponseException;
	
	// 前端页面上一条新闻
	NewsDTO queryPreviousNewsById(int id) throws ResponseException;
	// 前端页面下一条新闻
	NewsDTO queryNextNewsById(int id) throws ResponseException;
	
	//审核新闻
	int updateNewsVerifyStatus(NewsDTO newsDTO);
}
