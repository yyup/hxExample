package com.xmhx.buzz.platform.dao;

import java.util.List;

import com.xmhx.buzz.platform.model.manages.init.NewsDTO;

public interface NewsDao {

	List<NewsDTO> getNewsList();

	List<NewsDTO> getNewsOnetoMore();

	void addNewsList(List<NewsDTO> newsList);

	void addNewsOne(NewsDTO news);

}
