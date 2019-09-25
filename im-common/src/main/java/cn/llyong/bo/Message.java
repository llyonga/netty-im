package cn.llyong.bo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 消息传输对象
 * @author: lvyong
 * @date: 2019-09-25
 * @time: 9:19 上午
 * @version: 1.0
 */
public class Message implements Serializable {

    private final long serialVersionUID = 1L;

    private String msgId;
    private String content;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
