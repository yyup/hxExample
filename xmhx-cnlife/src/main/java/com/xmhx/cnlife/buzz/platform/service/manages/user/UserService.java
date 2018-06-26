package com.xmhx.cnlife.buzz.platform.service.manages.user;

import java.util.List;
import com.xmhx.cnlife.buzz.exception.ResponseException;
import com.xmhx.cnlife.buzz.platform.model.manages.user.UserDTO;
import com.xmhx.cnlife.core.model.PageBean;

public interface UserService {
	PageBean<UserDTO> getUserByPage(int page, int rows, String sort, String order, UserDTO userDTO)throws ResponseException;
	
	List<UserDTO> getAllUsers() throws ResponseException;						//查询所有用户
	
	UserDTO getUserByUUID(String uuid) throws ResponseException;					//根据UUID，精确查询某个用户
	
	String addUser(UserDTO userDTO) throws ResponseException;						//增加一个用户,返回uuid
	
	void modifyUser(UserDTO userDTO) throws ResponseException;					//修改用户
	
	UserDTO getUserByMobile(String mobile) throws ResponseException;				//根据手机号，精确查询某个用户(不包括admin)
	
	UserDTO getUserByMobileOrAdmin(String mobileoradmin) throws ResponseException;				//根据手机号，精确查询某个用户
	
	int modifyPatternlock(String mobile,String patternlock) throws ResponseException; //根据手机号，修改（可修改为空）手势密码
	
	int queryIfExistPatternlock(String mobile);		//查询手势密码开关
	
}