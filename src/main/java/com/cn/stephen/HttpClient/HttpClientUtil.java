package com.cn.stephen.HttpClient;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 使用httpclient调用第三方接口
 * @author doraemon4
 *
 */
public class HttpClientUtil {
	public static String send(String url) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 
	 * @param url
	 * @param host
	 * @param port
	 * @param account
	 * @param password
	 * @return
	 */
	public static String sendViaProxy(String url, String host, int port,
			String account, String password) {
		HttpHost proxy = new HttpHost(host, port, "http");
		
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(
				new AuthScope(proxy.getHostName(), proxy.getPort()),
				new UsernamePasswordCredentials(account, password));
		
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCredentialsProvider(credentialsProvider)
				.build();
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(3000)
				.setConnectionRequestTimeout(3000)
				.setProxy(proxy)
				.build();
		
		HttpGet request = new HttpGet(url);
		request.setConfig(config);
		
		try {
			HttpResponse httpResponse = httpClient.execute(request);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(send("http://www.baidu.com"));
	}
}
