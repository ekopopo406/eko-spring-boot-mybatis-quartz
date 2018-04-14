/*******************************************************************************
 *
 *  IBM Confidential
 *  
 *  OCO Source Materials
 *
 *  5747-SM3
 *
 *  (C) Copyright IBM Corp. 2015, 2017 All Rights Reserved.
 *
 *  The source code for this program is not published or otherwise divested 
 *  of its trade secrets, irrespective of what has been deposited with 
 *  the U.S. Copyright office .
 *
 *******************************************************************************/
package com.eko.utils;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;


public class HttpUtil {

	private static Registry<ConnectionSocketFactory> getRegistry()
			throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext sslContext = SSLContexts.custom().build();
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
				new String[] { "TLSv1.2" }, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		return RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslConnectionSocketFactory).build();
	}
	 
	public static String sendPostSCC(String url, List<NameValuePair> nvps,String bodyPayload,String accessToken) {
		String body = "";
		try {
			PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(getRegistry());
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr).build();
			URI formUri = new URI( url + "?" + URLEncodedUtils.format( nvps, "utf-8" ));
			HttpPost post = new HttpPost(formUri);
			
			StringEntity strEntity = new StringEntity(bodyPayload);
			post.getRequestLine();
			post.setEntity(strEntity);
			
			post.setHeader( "Content-Type","application/json; charset=UTF-8");
	 
			CloseableHttpResponse response = httpClient.execute(post);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {

				body = EntityUtils.toString(responseEntity, Consts.UTF_8);
			}
			EntityUtils.consume(responseEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return body;
	}
 
}
