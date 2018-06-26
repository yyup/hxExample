package com.xmhx.cnlife.buzz.platform.service.manages.activity;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityDTO;
import com.xmhx.cnlife.core.model.PageBean;

public interface ActivityService {
	
	PageBean<ActivityDTO> queryActivityPage(ActivityDTO activityDTO, int page, int rows);

	PageBean<ActivityDTO> queryActivityForPage(int page,
			int rows, ActivityDTO activity);
	
	int addActivity(ActivityDTO activityDTO, String attachids);

	ActivityDTO queryActivityById(String activityid, String targetDirectory);

	boolean updatehuodongverify(ActivityDTO activityDTO);

	
}
