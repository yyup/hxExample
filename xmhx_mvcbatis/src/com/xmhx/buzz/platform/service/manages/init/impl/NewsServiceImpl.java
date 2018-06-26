package com.xmhx.buzz.platform.service.manages.init.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xmhx.buzz.platform.dao.NewsDao;
import com.xmhx.buzz.platform.model.manages.init.NewsDTO;
import com.xmhx.buzz.platform.service.manages.init.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	@Resource
	private NewsDao newsDao;

	@Override
	public List<NewsDTO> getNewsList() {
		return newsDao.getNewsList();
	}

	@Override
	public List<NewsDTO> getNewsOnetoMore() {
		return newsDao.getNewsOnetoMore();
	}

	@Override
	public void addNewsList(List<NewsDTO> newsList) {
		newsDao.addNewsList(newsList);
	}

	@Override
	public void addNewsOne(NewsDTO news) {
		newsDao.addNewsOne(news);
	}

}
