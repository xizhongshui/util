package com.ssj.test.httpClient;

import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.ssj.util.HttpClientHelper;

public class RequstTask implements Runnable {
		Logger logger=Logger.getLogger(RequstTask.class);
		@Override
		public void run() {
				String url="http://127.0.0.1:8080/jfinal_demo/test";
				Map<String, String> map=HttpClientHelper.prepareMap();
				String reqData=JSONObject.fromObject(map).toString();
				String result=HttpClientHelper.postString(url, reqData);
//				logger.info("receive  response"+result);
		}

}
