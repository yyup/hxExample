package com.xmhx.cnlife.buzz.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 辅助对象、集合与Json的相互转换
 * @author 吴进 by 20160926
 * <p>
 *
 */
public class JsonUtils {
	
	/**
	 * 构建JSONObject对象
	 * @param state	状态
	 * @param msg	信息
	 * @param entrys	key-value成双数参数，参数中不能再包含（state与msg的键值）
	 * @return
	 */
	public static JSONObject buildJSONObject(boolean state, String msg, Object... entrys) {
		JSONObject json = new JSONObject();
		json.put("state", state);
		json.put("msg", msg);
		if (entrys.length % 2 == 0) {
			for (int i = 0, n = entrys.length; i < n; i+=2) {
				// 防止entrys中，把state与msg的值给冲掉
				String key = String.valueOf(entrys[i]);
				if ("state".equals(key) || "msg".equals(key)) {
					continue;
				} else {
					json.put(key, entrys[i+1]);
				}
			}
		}
		return json;
	}
	
	/**
	 * 构建JSONObject对象
	 * @param state	状态
	 * @param msg	信息
	 * @param map	传入参数，参数中不能再包含（state与msg的键值）
	 * @return
	 */
	public static JSONObject buildJSONObject(boolean state, String msg, Map<String, Object> map) {
		JSONObject json = new JSONObject();
		json.put("state", state);
		json.put("msg", msg);
		if (map != null && !map.isEmpty()) {
			Set<Map.Entry<String, Object>> set = map.entrySet();
			for (Map.Entry<String, Object> entry : set) {
				// 防止map中，把state与msg的值给冲掉
				String key = entry.getKey();
				if ("state".equals(key) || "msg".equals(key)) {
					continue;
				} else {
					json.put(key, entry.getValue());
				}
			}
		}
		return json;
	}
	
	/**
	 * 对象转换成Json
	 * @param o	对象
	 * @return	Json字串
	 */
	public static String object2Josn(Object o) {
		JSONObject object = JSONObject.fromObject(o);
		return object.toString();
	}
	
	/**
	 * 集合转换成Json
	 * @param c	集合
	 * @return	Json字串
	 */
	@SuppressWarnings("rawtypes")
	public static String array2Josn(Collection c) {
		JSONArray array = JSONArray.fromObject(c);
		return array.toString();
	}
	
	/**
	 * Json转换成List
	 * 注意，这里的Class对象，是不能有继承关系
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List json2Array(String json, Class clazz) {
		JSONArray jsonarray = JSONArray.fromObject(json);
		List list = JSONArray.toList(jsonarray, clazz);
		return list;
	}
	
}
