package com.jt.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.Util.HttpClientService;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHttpClient {
	
	
	/**
	 * 调用步骤:
	 * 	1.确定url的访问地址.
	 *  2.确定请求的方式类型 get/post
	 *  3.实例化httpClient对象.
	 *  4.发起请求. 获取响应response.
	 *  5.判断程序调用是否正确  200 302 400参数异常 406 参数转化异常
	 *  	404 500 502 504 访问超时...
	 *  6.获取返回值数据一般都是String.   JSON
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Autowired
	private HttpClientService httpClint;

	
	@Test
	public void test02() throws ParseException, IOException {
		String url = "http://manage.jt.com/web/item/findItemDescById?itemId=1474391960";
			System.out.println(httpClint.doGet(url));

	}
	
	
	
	
	
	
	
	
	
	
	
}
