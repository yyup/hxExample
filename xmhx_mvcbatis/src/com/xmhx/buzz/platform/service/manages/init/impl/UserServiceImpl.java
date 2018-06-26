//package com.xmhx.buzz.platform.service.manages.init.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.xmhx.buzz.exception.ResponseException;
//import com.xmhx.buzz.platform.dao.Init_UserDAO;
//import com.xmhx.buzz.platform.model.manages.init.UserDTO;
//import com.xmhx.buzz.platform.service.manages.init.UserService;
//import com.xmhx.core.model.PageBean;
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	private Init_UserDAO userDAO;
//	
//	@Override
//	public PageBean<UserDTO> getUserByPage(int pageNumber, int pageSize,
//			String sort, String order, UserDTO userDTO)
//			throws ResponseException {
//		return userDAO.getUserByPage( pageNumber,  pageSize,
//				 sort,  order,  userDTO);
//	}
//
//	@Override
//	public List<UserDTO> getAllUser() throws ResponseException {
//		return userDAO.getAllUser();
//	}
//
//	@Override
//	public UserDTO getUserByuuid(String uuid) throws ResponseException {
//		return userDAO.getUserByuuid(uuid);
//	}
//
//	@Override
//	public List<UserDTO> getUserBynickname(String nickname)
//			throws ResponseException {
//		return userDAO.getUserBynickname(nickname);
//	}
//
//	@Override
//	public int addUser(UserDTO userDTO) throws ResponseException {
//		return userDAO.addUser(userDTO);
//	}
//
//	@Override
//	public boolean modifyUser(UserDTO userDTO) throws ResponseException {
//		return userDAO.modifyUser(userDTO);
//	}
//
//	@Override
//	public boolean modifyUserStatus(String uuid, String status)
//			throws ResponseException {
//		return userDAO.modifyUserStatus(uuid, status);
//	}
//
//	@Override
//	public boolean addUserList(List<UserDTO> insertList)
//			throws ResponseException {
//		return userDAO.addUserList(insertList);
//	}
//
////	@Override
////	public UserDTO getUserByLogin(String mobile, String password)
////			throws ResponseException {
////		return userDAO.getUserByLogin(mobile, password);
////	}
//
//}
