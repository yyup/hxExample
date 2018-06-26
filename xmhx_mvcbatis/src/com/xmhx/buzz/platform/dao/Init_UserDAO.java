//package com.xmhx.buzz.platform.dao;
//
//import java.util.List;
//
//import com.xmhx.buzz.platform.model.manages.init.UserDTO;
//import com.xmhx.core.model.PageBean;
//
//public interface Init_UserDAO {
//
//	PageBean<UserDTO> getUserByPage(int pageNumber, int pageSize,
//			String sort, String order, UserDTO userDTO);
//	
//	List<UserDTO> getAllUser();
//	
//	UserDTO getUserByuuid(String uuid);
//	
//	List<UserDTO> getUserBynickname(String nickname);
//	
//	int addUser(UserDTO userDTO);
//	
//	boolean modifyUser(UserDTO userDTO);
//	
//	boolean modifyUserStatus(String uuid, String status);
//	
//	boolean addUserList(List<UserDTO> insertList);
//	
//}
