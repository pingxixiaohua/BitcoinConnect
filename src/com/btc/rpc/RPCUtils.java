package com.btc.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.btc.rpc.entity.RPCResult;
import com.btc.rpc.entity.Result;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Set;

/**
 * RPC通信的工具类
 */
public class RPCUtils {

    /**
     * 准备json-rpc通信的数据格式
     *
     * @param method 要调用的方法
     * @param params 调用对方时要传递的参数
     * @return rpc通信的json格式的请求数据
     */
    public static String prepareJSON(String method, Object... params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", System.currentTimeMillis());
        jsonObject.put("jsonrpc", "2.0");
        jsonObject.put("method", method);
        if (params != null) {
            jsonObject.put("params", params);
        }
        return jsonObject.toJSONString();
    }


    /**
     * 执行post网络请求
     *
     * @param url     url
     * @param headers 请求头设置信息
     * @param body    请求体数据
     * @return        请求的返回信息
     */
    public static RPCResult doPost(String url, Map<String, String> headers, String body) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //设置请求头
        if (!headers.isEmpty()) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                String value = headers.get(key);
                post.addHeader(key, value);
            }
        }


        try {
            //设置请求体
            StringEntity entity = new StringEntity(body);
            post.setEntity(entity);

            //发起请求
            HttpResponse response = client.execute(post);

            //判断响应结果
            int code = response.getStatusLine().getStatusCode();
            RPCResult result = new RPCResult();
            if (code == HttpStatus.SC_OK) {
                result.setCode(code);
                result.setMsg("请求成功");
                String jsonStr = EntityUtils.toString(response.getEntity());
                Result result1 = JSON.parseObject(jsonStr,Result.class);
                result.setData(result1);
            } else {
                result.setCode(code);
                result.setMsg("请求失败");
                result.setData(null);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
