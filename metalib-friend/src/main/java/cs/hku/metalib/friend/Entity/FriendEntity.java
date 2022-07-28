package cs.hku.metalib.friend.Entity;


/*
    created by zousiyi
    2022/06/27
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_friend")
public class FriendEntity {

    @TableId
    private int relationId;
    private String userId;
    private String contactId;

    public int getRelationId() {
        return relationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }
}
