package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityCommentDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityEnterDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.CommentAttachDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.VO.ActivityCommentVO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.VO.ActivityEnterVO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.VO.ActivityVO;
import com.xmhx.cnlife.buzz.platform.model.manages.news.NewsDTO;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageEntity;

public interface Common_activity_ActivityDAO {
	
	List<ActivityDTO> queryActivityPage(PageEntity<ActivityDTO> pageEntity);
	
	int getActivityCount(ActivityDTO activityDTO);

	List<ActivityDTO> queryActivityForPage(PageEntity<ActivityDTO> pageEntity);

	int addActivity(ActivityDTO activityDTO);

	ActivityDTO queryActivityById(String activityid);

	void updatehuodongverify(ActivityDTO activityDTO);


	
}
