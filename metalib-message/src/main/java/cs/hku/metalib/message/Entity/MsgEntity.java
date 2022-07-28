package cs.hku.metalib.message.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/*
    created by zousiyi
    2022/06/25
 */

@TableName("user_message")
public class MsgEntity {
    @TableId("message_id")
    private int messageId;
    private String from;
    private String to;
    private String message;
    private Date time;
    private int status;

    public int getMessageId() {
        return messageId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
