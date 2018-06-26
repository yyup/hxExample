package com.xmhx.cnlife.buzz.platform.service.manages.attach;

import java.util.List;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.model.manages.attach.AttachDTO;

public interface AttachService {
	
	/**
	 * 保存附件(添加)
	 * @param attachDTO
	 * @return
	 * @throws ResponseException
	 */
	String saveAttachment(AttachDTO attachDTO) throws ResponseException;
	
	/**
	 * 查询附件（外键）
	 * @param attachDTO
	 * @return
	 * @throws ResponseException
	 */
	List<AttachDTO> queryAttachByfkid(String fkid) throws ResponseException;
	
	/**
	 * 查询附件
	 * @param id
	 * @return
	 * @throws ResponseException
	 */
	AttachDTO queryAttachmentById(String id) throws ResponseException;
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 * @throws ResponseException
	 */
	int delAttachById(String id) throws ResponseException;
	
	/**
	 * 删除附件（外键）
	 * @param fkid
	 * @return
	 * @throws ResponseException
	 */
	int deleteAttachByfk(String[] fkid) throws ResponseException;
	
	/**
	 * 修改附件fkid
	 * @param id
	 * @param fkid
	 * @return
	 */
	int updateAttachfkid(String id, String fkid);
	
	/**
	 * 更新附件信息
	 * @param attachDTO
	 * @return
	 */
	//int updateAttachment(AttachDTO attachDTO);

}
