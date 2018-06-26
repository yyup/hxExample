package com.xmhx.cnlife.buzz.platform.service.manages.user.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.dao.Common_member_UserDAO;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.buzz.platform.service.manages.user.UserService;
import com.xmhx.cnlife.buzz.utils.pinyingTool;
import com.xmhx.cnlife.core.model.PageBean;
import com.xmhx.cnlife.core.model.PageEntity;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private Common_member_UserDAO userDAO;
	
	@Override
	public PageBean<UserDTO> getUserByPage(int page, int rows, String sort, String order, UserDTO userDTO)
			throws ResponseException {
		PageBean<UserDTO> pageBean = new PageBean<UserDTO>(page, rows);
		PageEntity<UserDTO> pageEntity = new PageEntity<UserDTO>(page, rows, sort, order, userDTO, pageBean);
		
		int count = userDAO.getUserCount(pageEntity);
		List<UserDTO> list = userDAO.getUserList(pageEntity);
		
		pageBean.setDataList(list);
		pageBean.setPageCount(count);
		pageBean.setRecordCount(count);
		return pageBean;
	}
	
	@Override
	public List<UserDTO> getAllUsers() throws ResponseException {
		return userDAO.getAllUsers();
	}

	@Override
	public UserDTO getUserByUUID(String uuid) throws ResponseException {
		return userDAO.getUserByUUID(uuid);
	}

	@Override
	public String addUser(UserDTO userDTO) throws ResponseException {
		//生成UUID
		String hxuuid = UUID.randomUUID().toString();
		userDTO.setHxuuid(hxuuid);
		//获取首字母
		String firstchar =pinyingTool.getFirstLetter(userDTO.getMember_name()).toUpperCase();
		userDTO.setFirst_char(firstchar);
		//新增用户
		userDAO.addUser(userDTO);
		return hxuuid;
	}

	@Override
	public void modifyUser(UserDTO userDTO) throws ResponseException {
		//获取首字母
		String firstchar =pinyingTool.getFirstLetter(userDTO.getMember_name()).toUpperCase();
		userDTO.setFirst_char(firstchar);
		userDAO.modifyUser(userDTO);
	}

	@Override
	public UserDTO getUserByMobile(String mobile) throws ResponseException {
		return userDAO.getUserByMobile(mobile);
	}

	@Override
	public UserDTO getUserByMobileOrAdmin(String mobileoradmin)
			throws ResponseException {
		return userDAO.getUserByMobileOrAdmin(mobileoradmin);
	}

	@Override
	public int modifyPatternlock(String mobile, String patternlock)
			throws ResponseException {
		UserDTO userDTO = new UserDTO();
		userDTO.setMember_mobile(mobile);
		userDTO.setPattern_lock(patternlock);
		return userDAO.modifyPatternlock(userDTO);
	}

	@Override
	public int queryIfExistPatternlock(String mobile) throws ResponseException {
		return userDAO.queryIfExistPatternlock(mobile);
	}
	
}