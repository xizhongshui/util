package com.ssj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.ssj.test.httpClient.RequstTask;

public class HttpClientHelper {
	private static Logger logger=Logger.getLogger(HttpClientHelper.class);
	private static volatile  CloseableHttpClient client =null;
	/**建立连接超时 */
	private static final int ConnTimeOut=1000*10;
	/**发起请求超时 */
	private static final int RequestTimeOut=1000*10;
	/**读取返回信息超时 */
	private static final int SocketTimeOut=1000*10;
	private static final int PerRouteCount=100;
	private static final int MaxCount=500;
	private static  IdleConnectionMonitorThread checkThread=null;
	
	public static CloseableHttpClient getClient(){
			 PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			 cm.setDefaultMaxPerRoute(PerRouteCount);
			 cm.setMaxTotal(MaxCount);
			if(client==null){
					synchronized (HttpClientHelper.class) {
							if(client==null){
								client =createClient(cm);
								checkThread=new IdleConnectionMonitorThread(cm);
								Thread thread=new Thread(checkThread);
								logger.info("启动一次===========");
								thread.start();
							}
					} 
			}
			return client;
	}
	
	private static CloseableHttpClient createClient(HttpClientConnectionManager cm){
			Builder builder=RequestConfig.custom();
			RequestConfig reqConfig=builder.setConnectTimeout(ConnTimeOut)
															.setConnectionRequestTimeout(RequestTimeOut)
															.setSocketTimeout(SocketTimeOut)
															.build();
			
			HttpClientBuilder clientBuilder=HttpClients.custom()
//											.setConnectionTimeToLive(10, TimeUnit.MINUTES)
												.setDefaultRequestConfig(reqConfig);
			
			clientBuilder.setConnectionManager(cm);
			CloseableHttpClient client=clientBuilder.build();
			return client;
	}
	
	public static String postForm(String url, Map<String,String> map) {
			CloseableHttpClient client =getClient();
			HttpPost post=withFormEntity(url,map);
			String result=null;
			try {
				logger.info("开始外网o请求【    】");
				long begin=System.currentTimeMillis();
				CloseableHttpResponse  resp = client.execute(post);
				long interval=System.currentTimeMillis()-begin;
				logger.info("外网请求响应【  "+interval+"  】");
				result=parse2String(resp);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				post.releaseConnection();
				/*try {
						client.close();//关闭连接池,client失效
				} catch (IOException e) {
						e.printStackTrace();
				}*/
			}
			return result;
	}
	
	public static String postString(String url, String req) {
		CloseableHttpClient client =getClient();
		HttpPost post=withStringEntity(url, req);
		String result=null;
		try {
//			logger.info("开始外网o请求【    】");
			long begin=System.currentTimeMillis();
			CloseableHttpResponse  resp = client.execute(post);
			long interval=System.currentTimeMillis()-begin;
//			logger.info("外网请求响应【  "+interval+"  】");
			result=parse2String(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
			/*try {
					client.close();//关闭连接池,client失效
			} catch (IOException e) {
					e.printStackTrace();
			}*/
		}
		return result;
}
	
	public static HttpPost withStringEntity(String url,String req){
			HttpPost post = new HttpPost(url);
			if(req!=null &&! "".equals(req)){
				StringEntity entity = new StringEntity(req, Constants.HttpCharSet);
				post.setEntity(entity);
			}
			return post;
	}
	
	public static HttpPost withFormEntity(String url,Map<String,String> map){
			HttpPost post = new HttpPost(url);
			if(map==null || map.size()<=0){
				return post;
			}
			List<NameValuePair> nvs=map2NameValues(map);
			UrlEncodedFormEntity entity=null;
			try {
					entity=new UrlEncodedFormEntity(nvs, "utf-8");
					logger.info("UrlEncodedFormEntity======"+entity.toString());
					post.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return post;
	}
	
	private static List<NameValuePair> map2NameValues(Map<String,String> map){
		 	List<NameValuePair> nvs=new ArrayList<NameValuePair>();
			Iterator<Entry<String,String>> it=map.entrySet().iterator();
			while(it.hasNext()){
					Entry<String,String> entry=it.next();
					nvs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			return nvs;
	}
	
	public static Map<String,String> prepareMap(){
			Map<String,String> map=new HashMap();
			map.put("map1"," value1");
			map.put("map2"," value2");
			map.put("map3"," value3");
			return map;
	}

	private static String parse2String(CloseableHttpResponse  response) {
			String result = null;
			if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				BufferedHttpEntity bufEntity=null;
				BufferedReader reader=null;
				try {
					if(entity==null){
						throw new ClientProtocolException("Respose contains no content");
					}
					 bufEntity=new BufferedHttpEntity(entity);
					   ContentType ct=ContentType.getOrDefault(entity);
						reader = new BufferedReader( new InputStreamReader( bufEntity.getContent(),ct.getCharset()));
						StringBuffer sb=new StringBuffer();
						while((result=reader.readLine())!=null){
							sb.append(result);
						}
				/*	ContentType ct=ContentType.getOrDefault(entity);
					reader = new BufferedReader( new InputStreamReader(entity.getContent(),ct.getCharset()));
					StringBuffer sb=new StringBuffer();
					while((result=reader.readLine())!=null){
						sb.append(result);
					}*/
						reader = new BufferedReader( new InputStreamReader( bufEntity.getContent(),ct.getCharset()));
						while((result=reader.readLine())!=null){
							sb.append(result);
						}
					result=sb.toString();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
							if(reader!=null){reader.close();}
							if(response!=null){response.close();}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return result;
		}
	
		public static void  shutDown(){
			try {
					if(client!=null){
						client.close();
					}
					if(checkThread!=null){
						checkThread.shutdown();
					}
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
		
	@Test
		public void helperTest(){
				try {
							
							for(int i=0;i<10;i++){
									for(int j=0;j<101;j++){
										RequstTask task=new RequstTask();
										Thread thread=new Thread(task);
										thread.start();
										Thread.sleep(10);
									}
							Thread.sleep(2000);
						}
						HttpClientHelper.shutDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
}
