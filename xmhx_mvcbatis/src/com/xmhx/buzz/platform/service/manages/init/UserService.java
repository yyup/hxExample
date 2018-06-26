//package com.xmhx.buzz.platform.service.manages.init;
//
//import java.util.List;
//
//import com.xmhx.buzz.exception.ResponseException;
//import com.xmhx.buzz.platform.model.manages.init.UserDTO;
//import com.xmhx.core.model.PageBean;
//
//public interface UserService {
//	PageBean<UserDTO> getUserByPage(int pageNumber, int pageSize, String sort, String order, UserDTO userDTO)throws ResponseException;
//	List<UserDTO> getAllUser()throws ResponseException;//获取所有用户
//	UserDTO getUserByuuid(String uuid)throws ResponseException;//获取一个用户
//	List<UserDTO> getUserBynickname(String nickname)throws ResponseException;//根据用户昵称查找用户
//	int addUser(UserDTO userDTO)throws ResponseException;	//增加一个用户
//	boolean modifyUser(UserDTO userDTO)throws ResponseException;//修改用户信息
//	boolean modifyUserStatus(String uuid, String status)throws ResponseException;//修改用户状态
//	boolean addUserList(List<UserDTO> insertList)throws ResponseException;//增加一组用户
//	/*UserDTO getUserByLogin(String mobile,String password)throws ResponseException;//获取登录用户*/
//}
