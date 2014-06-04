package com.caronapp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpUtil {

	public static String doGET(String url) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(url));
			return IOUtils.toString(response.getEntity().getContent());
		} catch (Exception e) {
			Log.e("[GET REQUEST]", "Network exception", e);
			return null;
		}
	}

	public static String doPOST(String url, String json) {

		String resposta = null;
		StringEntity entity;
		try {
			entity = new StringEntity(json);
			HttpPost request = new HttpPost(url);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			request.setEntity(entity);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			Log.i("POST", response.toString());
			resposta = response.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resposta;

	}
}
