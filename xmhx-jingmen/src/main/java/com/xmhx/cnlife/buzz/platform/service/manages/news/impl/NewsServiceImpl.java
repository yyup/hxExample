package com.xmhx.cnlife.buzz.platform.service.manages.news.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.dao.Sys_news_NewsDAO;
import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.news.NewsService;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageEntity;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private Sys_news_NewsDAO newsDAO;
	
	@Override
	public PageBean<NewsDTO> getNewsByPage(int page, int rows, String sort,
			String order, NewsDTO newsDTO) throws ResponseException {
		PageBean<NewsDTO> pageBean = new PageBean<NewsDTO>(page, rows);
		PageEntity<NewsDTO> pageEntity = new PageEntity<NewsDTO>(page, rows, sort, order, newsDTO, pageBean);
		int count = newsDAO.getNewsCount(newsDTO);
		List<NewsDTO> list = newsDAO.getNewsList(pageEntity);
		pageBean.setDataList(list);
		pageBean.setPageCount(count);
		pageBean.setRecordCount(count);
		return pageBean;
	}
	
	@Override
	public PageBean<NewsDTO> getNewsAndAttachByPage(int page, int rows, String sort,
			String order, NewsDTO newsDTO) throws ResponseException {
		PageBean<NewsDTO> pageBean = new PageBean<NewsDTO>(page, rows);
		PageEntity<NewsDTO> pageEntity = new PageEntity<NewsDTO>(page, rows, sort, order, newsDTO, pageBean);
		int count = newsDAO.getNewsCount(newsDTO);
		List<NewsDTO> list = newsDAO.getNewsAndAttachList(pageEntity);
		pageBean.setDataList(list);
		pageBean.setPageCount(count);
		pageBean.setRecordCount(count);
		return pageBean;
	}

	@Override
	public String addNews(NewsDTO newsDTO) throws ResponseException {
		//生成UUID
		String hxuuid = UUID.randomUUID().toString();
		newsDTO.setHxuuid(hxuuid);
		int num = newsDAO.addNews(newsDTO);
		if(num>0){
			return hxuuid;
		}else{
			return null;
		}
	}

	@Override
	public int deleteNews(String[] ids) throws ResponseException {
		return newsDAO.deleteNews(ids);
	}

	@Override
	public int modifyNews(NewsDTO newsDTO) throws ResponseException {
		return newsDAO.modifyNews(newsDTO);
	}

	@Override
	public NewsDTO queryNewsById(String hxuuid) throws ResponseException {
		return newsDAO.queryNewsById(hxuuid);
	}

	@Override
	public int updateNewsVerifyStatus(NewsDTO newsDTO) throws ResponseException {
		return newsDAO.updateNewsVerifyStatus(newsDTO);
	}

	@Override
	public NewsDTO queryPreviousNewsById(int id) throws ResponseException {
		return newsDAO.queryPreviousNewsById(id);
	}

	@Override
	public NewsDTO queryNextNewsById(int id) throws ResponseException {
		return newsDAO.queryNextNewsById(id);
	}
}
