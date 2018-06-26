package com.xmhx.buzz.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

public class HttpUtils {
    private static Logger logger = Logger.getLogger(HttpUtils.class); 

    public static int doPostForResponseStatus(String url, Map<String, String> params) {  
        HttpClient httpclient = new HttpClient();  
        PostMethod postmethod = addParams2Post(url, params);  
        return sendRequest(httpclient, postmethod); 
    }
    
    public static String doPostForResponseString(String url, Map<String, String> params) {  
        HttpClient httpclient = new HttpClient();  
        PostMethod postmethod = addParams2Post(url, params);  
        int status = sendRequest(httpclient, postmethod);
        if(status==HttpStatus.OK.value()){
        	return getResponseString(postmethod);
        }
        return null;
    }
    
    public static String doGetForResponseString(String url) {  
        HttpClient httpclient = new HttpClient();  
        GetMethod getmethod = new GetMethod(url);  
        if(sendRequest(httpclient, getmethod)==HttpStatus.OK.value()){
        	return getResponseString(getmethod);
        }
        return null;  
    }
    
    public static int doGetForResponseStatus(String url) {  
        HttpClient httpclient = new HttpClient();  
        GetMethod getmethod = new GetMethod(url);  
        return sendRequest(httpclient, getmethod);  
    }
    
    private static String getResponseString(HttpMethod method) {  
        try {
			return method.getResponseBodyAsString();
		} catch (IOException e) {
			logger.error("获取响应数据出错", e);
			return null;
		}  
    }  
  
    private static int sendRequest(HttpClient httpclient,  HttpMethod postmethod) {  
        try {
			return httpclient.executeMethod(postmethod);
		} catch (IOException e) {
			logger.error("http请求出错", e);
			return HttpStatus.BAD_REQUEST.value();
		}  
    }  
    
    /**
     * 添加请求参数
     * @param url
     * @param params
     * @return
     */
    public static PostMethod addParams2Post(String url, Map<String, String> params){  
        PostMethod postmethod = new PostMethod(url);  
        Set<String> keySet = params.keySet();  
        StringBuilder urlParams = new StringBuilder();
        for(String key : keySet) {
        	logger.info(key+" -> "+params.get(key));
        	urlParams.append(key+"="+params.get(key)+"&");
        	postmethod.addParameter(key, params.get(key));
        }
        logger.debug("Full Url : "+url+"?"+urlParams.toString());
        return postmethod;  
    }  
	
	/**
	 * 进行UrlDecode解码
	 * @param text 字符串
	 * @param charset 编码
	 * @return
	 */
	public static String urlDecode(String text, String charset) {
		try {
			return URLDecoder.decode(text,charset);
		} catch (Exception e) {
			return text;
		}
	}
	
	/**
	 * 进行urlEncode加码
	 * @param text 字符串
	 * @param charset 编码
	 * @return
	 */
	public static String urlEncode(String text, String charset) {
		try {
			return URLEncoder.encode(text,charset);
		} catch (Exception e) {
			return text;
		}
	}
    
	/**
     * 将请求中的参数封装成Map
     * @param request Http请求对象
     * @return 将所有请求参数封装为Map集合并返回
     */
    public static Map<String, String> getParams(HttpServletRequest request){
    	Map<String, String> params = new HashMap<String, String>();
    	for (String key : request.getParameterMap().keySet()) {
    		params.put(key, HttpUtils.urlDecode(request.getParameter(key), "UTF-8"));
		}
    	return params;
    }
    
    /**
     * 将Session域中的参数封装成Map
     * @param session HttpSession请求对象
     * @return 
     */
    public static Map<String, Object> getParams(HttpSession session) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	Enumeration<String> enumeration = session.getAttributeNames();
    	while(enumeration.hasMoreElements()){
    		String key = enumeration.nextElement();
    		params.put(key, session.getAttribute(key));
    	}
    	return params;
	}
    
    /**
     * 过滤请求参数中HTML及脚本
     * @param request
     * @return 如果包含非法字符，则返回true,否则放回false
     */
	public static boolean filterHtml(HttpServletRequest request) {
		boolean flag = false;
		for (String key : request.getParameterMap().keySet()) {
			String value = request.getParameter(key);
			if(TextUtils.TextisHtml(value)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * isAjaxRequest:判断请求是否为Ajax请求. <br/>
	 * @param request 请求对象
	 * @return boolean
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;
		return isAjax;
	}
}