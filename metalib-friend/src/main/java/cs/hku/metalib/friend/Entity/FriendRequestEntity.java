package cs.hku.metalib.friend.Entity;

/*
    created by zousiyi
    2022/06/27
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_friend_request")
public class FriendRequestEntity {

    @TableId
    private int requestId;

    private String from;

    private String to;

    private int status;

    public int getRequestId() {
        return requestId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getStatus() {
        return status;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
