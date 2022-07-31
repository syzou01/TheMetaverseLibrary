package cs.hku.metalib.user.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs.hku.metalib.user.Entity.UserEntity;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
    created by zousiyi
    2022/06/23
 */

public interface UserMapper extends BaseMapper<UserEntity> {

    @Update(value ="update user_password set user_state = 'ON' where user_id = #{user_id}")
    int login(String user_id);

    @Update(value ="update user_password set user_state = 'OFF' where user_id = #{user_id}")
    int logout(String user_id);

    @Update(value ="select * from user_password")
    List<UserEntity> selectAll();
}
