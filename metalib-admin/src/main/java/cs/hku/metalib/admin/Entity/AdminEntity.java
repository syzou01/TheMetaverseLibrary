package cs.hku.metalib.admin.Entity;


/*
    created by zousiyi
    2022/05/25
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("admin_password")
public class AdminEntity {
    @TableId
    private String adminId;
    private String password;

    public String getAdminId() {
        return adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
