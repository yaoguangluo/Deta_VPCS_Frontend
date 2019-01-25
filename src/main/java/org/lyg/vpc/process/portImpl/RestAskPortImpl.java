package org.lyg.vpc.process.portImpl;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.lyg.common.maps.VtoV;
import org.lyg.common.utils.DetaDBUtil;
//import org.lyg.vpc.controller.port.RestAskPort;

public class RestAskPortImpl{// implements RestAskPort {
	@SuppressWarnings("deprecation")
	public static Map<String, Object> ask(String ip, String token, String message, String pointIp) throws Exception {
		String json = null;
		if(token != null){
			json = DetaDBUtil.backEndRequest("checkStatus?token=" + URLEncoder.encode(token));
		}
		Map<String, Object> jsonCheckMap;
		boolean jsonCheck = true;
		if(null == json){
			jsonCheckMap = new HashMap<String, Object>();
			jsonCheckMap.put("usrName", "匿名咨询师");
			jsonCheck = false;
		}else if(json.contains("unsuccess")){
			jsonCheck = false;
		}
		if(jsonCheck){
			jsonCheckMap = VtoV.JsonObjectToMap(new JSONObject(json));	
			Map<String, Object> out = new HashMap<>();
			String object = DetaDBUtil.cacheRequest("get?key=" + pointIp + "&email=" 
					+ "313699483@qq.com" + "&password=" + "Fengyue1985!");
			boolean check = true;
			if(null == object){
				check = false;
			}else if(object.contains("unsuccess")){
				check = false;
			}
			JSONArray jobj = null;
			if(check){
				JSONArray newjobj = new JSONArray();
				Object listArray = new JSONTokener(object).nextValue();
				if (listArray instanceof JSONArray){
					jobj = new JSONArray(object);
				}else{
					jobj = new JSONArray();
					JSONObject jsobj = new JSONObject(object);
					jobj.put(jsobj);
				}
				for(int i=0;i<jobj.length();i++){
					JSONObject obj = (JSONObject) jobj.get(i);
					Map<String, Object> map = VtoV.JsonObjectToMap(obj);
					map.put("isRead", "true");
					newjobj.put(map);
				}
				jobj = newjobj;
			}else{
				jobj = new JSONArray();
			}
			Map<String, Object> talk = new HashMap<>();
			talk.put("object", jsonCheckMap.get("usrName"));
			talk.put("message", message);
			talk.put("time", System.currentTimeMillis());
			talk.put("isRead", "false");
			jobj.put(talk);
			DetaDBUtil.cacheRequest("put?key=" + pointIp + "&value=" + URLEncoder.encode(jobj.toString(), "UTF-8") 
			+ "&time=" + (System.currentTimeMillis()+900000) 
			+ "&email=" + "313699483@qq.com" + "&password=" + "Fengyue1985!");
			out.put("loginInfo", "success");
			out.put("returnResult", "发送成功");
			return out;
		}
		Map<String, Object> out = new HashMap<>();
		String object = DetaDBUtil.cacheRequest("get?key=" + "Ask:" + ip + "&email=" 
				+ "313699483@qq.com" + "&password=" + "Fengyue1985!");
	//	object=object.substring(1, object.length()-1);
		boolean check = true;
		if(null == object){
			check = false;
		}else if(object.contains("unsuccess")){
			check = false;
		}
		JSONArray jobj = null;
		if(check){
			JSONArray newjobj = new JSONArray();
			Object listArray = new JSONTokener(object).nextValue();
			if (listArray instanceof JSONArray){
				jobj = new JSONArray(object);
			}else{
				jobj = new JSONArray();
				JSONObject jsobj = new JSONObject(object);
				jobj.put(jsobj);
			}
			for(int i=0;i<jobj.length();i++){
				JSONObject obj = (JSONObject) jobj.get(i);
				Map<String, Object> map = VtoV.JsonObjectToMap(obj);
				map.put("isRead", "true");
				newjobj.put(map);
			}
			jobj = newjobj;
		}else{
			jobj = new JSONArray();
		}
		Map<String, Object> talk = new HashMap<>();
		talk.put("object", "Guest");
		talk.put("message", message);
		talk.put("time", System.currentTimeMillis());
		talk.put("isRead", "false");
		jobj.put(talk);
		DetaDBUtil.cacheRequest("put?key=" + "Ask:" + ip + "&value=" + URLEncoder.encode(jobj.toString(), "UTF-8") 
		+ "&time=" +(System.currentTimeMillis()+900000) 
		+ "&email=" + "313699483@qq.com" + "&password=" + "Fengyue1985!");
		out.put("loginInfo", "success");
		out.put("returnResult", "发送成功");
		return out;
	}

	@SuppressWarnings("deprecation")
	public static Map<String, Object> feedBack(String ip, String token, String pointIp) throws Exception {
		String json = null;
		if(null != token){
			json = DetaDBUtil.backEndRequest("checkStatus?token=" + URLEncoder.encode(token));
		}
		Map<String, Object> jsonCheckMap;
		boolean jsonCheck = true;
		if(null == json){
			jsonCheckMap = new HashMap<String, Object>();
			jsonCheckMap.put("usrName", "匿名咨询师");
			jsonCheck = false;
		}else if(json.contains("unsuccess")){
			jsonCheck = false;
		}
		if(jsonCheck){
			jsonCheckMap = VtoV.JsonObjectToMap(new JSONObject(json));	
			Map<String, Object> out = new HashMap<>();
			String object = DetaDBUtil.cacheRequest("get?key=" + URLEncoder.encode(pointIp, "UTF-8") 
			+ "&email=" + "313699483@qq.com" + "&password=" + "Fengyue1985!");
			boolean check = true;
			if(null == object){
				check = false;
			}
			if(object.contains("unsuccess")){
				check = false;
			}
			JSONArray jobj;
			if(check){
				Object listArray = new JSONTokener(object).nextValue();
				if (listArray instanceof JSONArray){
					jobj = new JSONArray(object);
				}else{
					jobj = new JSONArray();
					JSONObject jsobj = new JSONObject(object);
					jobj.put(jsobj);
				}
				out.put("obj", VtoV.JsonArrayToList(jobj));
				out.put("loginInfo", "success");
				out.put("returnResult", "发送成功");
			}else{
				jobj = new JSONArray();
				out.put("obj", VtoV.JsonArrayToList(jobj));
				out.put("loginInfo", "unsuccess");
				out.put("returnResult", "数据超时");
			}
			return out;
		}
		Map<String, Object> out = new HashMap<>();
		String object = DetaDBUtil.cacheRequest("get?key=" + "Ask:" + URLEncoder.encode(ip, "UTF-8") + "&email=" 
				+ "313699483@qq.com" + "&password=" + "Fengyue1985!");
		boolean check = true;
		if(null == object){
			check = false;
		}else if(object.contains("unsuccess")){
			check = false;
		}
		JSONArray jobj;
		if(check){
			Object listArray = new JSONTokener(object).nextValue();
			if (listArray instanceof JSONArray){
				jobj = new JSONArray(object);
			}else{
				jobj = new JSONArray();
				JSONObject jsobj = new JSONObject(object);
				jobj.put(jsobj);
			}
			out.put("obj", VtoV.JsonArrayToList(jobj));
			out.put("loginInfo", "success");
			out.put("returnResult", "发送成功");
		}else{
			jobj = new JSONArray();
			out.put("obj", VtoV.JsonArrayToList(jobj));
			out.put("loginInfo", "unsuccess");
			out.put("returnResult", "数据超时");
		}
		return out;
	}

	@SuppressWarnings("deprecation")
	public static Map<String, Object> getAskers(String token) throws Exception {
		String json=null;
		if(null != token){
			json = DetaDBUtil.backEndRequest("checkStatus?token=" + URLEncoder.encode(token));
		}
		Map<String, Object> jsonCheckMap;
		boolean jsonCheck = true;
		if(null == json){
			jsonCheckMap = new HashMap<String, Object>();
			jsonCheckMap.put("usrName", "匿名咨询师");
			jsonCheck = false;
		}else if(json.contains("unsuccess")){
			jsonCheck = false;
		}
		if(jsonCheck){
			jsonCheckMap = VtoV.JsonObjectToMap(new JSONObject(json));	
			Map<String, Object> out = new HashMap<>();
			String object = DetaDBUtil.cacheRequest("getAskers?email=" 
					+ URLEncoder.encode("313699483@qq.com") + "&password=" + URLEncoder.encode("Fengyue1985!"));
			boolean check = true;
			if(null == object){
				check = false;
			}else if(object.contains("unsuccess")){
				check = false;
			}
			JSONArray jobj;
			if(check){
				Object listArray = new JSONTokener(object).nextValue();
				if (listArray instanceof JSONArray){
					jobj = new JSONArray(object);
				}else{
					jobj = new JSONArray();
					JSONObject jsobj = new JSONObject(object);
					jobj.put(jsobj);
				}
				out.put("obj", VtoV.JsonArrayToList(jobj));
				out.put("loginInfo", "success");
				out.put("returnResult", "发送成功");
			}else{
				jobj = new JSONArray();
				out.put("obj", VtoV.JsonArrayToList(jobj));
				out.put("loginInfo", "unsuccess");
				out.put("returnResult", "数据超时");
			}
			return out;
		}
		Map<String, Object> out = new HashMap<>();
		out.put("obj", new ArrayList<Object>());
		out.put("loginInfo", "unsuccess");
		out.put("returnResult", "数据超时");
		return out;
	}
}