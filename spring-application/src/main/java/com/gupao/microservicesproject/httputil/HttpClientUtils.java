package com.gupao.microservicesproject.httputil;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtils {


    public static void tesPostGraphql() {
        //创建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //返回类型
        CloseableHttpResponse response = null;

        try {
            //创建http post
            HttpPost httpPost = new HttpPost("http://localhost:8082/graphql");
            //模拟浏览器设置头
            httpPost.setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
            httpPost.setHeader("Content-Type","application/json");
            //设置请求数据
            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("query",
//                    "{findAuthorsByFilter(filter:[{firstName:\\\"Jim\\\",lastName:\\\"Green\\\",gender:MAN}],first:3){firstName lastName}}"));
            params.add(new BasicNameValuePair("query", "query { countBooks}"));
            params.add(new BasicNameValuePair("variables", "{}"));
            params.add(new BasicNameValuePair("operationName", ""));

            //query userQuery{user{id,age,dogs}}
            //构建表单
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
            //将表达请求放入到httpost
            httpPost.setEntity(formEntity);

            response = httpClient.execute(httpPost);
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(content);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
