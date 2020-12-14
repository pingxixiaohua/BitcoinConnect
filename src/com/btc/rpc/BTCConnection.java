package com.btc.rpc;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BTCConnection {
//    private static final String RPCUSER = "user";//rpc服务的用户名
//    private static final String RPCPASSWORD = "pwd";//rpc服务的用户密码
//    private static final String RPCURL = "http://127.0.0.1:83" +
//            "32";//rpc的ip和端口

    public static void main(String[] args) {
        //1、准备json-rpc连接的数据
        JSONObject rpcJson = new JSONObject();
        rpcJson.put("id",System.currentTimeMillis());
        rpcJson.put("jsonrpc",2.0);
//        rpcJson.put("method","getbestblockhash");//调用的命令：最后一个区块的hash
        rpcJson.put("method", "getblockcount");//调用的命令：获取区块的总数
        int[] arr = new int[1];
        arr[0] = 1;
        rpcJson.put("params", arr);
        String json = rpcJson.toJSONString();
        System.out.println("请求的数据："+json);

        //System.out.println(rpcJson.toJSONString());

        //2、发起网络请求
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(Constants.RPCURL);
        try {
            //将认证信息放在请求头中
            String authStr = Constants.USER + ":" + Constants.PASSWORD;
            String authBase64 = base64Str(authStr);
            post.addHeader("Authorization", "Basic " + authBase64);
            //设置连接头信息
            post.addHeader("Encoding", "UTF-8");
            post.addHeader("Content-Type", "application/json");


            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);
            HttpResponse response = client.execute(post);//发起post请求操作
            int code = response.getStatusLine().getStatusCode();
            if (code == 200){//请求成功
                System.out.println("恭喜rpc请求成功");
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);

            }else {
                System.out.println("请求失败，失败状态码："+code);

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对某个字符串进行base64编码处理
     * @param msg
     * @return
     */
    public static String base64Str(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes(StandardCharsets.UTF_8));
    }

}
