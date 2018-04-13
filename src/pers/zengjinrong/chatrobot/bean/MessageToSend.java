package pers.zengjinrong.chatrobot.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Bean 用于发送消息的JSON格式化
 *
 * @author ZengJinRong
 * @version 2.0
 */
public class MessageToSend {
    private String key;         //图灵机器人APIKey
    private String info;        //消息内容

    public MessageToSend(String info) {
        if (key == null) {
            //从配置文件读取APIKey
            try {
                InputStream inputStream=MessageToSend.class.getResourceAsStream("/chatrobot.properties");
                Properties properties=new Properties();
                properties.load(inputStream);
                key=properties.getProperty("apikey");
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        this.info = info;
    }

}
