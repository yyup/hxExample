package com.xmhx.cnlife.buzz.platform.service.manages.activity.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.platform.dao.AttachDAO;
import com.xmhx.cnlife.buzz.platform.dao.Common_activity_ActivityDAO;
import com.xmhx.cnlife.buzz.platform.dao.Common_activity_comment_CommentDAO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityCommentDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.activity.ActivityDTO;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.activity.ActivityService;
import com.xmhx.cnlife.buzz.utils.DateUtils;
import com.xmhx.cnlife.buzz.utils.TextUtils;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageEntity;

@Service
public class AcivityServiceImpl implements ActivityService {

	@Autowired
	private Common_activity_ActivityDAO activityDAO;
	@Autowired
	private AttachDAO attachDAO;
	@Autowired
	private Common_activity_comment_CommentDAO commentDAO;
	
	
	@Override
	public PageBean<ActivityDTO> queryActivityPage(ActivityDTO activityDTO,
			int page, int rows) {
		PageBean<ActivityDTO> pageBean = new PageBean<ActivityDTO>(page, rows);
		PageEntity<ActivityDTO> pageEntity = new PageEntity<ActivityDTO>(page, rows, null, null, activityDTO, pageBean);
		int count = activityDAO.getActivityCount(activityDTO);
		List<ActivityDTO> list = activityDAO.queryActivityPage(pageEntity);
		pageBean.setDataList(list);
		pageBean.setPageCount(count);
		pageBean.setRecordCount(count);
		return pageBean;
	}

	//分页查询活动
	@Override
	public PageBean<ActivityDTO> queryActivityForPage(int page, int rows,
			ActivityDTO activity) {
		PageBean<ActivityDTO> pageBean = new PageBean<ActivityDTO>(page, rows);
		PageEntity<ActivityDTO> pageEntity = new PageEntity<ActivityDTO>(page, rows, null, null, activity, pageBean);
		List<ActivityDTO> list = activityDAO.queryActivityForPage(pageEntity);
		if(list != null && list.size() > 0) {
			try {
				//设置时间格式
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				//设置时间显示格式 
				DateFormat df2 = new SimpleDateFormat("MM月dd日");
				//获取当前时间
				Date now = df.parse(DateUtils.getDatetimeNow());
				for (ActivityDTO activitys : list) {
					if(activitys.getOvertime() != null && activitys.getStime() != null && activitys.getEtime() != null) {
					//获取报名截止时间
					Date overtime = df.parse(activitys.getOvertime());
					//获取活动开始时间
					Date stime = df.parse(activitys.getStime());
					//获取活动结束时间
					Date etime = df.parse(activitys.getEtime());
					String acstate = "4";
					if(overtime.getTime() > now.getTime()) {
						 acstate = "1";
					}else if(now.getTime() >= stime.getTime() && now.getTime() <= etime.getTime()) {
						 acstate = "2";
					}else if( now.getTime() > etime.getTime()) {
						 acstate = "3";
					}
					activitys.setStime(df2.format(stime));
					activitys.setEtime(df2.format(etime));
					activitys.setAcstate(acstate);
				   }
			 }
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		pageBean.setDataList(list);
		return pageBean;
	}
	@Override
	public int addActivity(ActivityDTO activityDTO, String attachids) {
		//不为空
		if(TextUtils.notEmpty(attachids)) {
			String[] attachidArray = attachids.split(",");
			if(attachidArray != null && attachidArray.length > 0) {
				attachDAO.updateAttachfkidForArray(attachidArray, activityDTO.getHxuuid());
//				for (int i = 0; i < attachidArray.length; i++) {
//					attachDAO.updateAttachfkid(Integer.valueOf(attachidArray[i]), activityDTO.getHxuuid());
//				}
			}
		}
		return activityDAO.addActivity(activityDTO);
	}
	//查询活动详情
	@Override
	public ActivityDTO queryActivityById(String activityid, String imgpath) {
		ActivityDTO activity = activityDAO.queryActivityById(activityid);
		if (null != activity) {
			List<AttachDTO> picList = attachDAO.queryPiclist(activityid);//查询图片附件
			if (picList != null) {
				for (AttachDTO attachDTO : picList) {
					attachDTO.setAttachPath(imgpath + File.separator + attachDTO.getAttachPath());
				}
			}
			activity.setPicList(picList);
			//查询评论(限制5条)
			ActivityCommentDTO activityComment = new ActivityCommentDTO();
			activityComment.setActivityid(activityid);
			List<ActivityCommentDTO> activityCommentDTO = commentDAO.queryNextComment(activityComment);
//			for (ActivityCommentDTO comment : activityCommentDTO) {//查询评论图片附件，存放到各评论中
//				List<CommentAttachDTO> attachs = activityDAO.queryCommentAttach(comment.getHxuuid());
//				comment.setCommentPicList(attachs);
//			}
			activity.setCommentList(activityCommentDTO);//评论信息存放到活动工厂类中
		}
		return activity;
	}
	//更新活动
	@Override
	public boolean updatehuodongverify(ActivityDTO activityDTO) {
		Boolean rst = Boolean.FALSE;
		try{
			activityDAO.updatehuodongverify(activityDTO);
			rst = Boolean.TRUE;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}
//	//我的参与
//	@Override
//	public PageBean<ActivityDTO> queryActivitylist(Object object,
//			int page, int rows, ActivityDTO activity) {
//		PageBean<ActivityDTO> pageBean = new PageBean<ActivityDTO>(page, rows);
//		PageEntity<ActivityDTO> pageEntity = new PageEntity<ActivityDTO>(page, rows, null, null, activity, pageBean);
//		PageBean<ActivityDTO> p = activityDAO.queryActivitylist(pageEntity);
//		List<ActivityDTO> actList = p.getDataList();
//		if(actList != null && actList.size() > 0) {
//			try {
//				//设置时间格式
//				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//				//设置时间显示格式 
//				DateFormat df2 = new SimpleDateFormat("MM月dd日");
//				//获取当前时间
//				Date now = df.parse(DateUtils.getDatetimeNow());
//				for (ActivityDTO activitys : actList) {
//					if(activitys.getOvertime() != null && activitys.getStime() != null && activitys.getEtime() != null) {
//					//获取报名截止时间
//					Date overtime = df.parse(activitys.getOvertime());
//					//获取活动开始时间
//					Date stime = df.parse(activitys.getStime());
//					//获取活动结束时间
//					Date etime = df.parse(activitys.getEtime());
//					String acstate = "4";
//					if(overtime.getTime() > now.getTime()) {
//						 acstate = "1";
//					}else if(now.getTime() >= stime.getTime() && now.getTime() <= etime.getTime()) {
//						 acstate = "2";
//					}else if( now.getTime() > etime.getTime()) {
//						 acstate = "3";
//					}
//					activitys.setStime(df2.format(stime));
//					activitys.setEtime(df2.format(etime));
//					activitys.setAcstate(acstate);
//				   }
//			 }
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//		return p;
//	}

}
