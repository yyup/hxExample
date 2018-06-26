package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityCommentDTO;

public interface Common_activity_comment_CommentDAO {

	List<ActivityCommentDTO> queryNextComment(ActivityCommentDTO activityComment);

}
