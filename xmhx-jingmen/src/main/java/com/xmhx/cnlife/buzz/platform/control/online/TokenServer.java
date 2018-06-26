package com.xmhx.cnlife.buzz.platform.control.online;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 客户端Token服务类.
 * 
 * @author lhq
 * @since JDK 1.6
 * @see
 */
public class TokenServer {

    private static List<OnlineUser> onlineUserList = new ArrayList<OnlineUser>();
    
    //private static List<Map<String,Object>> codes = new ArrayList<Map<String,Object>>();
    
    public static void tokenCreated(OnlineUser onlineMember) {
        onlineMember.setAccessTime(new Date());
        onlineUserList.add(onlineMember);
    }

    public static void tokenDestroyed(OnlineUser member) {
        onlineUserList.remove(member);
    }

    public static OnlineMessage tokenCheck(String token) {
    	OnlineUser temp = null;
        OnlineMessage onlineMessage = null;
        if (onlineUserList != null && onlineUserList.size() > 0) {
            // 判断该用户是否已经登录
            for (OnlineUser onlineMember : onlineUserList) {
                if (onlineMember != null && onlineMember.getToken() != null && onlineMember.getToken().equals(token)) {
                	onlineMessage = new OnlineMessage();
                    if(null==onlineMember.getTel()){
                    	onlineMessage.setMessage("此账号在异地登陆");
                        onlineMessage.setSuccess(false);
                        temp = onlineMember;
                    }else {
                        onlineMember.setAccessTime(new Date());
                        onlineMessage.setMessage("登录成功.");
                        onlineMessage.setSuccess(true);
                        onlineMessage.setOnlineMember(onlineMember);
                        break;
                    }
                }
            }
            if(null!=temp){
            	onlineUserList.remove(temp);
            }
        }
        if(null==onlineMessage){
        	onlineMessage = new OnlineMessage();
        	onlineMessage.setMessage("请先登录系统");
            onlineMessage.setSuccess(false);
        }
        return onlineMessage;
    }
    
    //登录时判断是否有重复登录，重复删除前一个登录记录
    public static boolean checkLoginState(String usrId){
    	 OnlineUser temp = null;
		 if (onlineUserList != null && onlineUserList.size() > 0) {
	        // 判断该用户是否已经登录
	        for (OnlineUser onlineMember : onlineUserList) {
	            if (onlineMember != null && onlineMember.getMemId()!=null&&onlineMember.getMemId().equals(usrId)){
	            	if(null==onlineMember.getTel()){
	            		temp = onlineMember;
//	            		onlineUserList.remove(onlineMember);
	            	}else{
		            	onlineMember.setTel(null);//提示异地登录用
	            	}
	            }
	        }
	        if(null!=temp){/**在list遍历完成之后再进行删除，不然会出现线程并发问题java.util.ConcurrentModificationException**/
	        	onlineUserList.remove(temp);
	        }
	    }
		return true;
	}
     
     //修改token信息
     public static void updateToken(String token,String memberNm,String memberMobile){
    	 if (onlineUserList != null && onlineUserList.size() > 0) {
 	        // 判断该用户是否已经登录
 	        for (OnlineUser onlineMember : onlineUserList) {
 	            if (null!=onlineMember  && null!=onlineMember.getToken() && onlineMember.getToken().equals(token)){
 	            	if(null!=memberNm){
 	            		onlineMember.setMemberNm(memberNm);
 	            	}
 	            	if(null!=memberMobile){
 	            		onlineMember.setTel(memberMobile);
 	            	}
 	            }
 	        }
 	    }
     }

//     //根据memId修改token信息
//     public static void updateDatasource(String database,Integer memId){
//    	 if (onlineUserList != null && onlineUserList.size() > 0) {
// 	        // 判断该用户是否已经登录
// 	        for (OnlineUser onlineMember : onlineUserList) {
// 	            if (onlineMember != null && onlineMember.getToken() != null && onlineMember.getMemId().equals(memId)){
// 	 	            onlineMember.setDatabase(database);
// 	            }
// 	        }
// 	    }
//     }
     
//     public static void addCode(String code,String mobile){
//     	for(int i=0;i<codes.size();i++){
//     		if(mobile.equals(codes.get(i).get("mobile"))){
//     			codes.remove(i);
//     		}
//     	}
//     	Map<String, Object> map = new HashMap<String, Object>();
//     	map.put("code", code);
//     	map.put("mobile", mobile);
//     	map.put("time", new Date());
//     	codes.add(map);
//     } 
//     
//     public static boolean checkCode(String code,String mobile){
//    	 boolean falg = false;
//    	 Date startTime = new Date();
//         if (codes != null && codes.size() > 0) {
//        	 for(int i=0;i<codes.size();i++){
//                Date endTime = (Date) codes.get(i).get("time");
//        		long time = endTime.getTime() - startTime.getTime();
//                long minutes = time / 60000;
//                if(minutes>10){
//                	codes.remove(i);;
//                }
//                if(code.equals(codes.get(i).get("code"))&&mobile.equals(codes.get(i).get("mobile"))){
//                	falg=true;
//                }
//             }
//         }
//         return falg;
//    }
}
