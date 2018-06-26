package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmhx.cnlife.buzz.platform.model.manages.activity.VO.ActivityVO;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;

public interface AttachDAO {

	int saveAttachment(AttachDTO attachDTO);		//添加附件
	
	List<AttachDTO> queryAttachByfkid(String fkid);	//根据来源ID查找附件列表
	
	AttachDTO queryAttachmentById(String id);		//根据ID查找附件
	
	int delAttachById(String id);					//删除附件
	
	int deleteAttachByfk(String[] fkid);		//根据来源ID删除附件
	
	int updateAttachfkid(Map<String,String> map); //修改来源ID

	int updateAttachfkidForArray(@Param("attachidArray") String[] attachidArray, @Param("hxuuid") String hxuuid);//更新附件来源id

	List<AttachDTO> queryPiclist(String activityid);
}
