package pers.zengjinrong.chatrobot.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import pers.zengjinrong.chatrobot.bean.MessageReceived;
import pers.zengjinrong.chatrobot.bean.MessageToSend;

public class PostUtil {
    private static final String URL_STRING = "http://www.tuling123.com/openapi/api";
    private static URL url;

    /**
     * 发送聊天消息
     *
     * @param msg 要发送的文本内容
     * @return 收到的回复信息
     */
    public static String sendMessage(String msg) {

        //初始化URL
        if (url == null) {
            try {
                url = new URL(URL_STRING);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        //将要发送的消息转换为JSON
        MessageToSend messageToSend = new MessageToSend(msg);
        Gson gson = new Gson();
        String PostParams = gson.toJson(messageToSend);

        //发送JSON消息，接收JSON回复并解析为Bean
        String jsonRes = post(url, PostParams);
        MessageReceived messageReceived = gson.fromJson(jsonRes, MessageReceived.class);

        //分析消息标识码，返回结果
        switch (messageReceived.getCode()) {
            case 100000:
                return messageReceived.getText();
            case 40001:
                return "参数key错误";
            case 40002:
                return "请求内容info为空";
            case 40004:
                return "当天请求次数已使用完";
            case 40007:
                return "数据格式异常";
            default:
                return messageReceived.getText();
        }
    }

    /**
     * 发送POST请求
     *
     * @param url    发送的URL地址
     * @param params 发送的请求内容
     * @return 收到的回复信息
     */
    private static String post(URL url, String params) {
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        String response = "";
        try {
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");              //设置请求方式为POST
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);                   //设置不使用缓存
            conn.setInstanceFollowRedirects(true);      //设置自动重定向
            conn.setDoOutput(true);                     //允许输出
            conn.setDoInput(true);                      //允许输入
            conn.connect();                             //连接

            //写入数据
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(params);
            writer.flush();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response += lines;
            }
            reader.close();

            // 断开连接
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输出流、输入流
            try {
                if (writer != null) {
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}