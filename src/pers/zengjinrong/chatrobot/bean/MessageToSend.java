package pers.zengjinrong.chatrobot.bean;

import key.Key;

public class MessageToSend {
    private String key = Key.API_KEY;   //图灵机器人APIKey（保密）
    private String info;                //消息内容

    public MessageToSend(String info) {
        this.info = info;
    }

}
