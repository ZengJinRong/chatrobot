package pers.zengjinrong.chatrobot.bean;

import key.Key;

/**
 * Bean 用于发送消息的JSON格式化
 *
 * @author ZengJinRong
 * @version 1.1
 */
public class MessageToSend {
    private String key = Key.API_KEY;   //图灵机器人APIKey（保密）
    private String info;                //消息内容

    public MessageToSend(String info) {
        this.info = info;
    }

}
