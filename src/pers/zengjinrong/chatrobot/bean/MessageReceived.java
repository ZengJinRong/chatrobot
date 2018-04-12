package pers.zengjinrong.chatrobot.bean;

/**
 * Bean 用于接收消息的JSON解析
 *
 * @author ZengJinRong
 * @version 1.1
 */
public class MessageReceived {
    private int code;       //消息标识码，表示消息的类型或异常状态
    private String text;    //文字消息内容

    public String getText() {
        //分析消息标识码，返回结果
        switch (code) {
            case 100000:
                return text;
            case 40001:
                return "参数key错误";
            case 40002:
                return "请求内容info为空";
            case 40004:
                return "当天请求次数已使用完";
            case 40007:
                return "数据格式异常";
            default:
                return text;
        }
    }

}
