package com.ssj.test.httpClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

import com.ssj.util.HttpClientHelper;

public class HttpClientTest {
	
	@Test  /**URIBuilder */
	public void uriTest(){
		
		try {
			URI uri=new URIBuilder()
									.setHost("127.0.0.1")
									.setPort(8080)
									.setPath("/MyJFianl")
									.addParameter("param1", "value1")
									.addParameter("param2", "value2")
									.build();
			HttpPost post=new HttpPost(uri);
			System.out.println(post.getURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test  /**  HeaderElementIterator 、HeaderElement*/
	public void headerTest(){
		HttpResponse resp=new BasicHttpResponse(HttpVersion.HTTP_1_0, HttpStatus.SC_OK, "ok");
		resp.addHeader("header1", "a=1;b=2; path=\"/\"  ");
		resp.addHeader("header1", "g=1;b=3; path=\"/\"  ");
		HeaderElementIterator hei=new BasicHeaderElementIterator(resp.headerIterator("header1"));
		while(hei.hasNext()){
			HeaderElement he=hei.nextElement();
			String name=he.getName();
			String value=he.getValue();
			System.out.println(name+" ="+value);
			NameValuePair []vnps=he.getParameters();
			for(NameValuePair vnp:vnps){
				System.out.println(vnp.getName()+" ="+vnp.getValue());
			}
		}
	}
	
	@Test /** StatusLine*/
	public void respStatusTest(){
			HttpResponse resp=new BasicHttpResponse(HttpVersion.HTTP_1_0,HttpStatus.SC_OK, "OK");
			StatusLine sl=resp.getStatusLine();
			System.out.println(sl.getProtocolVersion());
			System.out.println(sl.getStatusCode());
			System.out.println(sl.getReasonPhrase());
			System.out.println(sl.toString());
	}
	
	/** 
	 * 不可复用的实体InputStreamEntity,
	 * BufferedHttpEntity 可将不可复用的实体包装成可复用实体
	 */
	@Test 
	public void noReuseEntity(){
		String result=null;
		HttpResponse resp=new BasicHttpResponse(HttpVersion.HTTP_1_0,HttpStatus.SC_OK, "这是响应请求的实体内容！");
		resp.setEntity(prepareInEntity());
		HttpEntity entity=resp.getEntity();
		
		if(entity==null){
			throw new IllegalArgumentException("Response entity is null");
		}
		InputStream in=null;
		try {
			 StringBuffer sb=new StringBuffer();
//			 BufferedHttpEntity be=new BufferedHttpEntity(entity);//包装成可复用实体
			 ////////////////第一次读取////////////////////////////////////
			 BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent()));
			 while((result=reader.readLine())!=null){
				 sb.append(result);
			 }
			 ////////////////第二次读取流已读取完毕////////////////////////////////////
			 reader=new BufferedReader(new InputStreamReader(entity.getContent()));
			 while((result=reader.readLine())!=null){
				 sb.append(result);
			 }
			 
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){ 
					try { in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	/** 
	 * 可复用的实体 StringEntity、ByteArrayEntity、FileEntity
    */
	@Test 
	public void reuseEntity(){
		String result=null;
		HttpResponse resp=new BasicHttpResponse(HttpVersion.HTTP_1_0,HttpStatus.SC_OK, "这是响应请求的实体内容！");
		resp.setEntity(prepareStringEntity());
		HttpEntity entity=resp.getEntity();
		if(entity==null){
			throw new IllegalArgumentException("Response entity is null");
		}
		InputStream in=null;
		try {
				StringBuffer sb=new StringBuffer();
				
				////////////////第一次读取////////////////////////////////////
				BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent()));
				while((result=reader.readLine())!=null){
					sb.append(result);
				}
				
				////////////////第二次读取////////////////////////////////////
				reader=new BufferedReader(new InputStreamReader(entity.getContent()));
				while((result=reader.readLine())!=null){
					sb.append(result);
				}
				result=sb.toString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){ 
					try { in.close();
					} catch (IOException e) {
						e.printStackTrace();
					} 
			}
		}
	}
	
	public StringEntity prepareStringEntity(){
			StringEntity entity=new StringEntity("这是实体中的内容", "utf-8");
			return entity;
	}
	
	public InputStreamEntity prepareInEntity(){
			String temp="这是实体中的内容";
			byte[] bytes=null;
			try {
				bytes = temp.getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			InputStreamEntity entity=new InputStreamEntity(new ByteArrayInputStream(bytes));
			return entity;
	}
	
	
	/**
	 * HttpClient
	 * HttpClient已经实现了线程安全。所以实例化的HttpClient支持为多个请求
	 * 当一个CloseableHttpClient的实例不再被使用，并且它的作用范围即将失效.
	 * 和它相关的连接必须被关闭，关闭方法可以调用CloseableHttpClient的close()方法。
	 * client.close();//关闭连接池,client失效
	 * */
	@Test
	public void createClient(){
			HttpClient client1=HttpClients.createDefault();
			HttpClientBuilder clientBuilder=HttpClients.custom();
//			clientBuilder.
			CloseableHttpClient client2=clientBuilder.build();
	}
	
	/**配置参数
	 * RequestConfig
	 * HttpClientBuilder
	 * PoolingHttpClientConnectionManager
	 */
	@Test
	public void clientBuilderTest(){
		Builder builder=RequestConfig.custom();
		RequestConfig reqConfig=builder.setConnectTimeout(1000*10)
														.setConnectionRequestTimeout(1000*10)
														.setSocketTimeout(1000*10)
														.build();
		
		HttpClientBuilder clientBuilder=HttpClients.custom()
										.setMaxConnPerRoute(200)
										.setMaxConnTotal(500)
										.setConnectionTimeToLive(10, TimeUnit.MINUTES)
										.setDefaultRequestConfig(reqConfig);
		
		 PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setValidateAfterInactivity(1000*60*10);
		clientBuilder.setConnectionManager(cm);
		CloseableHttpClient client=clientBuilder.build();
}
	
	/**
	 * HttpContext 上下文 实际上就是一个任意命名的值的集合
	 * 如果在持续的http请求中使用了同样的上下文，那么这些请求就可以被分配到一个逻辑会话中
	 * 在多线程中共享上下文会不安全,推荐每个线程都只包含自己的http上下文
	 * 在Http请求执行的过程中，HttpClient会自动添加下面的属性到Http上下文中
	 * 		HttpConnection的实例、HttpHost的实例、HttpRoute的实例，表示全部的连接路由、
	 * 		HttpRequest的实例、 HttpResponse的实例、RequestConfig对象
	 * */
	@Test
	public void httpContextTest(){
			HttpClient client=HttpClients.createDefault();
			HttpPost post1=new HttpPost("http://localhost:8080/jfinal_demo/test");
			HttpPost post2=new HttpPost("http://localhost:8080/jfinal_demo/test");
			Builder builder=RequestConfig.custom();
			RequestConfig reqConfig=builder.setConnectTimeout(6000)
																		.setConnectionRequestTimeout(6000)
																		.setSocketTimeout(6000)
																		.build();
					
			post1.setConfig(reqConfig);
			HttpClientContext clientContext=HttpClientContext.create();
			try {
				client.execute(post1, clientContext);
				client.execute(post2, clientContext);//post2会使用post1的上下文
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
			
	
	
	
}
