package util;

import bean.MessageReceived;
import bean.MessageToSend;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用于消息的发送与接收相关操作
 *
 * @author ZengJinRong
 * @version 1.2
 */
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

        return messageReceived.getText();
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
        StringBuilder response=new StringBuilder();
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
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String lines;
            while ((lines = reader.readLine()) != null) {
                response.append(lines);
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
        return response.toString();
    }

}
