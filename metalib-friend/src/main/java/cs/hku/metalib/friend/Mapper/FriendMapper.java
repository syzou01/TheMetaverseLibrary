package cs.hku.metalib.friend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs.hku.metalib.friend.Entity.FriendEntity;
import org.apache.ibatis.annotations.Select;

/*
    created by zousiyi
    2022/06/27
 */


public interface FriendMapper extends BaseMapper<FriendEntity> {
    @Select(value ="select * from user_friend friend where friend.user_id = #{usr1} and friend.contact_id = #{usr2}")
    FriendEntity selectByName(String usr1, String usr2);
}
