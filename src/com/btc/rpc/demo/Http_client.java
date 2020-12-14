package com.btc.rpc.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Http_client {
    public static String doGet(String httpurl){
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
//        String result = null;

        try {
            //创建远程url连接对象
            URL url = new URL(httpurl);
            //通过远程url连接对象发起一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            //设置连接方式：get
            connection.setRequestMethod("GET");
            //设置连接服务器超出时间：
            connection.setConnectTimeout(15000);
            //设置读取远程返回的数据时间
            connection.setReadTimeout(60000);
            //发送请求连接
            connection.connect();

            if (connection.getResponseCode() == 200){
                //
                is = connection.getInputStream();
                //封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                //存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null){
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                return sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
