package org.deta.boot.vpc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.deta.boot.vpc.vision.VPCSRequest;
import org.deta.boot.vpc.vision.VPCSResponse;

public class RequestRecordController {
	
	public static void requestIpRecoder(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {
		vPCSRequest.setRequestIp(vPCSResponse.getSocket().getInetAddress().getHostAddress());
		vPCSRequest.setRequestName(vPCSResponse.getSocket().getInetAddress().getHostName());
	}

	public static void requestLinkRecoder(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(vPCSResponse.getSocket().getInputStream()));
		String mess = br.readLine();
//		System.out.println(mess);
		if(null == mess){
			vPCSResponse.returnErrorCode(200);
			return;
		}
		if(mess.equalsIgnoreCase("") || !mess.contains("HTTP")){
			vPCSResponse.returnErrorCode(200);
			return;
		}
		String[] type = mess.split(" ");
		if(type.length < 3){
			vPCSResponse.returnErrorCode(200);
			return;
		}
		String[] content = type[1].split("\\?");
		if(content.length == 2) {
			if(content[0].contains(".woff") || content[0].contains("ttf")) {
				vPCSResponse.returnErrorCode(200);
				return;
			}else {
				vPCSRequest.setRequestIsRest(true);
				if(content[1] == null ||content[1].equals("")){
					vPCSResponse.returnErrorCode(200);
					return;
				}	
			}
		}else {
			vPCSRequest.setRequestIsRest(false);
		}
		
		if(vPCSRequest.getRequestIsRest()){
			String[] column = content[1].split("&");
			Map<String, String> data = new ConcurrentHashMap<>();
			for(String cell:column){
				String[] cells = cell.split("=");
				data.put(cells[0], URLDecoder.decode(cells[1], "UTF-8"));
			}
			vPCSRequest.setRequestValue(data);	
		}
		vPCSRequest.setRequestLink(content[0]);	
	}
}