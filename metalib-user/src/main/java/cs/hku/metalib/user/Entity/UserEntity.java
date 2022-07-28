package cs.hku.metalib.user.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/*
    created by zousiyi
    2022/06/23
 */

@TableName("user_password")
public class UserEntity {
    @TableId
    private String userId;
    private String password;
    private String userState;

    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }
    public String getUserState() {
        return userState;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserState(String userState) {
        this.userState = userState;
    }
}
