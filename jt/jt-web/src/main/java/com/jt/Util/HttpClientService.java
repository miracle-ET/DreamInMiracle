package com.jt.Util;

import java.util.Map;

import org.apache.http.HttpResponse;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.util.ObjectMapperUtil;
@Service
public class HttpClientService{
	@Autowired
	private CloseableHttpClient htClient;//从池中获取连接
	@Autowired
	private RequestConfig requestConfig; //控制请求超时时间
	public String doGet(String url) {
		return doGet(url,null,null);
	}
	public String doGet(String url,String charset) {
		return doGet(url,null,charset);
	}
	public String doGet(String url,Map<String,String> parmas) {
		return doGet(url,parmas,null);
	}
	public String doGet(String url,Map<String,String> parmas,String charset) {
		if(url==null) {
			return null;
		}
		//如果参数不空则拼接参数
		if(parmas!=null) {
			url+="?";
			for (Map.Entry<String, String> entry: parmas.entrySet()) {
				url+=entry.getKey()+"="+entry.getValue()+"&";
			}
			url=url.substring(0,url.length()-1);
		}
		//如果字符集为空则为默认utf-8
		if(charset==null) {
			charset="utf-8";
		}
		HttpGet httpGet=new HttpGet(url);
		httpGet.setConfig(requestConfig);
		HttpResponse response=null;
		String result="";
		try {
			response = htClient.execute(httpGet);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(200 == response.getStatusLine().getStatusCode()) {	
			try {
				result = EntityUtils.toString(response.getEntity(),charset);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public <T> T doGet(String url,Map<String,String> params,Class<T> targetClass,String charset) {
		return ObjectMapperUtil.toObject(doGet(url,params), targetClass);
	}
	
	public <T> T doGet(String url,Class<T> targetClass) {
		return ObjectMapperUtil.toObject(doGet(url), targetClass);
	}
}
