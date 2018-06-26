package com.xmhx.cnlife.buzz.platform.service.impl;
//package com.xmhx.buzz.platform.service.manages.impl;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.xmhx.buzz.exception.ResponseException;
//import com.xmhx.buzz.platform.dao.AttachDAO;
//import com.xmhx.buzz.platform.model.AttachDTO;
//import com.xmhx.buzz.platform.service.manages.AttachService;
//
//@Service
//public class AttachServiceImpl implements AttachService {
//	
//	@SuppressWarnings("unused")
//	private static final Logger LOGGER = Logger.getLogger(AttachServiceImpl.class);
//
//	@Autowired
//	private AttachDAO attachDAO;
//	
//	@Override
//	public String saveAttachment(AttachDTO attachDTO) throws ResponseException {
//		return attachDAO.saveAttachment(attachDTO);
//	}
//	
//	@Override
//	public List<AttachDTO> queryAttachByfkid(AttachDTO attachDTO) 
//			throws ResponseException {
//		return attachDAO.queryAttachByfkid(attachDTO);
//	}
//
//	@Override
//	public AttachDTO queryAttachmentById(int id) throws ResponseException {
//		return attachDAO.queryAttachmentById(id);
//	}
//
//	@Override
//	public boolean delAttachById(int id) throws ResponseException {
//		return attachDAO.delAttachById(id);
//	}
//	
//}
