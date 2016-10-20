package com.cloopen.rest.test;

import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import com.cloopen.rest.sdk.CCPRestSDK;

public class SendTest {
	
	@Test
	public void withTempl(){
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f894e3e5b81014e3e6140960015", "a854fae7d6cf4eaca8bc6e85e02420b4");// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId("AppId");// 初始化应用ID
		result = restAPI.sendTemplateSMS("13323852175","1" ,new String[]{"模板内容1","模板内容2"});

		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}
	
	@Test
	public void voiceTest(){
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f894e3e5b81014e3e6140960015", "a854fae7d6cf4eaca8bc6e85e02420b4");// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId("8a48b5514e3e5862014e3e6a89710023");// 初始化应用ID
		
		
		result = restAPI.voiceVerify("123456", "13323852175","13323852175","3","");

		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}
	
	@Test
	public void send(){
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f894e3e5b81014e3e6140960015", "a854fae7d6cf4eaca8bc6e85e02420b4");// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId("8a48b5514e3e5862014e3e6a89710023");// 初始化应用ID
		restAPI.sendSMS("13323852175", "短信test1");//13323852175
	}
}
