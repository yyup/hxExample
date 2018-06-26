package com.xmhx.cnlife.buzz.platform.service.manages.attach.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.dao.AttachDAO;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.attach.AttachService;

@Service
public class AttachServiceImpl implements AttachService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(AttachServiceImpl.class);

	@Autowired
	private AttachDAO attachDAO;
	
	@Override
	public String saveAttachment(AttachDTO attachDTO) throws ResponseException {
		String hxuuid = UUID.randomUUID().toString();
		attachDTO.setHxuuid(hxuuid);
		int num = attachDAO.saveAttachment(attachDTO);
		if(num==1){
			return hxuuid;
		}else{
			return null;
		}
	}
	
	@Override
	public List<AttachDTO> queryAttachByfkid(String fkid) 
			throws ResponseException {
		return attachDAO.queryAttachByfkid(fkid);
	}

	@Override
	public AttachDTO queryAttachmentById(String id) throws ResponseException {
		return attachDAO.queryAttachmentById(id);
	}
	
	@Override
	public int delAttachById(String id) throws ResponseException {
		return attachDAO.delAttachById(id);
	}
	
	@Override
	public int deleteAttachByfk(String[] fkid) throws ResponseException {
		return attachDAO.deleteAttachByfk(fkid);
	}

	@Override
	public int updateAttachfkid(String id, String fkid) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("fkid", fkid);
		return attachDAO.updateAttachfkid(map);
	}
//	
//
//	@Override
//	public int updateAttachment(AttachDTO attachDTO) {
//		return attachDAO.updateAttachment(attachDTO);
//	}
//
//	@Override
//	public int updateAttachmentByid(AttachDTO attachDTO) {
//		return attachDAO.updateAttachmentByid(attachDTO);
//	}
//


	
}
