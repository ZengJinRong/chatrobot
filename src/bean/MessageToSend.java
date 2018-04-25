package bean;

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
    private String key="d4524da2c34a4f1eb5d47e285b613dba";         //图灵机器人APIKey
    private String info;        //消息内容

    public MessageToSend(String info) {
        this.info = info;
    }

}
