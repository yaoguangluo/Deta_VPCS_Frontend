package org.lyg.vpc.transaction;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONObject;
import org.lyg.common.maps.VtoV;
import org.lyg.common.utils.DetaDBUtil;

public class TransactionDelegate {
	@SuppressWarnings("deprecation")
	public static Map<String, Object> transactionLogin(String uEmail, String uPassword)throws Exception {
		String response = DetaDBUtil.backEndRequest("login?uEmail=" + URLEncoder.encode(uEmail) 
		+ "&uPassword=" + URLEncoder.encode(uPassword));
		Map<String, Object> out = VtoV.JsonObjectToMap(new JSONObject(response));
		return out;
	}

	@SuppressWarnings("deprecation")
	public static Map<String, Object> transactionRegister(String uEmail, String uEmailEnsure, String uName, String uPassword,
			String uPassWDEnsure, String uAddress, String uPhone, String uWeChat, String uQq, String uAge,
			String uSex) throws Exception {
		String response = DetaDBUtil.backEndRequest("register?uEmail=" + URLEncoder.encode(uEmail) 
		+ "&uName=" + URLEncoder.encode(uName)
		+ "&uPassword=" + URLEncoder.encode(uPassword));
		Map<String, Object> out = VtoV.JsonObjectToMap(new JSONObject(response));
		return out;
	}
}