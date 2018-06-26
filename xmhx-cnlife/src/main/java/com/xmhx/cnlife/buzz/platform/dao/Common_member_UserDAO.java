package com.xmhx.cnlife.buzz.platform.dao;

import java.util.List;

import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.core.model.PageEntity;

public interface Common_member_UserDAO {
	
	int getUserCount(PageEntity<UserDTO> pageEntity);	//查询用户列表总记录（分页）
	
	List<UserDTO> getUserList(PageEntity<UserDTO> pageEntity); //查询用户列表（分页）
	
	List<UserDTO> getAllUsers();						//查询所有用户
	
	UserDTO getUserByUUID(String uuid);					//根据UUID，精确查询某个用户
	
	void addUser(UserDTO userDTO);						//增加一个用户
	
	void modifyUser(UserDTO userDTO);					//修改用户
	
	UserDTO getUserByMobile(String mobile);				//根据手机号，精确查询某个用户
	
	UserDTO getUserByMobileOrAdmin(String mobileoradmin);				//根据手机号，精确查询某个用户
	
	int modifyPatternlock(UserDTO userDTO); 			//根据手机号，修改（可修改为空）手势密码
	
	int queryIfExistPatternlock(String mobile);  //查询手势密码开关
	
	//boolean modifyUserStatus(String uuid, String status);	//禁用或激活用户
	
	//boolean addUserList(List<UserDTO> insertList);			//增加用户（批量）
	
	//UserDTO getUserByLogin(String mobile,String password);	//获取登录用户
}
