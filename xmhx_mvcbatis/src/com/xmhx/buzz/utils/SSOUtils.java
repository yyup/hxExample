package com.xmhx.buzz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 远程访问
 * @author 吴进 by 20160110
 *
 */
public class SSOUtils {	
	/**
	 * 远程服务访问
	 * @param map	传入参数
	 * @param url	访问路径
	 * @return		服务器回传实体
	 */
	public static HttpEntity remoteHttpPost(HttpClient httpclient, Map<String, String> map, String url) {
		try {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			HttpPost httpPost = new HttpPost(url.trim());
			if (map != null && TextUtils.notEmpty(url)) {
				Set<Map.Entry<String, String >> set = map.entrySet();
				for (Map.Entry<String, String > entry : set) {
					String key = entry.getKey();
					String value = entry.getValue();
					nvps.add(new BasicNameValuePair(key, value));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			}
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return entity;
		} catch (UnsupportedEncodingException e) {
			System.out.println("SSOUtils remoteHttpPost UnsupportedEncodingException " + e);
		} catch (ClientProtocolException e) {
			System.out.println("SSOUtils remoteHttpPost ClientProtocolException " + e);
		} catch (IOException e) {
			System.out.println("SSOUtils remoteHttpPost IOException " + e);
		}
		return null;
	}
	
	/**
	 * 远程服务访问
	 * @param map	传入参数
	 * @param url	访问路径
	 * @return		字符值，可能是JSON，或指定的字符
	 */
	public static String remoteStringService(Map<String, String> map, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpEntity httpEntity = SSOUtils.remoteHttpPost(httpclient, map, url);
			return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
		} catch (ParseException e) {
			System.out.println("SSOUtils remoteStringService ParseException " + e);
		} catch (IOException e) {
			System.out.println("SSOUtils remoteStringService IOException " + e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	/**
	 * 远程服务访问
	 * @param map	传入参数
	 * @param url	访问路径
	 * @return		输出流
	 */
	public static InputStream remoteInputStreamService(Map<String, String> map, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpEntity httpEntity = SSOUtils.remoteHttpPost(httpclient, map, url);
			return httpEntity != null ? httpEntity.getContent() : null;
		} catch (IllegalStateException e) {
			System.out.println("SSOUtils remoteInputStreamService IllegalStateException " + e);
		} catch (IOException e) {
			System.out.println("SSOUtils remoteInputStreamService IOException " + e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
	
}