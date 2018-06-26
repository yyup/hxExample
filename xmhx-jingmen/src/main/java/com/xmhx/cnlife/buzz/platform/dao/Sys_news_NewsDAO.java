package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.core.model.PageEntity;

public interface Sys_news_NewsDAO {
	//添加新闻
	int addNews(NewsDTO newsDTO)throws ResponseException;
	//删除新闻
	int deleteNews(String[] ids)throws ResponseException;
	//修改新闻
	int modifyNews(NewsDTO newsDTO)throws ResponseException;
	//根据ID查询新闻
	NewsDTO queryNewsById(String hxuuid)throws ResponseException;
	//获取统计共多少新闻
	int getNewsCount(NewsDTO newsDTO);
	//获取新闻列表
	List<NewsDTO> getNewsList(PageEntity<NewsDTO> pageEntity);
	//获取新闻列表（包含标题路径）
	List<NewsDTO> getNewsAndAttachList(PageEntity<NewsDTO> pageEntity);
	//审核新闻
	int updateNewsVerifyStatus(NewsDTO newsDTO);
	
	// 前端页面上一条新闻
	NewsDTO queryPreviousNewsById(int id);
	// 前端页面下一条新闻
	NewsDTO queryNextNewsById(int id);
}
