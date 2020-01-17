package com.qixiang.got.utils.httputils;

import android.text.TextUtils;
import android.util.Log;

import com.lidroid.xutils.util.IOUtils;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * created by jinjianan
 * on 20200111
 */
public class HttpUtils2 {

    public static JSONObject sendJsonPost(String theUrl, JSONObject jsonObject) {
        // HttpClient 6.0被抛弃了
        String result = "";
        JSONObject resultJson = null;
        BufferedReader reader = null;
        try {
            String Json = jsonObject.toString();
            //String urlPath = "http://112.126.60.140:6789/users/toLogin";
            URL url = new URL(theUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept", "application/json");
            // 往服务器里面发送数据
            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
            resultJson = new JSONObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultJson;
    }
    public static JSONObject sendMsgGet(String theUrl, JSONObject data) {
        HttpURLConnection conn=null;
        BufferedReader reader = null;
        String result = "";
        JSONObject resultJson = null;
        try {
            String rData = jsonToFormData(data);
            String theUrl1 = theUrl+"?"+rData;
            URL url = new URL(theUrl1);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
                resultJson = new JSONObject(result);
            } else {
                Log.i("PostGetUtil","get请求失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultJson;
    }
    /**
           * 获取key=value格式字符串方法1
           *
           * @param JSONObject
           * @return
           */
    public static String jsonToFormData(JSONObject in){
        Iterator<?> it = in.keys();
        String result = "";
        String key = null;
        try {
            while(it.hasNext()){//遍历JSONObject
                key = (String) it.next().toString();
                result += key +"="+ in.getString(key)+"&";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result.length() < 1)
            return "";
        return result.substring(0,result.length()-1);
    }
    /**
           * 获取key=value格式字符串方法2
           *
           * @param map
           * @return
           */
    public static String getKeyValueString(Map<String,String> map){
            String result = "";
        Set<String> set = map.keySet();
            for (String key : set){
                result += key+"="+map.get(key)+"&";
            }
            return result.substring(0,result.length()-1);
    }
}

