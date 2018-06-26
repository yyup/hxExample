package com.xmhx.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.news.NewsService;
import com.xmhx.cnlife.core.model.PageBean;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewsServiceTest extends BaseTest{
	@Autowired
	private NewsService newsService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//添加新闻
	@Test
	public void test_01_addNews() {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setNewsTitle("新闻2的标题");
		newsDTO.setNewsContent("这个新闻2的内容");
		newsDTO.setVerifyStatus("0");
		String hxuuid = newsService.addNews(newsDTO);
		System.out.println(hxuuid);
		TestCase.assertNotNull(hxuuid);

	}
	//查询新闻
	@Test
	public void test_02_queryNewsById() {
		NewsDTO newsDTO = newsService.queryNewsById("c9ce6aa3-8fdb-4b46-b470-dd26fd7fa296");
		TestCase.assertNotNull(newsDTO);
		TestCase.assertEquals("新闻2的标题", newsDTO.getNewsTitle());
	}
	//修改新闻
	@Test
	public void test_03_modifyNews() {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setHxuuid("c9ce6aa3-8fdb-4b46-b470-dd26fd7fa296");
		newsDTO.setNewsTitle("新闻2的标题ABC");
		newsDTO.setNewsContent("这个新闻2的内容ABC");
		int num = newsService.modifyNews(newsDTO);
		TestCase.assertEquals(1, num);
		
	}
	//分页查询新闻
	@Test
	public void test_04_getNewsByPage() {
		NewsDTO newsDTO = new NewsDTO();
		PageBean<NewsDTO> pb = newsService.getNewsByPage(1, 10, "date_created", "desc", newsDTO);
		TestCase.assertNotNull(pb);
		System.out.println(pb.getPageCount());
		List<NewsDTO> list = pb.getDataList();
		System.out.println(list.get(0).getNewsTitle());
		boolean flag = false;
		for (NewsDTO newsDTO2 : list) {
			if(newsDTO2.getHxuuid().equals("c9ce6aa3-8fdb-4b46-b470-dd26fd7fa296")){
				TestCase.assertEquals("新闻2的标题ABC", list.get(0).getNewsTitle());
				TestCase.assertEquals("这个新闻2的内容ABC", list.get(0).getNewsContent());
				flag = true;
			}
		}
		if(!flag){
			TestCase.fail();
		}
	}
	//删除新闻
	@Test
	public void test_05_deleteNews() {
		String[] ids = {"c9ce6aa3-8fdb-4b46-b470-dd26fd7fa296"};
		int num = newsService.deleteNews(ids);
		TestCase.assertEquals(1, num);
	}


}
