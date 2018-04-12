package pers.zengjinrong.chatrobot.bean;

public class MessageReceived {
    private int code;       //消息标识码，表示消息的类型或异常状态
    private String text;    //文字消息内容

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
